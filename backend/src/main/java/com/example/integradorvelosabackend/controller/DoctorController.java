package com.example.integradorvelosabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.integradorvelosabackend.Entity.*;
import com.example.integradorvelosabackend.Repository.*;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(maxAge = 3600)
public class DoctorController {

    @Autowired
    private final DoctorRepository repo;

    public DoctorController(DoctorRepository doctorRepository) {
        this.repo = doctorRepository;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Doctor doctor) {
        repo.save(doctor);
        return ResponseEntity.status(200).body("Usuario almacenado exitosamente");
    }

    // TODO: fix undesired behavior when searching a non-existing user
    @GetMapping
    public ResponseEntity<?> getDoctors(@RequestParam(required = false) String name,
            @RequestParam(required = false) String email, @RequestParam(required = false) String documentId) {

        if (name == null && email == null && documentId == null)
            return ResponseEntity.status(200).body(repo.findAll());

        else if (name != null)
            return ResponseEntity.status(200).body(repo.findByName(name));

        else if (email != null)
            return ResponseEntity.status(200).body(repo.findByEmail(email));

        else if (documentId != null)
            return ResponseEntity.status(200).body(repo.findByDocumentId(documentId));

        else
            return ResponseEntity.status(404).build();
    }

    @GetMapping("/{id}") // user/search/10
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        var user = repo.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.status(200).body(user.get());
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") long id, @RequestBody Doctor doctor) {
        try {
            repo.updateById(id, doctor.getName(), doctor.getLastname(), doctor.getEmail(),
                    doctor.getPassword(), doctor.getDocumentId());
            return ResponseEntity.status(200).body(doctor);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        repo.deleteById(id);
        return ResponseEntity.status(200).body(
                ("Usuario Eliminado"));
    }

    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<?> getPatients(@PathVariable Long doctorId) {
        var box = repo.findById(doctorId);
        Doctor doc = null;

        if (box.isPresent())
            doc = box.get();
        else
            return ResponseEntity.status(404).build();

        var patients = doc.getListPatient();

        return ResponseEntity.status(202).body(patients);
    }

    @PostMapping("/{doctorId}/patients")
    public ResponseEntity<?> addPatient(@PathVariable Long doctorId, @RequestBody Patient patient) {
        var box = repo.findById(doctorId);
        Doctor doc = null;

        if (box.isPresent())
            doc = box.get();
        else
            return ResponseEntity.status(404).build();

        doc.getListPatient().add(patient);

        repo.save(doc);

        return ResponseEntity.status(201).build();
    }

}
