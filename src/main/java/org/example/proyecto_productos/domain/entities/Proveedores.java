package org.example.proyecto_productos.domain.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Proveedores")
public class Proveedores implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor", nullable = false)
    private Long idProveedor;
    @Column(name = "nombreProveedor", length = 45, nullable = false)
    private String nombreProveedor;
    @Column(name = "Telefono", length = 45, nullable = false)
    private String Telefono;
    @Column(name = "urlProveedor", length = 45, nullable = false)
    private String urlProveedor;
    @Column(name = "activo", nullable = false)
    private Integer activo;

}