package com.example.integradorvelosabackend.Entity;

public class SignalTransformation {

    private double[] spectrum;
    
    private double[] freqs;

    public SignalTransformation(double[] spectrum, double[] freqs) {
        this.spectrum = spectrum;
        this.freqs = freqs;
    }

    public SignalTransformation() {
    }

    public double[] getSpectrum() {
        return spectrum;
    }

    public void setSpectrum(double[] spectrum) {
        this.spectrum = spectrum;
    }

    public double[] getFreqs() {
        return freqs;
    }

    public void setFreqs(double[] freqs) {
        this.freqs = freqs;
    }

}