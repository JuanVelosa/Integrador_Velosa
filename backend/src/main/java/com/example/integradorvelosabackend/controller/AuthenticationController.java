package com.example.integradorvelosabackend.controller;

import com.example.integradorvelosabackend.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600 )
public class AuthenticationController {
    @Autowired
    DoctorRepository doctorRepository;

    private record LoginRequest(String email, String password) {}

    @PostMapping("signin")
    public ResponseEntity<?> create(@RequestBody LoginRequest request) {

        var doctor = doctorRepository.searchByEmail(request.email());

        if (doctor.isEmpty())
            return ResponseEntity.status(404).build();

        if (!doctor.get().getPassword().equals(request.password()))
            return ResponseEntity.status(401).build();

        return  ResponseEntity.status(200).body(doctor.get());

    }
}
