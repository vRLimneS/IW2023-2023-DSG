package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Contrato extends AbstractEntity {


    @OneToOne(fetch = FetchType.EAGER)
    private Numero fijo;
    @OneToOne(fetch = FetchType.EAGER)
    private Numero movil;
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

    public Contrato(Usuario usuario, Numero fijo, Numero movil, Tarifa tarifa, LocalDate fechaInicio, LocalDate fechaFin) {
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
                    this._estadoContrato = EstadoContrato.PENDIENTE_BAJA;

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

    public Numero getFijo() {
        return fijo;
    }

    public void setFijo(Numero fijo) {
        this.fijo = fijo;
    }

    public Numero getMovil() {
        return movil;
    }

    public void setMovil(Numero movil) {
        this.movil = movil;
    }

    public String getTarifanombre() {
        return tarifa.getNombre();
    }

    public Object getFijonumero() {
        if (fijo != null) {
            return fijo.getNumero();
        } else {
            return "No tiene fijo";
        }
    }

    public Object getMovilnumero() {
        if (movil != null) {
            return movil.getNumero();
        } else {
            return "No tiene movil";
        }
    }

    public Object getMinutosFijo() {

        if (fijo != null) {
            float total = fijo.getConsumidoTotal();
            if (total >= 0) {
                return total;
            } else {
                return "No tiene minutos fijo";
            }
        } else {
            return "No tiene fijo";
        }
    }

    public Object getMinutosFijoHoy() {

        if (fijo != null) {
            float total = fijo.getConsumidoHoy();
            if (total >= 0) {
                return total;
            } else {
                return "No tiene minutos fijo";
            }
        } else {
            return "No tiene fijo";
        }
    }

    public Object getMinutosMovilHoy() {

        if (movil != null) {
            float total = movil.getConsumidoHoy();
            if (total >= 0) {
                return total;
            } else {
                return "No tiene minutos movil";
            }
        } else {
            return "No tiene movil";
        }
    }

    public Object getMinutosMovil() {
        if (movil != null) {
            float total = movil.getConsumidoTotal();
            if (total >= 0) {
                return total;
            } else {
                return "No tiene minutos movil";
            }
        } else {
            return "No tiene movil";
        }
    }

    public Object getVelocidadFibra() {
        return tarifa.getVelocidadFibra();
    }

    public Object getDatosMoviles() {
        if (movil != null) {
            return movil.getDatosMoviles();

        } else
            return "No tiene movil";

    }

    public Object getDatosMovilesHoy() {
        if (movil != null) {
            return movil.getDatosMovilesHoy();

        } else
            return "No tiene movil";
    }
}
