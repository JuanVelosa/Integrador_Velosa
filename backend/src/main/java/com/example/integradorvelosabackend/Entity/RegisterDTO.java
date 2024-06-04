package com.example.integradorvelosabackend.Entity;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;

/**
 *
 * Ese metodo / clase record lo hice para filtar y solo guardar los datos de las
 * los registros
 *
 */
public record RegisterDTO(Long id, LocalDate testDate, String author, Patient patient,
        List<AnnotationDTO> annotations, List<TestDTO> tests) {
    public RegisterDTO(Register r) {
        this(r.getId(), r.getTestDate(), r.getAuthor(), r.getPatient(), new ArrayList<>(), new ArrayList<>());

        var fullAnns = r.getAnnotations();
        fullAnns.forEach(a -> annotations.add(new AnnotationDTO(a)));

        var fullTests = r.getTests();
        fullTests.forEach(s -> tests.add(new TestDTO(s)));

    }
}