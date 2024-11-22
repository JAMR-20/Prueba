package com.example.pruebaTecnica.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDtoResponseData {
    private String nombre;
    private String apellido;
    private String numeroIdentificacion;
    private String email;
}
