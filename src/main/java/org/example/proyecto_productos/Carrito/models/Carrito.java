package org.example.proyecto_productos.Carrito.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyecto_productos.Clientes.model.Cliente;

import java.io.Serializable;
import java.util.Set;

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

//    @ManyToOne
//    @JoinColumn(name = "producto", nullable = false)
//    private Producto producto;

    @NotBlank(message = "Cantidad no puede estar vacío")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToMany(mappedBy = "carritos")
    private Set<Cliente> clientes;
}
