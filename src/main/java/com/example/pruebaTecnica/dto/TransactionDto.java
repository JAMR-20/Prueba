package com.example.pruebaTecnica.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@Builder

public class TransactionDto {
    private String numeroCuenta;
    private BigDecimal monto;
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
}
