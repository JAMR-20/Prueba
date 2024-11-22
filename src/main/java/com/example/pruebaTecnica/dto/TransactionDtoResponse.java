package com.example.pruebaTecnica.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class TransactionDtoResponse {
    private String code;
    private String message;
    private TransactionDtoResponseData data;
}
