package org.example.proyecto_productos.Carrito_Producto.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Productos.model.Productos;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Carrito-Producto")
public class CarritoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto", nullable = false)
    private Productos producto;

    @NotNull(message = "Cantidad no puede ser nula")
    @Min(value = 0, message = "Cantidad no puede ser negativa")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
