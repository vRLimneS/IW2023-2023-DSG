package com.example.application.data;

import jakarta.persistence.Entity;

import java.time.LocalDate;
@Entity
public class Llamada extends AbstractEntity{
    private int numeroReceptor;
    private int duracion;
    private LocalDate fecha;
}
