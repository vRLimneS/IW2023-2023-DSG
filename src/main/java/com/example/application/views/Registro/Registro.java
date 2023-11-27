package com.example.application.views.Registro;

import com.example.application.views.AppLayoutNavbar;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PageTitle("Person Form")
@Route(value = "person-form", layout = AppLayoutNavbar.class)
@Uses(Icon.class)
public class Registro extends HorizontalLayout {

    public Registro() {

        //Declaracion de componentes

        TextField Nombre = new TextField();
        Nombre.setLabel("Nombre");
        TextField Apellido = new TextField();
        Apellido.setLabel("Apellido");
        TextField Correo = new TextField();
        Correo.setLabel("Correo Electronico");
        TextField Telefono = new TextField();
        Telefono.setLabel("Telefono");
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
        VerticalLayout vl1 = new VerticalLayout();
        VerticalLayout vl2 = new VerticalLayout();
        Button boton = new Button();



        Nombre.setWidthFull();
        Apellido.setWidthFull();
        Correo.setWidthFull();
        Telefono.setWidthFull();
        Direccion.setWidthFull();
        Contrasena.setWidthFull();
        ConfirmarContrasena.setWidthFull();
        FechaNacimiento.setWidthFull();
        DNI.setWidthFull();
        vl1.add(Nombre,Apellido,Correo,Telefono,Direccion);
        boton.setText("Registrarse");
        boton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        vl2.add(Contrasena,ConfirmarContrasena,FechaNacimiento,DNI,boton);

        vl1.setHeightFull();
        vl2.setHeightFull();
        vl1.setAlignItems(Alignment.END);
        vl2.setAlignItems(Alignment.START);
        boton.getStyle().set("margin-top", "20px");
        vl1.getStyle().set("margin-top", "20px");
        vl2.getStyle().set("margin-top", "20px");
        setAlignItems(Alignment.CENTER);

        add(vl1,vl2);



    }
}
