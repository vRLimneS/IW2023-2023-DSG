package com.example.application.views.Clientes;

import com.example.application.data.Contrato;
import com.example.application.services.ContratoService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@RolesAllowed("CLIENTE")
@PageTitle("Contratos")
@Route(value = "Contratos", layout = LayoutPrincipal.class)
public class ContratosView extends VerticalLayout {
    @Autowired
    private ContratoService contratoService;
    private Contrato contrato;
    public ContratosView(ContratoService contratoService) {
        this.contratoService = contratoService;
        for(Contrato contrato: contratoService.findAll()){
            if(contrato.getUsuario().getUsername().equals("user")){
                this.contrato = contrato;
            }
        }
        addClassName(Margin.Top.XLARGE);
        H2 titulo = new H2("Contratos");
        add(titulo);
        Paragraph textLarge = new Paragraph();
        textLarge.setText(contrato.getUsuario().getNombre() +
                " " + contrato.getUsuario().getApellidos()
                + " con DNI " + contrato.getUsuario().getDNI() +
                " tiene contratado el siguiente plan:");
        add(textLarge);

        //mostrar datos usuario
        H2 user = new H2(String.valueOf(contrato.getUsuario().getNombre()));
        H2 user2 = new H2(String.valueOf(contrato.getUsuario().getApellidos()));
        H2 user3 = new H2(String.valueOf(contrato.getUsuario().getDNI()));
        H2 user4 = new H2(String.valueOf(contrato.getUsuario().getDireccion()));
        H2 user6 = new H2(String.valueOf(contrato.getUsuario().getEmail()));

        //mostrar datos tarifa
        H2 tarifa = new H2(String.valueOf(contrato.getTarifa().getNombre()));
        H2 tarifa3 = new H2(String.valueOf(contrato.getTarifa().getPrecio()));
        H2 tarifa4 = new H2(String.valueOf(contrato.getTarifa().getMinutosMovil()));
        H2 tarifa5 = new H2(String.valueOf(contrato.getTarifa().getMinutosFijo()));
        H2 tarifa6 = new H2(String.valueOf(contrato.getTarifa().getVelocidadFibra()));
        H2 tarifa7 = new H2(String.valueOf(contrato.getTarifa().getDatosMoviles()));

        //mostrar datos fijo
        H2 fijo = new H2(String.valueOf(contrato.getFijo().getNumero()));
        //mostrar datos movil
        H2 movil = new H2(String.valueOf(contrato.getMovil().getNumero()));

        add(tarifa, tarifa3, tarifa4, tarifa5, tarifa6, tarifa7, fijo, movil);
    }
}
