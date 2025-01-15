package org.example.proyecto_productos.Clientes.Fixtures;

import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.model.UserRole;
import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.example.proyecto_productos.Clientes.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class UserRoleFixture {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Eliminar `static`

    @Bean
    public CommandLineRunner createRoles() {
        return args -> {
            // Verificar si ya existen los roles, si no, crearlos
            if (!userRoleRepository.existsById(1L)) {
                UserRole adminRole = new UserRole();
                adminRole.setId(1L);
                adminRole.setNombreRole("ADMIN");
                userRoleRepository.save(adminRole);
                if (!clienteRepository.existsById(1L)) {
                    Cliente cliente = getCliente(adminRole);
                    cliente.setPassword(passwordEncoder.encode("admin"));
                    clienteRepository.save(cliente);
                }
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

    private static Cliente getCliente(UserRole adminRole) {
        Cliente cliente = new Cliente();
        String fechaStr = "2001-09-13";
        LocalDate localDate = LocalDate.parse(fechaStr);
        Date fechaNacimiento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        cliente.setIdCliente(1L);
        cliente.setNombre("Admin");
        cliente.setApellidos("admin");
        cliente.setEmail("web.backend.productos@gmail.com");
        cliente.setIsVerified(true);
        cliente.setRole(adminRole);
        cliente.setUserName("web.backend.productos");
        cliente.setCalle("calle 1");
        cliente.setColonia("imaginaria");
        cliente.setNumero("23");
        cliente.setCodigoPostal("12345");
        cliente.setTelefono("2136458974");
        cliente.setFechaNacimiento(fechaNacimiento);
        return cliente;
    }
}
