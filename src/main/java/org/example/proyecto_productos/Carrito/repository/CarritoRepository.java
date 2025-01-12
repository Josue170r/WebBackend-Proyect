package org.example.proyecto_productos.Carrito.repository;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
