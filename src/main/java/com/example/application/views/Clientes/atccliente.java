package com.example.application.views.Clientes;

import com.example.application.data.Consulta;
import com.example.application.data.Usuario;
import com.example.application.services.ConsultaService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.Optional;

@RolesAllowed("CLIENTE")
@PageTitle("AtcCliente")
@Route(value = "AtcCliente", layout = LayoutPrincipal.class)
@Uses(Icon.class)
public class atccliente extends Div {

    private final ConsultaService consultaService;
    private final AuthenticatedUser authenticatedUser;

    public atccliente(ConsultaService consultaService, AuthenticatedUser authenticatedUser) {

        this.authenticatedUser = authenticatedUser;
        this.consultaService = consultaService;

        EmailField email = new EmailField();
        VerticalLayout vl = new VerticalLayout();
        email.setLabel("Introduzca su Correo Electrónico");
        email.setPlaceholder("Introduzca tu email con el dominio correspondiente");
        email.setTooltipText("Tooltip text");
        email.setClearButtonVisible(true);
        email.setPrefixComponent(VaadinIcon.ENVELOPE.create());
        email.setWidth("600px");
        vl.setHeight("calc(100vh - 32px)");
        vl.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        TextArea mensaje = new TextArea();
        mensaje.setLabel("Introduzca su Consulta");
        mensaje.setPlaceholder("Consulta");
        mensaje.setHeight("300px");
        mensaje.setWidth("600px");


        TextArea asunto = new TextArea();
        asunto.setLabel("Introduzca el Asunto");
        asunto.setPlaceholder("Asunto");
        asunto.setHeight("80px");
        asunto.setWidth("600px");


        Button buton = new Button("Enviar");
        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setAlignSelf(FlexComponent.Alignment.END, buton);
        hl2.add(mensaje, buton);
        asunto.getStyle().set("padding", "var(--lumo-space-s)");
        email.getStyle().set("padding", "var(--lumo-space-s)");
        mensaje.getStyle().set("padding", "var(--lumo-space-s)");
        vl.getStyle().set("padding", "var(--lumo-space-s)");
        vl.add(email, asunto, hl2);


        add(vl);
        this.setupGrid(authenticatedUser);


        buton.addClickListener(e -> {
            if (email.getValue().isEmpty() || mensaje.getValue().isEmpty() || asunto.getValue().isEmpty()) {
                Notification.show("Rellene todos los campos");
            } else {
                if (authenticatedUser.get().isPresent()) {
                    consultaService.save(new Consulta(email.getValue(), asunto.getValue(), mensaje.getValue(), authenticatedUser.get().get(), authenticatedUser.get().get().getUsername()));
                    Notification.show("Consulta enviada");
                    email.clear();
                    mensaje.clear();
                    asunto.clear();

                } else {
                    consultaService.save(new Consulta(email.getValue(), asunto.getValue(), mensaje.getValue()));
                    Notification.show("Consulta enviada");
                    email.clear();
                    mensaje.clear();
                    asunto.clear();
                }
            }
        });


    }

    public void setupGrid(AuthenticatedUser authenticatedUser) {
        Optional<Usuario> usuario = authenticatedUser.get();
        Grid<Consulta> grid = new Grid<>(Consulta.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(Consulta::getEmail).setHeader("Email");
        grid.addColumn(Consulta::getAsunto).setHeader("Asunto");
        grid.addColumn(Consulta::getMensaje).setHeader("Mensaje");
        grid.addColumn(Consulta::getEstado).setHeader("Estado");
        grid.addColumn(Consulta::getUsername).setHeader("Usuario");

        for (Consulta c : consultaService.findByCliente(usuario.get())) {
            grid.setItems(consultaService.findByCliente(usuario.get()));
            grid.getDataProvider().refreshAll();
        }

        add(grid);
    }

}