package com.example.application.views.Admin;

import com.example.application.data.TipoRol;
import com.example.application.data.Usuario;
import com.example.application.services.UsuarioService;
import com.example.application.views.Layouts.LayoutPrincipal;
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

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

@RolesAllowed("ADMIN")
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

        vl2.add(username, Nombre, Apellido, DNI, Correo, Contrasena, ConfirmarContrasena, Direccion, FechaNacimiento,
                rolComboBox);
        vl2.setAlignItems(Alignment.CENTER);
        vl2.setWidth("69%");
        add(vl2, boton);

        boton.addClickListener(event -> {
            if (Nombre.isEmpty() || Apellido.isEmpty() || DNI.isEmpty() || Correo.isEmpty() ||
                    Contrasena.isEmpty() || ConfirmarContrasena.isEmpty() || Direccion.isEmpty() || FechaNacimiento.isEmpty()) {
                Notification.show("Todos los campos son obligatorios");
            } else if (Contrasena.getValue().equals(ConfirmarContrasena.getValue()) && Contrasena.getValue().length() >= 8) {
                if (usuarioService.existsByUsername(username.getValue())) {
                    Notification.show("El nombre de usuario ya existe.\n Por favor, elija otro nombre de usuario").setPosition(Notification.Position.MIDDLE);
                } else if (usuarioService.existsByDNI(DNI.getValue())) {
                    Notification.show("El DNI ya existe.\n Por favor, elija otro DNI").setPosition(Notification.Position.MIDDLE);
                } else if (usuarioService.existsByEmail(Correo.getValue())) {
                    Notification.show("El Correo ya existe.\n Por favor, elija otro Correo").setPosition(Notification.Position.MIDDLE);
                } else if (!validarDNI(DNI.getValue())) {
                    Notification.show("El DNI no es válido").setPosition(Notification.Position.MIDDLE);
                } else if (!esMayorDeEdad(FechaNacimiento.getValue())) {
                    Notification.show("Debe ser mayor de edad para registrarse").setPosition(Notification.Position.MIDDLE);
                } else {
                    try {
                        System.out.println("Antes de registrar el usuario");
                        usuarioService.registerUser(new Usuario(Nombre.getValue(), username.getValue(), Apellido.getValue(), Contrasena.getValue(),
                                rolComboBox.getValue(), DNI.getValue(), Correo.getValue(), Direccion.getValue(), FechaNacimiento.getValue(), true));
                        System.out.println("Después de registrar el usuario");

                        Notification.show("Usuario registrado correctamente");
                        UI.getCurrent().navigate(AdminUsuariosView.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Notification.show("Error al registrar el usuario").setPosition(Notification.Position.MIDDLE);
                    }
                }
            } else {
                Notification.show("Las contraseñas no coinciden");
            }
        });
    }

    private boolean validarDNI(String dni) {
        if (dni == null || !dni.matches("\\d{8}[0-9A-Za-z]")) {
            return false;
        }

        char letraCalculada = calcularLetraDNI(dni.substring(0, 8));
        char letraDNI = dni.charAt(8);

        return letraCalculada == letraDNI;
    }

    private char calcularLetraDNI(String numerosDNI) {
        String caracteres = "TRWAGMYFPDXBNJZSQVHLCKE";
        int modulo = Integer.parseInt(numerosDNI) % caracteres.length();
        return caracteres.charAt(modulo);
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        Period period = Period.between(fechaNacimiento, fechaActual);
        int edad = period.getYears();
        return edad >= 18;
    }
}