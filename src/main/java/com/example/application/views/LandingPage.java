package com.example.application.views;

import com.example.application.views.Layouts.LayoutInicial;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "", layout = LayoutInicial.class)
@AnonymousAllowed

public class LandingPage extends VerticalLayout {

    public LandingPage() {
        add(new H1("Landing Page"));
    }
}
