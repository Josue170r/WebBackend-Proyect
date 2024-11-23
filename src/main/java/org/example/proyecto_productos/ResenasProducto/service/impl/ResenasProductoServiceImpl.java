package org.example.proyecto_productos.ResenasProducto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenasProductoServiceImpl implements ResenasProductoService {

    @Autowired
    private ResenasProductoRepository resenasProductoRepository;

    @Override
    public ResenasProducto saveResena(ResenasProducto resena) {
        return resenasProductoRepository.save(resena);
    }

    @Override
    public ResenasProducto getResenaById(Integer id) {
        return resenasProductoRepository.findById(id).orElse(null);
    }

    @Override
    public List<ResenasProducto> getAllResenas() {
        return resenasProductoRepository.findAll();
    }

    @Override
    public void deleteResena(Integer id) {
        resenasProductoRepository.deleteById(id);
    }
}

