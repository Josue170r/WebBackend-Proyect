package org.example.proyecto_productos.domain.repositories;

import org.example.proyecto_productos.domain.entities.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
    List<Productos> findByProveedorIdProveedor(Long idProveedor);
//    List<Productos> findByCategoriaIdCategoria(Long idCategoria);
    List<Productos> findByStockLessThan(Integer stockMinimo);
}