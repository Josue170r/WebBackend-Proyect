package org.example.proyecto_productos.Clientes.repository;


import org.example.proyecto_productos.Clientes.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
