package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class CallRecord extends AbstractEntity {
    @ManyToOne
    private Numero numero;
    private String destinationPhoneNumber;
    private int seconds;
    private String dateTime;

    public CallRecord() {
    }
}
