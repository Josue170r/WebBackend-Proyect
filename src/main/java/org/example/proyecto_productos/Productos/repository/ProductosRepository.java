package org.example.proyecto_productos.Productos.repository;

import org.example.proyecto_productos.Productos.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
    List<Productos> findByProveedorIdProveedor(Long idProveedor);
    //    List<Productos> findByCategoriaIdCategoria(Long idCategoria);
    List<Productos> findByStockLessThan(Integer stockMinimo);
}
