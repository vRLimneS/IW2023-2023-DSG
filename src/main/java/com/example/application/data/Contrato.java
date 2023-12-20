package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Contrato extends AbstractEntity {


    private int fijo;
    private int movil;
    @ManyToOne
    private Tarifa tarifa;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Tarjeta tarjeta;

    private EstadoContrato _estadoContrato;

    //Constructores
    public Contrato() {
    }

    public Contrato(Usuario usuario, int fijo, int movil, Tarifa tarifa, LocalDate fechaInicio, LocalDate fechaFin) {
        this.usuario = usuario;
        this.fijo = fijo;
        this.movil = movil;
        this.tarifa = tarifa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    //Getters

    public String get_estadoContrato() {
        return _estadoContrato.toString();
    }

    public void set_estadoContrato(String estadoContrato) {
        if (estadoContrato.equals("ACTIVO")) {
            this._estadoContrato = EstadoContrato.ACTIVO;
        } else {
            if (estadoContrato.equals("TERMINADO")) {
                this._estadoContrato = EstadoContrato.TERMINADO;
            } else {
                if (estadoContrato.equals("PENDIENTE")) {
                    this._estadoContrato = EstadoContrato.PENDIENTE;

                }
            }
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getFijo() {
        return fijo;
    }

    public void setFijo(int fijo) {
        this.fijo = fijo;
    }

    public int getMovil() {
        return movil;
    }

    public void setMovil(int movil) {
        this.movil = movil;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    //setters

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }


}
