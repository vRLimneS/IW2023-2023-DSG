package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Consulta extends AbstractEntity{

    @Id
    private UUID id;
    @Email
    private String email;

    private String asunto;

    private String mensaje;

    private boolean terminado;



    public String getAsunto() {
        return asunto;
    }

    public String getEmail() {
        return email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public boolean isTerminado() {
        return terminado;
    }

}
