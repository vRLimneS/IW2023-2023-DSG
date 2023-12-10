package com.example.application.data;

import jakarta.persistence.*;

@Entity
public class Contrato extends AbstractEntity{
    @ManyToOne
    private Usuario usuario;
    private int fijo;
    private int movil;
    @ManyToOne
    private Tarifa tarifa;
    private String fechaInicio;
    private String fechaFin;
    private String estado;

    //Constructores
    public Contrato() {
    }
    public Contrato(Usuario usuario, int fijo, int movil, Tarifa tarifa, String fechaInicio, String fechaFin, String estado) {
            this.usuario = usuario;
            this.fijo = fijo;
            this.movil = movil;
            this.tarifa = tarifa;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.estado = estado;
        }
}
