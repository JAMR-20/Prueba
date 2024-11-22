package com.example.pruebaTecnica.service;

import com.example.pruebaTecnica.dto.TransactionDtoResponse;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.exception.ProductNotFoundException;
import java.math.BigDecimal;


public interface TransactionService {
    TransactionDtoResponse realizarConsignacion(String cuentaOrigen, String cuentaDestino, BigDecimal monto) throws ProductNotFoundException;
    TransactionDtoResponse realizarTransferencia(String cuentaOrigen, String cuentaDestino, BigDecimal monto) throws ProductNotFoundException, ClientNotFoundException;
    TransactionDtoResponse retiro(String cuentaOrigen, BigDecimal monto) throws ProductNotFoundException;
}
