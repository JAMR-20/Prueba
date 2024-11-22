package com.example.pruebaTecnica.service;

import com.example.pruebaTecnica.dto.TransactionDtoResponse;
import com.example.pruebaTecnica.entity.TransactionEntity;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public interface TransactionService {
    TransactionDtoResponse realizarConsignacion(String cuentaOrigen, String cuentaDestino, BigDecimal monto) throws ProductNotFoundException;
    TransactionDtoResponse realizarTransferencia(String cuentaOrigen, String cuentaDestino, BigDecimal monto) throws ProductNotFoundException, ClientNotFoundException;
    TransactionDtoResponse retiro(String cuentaOrigen, BigDecimal monto) throws ProductNotFoundException;
}
