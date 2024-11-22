package org.example.proyecto_productos.Categorias.controller;

import org.example.proyecto_productos.Categorias.model.Categoria;
import org.example.proyecto_productos.Categorias.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*"})
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/categorias")
    @ResponseStatus(HttpStatus.OK)
    public List<Categoria> listarCategorias() {
        return service.readAllCategorias();
    }

    @GetMapping("/categorias/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria buscarCategoriaPorId(@PathVariable Integer id) {
        return service.readCategoria(id);
    }

    @PostMapping("/categorias")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria guardarCategoria(@RequestBody Categoria categoria) {
        return service.createCategoria(categoria);
    }

    @DeleteMapping("/categorias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoria(@PathVariable Integer id) {
        service.deleteCategoria(id);
    }
}

