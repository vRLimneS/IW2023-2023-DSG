package com.example.application.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.util.Date;
import java.util.UUID;
import java.time.LocalDate;

@Entity
public class Usuario extends AbstractEntity{

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

    @Max(9)
    private String telefono;


    private LocalDate fechaNacimiento;

    private String direccion;

    @Enumerated(EnumType.STRING)
    private TipoRol rol;
    private boolean active;


    //getters

    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getDni() {
        return DNI;
    }
    public String getEmail() {
        return email;
    }
    public String getContraseña() {
        return contraseña;
    }
    public String getTelefono() {
        return telefono;
    }
    @NotNull
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public String getDireccion() {
        return direccion;
    }
    public TipoRol getRol() {
        return rol;
    }

    public String getUsername() {
        return username;
    }

    public String gettoken() {
        return token;
    }

    //setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setDni(String dni) {
        this.DNI = dni;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setContraseña(String password) {
        this.contraseña = password;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setRol(TipoRol rol) {
        this.rol = rol;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }
    public void settoken(String token) {
        this.token = token;
    }
}
