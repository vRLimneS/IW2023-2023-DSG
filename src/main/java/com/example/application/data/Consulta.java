package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;

import java.util.Optional;

@Entity
public class Consulta extends AbstractEntity {
    @Email
    private String email;
    private String asunto;
    private String mensaje;

    @ManyToOne
    private Usuario usuario;

    private String username;
    @ManyToOne
    private Usuario cliente;
    private String usernameCliente;

    private Estadoconsulta _estadoConsulta = Estadoconsulta.PENDIENTE;

    public Consulta(String email, String asunto, String mensaje, Usuario usuario, String username) {
        this.email = email;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.cliente = usuario;
        this.usernameCliente = username;
    }

    public Consulta(String email, String asunto, String mensaje) {
        this.email = email;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public Consulta() {

    }


    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getEmail() {
        return email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getEstado() {
        return _estadoConsulta.toString();
    }

    public void setEstado(String estado) {
        if (estado.equals("ATENDIDO")) {
            this._estadoConsulta = Estadoconsulta.ATENDIDO;
        } else {
            if (estado.equals("PENDIENTE")) {
                this._estadoConsulta = Estadoconsulta.PENDIENTE;
            } else {
                if (estado.equals("RESUELTO")) {
                    this._estadoConsulta = Estadoconsulta.RESUELTO;

                }
            }
        }
    }

    public Object getUsuario() {
        return usuario;
    }

    public void setUsuario(Optional<Usuario> usuario) {

        if (usuario.isPresent()) {
            this.usuario = usuario.get();
        }

    }

    public Object getCliente() {
        return cliente;
    }

    public void setCliente(Optional<Usuario> cliente) {

        if (cliente.isPresent()) {
            this.cliente = cliente.get();
        }
    }

    public String getUsername() {
        if (usuario == null) {
            return "Sin asignar";
        }
        return usuario.getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCliente() {
        if (cliente == null) {
            return "Sin asignar";
        }
        return cliente.getUsername();
    }

    public void setUsernameCliente(Usuario cliente) {
        this.usernameCliente = cliente.getUsername();
    }

    public void BorrarUsuario() {
        this.usuario = null;
    }

}