package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import java.util.UUID;

@Entity
public class Consulta extends AbstractEntity{
    @Id
    private UUID id;
    @Email
    private String email;
    private String asunto;
    private String mensaje;
    @ManyToOne
    private TipoEstado _tipoEstado;

    public String getAsunto() {
        return asunto;
    }
    public String getEmail() {
        return email;
    }
    public String getMensaje() {
        return mensaje;
    }
    public Object getEstado() {
        return _tipoEstado.getTipo().toString();
    }
}