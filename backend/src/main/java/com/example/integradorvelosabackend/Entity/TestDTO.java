package com.example.integradorvelosabackend.Entity;

/**
 *
 * Ese metodo / clase record lo hice para filtar y solo guardar los datos de la
 * muestra
 *
 */
public record TestDTO(double t, double x, double y, double z) {
    public TestDTO(Test accel) {
        this(accel.getTime(), accel.getxPosition(), accel.getyPosition(), accel.getzPosition());
    }
}