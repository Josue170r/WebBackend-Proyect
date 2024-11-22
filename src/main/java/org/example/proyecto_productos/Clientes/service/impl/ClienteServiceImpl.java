package org.example.proyecto_productos.Clientes.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.example.proyecto_productos.Clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> readAllClientes() {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente readCliente(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    @Transactional
    @Override
    public void deleteCliente(Integer id) {
        repository.deleteById(id);
    }
}

