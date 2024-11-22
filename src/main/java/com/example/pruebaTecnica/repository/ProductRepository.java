package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);

    ProductEntity findByNumeroCuenta(String numeroCuenta);
}
