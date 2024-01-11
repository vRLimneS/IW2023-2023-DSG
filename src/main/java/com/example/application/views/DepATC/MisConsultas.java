package com.example.application.views.DepATC;

import com.example.application.data.Consulta;
import com.example.application.data.Usuario;
import com.example.application.services.ConsultaService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RolesAllowed("ATCCLT")
@Route(value = "MisConsultas", layout = LayoutPrincipal.class)
public class MisConsultas extends Div {

    private static final List<Consulta> consultas = new ArrayList<>();
    private static Grid<Consulta> grid;
    private static Div hint;
    private final ConsultaService consultaService;
    private final AuthenticatedUser authenticatedUser;

    public MisConsultas(ConsultaService consultaService, AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.consultaService = consultaService;
        this.setupGrid(authenticatedUser);
        this.setupGrid2(authenticatedUser);

    }

    private void setupGrid(AuthenticatedUser authenticatedUser) {
        Optional<Usuario> usuario = authenticatedUser.get();
        if (usuario.isPresent()) {
            grid = new Grid<>(Consulta.class, false);
            grid.setAllRowsVisible(true);
            grid.addColumn(Consulta::getEmail).setHeader("Email");
            grid.addColumn(Consulta::getAsunto).setHeader("Asunto");
            grid.addColumn(Consulta::getMensaje).setHeader("Mensaje");
            grid.addColumn(Consulta::getEstado).setHeader("Estado");

            grid.addComponentColumn(qconsulta -> {
                Button boton = new Button("Terminar");
                boton.addClickListener(click -> {
                    {
                        if (qconsulta.getEstado().equals("ATENDIDO") && usuario.get().getUsername().equals(qconsulta.getUsername())) {
                            qconsulta.setEstado("RESUELTO");
                            consultaService.save(qconsulta);
                            Notification.show("Consulta terminada");
                            UI.getCurrent().getPage().reload();
                        }
                    }
                });
                return boton;
            }).setHeader("Terminar");


            grid.setItems(consultaService.findBy_estadoConsultaAndUsername("ATENDIDO", usuario.get().getUsername()));
            grid.getDataProvider().refreshAll();

            add(grid);
        }


    }


    private void setupGrid2(AuthenticatedUser authenticatedUser) {
        Optional<Usuario> usuario = authenticatedUser.get();
        if (usuario.isPresent()) {
            grid = new Grid<>(Consulta.class, false);
            grid.setAllRowsVisible(true);
            grid.addColumn(Consulta::getEmail).setHeader("Email");
            grid.addColumn(Consulta::getAsunto).setHeader("Asunto");
            grid.addColumn(Consulta::getMensaje).setHeader("Mensaje");
            grid.addColumn(Consulta::getEstado).setHeader("Estado");
            grid.setItems(consultaService.findBy_estadoConsultaAndUsername("RESUELTO", usuario.get().getUsername()));
            grid.getDataProvider().refreshAll();

            add(grid);
        }
    }

}