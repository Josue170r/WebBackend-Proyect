package org.example.proyecto_productos.Carrito.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Productos.model.Productos;

import java.io.Serializable;
import java.util.HashSet;
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

    @NotNull(message = "Cantidad no puede estar vacío")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Productos producto;

    @ManyToMany
    @JoinTable(
            name = "Cliente_Carrito",
            joinColumns = @JoinColumn(name = "idCarrito"),
            inverseJoinColumns = @JoinColumn(name = "idCliente")
    )
    @JsonIgnoreProperties(value = {"carritos", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Set<Cliente> clientes = new HashSet<>();

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
        cliente.getCarritos().add(this);
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "idCarrito=" + idCarrito +
                ", cantidad=" + cantidad +
                ", producto=" + producto +
                '}';
    }
}