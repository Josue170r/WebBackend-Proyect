package org.example.proyecto_productos.Clientes.service.impl;

import org.example.proyecto_productos.Clientes.utils.Utils;
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
    @Autowired
    private Utils utils;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> readAllClientes() {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente readCliente(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        String codifiedPass = utils.encriptar(cliente.getContrasena());
        cliente.setContrasena(codifiedPass);
        return repository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        String codifiedPass = utils.encriptar(cliente.getContrasena());
        cliente.setContrasena(codifiedPass);
        return repository.save(cliente);
    }

    @Transactional
    @Override
    public void deleteCliente(Long id) {
        repository.deleteById(id);
    }
}

