package com.example.application.views;

import com.example.application.views.Servicios.ServiciosView;
import com.example.application.views.atcCliente.atccliente;
import com.example.application.views.contratar.ContratarView;
import com.example.application.views.login.LoginRichContent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class AppLayoutNavbar extends AppLayout {


    public AppLayoutNavbar() {
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
        RouterLink contratarLink = new RouterLink("Contratar", ContratarView.class);
        RouterLink AtcClienteLink = new RouterLink("Atc. Cliente", atccliente.class);
        RouterLink loginlink = new RouterLink("Login", LoginRichContent.class);

        //Creamos la tabla asociada a esa ruta y la unimos a la tabla general del layout

        Tab serviciosTab = new Tab(serviciosLink);
        Tab contratarTab = new Tab(contratarLink);
        Tab AtcClienteTab = new Tab(AtcClienteLink);
        Tab logintab = new Tab(loginlink);


        tabs.add(serviciosTab, contratarTab, AtcClienteTab, logintab);

        return tabs;
    }

}