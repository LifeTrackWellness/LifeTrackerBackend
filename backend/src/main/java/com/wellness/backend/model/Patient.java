package com.wellness.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Data // Genera Getters, Setters, Equals, HashCode y ToString
@NoArgsConstructor // Constructor vacío (obligatorio para JPA)
@AllArgsConstructor // Constructor con todos los campos
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "El documento es obligatorio")
    @Column(unique = true, nullable = false)
    private String identityDocument; // Para el criterio de "documento único"

    @Email(message = "Debe ser un correo válido")
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String status = "Activo"; // Criterio: 'Activo' por defecto





}
