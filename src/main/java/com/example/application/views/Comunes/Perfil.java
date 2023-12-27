package com.example.application.views.Comunes;

import com.example.application.data.Usuario;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.Optional;

@RolesAllowed({"CLIENTE", "ATCCLT", "ADMIN", "MARKETING"})
@Route(value = "Perfil", layout = LayoutPrincipal.class)
public class Perfil extends VerticalLayout {

    private final AuthenticatedUser authenticatedUser;


    public Perfil(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        Optional<Usuario> user = authenticatedUser.get();
        Usuario usuario = user.get();

        TextField Nombre = new TextField("Nombre");
        TextField Apellido = new TextField("Apellidos");
        TextField username = new TextField("Nombre de Usuario");
        TextField DNI = new TextField("DNI");
        TextField Email = new TextField("Email");
        TextField fechaNacimiento = new TextField("Fecha de Nacimiento");
        TextField direccion = new TextField("Direccion");


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


        add(Nombre, Apellido, username, DNI, Email, fechaNacimiento, direccion);

    }
}
