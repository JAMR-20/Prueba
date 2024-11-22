package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByNombre(String nombre);

}
