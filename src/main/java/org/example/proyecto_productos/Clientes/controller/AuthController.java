package org.example.proyecto_productos.Clientes.controller;

import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.model.LoginRequest;
import org.example.proyecto_productos.Clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*"})
public class AuthController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public Cliente login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Cliente userResult = clienteService.findByUsername(userDetails.getUsername());
            userResult.setPassword(null);
            return userResult;
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Credenciales inválidas");
        }
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public Cliente createUser(@RequestBody Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteService.createCliente(cliente);
    }
}