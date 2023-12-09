package com.example.application.views.Marketing;

import com.example.application.views.LayoutMarketing;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route(value = "creartarifas", layout = LayoutMarketing.class)
public class CrearTarifas extends VerticalLayout {
    public CrearTarifas(){
        H1 title = new H1("Crear Tarifas");
        add(title);
        TextField NombreTarifa = new TextField();
        NombreTarifa.setLabel("Nombre Tarifa");
        TextField PrecioTarifa = new TextField();
        PrecioTarifa.setLabel("Precio Tarifa");
        TextField MinutosMovil = new TextField();
        MinutosMovil.setLabel("Minutos Movil");
        TextField Datos = new TextField();
        Datos.setLabel("Datos");
        TextField SMS = new TextField();
        SMS.setLabel("SMS");
        TextField MinutosFijo = new TextField();
        MinutosFijo.setLabel("Minutos Fijo");
        TextField VelocidadFibra = new TextField();
        VelocidadFibra.setLabel("Velocidad Fibra");

        com.vaadin.flow.component.button.Button boton = new Button();
        boton.setText("Crear Tarifa");
        boton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout vl2 = new VerticalLayout();
        vl2.add(boton);

        NombreTarifa.getStyle().set("padding", "var(--lumo-space-s)");
        PrecioTarifa.getStyle().set("padding", "var(--lumo-space-s)");
        MinutosMovil.getStyle().set("padding", "var(--lumo-space-s)");
        Datos.getStyle().set("padding", "var(--lumo-space-s)");
        SMS.getStyle().set("padding", "var(--lumo-space-s)");
        MinutosFijo.getStyle().set("padding", "var(--lumo-space-s)");
        VelocidadFibra.getStyle().set("padding", "var(--lumo-space-s)");
        boton.getStyle().set("padding", "var(--lumo-space-s)");


        NombreTarifa.setWidth("50%");
        PrecioTarifa.setWidth("50%");
        MinutosMovil.setWidth("50%");
        Datos.setWidth("50%");
        SMS.setWidth("50%");
        MinutosFijo.setWidth("50%");
        VelocidadFibra.setWidth("50%");

        add(NombreTarifa, PrecioTarifa, MinutosMovil, Datos, SMS, MinutosFijo, VelocidadFibra, boton);
        setHorizontalComponentAlignment(Alignment.CENTER, title, NombreTarifa, PrecioTarifa, MinutosMovil, Datos, SMS, MinutosFijo, VelocidadFibra, boton);
        setSizeFull();

    }

}