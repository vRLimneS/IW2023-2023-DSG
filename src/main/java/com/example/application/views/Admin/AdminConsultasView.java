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
@Route(value = "AdminATC", layout = LayoutPrincipal.class)
public class AdminConsultasView extends Div {

    private static final List<Consulta> consultas = new ArrayList<>();

    private static Grid<Consulta> grid;
    private static Div hint;
    private final ConsultaService consultaService;

    private final AuthenticatedUser authenticatedUser;

    public AdminConsultasView(ConsultaService consultaService, AuthenticatedUser authenticatedUser) {
        this.consultaService = consultaService;
        this.authenticatedUser = authenticatedUser;
        this.setupGrid(authenticatedUser);

    }

    private void setupGrid(AuthenticatedUser authenticatedUser) {
        // tag::snippet[]
        grid = new Grid<>(Consulta.class, false);
        grid.setAllRowsVisible(true);
        // end::snippet[]
        grid.addColumn(Consulta::getEmail).setHeader("Email");
        grid.addColumn(Consulta::getAsunto).setHeader("Asunto");
        grid.addColumn(Consulta::getMensaje).setHeader("Mensaje");
        grid.addColumn(Consulta::getEstado).setHeader("Estado");
        grid.addColumn(Consulta::getUsernameCliente).setHeader("Cliente");
        grid.addColumn(Consulta::getUsername).setHeader("Usuario");
        grid.addComponentColumn(consulta -> {
            Button button = new Button("Asignar");
            button.addClickListener(click -> {
                if (consulta.getEstado().equals("RESUELTO")) {
                    Notification.show("Consulta ya Resuelta");
                } else if (consulta.getEstado().equals("ATENDIDO")) {
                    Notification.show("Consulta ya Atendida");
                } else {
                    consulta.setUsuario(authenticatedUser.get());
                    Optional<Usuario> user = authenticatedUser.get();
                    Notification.show("Consulta asignada");
                    consulta.setEstado("ATENDIDO");
                    consulta.setUsername(user.get().getUsername());
                    consultaService.save(consulta);
                    grid.setItems(consultaService.findAll());
                    grid.getDataProvider().refreshAll();
                }
            });
            return button;
        }).setHeader("Asignar");

        grid.addComponentColumn(consulta -> {
            Button button = new Button("Quitar");
            button.addClickListener(click -> {
                if (consulta.getEstado().equals("ATENDIDO")) {
                    consulta.BorrarUsuario();
                    consulta.setEstado("PENDIENTE");
                    consultaService.save(consulta);
                    Notification.show("Consulta desasignada");
                    grid.getDataProvider().refreshAll();
                }
            });
            return button;
        }).setHeader("Quitar");


        grid.setItems(consultaService.findAll());

        add(grid);
    }
}
