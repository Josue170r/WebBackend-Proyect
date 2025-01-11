package org.example.proyecto_productos.Clientes.model;

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
@Table(name = "Role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreRole", nullable = false)
    private String nombreRole;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", nombreRole='" + nombreRole + '\'' +
                '}';
    }
}
