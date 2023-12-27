package com.example.application.data;

import com.vaadin.flow.component.notification.Notification;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.UUID;

@Entity
public class Numero extends AbstractEntity {

    private UUID idapi;
    @OneToOne
    private Contrato contrato;
    private String numero;
    private TipoNumero Tipo;
    private int max;
    private int consumido;
    @OneToMany
    private List<CallRecord> callRecord;
    @OneToMany
    private List<DataUsageRecord> dataUsageRecord;

    public Numero() {
    }

    public Numero(String numero, TipoNumero Tipo, int max, int consumido) {
        this.numero = numero;
        this.Tipo = Tipo;
        this.max = max;
        this.consumido = consumido;
    }

    //getters

    public UUID getIdapi() {
        return idapi;
    }

    public void setIdapi(UUID idapi) {
        this.idapi = idapi;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoNumero getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {

        if (Tipo.equals("movil") || Tipo.equals("MOVIL"))
            this.Tipo = TipoNumero.MOVIL;
        else if (Tipo.equals("fijo") || Tipo.equals("FIJO"))
            this.Tipo = TipoNumero.FIJO;
        else
            Notification.show("Tipo de numero no valido");
    }

    public int getMax() {
        return max;
    }

    //setters

    public void setMax(int max) {
        this.max = max;
    }

    public int getConsumido() {
        return consumido;
    }

    public void setConsumido(int consumido) {
        this.consumido = consumido;
    }

    public List<CallRecord> getCallRecord() {
        return callRecord;
    }

    public void setCallRecord(List<CallRecord> callRecord) {
        this.callRecord = callRecord;
    }

    public List<DataUsageRecord> getDataUsageRecord() {
        return dataUsageRecord;
    }

    public void setDataUsageRecord(List<DataUsageRecord> dataUsageRecord) {
        this.dataUsageRecord = dataUsageRecord;
    }

}
