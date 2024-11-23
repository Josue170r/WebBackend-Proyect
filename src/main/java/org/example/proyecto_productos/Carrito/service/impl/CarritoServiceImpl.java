package org.example.proyecto_productos.Carrito.service.impl;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Carrito.repository.CarritoRepository;
import org.example.proyecto_productos.Carrito.service.CarritoService;
import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {
    @Autowired
    private CarritoRepository dao;
    @Autowired
    private ClienteRepository daoCliente;


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
    public void createCarrito(Carrito carrito) {
        System.out.println(carrito);
    }

    @Override
    @Transactional
    public void deleteCarrito(Long id) {
        dao.deleteById(id);
    }
}
