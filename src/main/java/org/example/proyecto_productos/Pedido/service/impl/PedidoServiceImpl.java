package org.example.proyecto_productos.Pedido.service.impl;

import org.example.proyecto_productos.Pedido.model.Pedido;
import org.example.proyecto_productos.Pedido.repository.PedidoRepository;
import org.example.proyecto_productos.Pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido getPedidoById(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public void deletePedido(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
