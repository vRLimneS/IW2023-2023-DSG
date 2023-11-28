package com.example.application.views.Registro;

import com.example.application.views.AppLayoutNavbar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("registro")
@Route(value = "registro", layout = AppLayoutNavbar.class)
@Uses(Icon.class)
public class Registro extends VerticalLayout {

    public Registro() {

        //Declaracion de componentes

        TextField Nombre = new TextField();
        Nombre.setLabel("Nombre");

        TextField Apellido = new TextField();
        Apellido.setLabel("Apellido");

        EmailField Correo = new EmailField();
        Correo.setLabel("Correo Electronico");

        TextField Telefono = new TextField();
        Telefono.setLabel("Telefono");

        TextField Direccion = new TextField();
        Direccion.setLabel("Direccion");

        PasswordField Contrasena = new PasswordField();
        Contrasena.setLabel("Contraseña");

        PasswordField ConfirmarContrasena = new PasswordField();
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


        Nombre.getStyle().set("padding", "var(--lumo-space-s)");
        Apellido.getStyle().set("padding", "var(--lumo-space-s)");
        Correo.getStyle().set("padding", "var(--lumo-space-s)");
        Telefono.getStyle().set("padding", "var(--lumo-space-s)");
        Direccion.getStyle().set("padding", "var(--lumo-space-s)");
        Contrasena.getStyle().set("padding", "var(--lumo-space-s)");
        ConfirmarContrasena.getStyle().set("padding", "var(--lumo-space-s)");
        FechaNacimiento.getStyle().set("padding", "var(--lumo-space-s)");
        DNI.getStyle().set("padding", "var(--lumo-space-s)");

        Nombre.setWidth("500px");
        Apellido.setWidth("500px");
        Correo.setWidth("500px");
        Telefono.setWidth("500px");
        Direccion.setWidth("500px");
        Contrasena.setWidth("500px");
        ConfirmarContrasena.setWidth("500px");
        FechaNacimiento.setWidth("500px");
        DNI.setWidth("500px");
        boton.setWidth("500px");
        Telefono.setMinLength(9);
        Telefono.setMaxLength(9);

        setAlignItems(Alignment.CENTER);

        vl2.add(Nombre, Apellido, Correo, Contrasena, ConfirmarContrasena, Telefono, Direccion,FechaNacimiento, DNI);
        vl2.setAlignItems(Alignment.CENTER);
        vl2.setWidth("69%");
        add(vl2,boton);

    }
}
