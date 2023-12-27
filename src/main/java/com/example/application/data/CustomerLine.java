package com.example.application.data;

import java.util.UUID;

public class CustomerLine {

    private UUID id;
    private String name;
    private String surname;
    private String carrier;
    private String phoneNumber;

    public CustomerLine() {
    }

    public String getName() {
        return name;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    //getters

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setid(UUID id) {
        this.id = id;
    }

}