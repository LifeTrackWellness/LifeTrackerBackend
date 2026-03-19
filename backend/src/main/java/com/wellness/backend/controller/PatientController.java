package com.wellness.backend.controller;

import com.wellness.backend.model.Patient;
import com.wellness.backend.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*") // Permite que React (en otro puerto) se conecte sin bloqueos de CORS
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // POST: Registrar un nuevo paciente
    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    // PATCH: Editar solo datos de contacto
    @PatchMapping("/{id}/contact")
    public ResponseEntity<Patient> updateContact(
            @PathVariable Long id,
            @RequestParam String email,
            @RequestParam String phoneNumber) {

        Patient updated = patientService.updateContactInfo(id, email, phoneNumber);
        return ResponseEntity.ok(updated);
    }

    // GET: Listar todos los pacientes
    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}