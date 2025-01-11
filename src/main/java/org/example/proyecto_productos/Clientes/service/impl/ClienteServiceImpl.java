package org.example.proyecto_productos.Clientes.service.impl;

import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;
    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> user = repository.findByUserName(username);
        if (user.isPresent()) {
            Cliente objUser = user.get();
            return User.builder()
                    .username(objUser.getUserName())
                    .password(objUser.getContrasena())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> readAllClientes() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente readCliente(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException("Usuario no encontrado")
        );
    }

    @Override
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente findByUsername(String username) {
        return repository.findByUserName(username).orElseThrow(
            () -> new RuntimeException("Usuario no encontrado")
        );
    }

    @Transactional
    @Override
    public void deleteCliente(Long id) {
        repository.deleteById(id);
    }
}

