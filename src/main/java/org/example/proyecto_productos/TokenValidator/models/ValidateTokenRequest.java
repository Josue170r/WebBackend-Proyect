package org.example.proyecto_productos.TokenValidator.models;

import lombok.Getter;

@Getter
public class ValidateTokenRequest {
    String token;
    String email;

    public ValidateTokenRequest(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
