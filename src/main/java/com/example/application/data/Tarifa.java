package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Tarifa extends AbstractEntity{

    @Id
    private UUID id;
    private String nombre;
    private String descripcion;
    private float precio;
    private int minutosMovil;
    private int minutosFijo;
    private int velocidadFibra;
    private int datosMoviles;

    public String getNombre() { return nombre;}
    public String getDescripcion() { return descripcion;}
    public float getPrecio() { return precio;}
    public int getMinutosMovil() { return minutosMovil;}
    public int getMinutosFijo() { return minutosFijo;}
    public int getVelocidadFibra() { return velocidadFibra;}
    public int getDatosMoviles() { return datosMoviles;}

}
