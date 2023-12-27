package com.example.application.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fijo extends AbstractEntity{
    @OneToOne
    private Usuario usuario;
    private int minutosDisponiles;
    private int minutosConsumidos;
    private int numero;
    @OneToMany
    private List<Llamada> llamada;

    public Fijo() {
    }
    public int getNumero() {return numero;}
    public void setNumero(int numero) {this.numero = numero;}
}
