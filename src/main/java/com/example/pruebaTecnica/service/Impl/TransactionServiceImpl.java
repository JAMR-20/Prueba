package com.example.pruebaTecnica.service.Impl;

import com.example.pruebaTecnica.dto.TransactionDtoResponse;
import com.example.pruebaTecnica.dto.TransactionDtoResponseData;
import com.example.pruebaTecnica.entity.ProductEntity;
import com.example.pruebaTecnica.entity.TransactionEntity;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.exception.ProductNotFoundException;
import com.example.pruebaTecnica.repository.ProductRepository;
import com.example.pruebaTecnica.repository.TransactionRepository;
import com.example.pruebaTecnica.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;


    @Override
    public TransactionDtoResponse realizarConsignacion(String cuentaOrigen, String cuentaDestino, BigDecimal monto) throws ProductNotFoundException {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }

        ProductEntity CuentaOrigen = productRepository.findByNumeroCuenta(cuentaOrigen)
                .orElseThrow(() -> new ProductNotFoundException("La cuenta origen no fue encontrado: " + cuentaOrigen));
        ProductEntity CuentaDestino = productRepository.findByNumeroCuenta(cuentaDestino)
                .orElseThrow(()-> new ProductNotFoundException("La cuenta destino no fue encontrada: " ));

        CuentaOrigen.setSaldo(CuentaOrigen.getSaldo().subtract(monto));
        CuentaDestino.setSaldo(CuentaDestino.getSaldo().add(monto));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTipo("Consignación");
        transaction.setMonto(monto);
        transaction.setFechaTransaccion(LocalDate.now());
        transaction.setCuentaOrigen(CuentaDestino);
        transaction.setCuentaDestino(CuentaOrigen);
        transactionRepository.save(transaction);

        productRepository.save(CuentaOrigen);
        productRepository.save(CuentaDestino);

        return TransactionDtoResponse.builder()
                .message("Transaccion realizada con exito")
                .data(TransactionDtoResponseData.builder()
                        .monto(monto)
                        .numeroCuentaDestino(cuentaDestino)
                        .numeroCuentaOrigen(cuentaOrigen)
                        .build())
                .code("200").build();
    }




    @Override
    public TransactionDtoResponse realizarTransferencia(String cuentaOrigen, String cuentaDestino, BigDecimal monto) throws ProductNotFoundException, ClientNotFoundException {
        ProductEntity CuentaOrigen = productRepository.findByNumeroCuenta(cuentaOrigen)
                .orElseThrow(()-> new ProductNotFoundException("La cuenta origen no fue encontrada: "));

        ProductEntity CuentaDestino = productRepository.findByNumeroCuenta(cuentaDestino)
                .orElseThrow(()-> new ProductNotFoundException("La cuenta destino no fue encontrada: " ));

        CuentaOrigen.setSaldo(CuentaOrigen.getSaldo().subtract(monto));
        CuentaDestino.setSaldo(CuentaDestino.getSaldo().add(monto));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTipo("Transferencia");
        transaction.setMonto(monto);
        transaction.setFechaTransaccion(LocalDate.now());
        transaction.setCuentaOrigen(CuentaDestino);
        transaction.setCuentaDestino(CuentaOrigen);
        transactionRepository.save(transaction);

        productRepository.save(CuentaOrigen);
        productRepository.save(CuentaDestino);

        return TransactionDtoResponse.builder()
                .message("Transaccion realizada con exito")
                .data(TransactionDtoResponseData.builder()
                        .monto(monto)
                        .numeroCuentaDestino(cuentaDestino)
                        .numeroCuentaOrigen(cuentaOrigen)
                        .build())
                .code("200").build();
    }

    @Override
    public TransactionDtoResponse retiro(String cuentaOrigen, BigDecimal monto) throws ProductNotFoundException {
            ProductEntity CuentaOrigen = productRepository.findByNumeroCuenta(cuentaOrigen)
                    .orElseThrow(()-> new ProductNotFoundException("La cuenta origen no fue encontrada: "));

            CuentaOrigen.setSaldo(CuentaOrigen.getSaldo().subtract(monto));
        TransactionEntity transaction = new TransactionEntity();
        transaction.setTipo("Retiro");
        transaction.setMonto(monto);
        transaction.setFechaTransaccion(LocalDate.now());
        transaction.setCuentaOrigen(CuentaOrigen);
        transactionRepository.save(transaction);

            productRepository.save(CuentaOrigen);

            return TransactionDtoResponse.builder()
                    .message("Transaccion realizada con exito")
                    .data(TransactionDtoResponseData.builder()
                            .monto(monto)
                            .numeroCuentaOrigen(cuentaOrigen)
                            .build())
                    .code("200").build();
        }
    }













