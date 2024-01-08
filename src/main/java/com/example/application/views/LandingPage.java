package com.example.application.views;

import com.example.application.views.Clientes.PublicTarifasView;
import com.example.application.views.Layouts.LayoutInicial;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "", layout = LayoutInicial.class)
@AnonymousAllowed
public class LandingPage extends VerticalLayout {

    public LandingPage() {
        //Pagina de inicio del trabajo
        H1 titulo = new H1("Bienvenido a la pagina de inicio");
        H2 subtitulo = new H2("Seleccione una opcion");
        //login
        Button login = new Button("Iniciar Sesion");
        login.addClickListener(e -> login.getUI().ifPresent(ui -> ui.navigate("login")));
        //registro
        Button registro = new Button("Registrarse");
        registro.addClickListener(e -> registro.getUI().ifPresent(ui -> ui.navigate("registro")));
        //tarifas
        Button tarifas = new Button("Tarifas");
        tarifas.addClickListener(e -> tarifas.getUI().ifPresent(ui -> ui.navigate(PublicTarifasView.class)));
        //añadir componentes
        add(titulo, subtitulo, login, registro, tarifas);

    }
}
