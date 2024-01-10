package com.example.application.views.Comunes;

import com.example.application.data.Usuario;
import com.example.application.services.UsuarioService;
import com.example.application.views.login.LoginBasic;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.Optional;
import java.util.UUID;

@AnonymousAllowed
@Route(value = "useractivation")
public class useractivation extends VerticalLayout implements HasUrlParameter<String> {

    private final UsuarioService usuarioService;

    public useractivation(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;


    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        Optional<Usuario> usuario = usuarioService.findById(UUID.fromString(parameter));
        Usuario user = usuario.get();

        Dialog dialog = new Dialog();

        dialog.open();

        TextField token = new TextField("token");

        Button activar = new Button("Activar");
        activar.addClickListener(e -> {
            if (token.getValue().equals(user.gettoken())) {
                user.setActive(true);
                usuarioService.save(user);
                Notification.show("Usuario activado", 300, Notification.Position.MIDDLE);
                UI.getCurrent().navigate(LoginBasic.class);
            } else {
                Notification.show("Token incorrecto", 300, Notification.Position.MIDDLE);
                token.clear();

            }
        });

        dialog.add(token, activar);

        add(dialog);

    }

}
