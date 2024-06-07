package com.example.integradorvelosabackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integradorvelosabackend.Entity.Register;
import com.example.integradorvelosabackend.Entity.Test;
import com.example.integradorvelosabackend.Entity.Annotation;
import com.example.integradorvelosabackend.Entity.SignalTransformation;

import com.example.integradorvelosabackend.Repository.RegisterRepository;
import com.example.integradorvelosabackend.Repository.TestRepository;
import static com.example.integradorvelosabackend.util.DFTUtils.fourierTransformation;

@RestController
@RequestMapping("/registers")
@CrossOrigin(maxAge = 3600)
public class RegisterController {

    @Autowired
    private final RegisterRepository repo;

    @Autowired
    private final TestRepository testRepo;

    public RegisterController(RegisterRepository repo, TestRepository testRepo) {
        this.repo = repo;
        this.testRepo = testRepo;
    }

    /**
     * metodo para crear un registro
     *
     */
    @PostMapping()
    public ResponseEntity<?> createRegister(@RequestBody Register register) {
        Register saved = repo.save(register);
        return ResponseEntity.status(201).body(saved);
    }

    /**
     * metodo para agregar una prueba relacionada con un paciente
     *
     */
    @PostMapping("/{registerId}")
    public ResponseEntity<?> register(@PathVariable Long registerId, @RequestBody List<Test> tests) {
        // Buscar el registro asociado
        Optional<Register> optionalRegister = repo.findById(registerId);
        if (optionalRegister.isEmpty()) {

            return ResponseEntity.status(404).body("Register not found");
        }

        Register register = optionalRegister.get();

        // Asociar las muestras al registro y guardar en la base de datos
        for (Test test : tests) {
            test.setRegister(register);
            testRepo.save(test);
        }

        return ResponseEntity.status(200).build();
    }

    /**
     *
     * Metodo para obtener los datos de los registros por paciente
     */

    @GetMapping("/{registerId}")
    public ResponseEntity<?> getRegister(@PathVariable Long registerId) {

        Optional<Register> optionalRegister = repo.findById(registerId);
        if (!optionalRegister.isPresent())
            return ResponseEntity.status(404).body("Register not found");

        return ResponseEntity.ok(optionalRegister.get());
    }

    /*
     * La función de este metodo es obtener los datos del registro por paciente
     */
    @GetMapping("/{registerId}/tests")
    public ResponseEntity<?> getRegisterWithTests(@PathVariable Long registerId) {
        Optional<Register> optionalRegister = repo.findById(registerId);
        if (optionalRegister.isEmpty()) {
            return ResponseEntity.status(404).body("Register not found");
        }
        List<Test> fullTests = testRepo.findByRegisterId(registerId);
        SignalTransformation signalTransformation = fourierTransformation(fullTests);
        return ResponseEntity.ok(signalTransformation);
    }

    @GetMapping("/{registerId}/annotations")
    public ResponseEntity<?> getAnnotationRegister(@PathVariable Long registerId) {
        Optional<Register> optionalRegister = repo.findById(registerId);
        if (optionalRegister.isPresent()) {
            List<Annotation> annotations = optionalRegister.get().getAnnotations();
            return ResponseEntity.status(200).body(annotations);
        }
        return ResponseEntity.status(404).body("No se encontraron anotaciones");
    }

    @DeleteMapping("/{registerId}")
    public ResponseEntity<?> deleteRegister(@PathVariable Long registerId) {
        Optional<Register> optionalRegister = repo.findById(registerId);
        if (optionalRegister.isPresent()) {
            repo.delete(optionalRegister.get());
            return ResponseEntity.status(200).body("Registro eliminado");
        }
        return ResponseEntity.status(404).body("Registro no encontrado");
    }

}
