package org.example.proyecto_productos.Carrito.service.impl;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Carrito.repository.CarritoRepository;
import org.example.proyecto_productos.Carrito.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {
    @Autowired
    private CarritoRepository dao;

    @Override
    @Transactional(readOnly = true)
    public List<Carrito> redAllCarritos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Carrito readCarrito(Long id) {
        return dao.findById(id).orElseThrow(
            () -> new RuntimeException("Carrito no encontrado")
        );
    }

    @Override
    @Transactional
    public Carrito createCarrito(Carrito carrito) {
        return dao.save(carrito);
    }

    @Override
    @Transactional
    public void deleteCarrito(Long id) {
        dao.deleteById(id);
    }
}
