package com.example.application.data;

import com.vaadin.flow.component.notification.Notification;
import jakarta.persistence.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
public class Numero extends AbstractEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> bloqueados;
    private UUID idapi;
    private String carrier;
    @OneToOne
    private Contrato contrato;
    private String numero;
    private TipoNumero Tipo;
    private int max;
    private float consumido;

    private float datosconsumidos;
    @OneToMany
    private List<CallRecord> callRecord;
    @OneToMany
    private List<DataUsageRecord> dataUsageRecord;

    private boolean roaming;
    private boolean compartir;


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

    //setters

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

    public void setMax(int max) {
        this.max = max;
    }

    public float getConsumido() {
        return consumido;
    }

    public void setConsumido(int consumido) {
        this.consumido = consumido;
    }

    public void setCallRecord(List<CallRecord> callRecord) {
        this.callRecord = callRecord;
    }

    public void setDataUsageRecord(List<DataUsageRecord> dataUsageRecord) {
        this.dataUsageRecord = dataUsageRecord;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public float getConsumidoTotal() {

        RestTemplate restTemplate = new RestTemplate();

        CallRecord[] callRecords = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + this.idapi + "/callrecords?carrier=" + this.carrier, CallRecord[].class);

        for (CallRecord callRecord : callRecords) {
            this.consumido += callRecord.getSeconds();
        }
        //Pasa a minutos
        this.consumido = this.consumido / 60;
        this.consumido = Math.round(this.consumido * 100) / 100f;

        return consumido;
    }

    public float getConsumidoHoy() {

        RestTemplate restTemplate = new RestTemplate();

        CallRecord[] callRecords = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + this.idapi + "/callrecords?carrier=" + this.carrier, CallRecord[].class);

        for (CallRecord callRecord : callRecords) {
            if (callRecord.getFecha().equals(LocalDateTime.now())) {
                this.consumido += callRecord.getSeconds();
            }
        }
        //Pasa a minutos
        this.consumido = this.consumido / 60;
        this.consumido = Math.round(this.consumido * 100) / 100f;

        return consumido;
    }

    public float getDatosMoviles() {

        RestTemplate restTemplate = new RestTemplate();

        DataUsageRecord[] dataUsageRecords = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + this.idapi + "/datausagerecords?carrier=" + this.carrier, DataUsageRecord[].class);

        for (DataUsageRecord dataUsageRecord : dataUsageRecords) {
            this.datosconsumidos += dataUsageRecord.getMegaBytes();
        }

        if (this.datosconsumidos > 0) {
            this.datosconsumidos = this.datosconsumidos / 1024;
            this.datosconsumidos = Math.round(this.datosconsumidos * 100) / 100f;
        } else {
            this.datosconsumidos = 0;
        }

        return datosconsumidos;
    }


    public float getDatosMovilesHoy() {

        RestTemplate restTemplate = new RestTemplate();

        DataUsageRecord[] dataUsageRecords = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + this.idapi + "/datausagerecords?carrier=" + this.carrier, DataUsageRecord[].class);

        for (DataUsageRecord dataUsageRecord : dataUsageRecords) {
            if (LocalDate.parse(dataUsageRecord.getDate()).equals(LocalDate.now())) {
                this.datosconsumidos += dataUsageRecord.getMegaBytes();
            }
        }

        if (this.datosconsumidos > 0) {
            this.datosconsumidos = this.datosconsumidos / 1024;
            this.datosconsumidos = Math.round(this.datosconsumidos * 100) / 100f;
        } else {
            this.datosconsumidos = 0;
        }

        return datosconsumidos;


    }

    public List<CallRecord> getCallRecords() {
        RestTemplate restTemplate = new RestTemplate();

        CallRecord[] callRecords = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + this.idapi + "/callrecords?carrier=" + this.carrier, CallRecord[].class);

        return Arrays.asList(callRecords);
    }

    public List<DataUsageRecord> getDataUsageRecords() {
        RestTemplate restTemplate = new RestTemplate();

        DataUsageRecord[] dataUsageRecords = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + this.idapi + "/datausagerecords?carrier=" + this.carrier, DataUsageRecord[].class);

        return Arrays.asList(dataUsageRecords);
    }

    public List<String> getNumerosBloqueados() {
        return bloqueados;
    }

    public void setNumerosBloqueados(String bloqueados) {
        this.bloqueados.add(bloqueados);
    }

    public boolean getRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    public boolean getCompartir() {
        return compartir;
    }

    public void setCompartir(boolean compartir) {
        this.compartir = compartir;
    }

}