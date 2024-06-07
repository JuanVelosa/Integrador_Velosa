package com.example.integradorvelosabackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.integradorvelosabackend.Entity.Test;
import com.example.integradorvelosabackend.Repository.TestRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class TestController {

    @Autowired
    private final TestRepository repo;

    public TestController(TestRepository repo) {
        this.repo = repo;
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
