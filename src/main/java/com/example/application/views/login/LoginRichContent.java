package com.example.application.views.login;

import com.example.application.views.AppLayoutNavbar;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "login-rich-content", layout = AppLayoutNavbar.class)
public class LoginRichContent extends VerticalLayout {
    public LoginRichContent() {
        // See login-rich-content.css
        addClassName("login-rich-content");
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setOpened(true);
        loginOverlay.setTitle("Inicio de sesion");
        loginOverlay.setDescription("Datos de inicio");
        add(loginOverlay);

        //loginOverlay.getElement().getAttribute("Nombre")

    }

}