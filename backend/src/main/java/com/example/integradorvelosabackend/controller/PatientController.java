package com.example.integradorvelosabackend.controller;

import com.example.integradorvelosabackend.Entity.Patient;
import com.example.integradorvelosabackend.Entity.Register;
import com.example.integradorvelosabackend.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@CrossOrigin(maxAge = 3600)
public class PatientController {

    @Autowired
    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    // TODO: fix undesired behavior when searching a non-existing user
    @GetMapping
    public ResponseEntity<?> getPatients(@RequestParam(required = false) String name,
            @RequestParam(required = false) String email, @RequestParam(required = false) String documentId) {

        if (name == null && email == null && documentId == null)
            return ResponseEntity.status(200).body(repo.findAll());

        else if (name != null)
            return ResponseEntity.status(200).body(repo.findByName(name));

        else if (documentId != null)
            return ResponseEntity.status(200).body(repo.findByDocumentId(documentId));

        else
            return ResponseEntity.status(404).build();
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatientById(@PathVariable Long patientId) {
        Optional<Patient> optional = repo.findById(patientId);
        if (optional.isPresent())
            return ResponseEntity.status(200).body(optional.get());

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{patientId}/registers")
    public ResponseEntity<?> getPatientRegisters(@PathVariable Long patientId) {
        Optional<Patient> optional = repo.findById(patientId);
        if (optional.isPresent()) {
            List<Register> registers = optional.get().getRegisterList();
            return ResponseEntity.status(200).body(registers);
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Patient patient) {
        repo.save(patient);
        return ResponseEntity.status(200).build();
    }

}
