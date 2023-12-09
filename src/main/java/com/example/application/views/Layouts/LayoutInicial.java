package com.example.application.views.Layouts;

import com.example.application.views.DepATC.AtcclienteadminView;
import com.example.application.views.Marketing.CrearTarifas;
import com.example.application.views.Clientes.ServiciosView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
public class LayoutInicial extends AppLayout {


    public LayoutInicial() {
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


        RouterLink HomeCliente = new RouterLink("HomeClientes", ServiciosView.class);
        RouterLink HomeDepATC = new RouterLink("HomeDepATC", AtcclienteadminView.class);
        RouterLink HomeMarketing = new RouterLink("HomeMarketing", CrearTarifas.class);

        //Creamos la tabla asociada a esa ruta y la unimos a la tabla general del layout

        Tab TarifasTab = new Tab(HomeCliente);
        Tab DepATCTab = new Tab(HomeDepATC);
        Tab Marketingtab = new Tab(HomeMarketing);



        tabs.add(TarifasTab, DepATCTab, Marketingtab);

        return tabs;
    }

}
