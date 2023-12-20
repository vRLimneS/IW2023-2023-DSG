package com.example.application.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario extends AbstractEntity {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Contrato> contrato;

    private String token;

    private String nombre;

    private String apellidos;

    private String DNI;

    @Column(name = "username")

    private String username;

    @Email
    private String email;

    @Column(name = "contraseña")
    private String contraseña;


    private LocalDate fechaNacimiento;

    private String direccion;

    @Enumerated(EnumType.STRING)
    private TipoRol rol;
    private boolean active;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Tarjeta> tarjeta;


    //getters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dni) {
        this.DNI = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }


    //setters

    public void setContraseña(String password) {
        this.contraseña = password;
    }

    @NotNull
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TipoRol getRol() {
        return rol;
    }

    public void setRol(TipoRol rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String gettoken() {
        return token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void settoken(String token) {
        this.token = token;
    }

    public void setContrato(Contrato contrato) {
        this.contrato.add(contrato);
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta.add(tarjeta);
    }
}
