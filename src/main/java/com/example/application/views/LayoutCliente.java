package com.example.application.views;

import com.example.application.views.Servicios.ServiciosView;
import com.example.application.views.Clientes.atccliente;
import com.example.application.views.Clientes.PublicTarifasView;
import com.example.application.views.login.LoginBasic;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class LayoutCliente extends AppLayout {


    public LayoutCliente() {
        H1 title = new H1("Inicio");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)").set("margin", "0")
                .set("position", "absolute");


        Tabs tabs = getTabs();

        addToNavbar(title, tabs);
    }


    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");


        //Creamos los routerlinks de las pestañas posibles conectados a las clases

        RouterLink serviciosLink = new RouterLink("Servicios", ServiciosView.class);
        RouterLink TarifasLink = new RouterLink("Tarifas", PublicTarifasView.class);
        RouterLink AtcClienteLink = new RouterLink("Atc. Cliente", atccliente.class);
        RouterLink loginlink = new RouterLink("Login", LoginBasic.class);

        //Creamos la tabla asociada a esa ruta y la unimos a la tabla general del layout

        Tab serviciosTab = new Tab(serviciosLink);
        Tab TarifasTab = new Tab(TarifasLink);
        Tab AtcClienteTab = new Tab(AtcClienteLink);
        Tab logintab = new Tab(loginlink);


        tabs.add(serviciosTab, TarifasTab, AtcClienteTab, logintab);

        return tabs;
    }

}
