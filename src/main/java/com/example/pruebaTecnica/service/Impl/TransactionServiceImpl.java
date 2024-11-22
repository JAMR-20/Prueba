package com.example.pruebaTecnica.service.Impl;

import com.example.pruebaTecnica.dto.TransactionDtoResponse;
import com.example.pruebaTecnica.dto.TransactionDtoResponseData;
import com.example.pruebaTecnica.entity.ProductEntity;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.exception.ProductNotFoundException;
import com.example.pruebaTecnica.repository.ProductRepository;
import com.example.pruebaTecnica.repository.TransactionRepository;
import com.example.pruebaTecnica.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.math.BigDecimal;

@Service
@Validated
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;


    @Override
    public TransactionDtoResponse realizarConsignacion(String numeroCuenta, BigDecimal monto) throws ProductNotFoundException {
        String code = null;
        String message = null;
        ProductEntity product = productRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(()-> new ProductNotFoundException("El producto no fue encontrado: "));



        if (monto.compareTo(BigDecimal.ZERO)<=0){
            return null;
        }

        product.setSaldo(product.getSaldo().add(monto));
        productRepository.save(product);

        TransactionDtoResponseData transactionDataDto = TransactionDtoResponseData
                .builder()
                .numeroCuenta(numeroCuenta)
                .monto(monto)
                .build();

        return TransactionDtoResponse
                .builder()
                .message(message)
                .data(transactionDataDto)
                .build();
    }

    @Override
    public String realizarRetiro(String numeroCuenta, BigDecimal monto) throws ProductNotFoundException {
        return "";
    }

    @Override
    public TransactionDtoResponse realizarTransferencia(String cuentaOrigen, String cuentaDestino, BigDecimal monto) throws ProductNotFoundException, ClientNotFoundException {
        return null;
    }

}



