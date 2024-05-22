package com.example.integradorvelosabackend.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;

@Entity
public class Reading {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test")
    private PhysicalTest test;

    private int t;

    private int x;

    public Reading(int t, int x) {
        this.t = t;
        this.x = x;
    }

    public Reading() {
    }

    public int getT() { return t;}

    public void setT(int t) {
        this.t = t;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}