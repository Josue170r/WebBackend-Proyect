package org.example.proyecto_productos.Carrito.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.proyecto_productos.Clientes.model.Cliente;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Carrito")
public class Carrito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente", nullable = false)
    @JsonIgnore
    private Cliente cliente;

    @Override
    public String toString() {
        return "Carrito{" +
                "idCarrito=" + idCarrito +
                '}';
    }
}