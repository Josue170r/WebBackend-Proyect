package org.example.proyecto_productos.Productos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyecto_productos.Proveedores.models.Proveedores;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Productos")
public class Productos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto", nullable = false)
    private Long idProducto;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 50, message = "El nombre del producto no puede superar los 50 caracteres")
    @Column(name = "nombreProducto", length = 50, nullable = false)
    private String nombreProducto;

    @Size(max = 100, message = "La descripción no puede superar los 100 caracteres")
    @Column(name = "descripcionProducto", length = 100)
    private String descripcionProducto;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio unitario debe tener hasta 10 dígitos enteros y 2 decimales")
    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", nullable = false)
    @JsonIgnoreProperties(value = {"productos", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Proveedores proveedor;
}
