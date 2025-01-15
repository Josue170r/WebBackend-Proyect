package org.example.proyecto_productos.Pedido.controller;

import org.example.proyecto_productos.Pedido.model.Pedido;
import org.example.proyecto_productos.Pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@CrossOrigin(origins = {"*"})
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/")
    public ResponseEntity<Pedido> createPedido(
        @RequestBody Pedido pedido,
        @RequestParam("idCliente") Long idCliente
    ) {
        return ResponseEntity.ok(pedidoService.savePedido(pedido, idCliente));
    }

    @GetMapping("/by-cliente/")
    public ResponseEntity<List<Pedido>> findByCliente(@RequestParam("idCliente") Long idCliente) {
        return ResponseEntity.ok(pedidoService.getPedidosByClienteId(idCliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Integer id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
