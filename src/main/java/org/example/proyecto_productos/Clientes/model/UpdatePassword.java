package org.example.proyecto_productos.Clientes.model;

import lombok.Getter;

@Getter
public class UpdatePassword {
    String currentPassword;
    String newPassword;
    String username;

    public UpdatePassword(String currentPassword, String newPassword, String username) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.username = username;
    }
}
