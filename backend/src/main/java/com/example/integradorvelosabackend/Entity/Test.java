package com.example.integradorvelosabackend.Entity;

import com.example.integradorvelosabackend.Entity.Reading;

import java.util.ArrayList;
import jakarta.persistence.Entity;

@Entity
public class Test {
    private String type;
    private int samples;
    private ArrayList<Reading> readings;

    public Test() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public ArrayList<Reading> getReadings() {
        return readings;
    }

    public void setReadings(ArrayList<Reading> readings) {
        this.readings = readings;
    }

    public Test(String type, int samples, ArrayList<Reading> readings) {
        this.type = type;
        this.samples = samples;
        this.readings = readings;
    }
    //GETS y SETS
}
