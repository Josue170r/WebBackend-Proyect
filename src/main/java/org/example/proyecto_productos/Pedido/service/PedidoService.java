package org.example.proyecto_productos.Pedido.service;

import java.util.List;


public interface PedidoService {
    Pedido savePedido(Pedido pedido);
    Pedido getPedidoById(Integer id);
    List<Pedido> getAllPedidos();
    void deletePedido(Integer id);
}