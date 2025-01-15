package org.example.proyecto_productos.Carrito_Producto.service;

import org.example.proyecto_productos.Carrito_Producto.models.CarritoProducto;

import java.util.List;

public interface CarritoProductoService {
    List<CarritoProducto> getCartProductsByClient(Long idCliente);
    Double getTotalProductsByCart(Long id);
    CarritoProducto addToCart(CarritoProducto carritoProducto);
    void removeFromCart(CarritoProducto carrito);
    Long countCarrito(Long idCliente);
    void updateQuantity(CarritoProducto carritoProducto);
}
