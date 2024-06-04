package com.example.integradorvelosabackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.integradorvelosabackend.Entity.Test;
import com.example.integradorvelosabackend.Entity.TestDTO;
import com.example.integradorvelosabackend.Repository.TestRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class TestController {

    @Autowired
    private final TestRepository repo;

    public TestController(TestRepository repo) {
        this.repo = repo;
    }

    /*
     * Buscar por el id de la prueba
     */
    @GetMapping("/tests/{id}") // TODO: Revisar si este metodo se usa o no
    public ResponseEntity<?> getTestById(@PathVariable Long id) {
        Optional<Test> box = repo.findById(id);
        if (box.isPresent()) {
            var test = new TestDTO(box.get());
            return ResponseEntity.ok(test);
        } else
            return ResponseEntity.status(404).build();
    }

    /**
     * metodo para crear pruebas sin relaciones
     * (debug)
     */
    @PostMapping("/tests")
    public ResponseEntity<?> addTests(@RequestBody List<Test> tests) {
        repo.saveAll(tests);
        return ResponseEntity.status(201).build();
    }

}
