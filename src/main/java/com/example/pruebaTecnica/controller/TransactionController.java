package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.dto.TransactionDto;
import com.example.pruebaTecnica.dto.TransactionDtoResponse;
import com.example.pruebaTecnica.dto.TransactionDtoResponseData;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.exception.ProductNotFoundException;
import com.example.pruebaTecnica.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaccion")
public class TransactionController {
    private final TransactionService transactionService;


    @PostMapping("/consignacion")
    public TransactionDtoResponse realizarConsignacion(@Valid @RequestBody TransactionDto request) throws ProductNotFoundException {
        return transactionService.realizarConsignacion(request.getNumeroCuentaOrigen(), request.getNumeroCuentaDestino(), request.getMonto());
    }

    @PostMapping("/retiro")
    public TransactionDtoResponse retiro(@RequestBody TransactionDto request) throws ProductNotFoundException {
        return transactionService.retiro(request.getNumeroCuentaOrigen(), request.getMonto());
    }

    @PostMapping("/transferencia")
    public TransactionDtoResponse realizarTransferencia(@Valid @RequestBody TransactionDtoResponseData request) throws ClientNotFoundException, ProductNotFoundException {
        return transactionService.realizarTransferencia(request.getNumeroCuentaOrigen(), request.getNumeroCuentaDestino(), request.getMonto());
    }
}
