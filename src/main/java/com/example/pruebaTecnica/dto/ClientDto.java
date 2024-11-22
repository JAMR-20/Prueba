package com.example.pruebaTecnica.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Builder
@Getter
public class ClientDto {
    private String tipoIdentificacion;

    private String numeroIdentificacion;

    private String nombre;

    private String apellido;

    private String email;

    private LocalDate fechaNacimiento;
}
