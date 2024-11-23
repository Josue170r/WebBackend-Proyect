package org.example.proyecto_productos.ResenasProducto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Resenas")
public class ResenasProductoController {

    @Autowired
    private ResenasProductoService resenasProductoService;

    @PostMapping
    public ResponseEntity<ResenasProducto> createResena(@RequestBody ResenasProducto resena) {
        return ResponseEntity.ok(resenasProductoService.saveResena(resena));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenasProducto> getResenaById(@PathVariable Integer id) {
        return ResponseEntity.ok(resenasProductoService.getResenaById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResenasProducto>> getAllResenas() {
        return ResponseEntity.ok(resenasProductoService.getAllResenas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Integer id) {
        resenasProductoService.deleteResena(id);
        return ResponseEntity.noContent().build();
    }
}
