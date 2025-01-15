package org.example.proyecto_productos.Pedido.service;


import java.util.List;
import org.example.proyecto_productos.Pedido.model.Pedido;

public interface PedidoService {
    Pedido savePedido(Pedido pedido, Long idCliente);
    Pedido getPedidoById(Integer id);
    List<Pedido> getAllPedidos();
    List<Pedido> getPedidosByClienteId(Long idCliente);
    void deletePedido(Integer id);
}