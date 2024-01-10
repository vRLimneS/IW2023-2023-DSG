package com.example.application.views.Comunes;

import com.example.application.data.TipoRol;
import com.example.application.data.Usuario;
import com.example.application.services.UsuarioService;
import com.example.application.views.Layouts.LayoutInicial;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.crypto.password.PasswordEncoder;

@AnonymousAllowed
@PageTitle("registro")
@Route(value = "registro", layout = LayoutInicial.class)
@Uses(Icon.class)
public class Registro extends VerticalLayout {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    PasswordField Contrasena = new PasswordField();
    PasswordField ConfirmarContrasena = new PasswordField();

    public Registro(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;

        TextField username = new TextField();
        username.setLabel("Username");

        TextField Nombre = new TextField();
        Nombre.setLabel("Nombre");

        TextField Apellido = new TextField();
        Apellido.setLabel("Apellido");

        EmailField Correo = new EmailField();
        Correo.setLabel("Correo Electronico");

        TextField Direccion = new TextField();
        Direccion.setLabel("Direccion");

        Contrasena.setLabel("Contraseña");

        ConfirmarContrasena.setLabel("Confirmar Contraseña");

        DatePicker FechaNacimiento = new DatePicker();
        FechaNacimiento.setLabel("Fecha de Nacimiento");

        TextField DNI = new TextField();
        DNI.setLabel("DNI");

        Button boton = new Button();
        boton.setText("Registrarse");
        boton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout vl2 = new VerticalLayout();
        vl2.add(boton);

        username.getStyle().set("margin", "var(--lumo-space-s)");
        Nombre.getStyle().set("padding", "var(--lumo-space-s)");
        Apellido.getStyle().set("padding", "var(--lumo-space-s)");
        Correo.getStyle().set("padding", "var(--lumo-space-s)");
        Direccion.getStyle().set("padding", "var(--lumo-space-s)");
        Contrasena.getStyle().set("padding", "var(--lumo-space-s)");
        ConfirmarContrasena.getStyle().set("padding", "var(--lumo-space-s)");
        FechaNacimiento.getStyle().set("padding", "var(--lumo-space-s)");
        DNI.getStyle().set("padding", "var(--lumo-space-s)");

        username.setWidth("500px");
        Nombre.setWidth("500px");
        Apellido.setWidth("500px");
        Correo.setWidth("500px");
        Direccion.setWidth("500px");
        Contrasena.setWidth("500px");
        ConfirmarContrasena.setWidth("500px");
        FechaNacimiento.setWidth("500px");
        DNI.setWidth("500px");
        boton.setWidth("500px");

        setAlignItems(Alignment.CENTER);

        vl2.add(username, Nombre, Apellido, DNI, Correo, Contrasena, ConfirmarContrasena, Direccion, FechaNacimiento);
        vl2.setAlignItems(Alignment.CENTER);
        vl2.setWidth("69%");
        add(vl2, boton);

        boton.addClickListener(event -> {
            if (Nombre.isEmpty() || Apellido.isEmpty() || DNI.isEmpty() || Correo.isEmpty() ||
                    Contrasena.isEmpty() || ConfirmarContrasena.isEmpty() || Direccion.isEmpty() || FechaNacimiento.isEmpty()) {
                Notification.show("Todos los campos son obligatorios");
            } else if (Contrasena.getValue().equals(ConfirmarContrasena.getValue())) {
                if (usuarioService.existsByUsername(username.getValue())) {
                    Notification.show("El nombre de usuario ya existe.\n Por favor, elija otro nombre de usuario").setPosition(Notification.Position.MIDDLE);
                } else {
                    TipoRol rol = TipoRol.CLIENTE;
                    Usuario usuario = new Usuario(Nombre.getValue(), username.getValue(), Apellido.getValue(), Contrasena.getValue(),
                            rol, DNI.getValue(), Correo.getValue(), Direccion.getValue(), FechaNacimiento.getValue(), false);
                    usuarioService.registerUser(usuario);
                    Notification.show("Usuario registrado correctamente");

                    UI.getCurrent().navigate(useractivation.class, String.valueOf(usuario.getId()));
                }
            } else {
                Notification.show("Las contraseñas no coinciden");
            }
        });
    }
}
