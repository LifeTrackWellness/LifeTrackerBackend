package com.wellness.backend.repository;

import com.wellness.backend.enums.PatientStatus;
import com.wellness.backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Spring Data JPA crea la consulta automáticamente
    boolean existsByIdentityDocument(String identityDocument);

    Optional<Patient> findByIdentityDocument(String identityDocument);

    List<Patient> findByStatus(PatientStatus status);
}
