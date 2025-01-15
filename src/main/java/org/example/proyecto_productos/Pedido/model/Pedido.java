package org.example.proyecto_productos.Pedido.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyecto_productos.Clientes.model.Cliente;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @NotNull(message = "El número de folio no puede estar vacío")
    @Column(name = "numeroFolio", nullable = false, unique = true)
    private String numeroFolio;

    @NotNull(message = "El precio total no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio total debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio total debe tener un formato válido")
    @Column(name = "precioTotal", nullable = false)
    private BigDecimal precioTotal;

    @NotNull(message = "La fecha de compra no puede estar vacía")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaCompra", nullable = false)
    private Date fechaCompra;

    @NotBlank(message = "El tipo de pago no puede estar vacío")
    @Size(max = 50, message = "El tipo de pago no debe exceder los 50 caracteres")
    @Column(name = "tipodePago", length = 50, nullable = false)
    private String tipodePago;

    @NotBlank(message = "El estatus del pedido no puede estar vacío")
    @Size(max = 50, message = "El estatus del pedido no debe exceder los 50 caracteres")
    @Column(name = "estatusPedido", length = 50, nullable = false)
    private String estatusPedido;

    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoDetalle> productos;
}
