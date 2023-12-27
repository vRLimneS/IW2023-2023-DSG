package com.example.application.views.Layouts;

import com.example.application.views.Clientes.ServiciosView;
import com.example.application.views.DepATC.AtcclienteadminView;
import com.example.application.views.LandingPage;
import com.example.application.views.Marketing.CrearTarifas;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
public class LayoutInicial extends AppLayout {

    public LayoutInicial() {
        H1 title = new H1("SAGOSL");
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


        RouterLink Inicio = new RouterLink("Inicio", LandingPage.class);
        RouterLink HomeCliente = new RouterLink("HomeClientes", ServiciosView.class);
        RouterLink HomeDepATC = new RouterLink("HomeDepATC", AtcclienteadminView.class);
        RouterLink HomeMarketing = new RouterLink("HomeMarketing", CrearTarifas.class);
        RouterLink AtcCliente = new RouterLink("AtcCliente", atcclientenoregistrados.class);
        RouterLink HomeAdmin = new RouterLink("HomeAdmin", CrearTarifas.class);

        //Creamos la tabla asociada a esa ruta y la unimos a la tabla general del layout

        Tab InicioTab = new Tab(Inicio);
        Tab TarifasTab = new Tab(HomeCliente);
        Tab DepATCTab = new Tab(HomeDepATC);
        Tab Marketingtab = new Tab(HomeMarketing);
        Tab AtcClientetab = new Tab(AtcCliente);
        Tab AdminTab = new Tab(HomeAdmin);


        tabs.add(InicioTab, TarifasTab, DepATCTab, Marketingtab, AtcClientetab, AdminTab);

        return tabs;
    }

}
