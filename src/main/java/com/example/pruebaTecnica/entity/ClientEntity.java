package com.example.pruebaTecnica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name= "clientes")

public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoIdentificacion;

    @Column (name = "numero_identificacion", unique = true, nullable = false)
    @NotBlank(message = "El número de identificación no puede estar vacío")
    private String numeroIdentificacion;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres.")
    private String nombre;


    @NotBlank(message = "El apellido no puede estar vacío.")
    @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres.")
    private String apellido;


    @Column(name = "email_address", unique = true, nullable = false)
    private String email;


    private LocalDate fechaNacimiento;


    private LocalDateTime fechaCreacion;


    private LocalDateTime fechaModificacion;
}
