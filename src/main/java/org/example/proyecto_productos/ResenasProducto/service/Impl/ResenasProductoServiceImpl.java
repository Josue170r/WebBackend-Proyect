package org.example.proyecto_productos.ResenasProducto.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.proyecto_productos.ResenasProducto.service.ResenasProductoService;
import org.example.proyecto_productos.ResenasProducto.repository.ResenasProductoRepository;
import org.example.proyecto_productos.ResenasProducto.model.ResenasProducto;
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

