package com.wellness.backend.service;

import com.wellness.backend.model.Patient;
import com.wellness.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // CRITERIO: Registrar un paciente con validación de documento único
    @Transactional
    public Patient createPatient(Patient patient) {
        if (patientRepository.existsByIdentityDocument(patient.getIdentityDocument())) {
            throw new RuntimeException("Error: El documento de identidad " +
                    patient.getIdentityDocument() + " ya está registrado.");
        }

        return patientRepository.save(patient);
    }

    // CRITERIO: El terapeuta puede editar datos de contacto (correo, celular)
    @Transactional
    public Patient updateContactInfo(Long id, String newEmail, String newPhone) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        patient.setEmail(newEmail);
        patient.setPhoneNumber(newPhone);

        return patientRepository.save(patient);
    }

    // Método adicional para listar (útil para el frontend en React)
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
