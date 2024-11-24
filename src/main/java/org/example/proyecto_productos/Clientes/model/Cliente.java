package org.example.proyecto_productos.Clientes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Pedido.model.Pedido;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato de email es inválido")
    @Size(max = 150, message = "El email no debe exceder los 150 caracteres")
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(max = 200, message = "La contraseña no debe exceder los 200 caracteres")
    @Column(name = "contrasena", length = 200, nullable = false)
    private String contrasena;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 45, message = "El nombre no debe exceder los 45 caracteres")
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    @Size(max = 100, message = "Los apellidos no deben exceder los 100 caracteres")
    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    @NotBlank(message = "La calle no puede estar vacía")
    @Column(name = "calle", length = 255, nullable = false)
    private String calle;

    @NotBlank(message = "La colonia no puede estar vacía")
    @Column(name = "colonia", length = 255, nullable = false)
    private String colonia;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @NotBlank(message = "El código postal no puede estar vacío")
    @Pattern(regexp = "^\\d{5}$", message = "El código postal debe tener 5 dígitos")
    @Column(name = "codigo_postal", length = 5, nullable = false)
    private String codigoPostal;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^\\d{10,12}$", message = "El teléfono debe tener entre 10 y 12 dígitos numéricos")
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "idPedido")
    private List<Pedido> pedidos;

    @ManyToMany
    @JoinTable(
            name = "Cliente_Carrito",
            joinColumns = @JoinColumn(name = "idCliente"),
            inverseJoinColumns = @JoinColumn(name = "idCarrito")
    )
    @JsonIgnoreProperties(value = {"clientes", "hibernateLazyInitializer"}, allowSetters = true)
    private Set<Carrito> carritos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idCliente, cliente.idCliente) &&
                Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, email);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", calle='" + calle + '\'' +
                ", colonia='" + colonia + '\'' +
                ", numero=" + numero +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", pedidos=" + pedidos +
                '}';
    }
}