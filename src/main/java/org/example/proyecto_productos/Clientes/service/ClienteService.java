package org.example.proyecto_productos.Clientes.service;

import org.example.proyecto_productos.Clientes.model.Cliente;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClienteService extends UserDetailsService {
    List<Cliente> readAllClientes();
    Cliente readCliente(Long id);
    Cliente createCliente(Cliente cliente);
    Cliente findByUsername(String username);
    void deleteCliente(Long id);
}

