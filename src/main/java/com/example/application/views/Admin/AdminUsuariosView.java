package com.example.application.views.Admin;

import com.example.application.data.Usuario;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.example.application.services.UsuarioService;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RolesAllowed({"CLIENTE", "ADMIN"})
@Route(value = "AdminUsuarios", layout = LayoutPrincipal.class)
public class AdminUsuariosView extends Div {

    private static final List<Usuario> usuarios = new ArrayList<>();
    private static Grid<Usuario> grid;
    private static Div hint;
    private UsuarioService usuarioService;
    private AuthenticatedUser authenticatedUser;

    public AdminUsuariosView(UsuarioService usuarioService, AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.usuarioService = usuarioService;

        Button BotonCrear = new Button();
        BotonCrear.setText("Registrar Usuario");

        add(BotonCrear);

        BotonCrear.addClickListener(click -> {
            UI navigate = UI.getCurrent();
            navigate.navigate(AdminRegistro.class);
        });

        this.setupGrid(authenticatedUser);
    }

    private void setupGrid(AuthenticatedUser authenticatedUser) {
        Optional<Usuario> usuario = authenticatedUser.get();
        grid = new Grid<>(Usuario.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(Usuario::getUsername).setHeader("Username");
        grid.addColumn(Usuario::getNombre).setHeader("Nombre");
        grid.addColumn(Usuario::getApellidos).setHeader("Apellidos");
        grid.addColumn(Usuario::getRol).setHeader("Rol");
        grid.addColumn(Usuario::getDNI).setHeader("DNI");

        grid.addComponentColumn(quser -> {
            Button boton = new Button("Eliminar Usuario");
            boton.addClickListener(click -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Confirmar Eliminación");
                dialog.setText("¿Está seguro de que desea eliminar este usuario?");

                dialog.setRejectable(true);
                dialog.setRejectText("Cancelar");
                dialog.addRejectListener(event -> {
                    Notification.show("Cancelado");
                });

                dialog.setConfirmText("Eliminar");
                dialog.addConfirmListener(event -> {
                    usuarioService.delete(quser);
                    grid.setItems(usuarioService.findAll());
                    grid.getDataProvider().refreshAll();
                    Notification.show("Usuario eliminado correctamente");
                });

                dialog.open();
            });
            return boton;
        }).setHeader("Eliminar Usuario");


        grid.setItems(usuarioService.findAll());
        grid.getDataProvider().refreshAll();

        add(grid);
    }
}
