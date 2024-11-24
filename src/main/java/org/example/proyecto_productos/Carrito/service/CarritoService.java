package org.example.proyecto_productos.Carrito.service;

import org.example.proyecto_productos.Carrito.models.Carrito;

import java.util.List;

public interface CarritoService {
    public List<Carrito> redAllCarritos();
    public Carrito readCarrito(Long id);
    public Carrito createCarrito(Carrito carrito);
    public void deleteCarrito(Long id);
}
