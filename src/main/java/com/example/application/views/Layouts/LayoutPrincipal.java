package com.example.application.views.Layouts;

import com.example.application.data.TipoRol;
import com.example.application.data.Usuario;
import com.example.application.views.Admin.AdminTarifas;
import com.example.application.views.Clientes.PublicTarifasView;
import com.example.application.views.Clientes.atccliente;
import com.example.application.views.Clientes.inicio;
import com.example.application.views.Comunes.Perfil;
import com.example.application.views.Comunes.ServiciosView;
import com.example.application.views.DepATC.AtcclienteadminView;
import com.example.application.views.DepATC.MisConsultas;
import com.example.application.views.LandingPage;
import com.example.application.views.Marketing.CrearTarifas;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;

import java.util.Optional;

@RolesAllowed("CLIENTE, ATCCLT, MARKETING")
public class LayoutPrincipal extends AppLayout {

    private final AuthenticatedUser authenticatedUser;


    public LayoutPrincipal(AuthenticatedUser authenticatedUser) {
        H1 title = new H1("SAGOSL");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)").set("margin", "0")
                .set("position", "absolute");

        this.authenticatedUser = authenticatedUser;

        Tabs tabs = getTabs(authenticatedUser);

        addToNavbar(title, tabs, createFooter());
    }


    private Tabs getTabs(AuthenticatedUser authenticatedUser) {
        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");


        authenticatedUser.get().ifPresent(user -> {
            if (user.getRol().equals(TipoRol.CLIENTE)) {
                tabs.add(new Tab(new RouterLink("Inicio", inicio.class)));
                tabs.add(new Tab(new RouterLink("Tarifas", PublicTarifasView.class)));
                tabs.add(new Tab(new RouterLink("Servicios", ServiciosView.class)));
                tabs.add(new Tab(new RouterLink("Atc. Cliente", atccliente.class)));
            } else if (user.getRol().equals(TipoRol.ATCCLT)) {
                tabs.add(new Tab(new RouterLink("Inicio", inicio.class)));
                tabs.add(new Tab(new RouterLink("Tarifas", PublicTarifasView.class)));
                tabs.add(new Tab(new RouterLink("Consultas", AtcclienteadminView.class)));
                tabs.add(new Tab(new RouterLink("Mis Consultas", MisConsultas.class)));
                tabs.add(new Tab(new RouterLink("Servicios", ServiciosView.class)));

            } else if (user.getRol().equals(TipoRol.MARKETING)) {
                tabs.add(new Tab(new RouterLink("Inicio", inicio.class)));
                tabs.add(new Tab(new RouterLink("Crear Tarifas", CrearTarifas.class)));
                tabs.add(new Tab(new RouterLink("TarifasCliente", PublicTarifasView.class)));
                tabs.add(new Tab(new RouterLink("Tarifas", AdminTarifas.class)));
                tabs.add(new Tab(new RouterLink("Servicios", ServiciosView.class)));
            }
            if (user.getRol().equals(TipoRol.ADMIN)) {
                tabs.add(new Tab(new RouterLink("Crear Tarifas", CrearTarifas.class)));
                tabs.add(new Tab(new RouterLink("TarifasCliente", PublicTarifasView.class)));
                tabs.add(new Tab(new RouterLink("Consultas", AtcclienteadminView.class)));
                tabs.add(new Tab(new RouterLink("Servicios", ServiciosView.class)));
                tabs.add(new Tab(new RouterLink("TarifasMarketing", AdminTarifas.class)));
                tabs.add(new Tab(new RouterLink("Atc. Cliente", atccliente.class)));
            }
        });
        return tabs;
    }


    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<Usuario> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            Usuario user = maybeUser.get();


            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            Avatar avatar = new Avatar();
            avatar.setAbbreviation(user.getUsername().substring(0, 2));
            div.add(avatar);
            div.add(user.getUsername());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Perfil", e -> {
                UI.getCurrent().navigate(Perfil.class);
            });
            userName.getSubMenu().addItem("Cerrar sesión", e -> {
                authenticatedUser.logout();
                UI.getCurrent().getPage().reload();
                UI.getCurrent().navigate(LandingPage.class);

            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }
        return layout;
    }


}
