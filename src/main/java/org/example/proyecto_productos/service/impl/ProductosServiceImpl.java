package org.example.proyecto_productos.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.proyecto_productos.domain.entities.Productos;
import org.example.proyecto_productos.domain.repositories.ProductosRepository;
import org.example.proyecto_productos.service.ProductosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductosServiceImpl implements ProductosService {

    private final ProductosRepository productosRepository;

    @Override
    @Transactional
    public Productos guardarProducto(Productos producto) {
        return productosRepository.save(producto);
    }

    @Override
    @Transactional
    public Productos actualizarProducto(Long id, Productos producto) {
        Productos productoExistente = obtenerProductoPorId(id);
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcionProducto(producto.getDescripcionProducto());
        productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
        productoExistente.setStock(producto.getStock());
        //productoExistente.setCategoria(producto.getCategoria());
        productoExistente.setProveedor(producto.getProveedor());
        return productosRepository.save(productoExistente);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        if (!productosRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productosRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Productos obtenerProductoPorId(Long id) {
        return productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> listarProductos() {
        return productosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> obtenerProductosPorProveedor(Long idProveedor) {
        return productosRepository.findByProveedorIdProveedor(idProveedor);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Productos> obtenerProductosPorCategoria(Long idCategoria) {
//        return productosRepository.findByCategoriaIdCategoria(idCategoria);
//    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> obtenerProductosConBajoStock(Integer stockMinimo) {
        return productosRepository.findByStockLessThan(stockMinimo);
    }

    @Override
    @Transactional
    public void actualizarStock(Long id, Integer cantidad) {
        Productos producto = obtenerProductoPorId(id);
        producto.setStock(producto.getStock() + cantidad);
        productosRepository.save(producto);
    }
}