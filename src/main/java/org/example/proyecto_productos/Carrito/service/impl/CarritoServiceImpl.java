package org.example.proyecto_productos.Carrito.service.impl;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Carrito.repository.CarritoRepository;
import org.example.proyecto_productos.Carrito.service.CarritoService;
import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.example.proyecto_productos.Productos.model.Productos;
import org.example.proyecto_productos.Productos.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarritoServiceImpl implements CarritoService {
    @Autowired
    private CarritoRepository dao;
    @Autowired
    private ClienteRepository daoCliente;
    @Autowired
    private ProductosRepository daoProductos;


    @Override
    @Transactional(readOnly = true)
    public List<Carrito> redAllCarritos() {
        return (List<Carrito>) dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Carrito readCarrito(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Carrito createCarrito(Carrito carrito) {
        Long idProducto = carrito.getProducto().getIdProducto();
        Optional<Productos> productoOpt = daoProductos.findById(idProducto);
        Productos producto = productoOpt
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        carrito.setProducto(producto);
        Set<Cliente> clientes = new HashSet<>();
        for (Cliente cliente : carrito.getClientes()) {
            Long idCliente = cliente.getIdCliente();
            Optional<Cliente> clienteOpt = daoCliente.findById(idCliente);
            Cliente cliente_carrito = clienteOpt
                    .orElseThrow(() -> new RuntimeException("Cliente con id " + idCliente + " no encontrado"));
            clientes.add(cliente_carrito);
        }
        carrito.setClientes(clientes);
        return dao.save(carrito);
    }

    @Override
    @Transactional
    public void deleteCarrito(Long id) {
        dao.deleteById(id);
    }
}
