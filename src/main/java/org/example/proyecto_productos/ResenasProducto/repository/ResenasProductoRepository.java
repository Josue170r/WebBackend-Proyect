package org.example.proyecto_productos.ResenasProducto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.proyecto_productos.ResenasProducto.model.ResenasProducto;

@Repository
public interface ResenasProductoRepository extends JpaRepository<ResenasProducto, Integer> {
}
