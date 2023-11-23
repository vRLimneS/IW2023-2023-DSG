package com.example.application.views.atcCliente;

import com.example.application.views.AppLayoutNavbar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("AtcCliente")
@Route(value = "AtcCliente", layout = AppLayoutNavbar.class)
@Uses(Icon.class)
public class atccliente extends FormLayout {

    public atccliente() {

        EmailField field = new EmailField();
        VerticalLayout vl = new VerticalLayout();
        field.setLabel("Correo");
        field.setHelperText("Introduce tu email");
        field.setPlaceholder("Introduzca tu email con el dominio correspondiente");
        field.setTooltipText("Tooltip text");
        field.setClearButtonVisible(true);
        field.setPrefixComponent(VaadinIcon.ENVELOPE.create());
        field.setWidth("600px");
        vl.setHeight("calc(100vh - 32px)");
        //vl.setAlignSelf(Alignment.START, field);
        vl.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        TextArea text = new TextArea();
        text.setLabel("Introduzca su consulta");
        text.setPlaceholder("Introduzca su consulta");
        text.setHeight("300px");
        text.setWidth("600px");

        Button buton = new Button("Enviar");
        buton.addClickListener(e -> {
            Notification.show(text.getValue());
            Notification.show(field.getValue());
            field.clear();
            text.clear();
        });

        Grid<Consulta> grid = new Grid(Consulta.class, false);
        grid.addColumn(Consulta::getAsunto).setHeader("Asunto");
        grid.addColumn(Consulta::getDescripcion).setHeader("Descripcion");
        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setAlignSelf(FlexComponent.Alignment.CENTER, buton);
        hl2.add(field, buton);
        field.getStyle().set("padding", "var(--lumo-space-s)");
        text.getStyle().set("padding", "var(--lumo-space-s)");
        vl.getStyle().set("padding", "var(--lumo-space-s)");
        vl.add(text, hl2, grid);

        add(vl);

    }

}