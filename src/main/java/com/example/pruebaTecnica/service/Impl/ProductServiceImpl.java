package com.example.pruebaTecnica.service.Impl;

import com.example.pruebaTecnica.dto.ProductDto;
import com.example.pruebaTecnica.dto.ProductRequestDto;
import com.example.pruebaTecnica.entity.ClientEntity;
import com.example.pruebaTecnica.entity.ProductEntity;
import com.example.pruebaTecnica.enums.EstadoCuenta;
import com.example.pruebaTecnica.enums.TipoCuenta;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.mapper.ProductMapper;
import com.example.pruebaTecnica.repository.ClientRepository;
import com.example.pruebaTecnica.repository.ProductRepository;
import com.example.pruebaTecnica.service.ClientService;
import com.example.pruebaTecnica.service.ProductService;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    @Override
    public ProductDto save(TipoCuenta tipocuenta, BigDecimal saldo, Boolean exentaGMF, Long clienteId) throws ClientNotFoundException {
        if (tipocuenta == TipoCuenta.AHORROS && saldo.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("La cuenta de ahorros debe ser mayor a cero");
        }

        String numeroCuenta = generarNumero(tipocuenta);

        if (productRepository.existsByNumeroCuenta(numeroCuenta)){
            throw new IllegalArgumentException("El cliente ya existe");
        }
        ClientEntity client = clientRepository.findById(clienteId)
                .orElseThrow(()-> new ClientNotFoundException("El cliente ya existe con el id: " + clienteId));

        ProductEntity producto = new ProductEntity();
        producto.setTipoCuenta(tipocuenta);
        producto.setNumeroCuenta(numeroCuenta);
        producto.setEstado(EstadoCuenta.ACTIVA);
        producto.setSaldo(producto.getSaldo());
        producto.setExentaGMF(exentaGMF);
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaModificacion(null);
        producto.setCliente(client);

        productRepository.save(producto);
        return ProductMapper.toProductDto(producto);

    }
    private String generarNumero(TipoCuenta tipoCuenta){
        String prefijo = tipoCuenta == TipoCuenta.AHORROS ? "33" : "53";
        String numeroRestante = String.format("%08d", (int) (Math.random() * 100000000));
        return prefijo + numeroRestante;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductEntity productEntity) {
        try{
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Producto no encontrado con id: " +id));

        if (productEntity.getEstado() != null){
            product.setEstado(productEntity.getEstado());

            if (product.getTipoCuenta() == TipoCuenta.AHORROS&&
                    product.getEstado() == EstadoCuenta.CANCELADA&&
                    product.getSaldo().compareTo(BigDecimal.ZERO) <= 0){
                throw new IllegalArgumentException("El valor para las cuentas de ahorros debe ser mayor a cero");
            }
        }

        product.setFechaModificacion(LocalDateTime.now());
        product.setEstado(productEntity.getEstado());
        product.setSaldo(productEntity.getSaldo());
        product.setFechaCreacion(productEntity.getFechaCreacion());

        product = productRepository.save(product);

        return ProductMapper.toProductDto(product);
    }catch (Exception e){
            throw  new IllegalArgumentException("Error en la actualizacion: "+e.getMessage());
    }
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }
}

