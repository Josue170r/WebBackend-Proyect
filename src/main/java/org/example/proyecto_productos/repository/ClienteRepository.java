package org.example.proyecto_productos.repository;


import org.example.proyecto_productos.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
