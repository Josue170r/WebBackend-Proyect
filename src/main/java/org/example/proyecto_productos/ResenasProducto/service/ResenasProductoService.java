package org.example.proyecto_productos.ResenasProducto.service;

import java.util.List;

public interface ResenasProductoService {
    ResenasProducto saveResena(ResenasProducto resena);
    ResenasProducto getResenaById(Integer id);
    List<ResenasProducto> getAllResenas();
    void deleteResena(Integer id);
}