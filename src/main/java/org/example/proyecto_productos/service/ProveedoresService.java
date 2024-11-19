package org.example.proyecto_productos.service;

import org.example.proyecto_productos.domain.entities.Proveedores;

import java.util.List;

public interface ProveedoresService {

    Proveedores guardarProveedor(Proveedores proveedor);
    Proveedores actualizarProveedor(Long id, Proveedores proveedor);
    void eliminarProveedor(Long id);
    Proveedores obtenerProveedorPorId(Long id);
    List<Proveedores> listarProveedores();
    void activarProveedor(Long id);
    void desactivarProveedor(Long id);
}