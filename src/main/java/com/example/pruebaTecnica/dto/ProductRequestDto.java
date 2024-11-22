package com.example.pruebaTecnica.dto;

import com.example.pruebaTecnica.enums.EstadoCuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private EstadoCuenta estado;
}
