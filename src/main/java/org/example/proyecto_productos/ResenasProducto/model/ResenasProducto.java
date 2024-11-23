package org.example.proyecto_productos.ResenasProducto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyecto_productos.Clientes.model.Cliente;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ResenasProducto")
public class ResenasProducto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResenaProducto;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(max = 500, message = "El comentario no debe exceder los 500 caracteres")
    @Column(name = "comentario", length = 500, nullable = false)
    private String comentario;

    @NotNull(message = "La fecha de la reseña no puede estar vacía")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaReseña", nullable = false)
    private Date fechaResena;

    @NotNull(message = "La puntuación no puede estar vacía")
    @DecimalMin(value = "0.0", inclusive = true, message = "La puntuación debe ser entre 0 y 5")
    @DecimalMax(value = "5.0", inclusive = true, message = "La puntuación debe ser entre 0 y 5")
    @Column(name = "puntuacion", nullable = false)
    private Double puntuacion;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @NotNull(message = "El producto es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", nullable = false)
    @JsonIgnoreProperties(value = {"reseñasProducto", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Producto producto;

    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", nullable = false)
    @JsonIgnoreProperties(value = {"reseñasProducto", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Cliente cliente;
}


