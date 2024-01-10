package com.example.application.views.Admin;

import com.example.application.data.TipoRol;
import com.example.application.data.Usuario;
import com.example.application.services.UsuarioService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.Optional;

@RolesAllowed("ADMIN")
@Route(value = "AdminUsuarios", layout = LayoutPrincipal.class)
public class AdminUsuariosView extends Div {

    private final UsuarioService usuarioService;
    private final AuthenticatedUser authenticatedUser;
    private Grid<Usuario> grid;

    public AdminUsuariosView(UsuarioService usuarioService, AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.usuarioService = usuarioService;

        Button botonCrear = new Button("Registrar Usuario");
        botonCrear.addClickListener(click -> {
            UI.getCurrent().navigate(AdminRegistro.class);
        });

        add(botonCrear);

        setupGrid(authenticatedUser);
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

        // Columna para seleccionar el nuevo rol
        grid.addComponentColumn(usuarioItem -> {
            ComboBox<TipoRol> rolComboBox = new ComboBox<>();
            rolComboBox.setItems(TipoRol.values());
            rolComboBox.setValue(usuarioItem.getRol());

            rolComboBox.addValueChangeListener(event -> {
                TipoRol nuevoRol = event.getValue();
                if (nuevoRol != null) {
                    usuarioItem.setRol(nuevoRol);
                    usuarioService.save(usuarioItem);
                    Notification.show("Rol actualizado correctamente");
                    grid.getDataProvider().refreshItem(usuarioItem);
                }
            });

            return rolComboBox;
        }).setHeader("Nuevo Rol");

        grid.addComponentColumn(quser -> {
            Button botonEliminar = new Button("Eliminar Usuario");
            botonEliminar.addClickListener(click -> {
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
            return botonEliminar;
        }).setHeader("Eliminar Usuario");

        grid.setItems(usuarioService.findAll());
        grid.getDataProvider().refreshAll();

        add(grid);
    }
}
