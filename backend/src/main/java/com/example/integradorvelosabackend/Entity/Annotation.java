package com.example.integradorvelosabackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity

public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String annotation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="registerID")
    private Register register;

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Annotation(String annotation) {
        this.annotation = annotation;
    }

    public Annotation() {
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}