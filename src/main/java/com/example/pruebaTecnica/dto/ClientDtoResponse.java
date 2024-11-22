package com.example.pruebaTecnica.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientDtoResponse {
    private String code;
    private String message;
    private ClientDtoResponseData data;
}
