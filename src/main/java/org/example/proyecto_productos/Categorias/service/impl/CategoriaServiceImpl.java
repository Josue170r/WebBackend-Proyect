package org.example.proyecto_productos.Categorias.service.impl;


import org.example.proyecto_productos.Categorias.model.Categoria;
import org.example.proyecto_productos.Categorias.repository.CategoriaRepository;
import org.example.proyecto_productos.Categorias.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> readAllCategorias() {
        return (List<Categoria>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria readCategoria(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Categoria createCategoria(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    @Transactional
    public void deleteCategoria(Integer id) {
        repository.deleteById(id);
    }
}

