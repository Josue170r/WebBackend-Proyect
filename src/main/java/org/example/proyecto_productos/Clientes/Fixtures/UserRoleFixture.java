package org.example.proyecto_productos.Clientes.Fixtures;

import org.example.proyecto_productos.Clientes.model.UserRole;
import org.example.proyecto_productos.Clientes.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRoleFixture {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Bean
    public CommandLineRunner createRoles() {
        return args -> {
            // Verificar si ya existen los roles, si no, crearlos
            if (!userRoleRepository.existsById(1L)) {
                UserRole adminRole = new UserRole();
                adminRole.setId(1L);
                adminRole.setNombreRole("ADMIN");
                userRoleRepository.save(adminRole);
            }

            if (!userRoleRepository.existsById(2L)) {
                UserRole clientRole = new UserRole();
                clientRole.setId(2L);
                clientRole.setNombreRole("CLIENT");
                userRoleRepository.save(clientRole);
            }

            System.out.println("Roles inicializados");
        };
    }
}
