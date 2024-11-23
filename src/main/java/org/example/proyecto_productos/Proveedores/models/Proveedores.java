package org.example.proyecto_productos.Proveedores.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
}

