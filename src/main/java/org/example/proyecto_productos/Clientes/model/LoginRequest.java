package org.example.proyecto_productos.Clientes.model;

import lombok.Getter;

@Getter
public class LoginRequest {
    String username;
    String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
