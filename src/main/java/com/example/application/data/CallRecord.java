package com.example.application.data;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class CallRecord extends AbstractEntity {

    private String destinationPhoneNumber;
    private int seconds;
    private String dateTime;

    public CallRecord() {
    }

    //getters

    public String getDestinationPhoneNumber() {
        return destinationPhoneNumber;
    }

    public void setDestinationPhoneNumber(String destinationPhoneNumber) {
        this.destinationPhoneNumber = destinationPhoneNumber;
    }

    //setters

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getFecha() {
        return LocalDateTime.parse(dateTime);
    }

    public String getDuracion() {
        int horas = seconds / 3600;
        int minutos = (seconds % 3600) / 60;
        int segundos = seconds % 60;
        return horas + ":" + minutos + ":" + segundos;

    }
}
