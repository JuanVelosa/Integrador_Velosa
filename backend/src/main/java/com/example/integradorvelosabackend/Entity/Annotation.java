package com.example.integradorvelosabackend.Entity;

import jakarta.persistence.*;

@Entity
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String note;

    @ManyToOne
    @JoinColumn(name = "id register")
    private Register register;


    public Annotation(long id, String note) {
        this.id = id;
        this.note = note;
    }

    public Annotation() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}


