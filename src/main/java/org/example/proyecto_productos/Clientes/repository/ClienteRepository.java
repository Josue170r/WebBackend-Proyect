package org.example.proyecto_productos.Clientes.repository;


import org.example.proyecto_productos.Clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUserName(String userName);
    Optional<Cliente> findByEmail(String email);
    boolean existsClienteByIdCliente(Long idCliente);
}
