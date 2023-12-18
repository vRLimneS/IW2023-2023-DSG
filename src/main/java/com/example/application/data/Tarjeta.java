package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Tarjeta extends AbstractEntity {

    @Max(16)
    @NotNull
    private int numeroTarjeta;
    @NotEmpty
    private String titular;
    @NotNull
    private LocalDate fechaCaducidad;
    @NotNull
    private int cvv;

    private Contrato contrato;

    public Tarjeta() {
    }

    public Tarjeta(int numero, String titular, LocalDate fechaCaducidad, int cvv) {
        this.numeroTarjeta = numero;
        this.titular = titular;
        this.fechaCaducidad = fechaCaducidad;
        this.cvv = cvv;
    }


    //getters
    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    //setters
    public void setIban(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

}
