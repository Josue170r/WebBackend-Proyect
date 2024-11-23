package org.example.proyecto_productos.Clientes.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    public String encriptar(String contrasena) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(contrasena);
    }
}
