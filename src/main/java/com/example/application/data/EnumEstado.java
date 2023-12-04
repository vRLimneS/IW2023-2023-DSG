package com.example.application.data;


import jakarta.persistence.*;

@Entity
public class EnumEstado extends AbstractEntity {

    private static String Pendiente, Resuelto, Rechazado;
    public static String toTipo(Integer iTipo) {
        switch (iTipo) {
            case 1:
                return EnumEstado.Pendiente;
            case 2:
                return EnumEstado.Resuelto;
            case 3:
                return EnumEstado.Rechazado;
            default:
                throw new IllegalArgumentException("El tipo de estado no esta entre los registrados");
        }

    }

    public static Integer toInt(EnumEstado tipo) {
        if (tipo.equals(Pendiente)) {
            return 1;
        } else if (tipo.equals(Resuelto)) {
            return 2;
        } else if (tipo.equals(Rechazado)) {
            return 3;
        }
        throw new IllegalArgumentException("El tipo de estado no esta entre los registrados");
    }

}
