package com.example.application.views.atcCliente;

import com.example.application.views.LayoutCliente;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
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
@Route(value = "AtcCliente", layout = LayoutCliente.class)
@Uses(Icon.class)
public class atccliente extends FormLayout {

    public atccliente() {

        EmailField email = new EmailField();
        VerticalLayout vl = new VerticalLayout();
        email.setLabel("Correo");
        email.setHelperText("Introduce tu email");
        email.setPlaceholder("Introduzca tu email con el dominio correspondiente");
        email.setTooltipText("Tooltip text");
        email.setClearButtonVisible(true);
        email.setPrefixComponent(VaadinIcon.ENVELOPE.create());
        email.setWidth("600px");
        vl.setHeight("calc(100vh - 32px)");
        vl.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        TextArea Mensaje = new TextArea();
        Mensaje.setLabel("Introduzca su consulta");
        Mensaje.setPlaceholder("Introduzca su consulta");
        Mensaje.setHeight("300px");
        Mensaje.setWidth("600px");

        Button buton = new Button("Enviar");
        buton.addClickListener(e -> {
            Notification.show(Mensaje.getValue());
            Notification.show(email.getValue());
            email.clear();
            Mensaje.clear();
        });

        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setAlignSelf(FlexComponent.Alignment.CENTER, buton);
        hl2.add(email, buton);
        email.getStyle().set("padding", "var(--lumo-space-s)");
        Mensaje.getStyle().set("padding", "var(--lumo-space-s)");
        vl.getStyle().set("padding", "var(--lumo-space-s)");
        vl.add(Mensaje, hl2);

        add(vl);

    }

}