package org.example.proyecto_productos.Pedido.repository;

import org.example.proyecto_productos.Pedido.model.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
}
