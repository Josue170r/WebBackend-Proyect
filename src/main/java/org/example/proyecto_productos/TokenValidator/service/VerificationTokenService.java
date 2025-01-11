package org.example.proyecto_productos.TokenValidator.service;

import org.example.proyecto_productos.TokenValidator.models.ValidateTokenRequest;

public interface VerificationTokenService {
    void sendVerificationToken(String email);
    void validateToken(ValidateTokenRequest tokenRequest);
}
