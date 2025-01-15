package org.example.proyecto_productos.Productos.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.proyecto_productos.Productos.model.Productos;
import org.example.proyecto_productos.Productos.repository.ProductosRepository;
import org.example.proyecto_productos.Productos.service.ProductosService;
import org.example.proyecto_productos.Proveedores.models.Proveedores;
import org.example.proyecto_productos.Proveedores.repository.ProveedoresRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        productoExistente.setStock(producto.getStock());
        productoExistente.setImagenUrl(producto.getImagenUrl());
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
    public List<Productos> listarProductosActivos() {
        return productosRepository.findByActivo(true);
    }

    @Override
    public List<Productos> listarAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> obtenerProductosPorProveedor(Long idProveedor) {
        return productosRepository.findByProveedorIdProveedor(idProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> obtenerProductosPorCategoria(Long idCategoria) {
        return productosRepository.findByCategoriaIdCategoria(idCategoria);
    }

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

