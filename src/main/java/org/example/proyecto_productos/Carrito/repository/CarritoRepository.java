package org.example.proyecto_productos.Carrito.repository;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    public List<Carrito> findByClientes_idCliente(Long idCliente);
}
