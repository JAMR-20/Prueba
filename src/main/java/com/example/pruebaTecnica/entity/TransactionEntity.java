package com.example.pruebaTecnica.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity

@Table(name = "transaccion")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private BigDecimal monto;

    @Column(name = "fecha_transaccion")
    private LocalDate fechaTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id_origen")
    private ProductEntity cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_id_destino")
    private ProductEntity cuentaDestino;

}
