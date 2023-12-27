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
}
