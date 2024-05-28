package com.example.integradorvelosabackend.Entity;

import com.example.integradorvelosabackend.Entity.Register;
import jakarta.persistence.*;

/**
 *La clase SampleAcelerometer implementa la logica
 * que sea necesaria para manipular todo lo correspondiente
 * a las pruebas
 *
 */

@Entity
public class Test{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double time;
    private double xPosition;
    private double yPosition;
    private double zPosition;

    @ManyToOne
    @JoinColumn(name="registerID")
    private Register register;

    public Test() {
    }

    public Test(double time, double xPosition, double yPosition, double zPosition, Register register) {
        this.time = time;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.register = register;
    }

    // Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public double getzPosition() {
        return zPosition;
    }

    public void setzPosition(double zPosition) {
        this.zPosition = zPosition;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
}
}