package org.example.proyecto_productos.Proveedores.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.proyecto_productos.Productos.model.Productos;

import java.io.Serializable;
import java.util.List;

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

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Size(max = 45, message = "El nombre del proveedor no puede superar los 45 caracteres")
    @Column(name = "nombreProveedor", length = 45, nullable = false)
    private String nombreProveedor;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^\\d{10,12}$", message = "El teléfono debe tener entre 10 y 12 dígitos numéricos")
    @Column(name = "Telefono", length = 45, nullable = false)
    private String telefono;

    @NotBlank(message = "La URL del proveedor no puede estar vacía")
    @Size(max = 45, message = "La URL no puede superar los 45 caracteres")
    @Column(name = "urlProveedor", length = 45, nullable = false)
    private String urlProveedor;

    @Column(name = "activo", nullable = false)
    private Boolean activo; // Cambiado a Boolean para mayor claridad

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proveedor")
    @JsonIgnoreProperties(value = {"productos", "proveedor", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private List<Productos> productos;

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
        for (Productos producto : productos) {
            producto.setProveedor(this);
        }
    }

    @Override
    public String toString() {
        return "Proveedores{" +
                "activo=" + activo +
                ", urlProveedor='" + urlProveedor + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombreProveedor='" + nombreProveedor + '\'' +
                ", idProveedor=" + idProveedor +
                '}';
    }
}

