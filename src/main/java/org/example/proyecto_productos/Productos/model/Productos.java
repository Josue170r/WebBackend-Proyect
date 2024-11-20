package org.example.proyecto_productos.Productos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyecto_productos.Proveedores.models.Proveedores;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Productos")
public class Productos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long idProducto;

    @Column(name = "nombreProducto", length = 50, nullable = false)
    private String nombreProducto;

    @Column(name = "descripcionProducto", length = 100)
    private String descripcionProducto;

    @Column(name = "precioUnitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    //@ManyToOne
    //@JoinColumn(name = "idCategoria", nullable = false)
    //private Categorias categoria;

    @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedores proveedor;
}
