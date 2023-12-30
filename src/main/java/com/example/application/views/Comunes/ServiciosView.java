package com.example.application.views.Comunes;

import com.example.application.data.Contrato;
import com.example.application.data.Usuario;
import com.example.application.services.ContratoService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;
import java.util.Optional;

@RolesAllowed({"CLIENTE", "ATCCLT", "ADMIN", "MARKETING"})
@PageTitle("Servicios")
@Route(value = "Servicios", layout = LayoutPrincipal.class)
public class ServiciosView extends VerticalLayout {

    private final ContratoService contratoService;
    private final AuthenticatedUser authenticatedUser;
    private final Grid<Contrato> grid;

    public ServiciosView(ContratoService contratoService, AuthenticatedUser authenticatedUser) {
        this.contratoService = contratoService;
        this.authenticatedUser = authenticatedUser;

        Optional<Usuario> user = authenticatedUser.get();
        List<Contrato> contrato = contratoService.findByUsuarioId(user.get().getId());

        addClassName(Margin.Top.XLARGE);
        H1 titulo = new H1("Tarifas Contratadas");
        add(titulo);

        grid = new Grid<>(Contrato.class, false);
        grid.setAllRowsVisible(true);
        grid.setItems(contratoService.findByUsuarioId(user.get().getId()));
        grid.addColumn(Contrato::getTarifanombre).setHeader("Tarifa");
        grid.addColumn(Contrato::getFijonumero).setHeader("Fijo");
        grid.addColumn(Contrato::getMinutosFijo).setHeader("Minutos Fijo");
        grid.addColumn(Contrato::getMovilnumero).setHeader("Movil");
        grid.addColumn(Contrato::getMinutosMovil).setHeader("Minutos Movil");
        grid.addColumn(Contrato::getDatosMoviles).setHeader("Datos Moviles");
        grid.addColumn(Contrato::getVelocidadFibra).setHeader("Velocidad Fibra");
        grid.addColumn(Contrato::getFechaFin).setHeader("Fecha Fin Contrato");

        grid.addItemClickListener(event -> {
                    UI.getCurrent().navigate(ContratoIndiviualVista.class, event.getItem().getId().toString());
                }
        );


        add(grid);

        getStyle().set("margin-left", "auto");
        getStyle().set("margin-right", "auto");
        getStyle().set("margin-top", "none");
        getStyle().set("margin-bottom", "none");
        getStyle().set("padding", "none");
    }

}
