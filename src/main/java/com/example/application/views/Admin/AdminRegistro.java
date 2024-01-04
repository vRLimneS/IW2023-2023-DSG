package com.example.application.views.Admin;

import com.example.application.data.TipoRol;
import com.example.application.data.Usuario;
import com.example.application.services.UsuarioService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.login.LoginBasic;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static com.example.application.data.TipoRol.CLIENTE;


@RolesAllowed({"CLIENTE", "ADMIN"})
@Route(value = "AdminRegistro", layout = LayoutPrincipal.class)
public class AdminRegistro extends VerticalLayout {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public AdminRegistro(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;

        TextField username = new TextField();
        username.setLabel("Username");

        TextField Nombre = new TextField();
        Nombre.setLabel("Nombre");

        TextField Apellido = new TextField();
        Apellido.setLabel("Apellido");

        TextField Correo = new TextField();
        Correo.setLabel("Correo Electronico");

        TextField Direccion = new TextField();
        Direccion.setLabel("Direccion");

        TextField Contrasena = new TextField();
        Contrasena.setLabel("Contraseña");

        TextField ConfirmarContrasena = new TextField();
        ConfirmarContrasena.setLabel("Confirmar Contraseña");

        DatePicker FechaNacimiento = new DatePicker();
        FechaNacimiento.setLabel("Fecha de Nacimiento");

        TextField DNI = new TextField();
        DNI.setLabel("DNI");

        ComboBox<TipoRol> rolComboBox = new ComboBox<>("Rol", Arrays.asList(TipoRol.values()));

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
        rolComboBox.getStyle().set("padding", "var(--lumo-space-s)");

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
        rolComboBox.setWidth("500px");

        setAlignItems(Alignment.CENTER);

        vl2.add(username, Nombre, Apellido, DNI, Correo, Contrasena, ConfirmarContrasena, Direccion, FechaNacimiento, rolComboBox);
        vl2.setAlignItems(Alignment.CENTER);
        vl2.setWidth("69%");
        add(vl2, boton);

        boton.addClickListener(event -> {
            if (Nombre.isEmpty() || Apellido.isEmpty() || DNI.isEmpty() || Correo.isEmpty() ||
                    Contrasena.isEmpty() || ConfirmarContrasena.isEmpty() || Direccion.isEmpty() || FechaNacimiento.isEmpty()) {
                Notification.show("Todos los campos son obligatorios");
            } else if (Contrasena.getValue().equals(ConfirmarContrasena.getValue())) {
                // Codificar la contraseña antes de guardarla
                //String contraseñaCodificada = passwordEncoder.encode(Contrasena.getValue());

                TipoRol rol = CLIENTE;
                usuarioService.registerUser(new Usuario(Nombre.getValue(),username.getValue(), Apellido.getValue(), Contrasena.getValue(),
                        rolComboBox.getValue(), DNI.getValue(), Correo.getValue(), Direccion.getValue(), FechaNacimiento.getValue(), true));
                Notification.show("Usuario registrado correctamente");

                UI.getCurrent().navigate(LoginBasic.class);
            } else {
                Notification.show("Las contraseñas no coinciden");
            }
        });

    }
}
