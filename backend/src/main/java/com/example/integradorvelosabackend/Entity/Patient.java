package com.example.integradorvelosabackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String documentId;

    private LocalDate consultationDate;

    @JsonIgnore // Evita la serialización infinita
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorID")
    private Doctor doctor;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Register> registerList;

    public Patient(long id, String name, String documentId, LocalDate consultationDate, Doctor doctor,
            List<Register> registerList) {
        this.id = id;
        this.name = name;
        this.documentId = documentId;
        this.consultationDate = consultationDate;
        this.doctor = doctor;
        this.registerList = registerList;
    }

    public Patient() {
    }

    public LocalDate getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<Register> registerList) {
        this.registerList = registerList;
    }
}
