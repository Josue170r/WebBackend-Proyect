package org.example.proyecto_productos.ReseñasProducto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reseñas")
public class ReseñasProductoController {

    @Autowired
    private ReseñasProductoService reseñasProductoService;

    @PostMapping
    public ResponseEntity<ReseñasProducto> createReseña(@RequestBody ReseñasProducto reseña) {
        return ResponseEntity.ok(reseñasProductoService.saveReseña(reseña));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReseñasProducto> getReseñaById(@PathVariable Integer id) {
        return ResponseEntity.ok(reseñasProductoService.getReseñaById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReseñasProducto>> getAllReseñas() {
        return ResponseEntity.ok(reseñasProductoService.getAllReseñas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReseña(@PathVariable Integer id) {
        reseñasProductoService.deleteReseña(id);
        return ResponseEntity.noContent().build();
    }
}
