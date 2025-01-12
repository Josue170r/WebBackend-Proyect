package org.example.proyecto_productos.Clientes.service.impl;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Carrito.service.CarritoService;
import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.example.proyecto_productos.TokenValidator.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final VerificationTokenService vericationService;

    @Autowired
    private final CarritoService carritoService;

    public ClienteServiceImpl(
            ClienteRepository repository,
            VerificationTokenService vericationService, CarritoService carritoService
    ) {
        this.repository = repository;
        this.vericationService = vericationService;
        this.carritoService = carritoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> user = repository.findByUserName(username);
        if (user.isPresent()) {
            Cliente objUser = user.get();
            return User.builder()
                .username(objUser.getUserName())
                .password(objUser.getPassword())
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
        Cliente savedCliente = repository.save(cliente);
        Carrito carrito = new Carrito();
        carrito.setCliente(savedCliente);
        carritoService.createCarrito(carrito);
        vericationService.sendVerificationToken(cliente.getEmail());
        return savedCliente;
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

