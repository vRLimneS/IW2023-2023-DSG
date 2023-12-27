package com.example.application.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movil extends AbstractEntity{
    @OneToOne
    private Usuario usuario;
    private int minutosDisponiles;
    private int minutosConsumidos;
    private int numero;
    @OneToMany
    private List<Llamada> llamada;
    @OneToMany
    private List<SMS> sms;

    public Movil() {
    }
    public int getNumero() {return numero;}
    public void setNumero(int numero) {this.numero = numero;}

}
