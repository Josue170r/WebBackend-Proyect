package org.example.proyecto_productos.Clientes.repository;


import org.example.proyecto_productos.Clientes.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
