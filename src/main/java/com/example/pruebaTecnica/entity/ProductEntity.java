package com.example.pruebaTecnica.entity;

import com.example.pruebaTecnica.enums.EstadoCuenta;
import com.example.pruebaTecnica.enums.TipoCuenta;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "producto")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta")
    private TipoCuenta tipoCuenta;

    @Column(name = "numero_cuenta", unique = true, length = 10)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;

    @DecimalMin(value = "0.0", inclusive = false, message = "El saldo debe ser mayor a $0 para cuentas de ahorro")
    @Column(precision = 15, scale = 2)
    private BigDecimal saldo;

    private Boolean exentaGMF;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClientEntity cliente;
}
