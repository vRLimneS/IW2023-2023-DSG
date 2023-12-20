package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Tarjeta extends AbstractEntity {

    @NotNull
    private String numeroTarjeta;
    @NotEmpty
    private String titular;
    @NotNull
    private LocalDate fechaCaducidad;
    @NotEmpty
    private String cvv;

    @ManyToOne
    private Usuario usuario;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "tarjeta")
    //private List<Contrato> contrato;

    public Tarjeta() {

    }

    public Tarjeta(String numero, String titular, String fechaCaducidad, String cvv) {
        this.numeroTarjeta = numero;
        this.titular = titular;
        this.fechaCaducidad = LocalDate.parse(fechaCaducidad);
        this.cvv = cvv;
    }


    //getters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String i) {
        this.numeroTarjeta = i;
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

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    //setters
    public void setIban(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    //public void setContrato(Contrato contrato) {
    //    this.contrato.add(contrato);
    //}

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
