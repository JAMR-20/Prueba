package com.example.pruebaTecnica.mapper;

import com.example.pruebaTecnica.dto.ProductDto;
import com.example.pruebaTecnica.entity.ClientEntity;
import com.example.pruebaTecnica.entity.ProductEntity;
import com.example.pruebaTecnica.enums.TipoCuenta;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;


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
    public static ProductEntity toEntity(TipoCuenta tipoCuenta, BigDecimal saldo, Boolean exentaGMF, ClientEntity cliente) {
        ProductEntity entity = new ProductEntity();
        entity.setTipoCuenta(tipoCuenta);
        entity.setSaldo(saldo);
        entity.setExentaGMF(exentaGMF);
        entity.setCliente(cliente);
        return entity;
    }


}

