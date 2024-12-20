package com.example.pruebaTecnica.dto;

import com.example.pruebaTecnica.enums.TipoCuenta;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder

public class ProductDto {
    private TipoCuenta tipoCuenta;
    private BigDecimal saldo;
    private Boolean exentaGMF;
    private Long clienteId;
}
