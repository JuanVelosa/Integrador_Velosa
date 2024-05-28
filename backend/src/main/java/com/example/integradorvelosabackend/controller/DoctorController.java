package com.example.integradorvelosabackend.controller;

import com.example.integradorvelosabackend.Entity.Doctor;
import com.example.integradorvelosabackend.Entity.Patient;
import com.example.integradorvelosabackend.Entity.Test;
import com.example.integradorvelosabackend.Repository.DoctorRepository;
import com.example.integradorvelosabackend.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.integradorvelosabackend.Repository.PatientRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600 )
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    TestRepository testRepository;



    @PostMapping("user/create")
    public ResponseEntity<?> create(@RequestBody Doctor doctor) {
        doctorRepository.save(doctor);
        return ResponseEntity.status(200).body("Usuario almacenado exitosamente");
    }

    @PostMapping("patient/create")
    public ResponseEntity<?> create(@RequestBody Patient patient){
        patientRepository.save(patient);
        return ResponseEntity.status(200).body("paciente almacenado exitosamente");
    }



    @GetMapping("user/list")
    public ResponseEntity<?> list() {
        var users = doctorRepository.findAll();
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("user/search/{id}") //user/search/10
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        var user = doctorRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.status(200).body(user.get());
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }

    @GetMapping("user/searchByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name){

        List<Doctor> doctors = doctorRepository.findByName(name);

        if(doctors.isEmpty())
            return ResponseEntity.status(404).body("Usuario no encontrado");

        return ResponseEntity.status(200).body(doctors);

    }

    @GetMapping("user/searchByEmail/{email}") //user/searchByEmail/alfa@a.com
    public ResponseEntity<?> searchByEmail(@PathVariable("email") String email) {

        var user = doctorRepository.searchByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.status(200).body(user.get());
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

    }



    @GetMapping("user/searchByDocumentId/{document}") //user/search/10
    public ResponseEntity<?> findById(@PathVariable("document") String document){
        var user = doctorRepository.findByDocumentId(document);
        if(user.isPresent()){
            return ResponseEntity.status(200).body(user.get());
        }else{
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }

    @PutMapping("/user/edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") long id, @RequestBody Doctor doctor) {
        try {
            doctorRepository.updateById(id, doctor.getName(), doctor.getLastname(), doctor.getEmail(), doctor.getPassword(), doctor.getDocumentId());
            return ResponseEntity.status(200).body(doctor);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }


    /*@PutMapping("/user/edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") long id, @RequestBody Doctor doctor) {
        try {
            doctorRepository.updateById(id, doctor.getName(), doctor.getLastname(), doctor.getEmail(), doctor.getPassword(), doctor.getDocumentId());
            return ResponseEntity.status(200).body(doctor);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }*/

    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        doctorRepository.deleteById(id);
        return ResponseEntity.status(200).body(
                ("Usuario Eliminado")
        );
    }

    /**
     * Método para obtener todas las muestras
     * (no usar si ya tiene relacionado un paciente o doctor, se va a generar una lista circular)
     * (debug)
     */
    @GetMapping("/samples")
    public ResponseEntity<?> getAllSamples() {
        Iterable<Test> samplesIterable = testRepository.findAll();
        List<Test> samples = new ArrayList<>();
        samplesIterable.forEach(samples::add);
        return ResponseEntity.ok(samples);
    }

    /**
     * metodo para crear pruebas sin relaciones
     * (debug)

     */
    @PostMapping("/samples")
    public ResponseEntity<?> addSamples(@RequestBody List<Test> samples) {
        testRepository.saveAll(samples);
        return ResponseEntity.status(HttpStatus.CREATED).body("Samples added successfully");
    }



}
