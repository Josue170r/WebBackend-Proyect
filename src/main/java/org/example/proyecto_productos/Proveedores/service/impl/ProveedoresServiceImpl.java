package org.example.proyecto_productos.Proveedores.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.proyecto_productos.Proveedores.models.Proveedores;
import org.example.proyecto_productos.Proveedores.repository.ProveedoresRepository;
import org.example.proyecto_productos.Proveedores.service.ProveedoresService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProveedoresServiceImpl implements ProveedoresService {

    private final ProveedoresRepository proveedoresRepository;

    @Override
    @Transactional
    public Proveedores guardarProveedor(Proveedores proveedor) {
        proveedor.setActivo(true); // Por defecto activo
        return proveedoresRepository.save(proveedor);
    }

    @Override
    @Transactional
    public Proveedores actualizarProveedor(Long id, Proveedores proveedor) {
        Proveedores proveedorExistente = proveedoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        proveedorExistente.setNombreProveedor(proveedor.getNombreProveedor());
        proveedorExistente.setTelefono(proveedor.getTelefono());
        proveedorExistente.setUrlProveedor(proveedor.getUrlProveedor());

        return proveedoresRepository.save(proveedorExistente);
    }

    @Override
    @Transactional
    public void eliminarProveedor(Long id) {
        if (!proveedoresRepository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado");
        }
        proveedoresRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedores obtenerProveedorPorId(Long id) {
        return proveedoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proveedores> listarProveedores() {
        List<Proveedores> proveedores = new ArrayList<>();
        proveedoresRepository.findAll().forEach(proveedores::add);
        return proveedores;
    }

    @Override
    @Transactional
    public void activarProveedor(Long id) {
        Proveedores proveedor = obtenerProveedorPorId(id);
        proveedor.setActivo(true);
        proveedoresRepository.save(proveedor);
    }

    @Override
    @Transactional
    public void desactivarProveedor(Long id) {
        Proveedores proveedor = obtenerProveedorPorId(id);
        proveedor.setActivo(false);
        proveedoresRepository.save(proveedor);
    }
}

