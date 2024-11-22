package org.example.proyecto_productos.Categorias.service;

import org.example.proyecto_productos.Categorias.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> readAllCategorias();
    Categoria readCategoria(Integer id);
    Categoria createCategoria(Categoria categoria);
    void deleteCategoria(Integer id);
}

