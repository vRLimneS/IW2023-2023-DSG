package com.example.application.views.Comunes;

import com.example.application.data.Contrato;
import com.example.application.data.Usuario;
import com.example.application.services.ContratoService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RolesAllowed({"CLIENTE", "ATCCLT", "ADMIN", "MARKETING"})
@Route(value = "ContratoIndiviualVista", layout = LayoutPrincipal.class)
public class ContratoIndiviualVista extends VerticalLayout implements HasUrlParameter<String> {

    private final AuthenticatedUser authenticatedUser;

    private final ContratoService contratoService;

    public ContratoIndiviualVista(ContratoService contratoService, AuthenticatedUser authenticatedUser) {
        this.contratoService = contratoService;
        this.authenticatedUser = authenticatedUser;
        add(new H1("Contrato Individual"));

    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        Optional<Usuario> user = authenticatedUser.get();
        List<Contrato> contrato = contratoService.findByUsuarioId(user.get().getId());
        H1 titulo = new H1("Tarifas Contratadas");
        Optional<Contrato> c = contratoService.findById(UUID.fromString(parameter));
        add(titulo);

    }

}

