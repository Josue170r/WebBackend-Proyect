package org.example.proyecto_productos.Carrito_Producto.service.impl;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Carrito_Producto.models.CarritoProducto;
import org.example.proyecto_productos.Carrito_Producto.repository.CarritoProductoRepository;
import org.example.proyecto_productos.Carrito_Producto.service.CarritoProductoService;
import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.service.ClienteService;
import org.example.proyecto_productos.Productos.model.Productos;
import org.example.proyecto_productos.Productos.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoProductServImpl implements CarritoProductoService {

    @Autowired
    private final CarritoProductoRepository repository;

    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final ProductosService productoService;

    public CarritoProductServImpl(
            CarritoProductoRepository repository,
            ClienteService clienteService, ProductosService productoService
    ) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @Override
    public List<CarritoProducto> getCartProductsByClient(Long idCliente) {
        Cliente cliente = clienteService.readCliente(idCliente);
        Long idCarrito = cliente.getCarrito().getIdCarrito();
        return repository.findByCarritoIdCarrito(idCarrito);
    }

    @Override
    public CarritoProducto addToCart(CarritoProducto carritoProducto) {
        Long idProducto = carritoProducto.getProducto().getIdProducto();
        Productos producto = productoService.obtenerProductoPorId(idProducto);
        if (producto.getStock() < carritoProducto.getCantidad()) {
            throw new IllegalStateException("Stock insuficiente");
        }
        return repository.save(carritoProducto);
    }

    @Override
    public void removeFromCart(CarritoProducto carrito) {
        CarritoProducto carritoProducto = repository.findByCarritoAndProducto(
            carrito.getCarrito(), carrito.getProducto()
        );
        repository.delete(carritoProducto);
    }

    @Override
    public void updateQuantity(CarritoProducto carrito) {
        Long idProducto = carrito.getProducto().getIdProducto();
        Productos producto = productoService.obtenerProductoPorId(idProducto);
        if (producto.getStock() < carrito.getCantidad()) {
            throw new IllegalStateException("Stock insuficiente");
        }
        CarritoProducto carritoProducto = repository.findByCarritoAndProducto(
            carrito.getCarrito(), carrito.getProducto()
        );
        carritoProducto.setCantidad(carrito.getCantidad());
        repository.save(carritoProducto);
    }
}
