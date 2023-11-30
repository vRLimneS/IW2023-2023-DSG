package com.example.application.views;

import com.example.application.views.Tarifas.PublicTarifasView;
import com.example.application.views.Tarifas.TarifasView;
import com.example.application.views.login.LoginBasic;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

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

        RouterLink TarifasLink = new RouterLink("Tarifas", PublicTarifasView.class);
        RouterLink loginlink = new RouterLink("Login", LoginBasic.class);

        //Creamos la tabla asociada a esa ruta y la unimos a la tabla general del layout

        Tab TarifasTab = new Tab(TarifasLink);
        Tab logintab = new Tab(loginlink);


        tabs.add(TarifasTab, logintab);

        return tabs;
    }

}
