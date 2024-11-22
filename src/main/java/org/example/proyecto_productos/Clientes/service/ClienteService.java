package org.example.proyecto_productos.Clientes.service;



import org.example.proyecto_productos.Clientes.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> readAllClientes();
    Cliente readCliente(Integer id);
    Cliente createCliente(Cliente cliente);
    void deleteCliente(Integer id);
}

