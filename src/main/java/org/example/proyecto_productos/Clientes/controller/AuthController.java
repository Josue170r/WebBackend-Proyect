package org.example.proyecto_productos.Clientes.controller;

import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.model.LoginRequest;
import org.example.proyecto_productos.Clientes.model.UpdatePassword;
import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.example.proyecto_productos.Clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ClienteRepository clienteRepository;

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
    public ResponseEntity<Cliente> createUser(@RequestBody Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePassword updatePassword) {
        Cliente cliente = clienteService.findByUsername(updatePassword.getUsername());
        if (cliente != null && passwordEncoder.matches(updatePassword.getCurrentPassword(), cliente.getPassword())) {
            cliente.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
            clienteRepository.save(cliente);
            return ResponseEntity.ok("Contraseña actualizada con éxito.");
        } else {
            return ResponseEntity.status(400).body("La contraseña es incorrecta");
        }
    }
}