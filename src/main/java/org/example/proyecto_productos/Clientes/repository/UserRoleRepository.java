package org.example.proyecto_productos.Clientes.repository;

import org.example.proyecto_productos.Clientes.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    boolean existsById(Long id);
}
