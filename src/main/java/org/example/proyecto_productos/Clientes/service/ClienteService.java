package org.example.proyecto_productos.Clientes.service;



import org.example.proyecto_productos.Clientes.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> readAllClientes();
    Cliente readCliente(Long id);
    Cliente createCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    void deleteCliente(Long id);
}

