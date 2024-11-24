package org.example.proyecto_productos.Clientes.controller;

import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*"})
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listarClientes() {
        return service.readAllClientes();
    }

    @GetMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscarClientePorId(@PathVariable Long id) {
        return service.readCliente(id);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return service.createCliente(cliente);
    }

    @PutMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente actualizarCliente(@RequestBody Cliente cliente) {
        return service.actualizarCliente(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable Long id) {
        service.deleteCliente(id);
    }
}
