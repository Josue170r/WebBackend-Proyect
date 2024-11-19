package org.example.proyecto_productos.controller;

import lombok.RequiredArgsConstructor;
import org.example.proyecto_productos.domain.entities.Proveedores;
import org.example.proyecto_productos.service.ProveedoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Endpoints disponibles:

POST /api/proveedores - Crear proveedor
PUT /api/proveedores/{id} - Actualizar proveedor
DELETE /api/proveedores/{id} - Eliminar proveedor
GET /api/proveedores/{id} - Obtener proveedor por ID
GET /api/proveedores - Listar todos los proveedores
PATCH /api/proveedores/{id}/activar - Activar proveedor
PATCH /api/proveedores/{id}/desactivar - Desactivar proveedor
 */

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

    @PostMapping
    public ResponseEntity<Proveedores> crearProveedor(@RequestBody Proveedores proveedor) {
        return new ResponseEntity<>(proveedoresService.guardarProveedor(proveedor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedores> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedores proveedor) {
        return ResponseEntity.ok(proveedoresService.actualizarProveedor(id, proveedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedoresService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedores> obtenerProveedorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedoresService.obtenerProveedorPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Proveedores>> listarProveedores() {
        return ResponseEntity.ok(proveedoresService.listarProveedores());
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Void> activarProveedor(@PathVariable Long id) {
        proveedoresService.activarProveedor(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivarProveedor(@PathVariable Long id) {
        proveedoresService.desactivarProveedor(id);
        return ResponseEntity.ok().build();
    }
}