package com.example.application.data;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Tarjeta extends AbstractEntity{

    private String iban;
    private String titular;
    private LocalDate fechaCaducidad;
    private String cvv;

    //getters
    public String getIban() {
        return iban;
    }
    public String getTitular() {
        return titular;
    }
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    public String getCvv() {
        return cvv;
    }
    //setters
    public void setIban(String iban) {
        this.iban = iban;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
