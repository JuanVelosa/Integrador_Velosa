package com.example.integradorvelosabackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate testDate;

    private String author;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patientID")
    private Patient patient;

    @JsonIgnore
    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL)
    private List<Test> tests;
    @JsonIgnore
    @OneToMany(mappedBy = "register")
    private List<Annotation> annotations;

    // Constructor vacio
    public Register() {
    }

    public Register(LocalDate testDate, String author, Patient patient, List<Annotation> annotations) {
        this.testDate = testDate;
        this.author = author;
        this.patient = patient;
        this.annotations = annotations;
    }

    // Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }
}
