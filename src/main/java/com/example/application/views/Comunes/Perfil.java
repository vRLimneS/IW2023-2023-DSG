package com.example.application.views.Comunes;

import com.example.application.data.Usuario;
import com.example.application.services.UsuarioService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.time.LocalDate;
import java.util.Optional;

@RolesAllowed({"CLIENTE", "ATCCLT", "ADMIN", "MARKETING"})
@Route(value = "Perfil", layout = LayoutPrincipal.class)
public class Perfil extends VerticalLayout {

    private final AuthenticatedUser authenticatedUser;

    private final UsuarioService usuarioService;


    public Perfil(AuthenticatedUser authenticatedUser, UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.authenticatedUser = authenticatedUser;
        Optional<Usuario> user = authenticatedUser.get();
        if (user.isPresent()) {
            Usuario usuario = user.get();


            TextField Nombre = new TextField("Nombre");
            TextField Apellido = new TextField("Apellidos");
            TextField username = new TextField("Nombre de Usuario");
            TextField DNI = new TextField("DNI");
            TextField Email = new TextField("Email");
            TextField fechaNacimiento = new TextField("Fecha de Nacimiento");
            TextField direccion = new TextField("Direccion");
            TextField mensaje = new TextField("Mensaje");
            mensaje.setValue("Se cerrara la sesión al cambio de crendenciales para su seguridad, vuelva a iniciarla al finalizar");
            mensaje.setWidthFull();
            mensaje.setReadOnly(true);

            Nombre.setValue(usuario.getNombre());
            Apellido.setValue(usuario.getApellidos());
            username.setValue(usuario.getUsername());
            DNI.setValue(usuario.getDNI());
            Email.setValue(usuario.getEmail());
            fechaNacimiento.setValue(usuario.getFechaNacimiento().toString());
            direccion.setValue(usuario.getDireccion());

            Nombre.setReadOnly(true);
            Apellido.setReadOnly(true);
            username.setReadOnly(true);
            DNI.setReadOnly(true);
            Email.setReadOnly(true);
            fechaNacimiento.setReadOnly(true);
            direccion.setReadOnly(true);

            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.add(Nombre, Apellido, username, DNI, Email, fechaNacimiento, direccion);

            Button editar = new Button("Editar");
            editar.addClickListener(e -> {
                Button modificar = new Button("Modificar");
                Nombre.setReadOnly(false);
                Apellido.setReadOnly(false);
                username.setReadOnly(false);
                DNI.setReadOnly(true);
                Email.setReadOnly(false);
                fechaNacimiento.setReadOnly(false);
                direccion.setReadOnly(false);
                removeAll();
                add(horizontalLayout, modificar, mensaje);
                setAlignSelf(Alignment.CENTER, modificar);
                setAlignSelf(Alignment.CENTER, mensaje);
                modificar.addClickListener(a -> {
                    if (usuarioService.existsByUsername(username.getValue()) && !username.getValue().equals(usuario.getUsername())) {
                        Notification.show("El nombre de usuario ya existe.\n Por favor, elija otro nombre de usuario").setPosition(Notification.Position.MIDDLE);
                        UI.getCurrent().getPage().reload();
                    } else if (usuarioService.existsByEmail(Email.getValue()) && !Email.getValue().equals(usuario.getEmail())) {
                        Notification.show("El email ya existe.\n Por favor, elija otro email").setPosition(Notification.Position.MIDDLE);
                        UI.getCurrent().getPage().reload();
                    } else {
                        usuario.setNombre(Nombre.getValue());
                        usuario.setApellidos(Apellido.getValue());
                        usuario.setUsername(username.getValue());
                        usuario.setEmail(Email.getValue());
                        usuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento.getValue()));
                        usuario.setDireccion(direccion.getValue());
                        usuarioService.update(usuario);
                        UI.getCurrent().getPage().reload();
                        authenticatedUser.logout();
                    }
                });
            });

            add(horizontalLayout, editar);
            setAlignSelf(Alignment.CENTER, editar);

        }

    }
}
