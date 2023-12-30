package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class DataUsageRecord extends AbstractEntity {
    private int megaBytes;
    private String date;
    @ManyToOne
    private Numero numero;

    public DataUsageRecord() {
    }

    //getters

    public int getMegaBytes() {
        return megaBytes;
    }

    public void setMegaBytes(int megaBytes) {
        this.megaBytes = megaBytes;
    }

    //setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }
}
