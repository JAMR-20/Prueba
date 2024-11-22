package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);

    Optional<ProductEntity> findByNumeroCuenta(String numeroCuenta);
}
