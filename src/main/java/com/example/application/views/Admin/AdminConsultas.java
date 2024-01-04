package com.example.application.views.Admin;

import com.example.application.data.Consulta;
import com.example.application.data.Usuario;
import com.example.application.services.ConsultaService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RolesAllowed({"CLIENTE", "ADMIN"})
@Route(value = "AdminConsultas", layout = LayoutPrincipal.class)
public class AdminConsultas extends Div {

    private static final List<Consulta> consultas = new ArrayList<>();
    private static Grid<Consulta> grid;
    private static Div hint;
    private ConsultaService consultaService;
    private AuthenticatedUser authenticatedUser;

    public AdminConsultas(ConsultaService consultaService, AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.consultaService = consultaService;
        this.setupGrid(authenticatedUser);

    }

    private void setupGrid(AuthenticatedUser authenticatedUser) {
        Optional<Usuario> usuario = authenticatedUser.get();
        grid = new Grid<>(Consulta.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(Consulta::getEmail).setHeader("Email");
        grid.addColumn(Consulta::getAsunto).setHeader("Asunto");
        grid.addColumn(Consulta::getMensaje).setHeader("Mensaje");
        grid.addColumn(Consulta::getEstado).setHeader("Estado");

        grid.addComponentColumn(qconsulta -> {
            Button boton = new Button("Quitar");
            boton.addClickListener(click -> {
                {
                    if (qconsulta.getEstado().equals("ATENDIDO") && usuario.get().getUsername().equals(qconsulta.getUsername())) {
                        qconsulta.BorrarUsuario();
                        qconsulta.setEstado("PENDIENTE");
                        consultaService.save(qconsulta);
                        Notification.show("Consulta desasignada");
                        grid.getDataProvider().refreshAll();
                    }
                }
            });
            return boton;
        }).setHeader("Quitar");


        grid.setItems(consultaService.findBy_estadoConsultaAndUsername("ATENDIDO", usuario.get().getUsername()));
        grid.getDataProvider().refreshAll();

        add(grid);
    }
}