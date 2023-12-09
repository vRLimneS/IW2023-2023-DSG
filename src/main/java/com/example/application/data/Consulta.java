package com.example.application.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.UUID;

@Entity
public class Consulta extends AbstractEntity{

    @Email
    private String email;

    private String asunto;

    private String mensaje;

    @Enumerated(EnumType.STRING)
    private Estadoconsulta _estadoConsulta = Estadoconsulta.PENDIENTE;


    public String getAsunto() {
        return asunto;
    }

    public String getEmail() {
        return email;
    }

    public String getMensaje() {
        return mensaje;
    }

}
