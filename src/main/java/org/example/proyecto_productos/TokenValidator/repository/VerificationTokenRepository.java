package org.example.proyecto_productos.TokenValidator.repository;

import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.TokenValidator.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByTokenAndCliente_Email(String token, String email);
    Optional<VerificationToken> findByCliente(Cliente cliente);
}
