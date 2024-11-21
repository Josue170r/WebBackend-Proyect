package org.example.proyecto_productos.service.impl;

import org.example.proyecto_productos.model.Cliente;
import org.example.proyecto_productos.repository.ClienteRepository;
import org.example.proyecto_productos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> readAllClientes() {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    public Cliente readCliente(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void deleteCliente(Integer id) {
        repository.deleteById(id);
    }
}

