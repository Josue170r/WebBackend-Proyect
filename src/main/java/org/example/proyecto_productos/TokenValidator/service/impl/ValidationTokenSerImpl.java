package org.example.proyecto_productos.TokenValidator.service.impl;

import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.example.proyecto_productos.Clientes.tasks.ClientesEmailService;
import org.example.proyecto_productos.TokenValidator.models.ValidateTokenRequest;
import org.example.proyecto_productos.TokenValidator.models.VerificationToken;
import org.example.proyecto_productos.TokenValidator.repository.VerificationTokenRepository;
import org.example.proyecto_productos.TokenValidator.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class ValidationTokenSerImpl implements VerificationTokenService {

    @Autowired
    private final VerificationTokenRepository tokenRepository;

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final ClientesEmailService emailService;


    public ValidationTokenSerImpl(
        VerificationTokenRepository tokenRepository,
        ClienteRepository clienteRepository,
        ClientesEmailService emailService
    ) {
        this.tokenRepository = tokenRepository;
        this.clienteRepository = clienteRepository;
        this.emailService = emailService;
    }

    @Override
    public void sendVerificationToken(String email) {
        Cliente cliente = clienteRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalStateException("El usuario no existe"));

        String token = String.format("%06d", new Random().nextInt(999999));

        Optional<VerificationToken> existingToken = tokenRepository.findByCliente(cliente);

        //Actualizar token existente
        if (existingToken.isPresent()) {
            VerificationToken verificationToken = existingToken.get();
            verificationToken.setToken(token);
            verificationToken.setExpiresAt(LocalDateTime.now().plusMinutes(30));
            verificationToken.setUsed(false);
            tokenRepository.save(verificationToken);
        } else {
            VerificationToken newToken = VerificationToken.builder()
                .token(token)
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .cliente(cliente)
                .build();
            tokenRepository.save(newToken);
        }
        emailService.send_verification_email(cliente, token);
    }

    @Override
    public void validateToken(ValidateTokenRequest tokenRequest) {
        VerificationToken verificationToken = tokenRepository.findByTokenAndCliente_Email(
                tokenRequest.getToken(),
                tokenRequest.getEmail()
            )
            .orElseThrow(() -> new IllegalStateException("Código inválido"));

        if (verificationToken.isUsed()) {
            throw new IllegalStateException("El código ya ha sido utilizado");
        }

        if (verificationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("El código ha expirado");
        }

        verificationToken.setUsed(true);
        tokenRepository.save(verificationToken);

        Cliente cliente = verificationToken.getCliente();
        cliente.setIsVerified(true);
        clienteRepository.save(cliente);
    }
}
