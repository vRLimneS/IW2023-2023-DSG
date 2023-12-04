package com.example.application.data;


import jakarta.persistence.*;

@Entity
public class TipoEstado extends AbstractEntity{

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "Nombre")
    String nombre;

    public TipoEstado() {
    }

    public TipoEstado(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getTipo() {
        return EnumEstado.toTipo(this.id);
    }


}
