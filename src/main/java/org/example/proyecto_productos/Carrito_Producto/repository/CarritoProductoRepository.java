package org.example.proyecto_productos.Carrito_Producto.repository;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Carrito_Producto.models.CarritoProducto;
import org.example.proyecto_productos.Productos.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    List<CarritoProducto> findByCarritoIdCarrito(Long id);
    CarritoProducto findByCarritoAndProducto(Carrito carrito, Productos producto);
}
