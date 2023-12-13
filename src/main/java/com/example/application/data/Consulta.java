package com.example.application.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import javax.swing.text.html.Option;
import java.util.Optional;

@Entity
public class Consulta extends AbstractEntity{
    @Email
    private String email;
    private String asunto;
    private String mensaje;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Usuario cliente;

    private Estadoconsulta _estadoConsulta = Estadoconsulta.PENDIENTE;

    public Consulta(String email, String asunto, String mensaje, Usuario usuario){
        this.email = email;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.cliente = usuario;
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
    public String getEmail() {
        return email;
    }
    public String getMensaje() {
        return mensaje;
    }

    public Object getEstado() {
        return _estadoConsulta.toString();
    }

    public Object getUsuario() {
        return usuario;
    }

    public Object getCliente() {
        return cliente;
    }

    public String getUsername() {
        if(usuario == null){
            return "Sin asignar";
        }
        return usuario.getUsername();
    }

    public String getUsernameCliente() {
        if(cliente == null){
            return "Sin asignar";
        }
        return cliente.getUsername();
    }

    public void setEstado(String estado) {
        if(estado.equals("ATENDIDO")){
            this._estadoConsulta = Estadoconsulta.ATENDIDO;
        }else{if (estado.equals("PENDIENTE")){
            this._estadoConsulta = Estadoconsulta.PENDIENTE;
        }else{if (estado.equals("RESUELTO")){
            this._estadoConsulta = Estadoconsulta.RESUELTO;

        }}}}

    public void setUsuario(Optional<Usuario> usuario) {
        this.usuario = usuario.get();
    };

    public void setCliente(Optional<Usuario> cliente) {
        this.cliente = cliente.get();
    };
    public void BorrarUsuario() {
        this.usuario = null;
    };
}