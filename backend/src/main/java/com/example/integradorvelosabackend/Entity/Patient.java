package com.example.integradorvelosabackend.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String documentId;



    public Patient(long id, String name, String documentId) {
        this.id = id;
        this.name = name;
        this.documentId = documentId;

    }

    public Patient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }


    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @OneToMany(mappedBy = "patient")
    private List<Register>listRegister;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Register> getListRegister() {
        return listRegister;
    }

    public void setListRegister(List<Register> listRegister) {
        this.listRegister = listRegister;
    }
}
