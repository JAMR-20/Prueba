package com.example.pruebaTecnica.mapper;

import com.example.pruebaTecnica.dto.ProductDto;
import com.example.pruebaTecnica.dto.ProductRequestDto;
import com.example.pruebaTecnica.entity.ClientEntity;
import com.example.pruebaTecnica.entity.ProductEntity;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {
public static ProductDto toProductDto (ProductEntity productEntity){
    return ProductDto.builder()
            .clienteId(productEntity.getCliente().getId())
            .exentaGMF(productEntity.getExentaGMF())
            .saldo(productEntity.getSaldo())
            .tipoCuenta(productEntity.getTipoCuenta())
            .build();

}
}

