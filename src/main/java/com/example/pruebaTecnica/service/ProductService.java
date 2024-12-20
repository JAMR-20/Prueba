package com.example.pruebaTecnica.service;

import com.example.pruebaTecnica.dto.ProductDto;
import com.example.pruebaTecnica.entity.ProductEntity;
import com.example.pruebaTecnica.enums.TipoCuenta;
import com.example.pruebaTecnica.exception.ClientNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductDto save(TipoCuenta tipoCuenta, BigDecimal saldo, Boolean exentaGMF, Long clienteId) throws ClientNotFoundException;
    ProductDto updateProduct(Long id, ProductEntity productEntity);
    List<ProductEntity> findAll();
}
