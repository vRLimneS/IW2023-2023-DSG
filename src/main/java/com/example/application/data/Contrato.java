package com.example.application.data;

import jakarta.persistence.*;

@Entity
public class Contrato extends AbstractEntity{
    @ManyToOne
    private Usuario usuario;
    @OneToOne
    private Fijo fijo;
    @OneToOne
    private Movil movil;
    @ManyToOne
    private Tarifa tarifa;
    private String fechaInicio;
    private String fechaFin;
    private String estado;

    //Constructores
    public Contrato() {
    }
    public Contrato(Usuario usuario, Fijo fijo, Movil movil, Tarifa tarifa, String fechaInicio, String fechaFin, String estado) {
            this.usuario = usuario;
            this.fijo = fijo;
            this.movil = movil;
            this.tarifa = tarifa;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.estado = estado;
        }

    //Getters y Setters
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public Fijo getFijo() {return fijo;}
    public void setFijo(Fijo fijo) {this.fijo = fijo;}
    public Movil getMovil() {return movil;}
    public void setMovil(Movil movil) {this.movil = movil;}
    public Tarifa getTarifa() {return tarifa;}
    public void setTarifa(Tarifa tarifa) {this.tarifa = tarifa;}
    public String getFechaInicio() {return fechaInicio;}
    public void setFechaInicio(String fechaInicio) {this.fechaInicio = fechaInicio;}
    public String getFechaFin() {return fechaFin;}
    public void setFechaFin(String fechaFin) {this.fechaFin = fechaFin;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}


}
