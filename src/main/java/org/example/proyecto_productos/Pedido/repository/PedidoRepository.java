package org.example.proyecto_productos.Pedido.repository;

import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteIdCliente(Long id);
    Pedido findByIdPedido(Long idPedido);
}
