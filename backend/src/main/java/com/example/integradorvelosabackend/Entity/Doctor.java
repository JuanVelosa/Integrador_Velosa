package com.example.integradorvelosabackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    
    private String lastname;
    
    private String email;

    private String documentId;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Patient> listPatient;

    public Doctor(long id, String name, String lastname, String email, String documentId, String password,
            List<Patient> listPatient) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.documentId = documentId;
        this.password = password;
        this.listPatient = listPatient;
    }

    public Doctor() {
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Patient> getListPatient() {
        return listPatient;
    }

    public void setListPatient(List<Patient> listPatient) {
        this.listPatient = listPatient;
    }
}
