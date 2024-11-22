package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
