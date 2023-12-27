package com.example.application.data;

import jakarta.persistence.Entity;

@Entity
public class SMS extends AbstractEntity{
    private int numeroReceptor;
    private String mensaje;
    private String fecha;

}
