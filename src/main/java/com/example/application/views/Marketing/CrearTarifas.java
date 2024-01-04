package com.example.application.views.Marketing;

import com.example.application.data.Tarifa;
import com.example.application.services.TarifaService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import java.math.BigDecimal;

@RolesAllowed({"MARKETING", "ADMIN", "CLIENTE"})

@Route(value = "creartarifas", layout = LayoutPrincipal.class)
public class CrearTarifas extends VerticalLayout {
    private TarifaService tarifaService;
    private AuthenticatedUser authenticatedUser;
    public CrearTarifas(TarifaService tarifaService, AuthenticatedUser authenticatedUser) {
        this.tarifaService = tarifaService;
        this.authenticatedUser = authenticatedUser;

        H1 title = new H1("Crear Tarifas");
        add(title);
        TextField NombreTarifa = new TextField();
        NombreTarifa.setLabel("Nombre Tarifa");
        TextArea DescripcionTarifa = new TextArea();
        DescripcionTarifa.setLabel("Descripcion Tarifa");
        TextField PrecioTarifa = new TextField();
        PrecioTarifa.setLabel("Precio Tarifa(€)");
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
        TextField Permanencia = new TextField();
        Permanencia.setLabel("Permanencia");
        TextField Estado = new TextField();
        Estado.setLabel("Estado (false no visible, true visible)");
        TextField Url = new TextField();
        Url.setLabel("Url");

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
        DescripcionTarifa.getStyle().set("padding", "var(--lumo-space-s)");
        Permanencia.getStyle().set("padding", "var(--lumo-space-s)");
        Estado.getStyle().set("padding", "var(--lumo-space-s)");
        Url.getStyle().set("padding", "var(--lumo-space-s)");
        boton.getStyle().set("padding", "var(--lumo-space-s)");


        NombreTarifa.setWidth("50%");
        PrecioTarifa.setWidth("50%");
        MinutosMovil.setWidth("50%");
        Datos.setWidth("50%");
        SMS.setWidth("50%");
        MinutosFijo.setWidth("50%");
        VelocidadFibra.setWidth("50%");
        DescripcionTarifa.setWidth("50%");
        Permanencia.setWidth("50%");
        Estado.setWidth("50%");
        Url.setWidth("50%");

        add(NombreTarifa, DescripcionTarifa, PrecioTarifa, MinutosMovil, Datos, SMS, MinutosFijo, VelocidadFibra,
                Permanencia, Estado, Url, boton);
        setHorizontalComponentAlignment(Alignment.CENTER, title, NombreTarifa, PrecioTarifa, MinutosMovil, Datos, SMS,
                MinutosFijo, VelocidadFibra,DescripcionTarifa, Permanencia, Estado, Url,  boton);
        setSizeFull();
        boton.addClickListener(e -> {
            if (NombreTarifa.getValue().isEmpty() || PrecioTarifa.getValue().isEmpty()) {
                Notification.show("Rellene todos los campos");
            } else {
                try {
                    Tarifa tarifa = new Tarifa(NombreTarifa.getValue(), DescripcionTarifa.getValue(),
                            BigDecimal.valueOf(Long.parseLong(PrecioTarifa.getValue())),
                            Integer.parseInt(MinutosMovil.getValue()), Integer.parseInt(MinutosFijo.getValue()),
                            Integer.parseInt(VelocidadFibra.getValue()), Integer.parseInt(Datos.getValue()),
                            Boolean.parseBoolean(Estado.getValue()), Integer.parseInt(Permanencia.getValue()),
                            Url.getValue());

                    tarifaService.save(tarifa);

                    Notification.show("Tarifa creada");
                    NombreTarifa.clear();
                    DescripcionTarifa.clear();
                    PrecioTarifa.clear();
                    MinutosMovil.clear();
                    Datos.clear();
                    SMS.clear();
                    MinutosFijo.clear();
                    VelocidadFibra.clear();
                    Permanencia.clear();
                    Estado.clear();
                    Url.clear();

                } catch (Exception ex) {
                    ex.printStackTrace();  // Imprime la traza de la excepción en la consola
                    Notification.show("Error al intentar guardar la tarifa: " + ex.getMessage(), 5000, Notification.Position.MIDDLE);
                }
            }
        });
    }
}