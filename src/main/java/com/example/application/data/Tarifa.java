package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Entity
public class Tarifa extends AbstractEntity{
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int minutosMovil;
    private int minutosFijo;
    private int velocidadFibra;
    private int datosMoviles;
    private boolean estado;

    //Tarifa Sago
    public Tarifa(String nombre, String descripcion, BigDecimal precio, int minutosMovil, int minutosFijo, int velocidadFibra, int datosMoviles, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.minutosMovil = minutosMovil;
        this.minutosFijo = minutosFijo;
        this.velocidadFibra = velocidadFibra;
        this.datosMoviles = datosMoviles;
        this.estado = estado;
        //(añadir los otros atributos con un set)
    }
    public Tarifa(){}

    public String getNombre() { return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre;}
    public String getDescripcion() { return descripcion;}
    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}
    public BigDecimal getPrecio() { return precio;}
    public void setPrecio(BigDecimal precio) { this.precio = precio;}
    public int getMinutosMovil() { return minutosMovil;}
    public void setMinutosMovil(int minutosMovil) { this.minutosMovil = minutosMovil;}
    public int getMinutosFijo() { return minutosFijo;}
    public void setMinutosFijo(int minutosFijo) { this.minutosFijo = minutosFijo;}
    public int getVelocidadFibra() { return velocidadFibra;}
    public void setVelocidadFibra(int velocidadFibra) { this.velocidadFibra = velocidadFibra;}
    public int getDatosMoviles() { return datosMoviles;}
    public void setDatosMoviles(int datosMoviles) { this.datosMoviles = datosMoviles;}
    public boolean getEstado() { return estado;}
    public void setEstado(boolean estado) { this.estado = estado;}
}
