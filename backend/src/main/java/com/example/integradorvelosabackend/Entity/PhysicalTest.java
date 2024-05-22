package com.example.integradorvelosabackend.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "test")
public class PhysicalTest {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "register")
    private Register register;

    private String type; // TODO: Enum
    
    private int samples;

    @OneToMany(mappedBy = "test")
    private List<Reading> readings;

    public PhysicalTest() {
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

    public List<Reading> getReadings() {
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }

    public PhysicalTest(String type, int samples, List<Reading> readings) {
        this.type = type;
        this.samples = samples;
        this.readings = readings;
    }
    //GETS y SETS
}
