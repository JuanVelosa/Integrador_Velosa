package com.example.integradorvelosabackend.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String author;
    private String testDate;


    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;


    @OneToMany(mappedBy = "register")
    private List<Annotation> annotation;




    public Register(long id, String author, String testDate) {
        this.id = id;
        this.author = author;
        this.testDate = testDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }



    public List<Annotation> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(List<Annotation> annotation) {
        this.annotation = annotation;
    }

    public Register() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}
