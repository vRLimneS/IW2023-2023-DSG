package com.example.application.views.Comunes;

import com.example.application.data.Tarifa;
import com.example.application.services.TarifaService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@RolesAllowed({"CLIENTE","ATCCLT","ADMIN","MARKETING"})
@Route(value = "PasareladePago",layout = LayoutPrincipal.class)
public class PasareladePago extends HorizontalLayout {


    private TarifaService tarifaService;
    private AuthenticatedUser authenticatedUser;
    public PasareladePago(AuthenticatedUser authenticatedUser, TarifaService tarifaService) {
        this.authenticatedUser = authenticatedUser;
        this.tarifaService = tarifaService;

        VerticalLayout vl = new VerticalLayout();
        VerticalLayout vl2 = new VerticalLayout();

        TextArea Nombre = new TextArea("Nombre");
        TextArea Descripcion = new TextArea("");
        TextArea Precio = new TextArea("Precio");
        TextArea minutosFijos = new TextArea("Minutos Fijos");
        TextArea minutosMoviles = new TextArea("Minutos Moviles");
        TextArea velocidad = new TextArea("Velocidad");
        TextArea gigas = new TextArea("Gigas");

        Nombre.setReadOnly(true);
        Descripcion.setReadOnly(true);
        Precio.setReadOnly(true);
        minutosFijos.setReadOnly(true);
        minutosMoviles.setReadOnly(true);
        velocidad.setReadOnly(true);
        gigas.setReadOnly(true);


        Nombre.setValue("la caca");
        Descripcion.setValue(tarifaService.findByNombre("la caca").getDescripcion());
        Precio.setValue(String.valueOf(tarifaService.findByNombre("la caca").getPrecio()));
        minutosFijos.setValue(String.valueOf(tarifaService.findByNombre("la caca").getMinutosFijo()));
        minutosMoviles.setValue(String.valueOf(tarifaService.findByNombre("la caca").getMinutosMovil()));
        velocidad.setValue(String.valueOf(tarifaService.findByNombre("la caca").getVelocidadFibra()));
        gigas.setValue(String.valueOf(tarifaService.findByNombre("la caca").getDatosMoviles()));

        vl.add(Nombre,Descripcion,Precio,minutosFijos,minutosMoviles,velocidad,gigas);

        TextArea NumeroTarjeta = new TextArea("Numero Tarjeta");
        NumeroTarjeta.setWidth("500px");
        NumeroTarjeta.setHeight("65px");
        NumeroTarjeta.setPlaceholder("0000 0000 0000 0000");
        TextArea Titular = new TextArea("Titular");
        TextArea FechaCaducidad = new TextArea("Fecha Caducidad");
        TextArea CVV = new TextArea("CVV");

        vl2.add(NumeroTarjeta,Titular,FechaCaducidad,CVV);


        add(vl,vl2);
    }

}
