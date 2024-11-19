package org.example.proyecto_productos.service;

import org.example.proyecto_productos.domain.entities.Productos;
import java.util.List;

public interface ProductosService {
    Productos guardarProducto(Productos producto);
    Productos actualizarProducto(Long id, Productos producto);
    void eliminarProducto(Long id);
    Productos obtenerProductoPorId(Long id);
    List<Productos> listarProductos();
    List<Productos> obtenerProductosPorProveedor(Long idProveedor);
//    List<Productos> obtenerProductosPorCategoria(Long idCategoria);
    List<Productos> obtenerProductosConBajoStock(Integer stockMinimo);
    void actualizarStock(Long id, Integer cantidad);
}