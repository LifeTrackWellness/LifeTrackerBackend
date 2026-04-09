package com.wellness.backend.model;

import com.wellness.backend.enums.DeactivationReason;
import com.wellness.backend.enums.PatientStatus;
import com.wellness.backend.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "El tipo de documento es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType = DocumentType.CEDULA;

    @NotBlank(message = "El documento es obligatorio")
    @Column(unique = true, nullable = false)
    private String identityDocument; // para el criterio de "documento único"

    @Email(message = "Debe ser un correo valido")
    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PatientStatus status = PatientStatus.ACTIVO;

    @Enumerated(EnumType.STRING)
    @Column(name = "deactivation_reason")
    private DeactivationReason deactivationReason;

    @Column(name = "deactivated_at")
    private LocalDateTime deactivatedAt;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null)
            this.status = PatientStatus.ACTIVO;
        if (this.documentType == null)
            this.documentType = DocumentType.CEDULA;
    }

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private ClinicalInfo clinicalInfo;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Guardian guardian;

}
