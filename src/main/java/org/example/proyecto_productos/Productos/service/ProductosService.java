package org.example.proyecto_productos.Productos.service;

import org.example.proyecto_productos.Productos.model.Productos;
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
