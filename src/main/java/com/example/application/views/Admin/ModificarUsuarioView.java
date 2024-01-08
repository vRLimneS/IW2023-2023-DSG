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
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@RolesAllowed("ADMIN")
@Route(value = "ModificarUsuario", layout = LayoutPrincipal.class)
public class ModificarUsuarioView extends HorizontalLayout implements HasUrlParameter<String> {

    private UsuarioService usuarioService;
    private AuthenticatedUser authenticatedUser;

    TextField Username = new TextField("Username");
    TextField Nombre = new TextField("Nombre");
    TextField Apellidos = new TextField("Apellidos");
    TextField Password = new TextField("Password");
    TextField Dni = new TextField("Dni");
    ComboBox<TipoRol> rolComboBox = new ComboBox<>("Rol", Arrays.asList(TipoRol.values()));
    TextField Email = new TextField("Email");
    DatePicker FechaNacimiento = new DatePicker();
    TextField Direccion = new TextField("Direccion");
    TextField Estado = new TextField("Estado (false no se muestra, true se muestra)");

    private String nobUsuario;

    public ModificarUsuarioView(UsuarioService usuarioService, AuthenticatedUser authenticatedUser) {
        this.usuarioService = usuarioService;
        this.authenticatedUser = authenticatedUser;
        Optional<Usuario> user = authenticatedUser.get();
        UUID id = user.get().getId();

        VerticalLayout vl = new VerticalLayout();
        VerticalLayout vl2 = new VerticalLayout();
        VerticalLayout vl3 = new VerticalLayout();

        Button boton = new Button("Modificar");

        boton.getStyle().set("margin", "auto");

        boton.addClickListener(click -> {
            ConfirmDialog dialog = new ConfirmDialog();
            dialog.setHeader("Confirmar Modificacion");
            dialog.setText("¿Está seguro?");

            dialog.setCancelable(true);
            dialog.addCancelListener(event -> Notification.show("Cancelado"));

            dialog.setRejectable(true);
            dialog.setRejectText("Descartar");
            dialog.addRejectListener(event -> {
                Notification.show("Descartar");
                UI.getCurrent().navigate(AdminUsuariosView.class);
            });

            dialog.setConfirmText("Guardar");
            dialog.addConfirmListener(event -> {
                if (Nombre.getValue().isEmpty() && Username.getValue().isEmpty()) {
                    Notification.show("Rellene todos los campos");
                } else {
                    try {
                        Usuario usuario = new Usuario(Nombre.getValue(), Username.getValue(),
                                Apellidos.getValue(), Password.getValue(), rolComboBox.getValue(),
                                Dni.getValue(), Email.getValue(), Direccion.getValue(),
                                FechaNacimiento.getValue(), Boolean.parseBoolean(Estado.getValue()));

                        Optional<Usuario> existingUsuarioOptional = usuarioService.findById(usuario.getId());
                        if (existingUsuarioOptional.isPresent()) {
                            Usuario existingUsuario = existingUsuarioOptional.get();
                            existingUsuario.setNombre(usuario.getNombre());
                            existingUsuario.setUsername(usuario.getUsername());
                            existingUsuario.setApellidos(usuario.getApellidos());
                            existingUsuario.setContraseña(usuario.getContraseña());
                            existingUsuario.setDNI(usuario.getDNI());
                            existingUsuario.setRol(usuario.getRol());
                            existingUsuario.setEmail(usuario.getEmail());
                            existingUsuario.setDireccion(usuario.getDireccion());
                            existingUsuario.setFechaNacimiento(usuario.getFechaNacimiento());
                            existingUsuario.setActive(usuario.isActive());

                            usuarioService.update(existingUsuario);
                            Notification.show("Usuario modificado correctamente");
                        } else {
                            Notification.show("No se encontró el usuario a modificar");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Notification.show("Error al intentar modificar el usuario: " + ex.getMessage(), 5000, Notification.Position.MIDDLE);
                    }
                    UI.getCurrent().navigate(AdminUsuariosView.class);
                }
            });

            dialog.open();
        });

        vl.setSpacing(false);
        vl2.setSpacing(false);

        vl.add(Username, Nombre, Apellidos, Password);
        vl2.add(Dni, rolComboBox, Email, Direccion, Estado);
        vl3.add(boton);

        vl2.setAlignItems(Alignment.CENTER);

        add(vl, vl2, vl3);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        nobUsuario = s;
        authenticatedUser.get().ifPresent(user -> {
            Optional<Usuario> usuarioOptional = usuarioService.findByNombreOptional(nobUsuario);

            usuarioOptional.ifPresent(usuario -> {
                System.out.println("Usuario encontrado: " + usuario);
                System.out.println("nobUsuario: " + nobUsuario);

                // Resto del código...
                Username.setValue(usuario.getUsername());
                Nombre.setValue(usuario.getNombre());
                Apellidos.setValue(usuario.getApellidos());
                Password.setValue(usuario.getContraseña());
                Dni.setValue(usuario.getDNI());
                rolComboBox.setValue(TipoRol.valueOf(usuario.getRol().name()));
                Email.setValue(usuario.getEmail());
                Direccion.setValue(usuario.getDireccion());
                Estado.setValue(String.valueOf(usuario.isActive()));

                // Resto del código...
            });

            usuarioOptional.orElseGet(() -> {
                System.out.println("No se encontró el usuario con el nombre especificado: " + nobUsuario);
                Notification.show("No se encontró el usuario con el nombre especificado", 3000, Notification.Position.MIDDLE);

                UI.getCurrent().navigate(AdminUsuariosView.class);
                return null;
            });
        });
    }

}
