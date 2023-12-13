package com.example.application.views.Layouts;

import com.example.application.data.Consulta;
import com.example.application.data.Usuario;
import com.example.application.services.ConsultaService;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
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
import com.vaadin.flow.server.auth.AnonymousAllowed;
@AnonymousAllowed
@PageTitle("AtcClientenoregistrados")
@Route(value = "AtcClientenoregistrados", layout = LayoutInicial.class)
@Uses(Icon.class)
public class atcclientenoregistrados extends Div {

    private ConsultaService consultaService;

    public atcclientenoregistrados(ConsultaService consultaService, AuthenticatedUser authenticatedUser) {

        this.consultaService = consultaService;

        EmailField email = new EmailField();
        VerticalLayout vl = new VerticalLayout();
        email.setLabel("Introduzca su Correo Electrónico");
        email.setPlaceholder("Introduzca tu email con el dominio correspondiente");
        email.setTooltipText("Tooltip text");
        email.setClearButtonVisible(true);
        email.setPrefixComponent(VaadinIcon.ENVELOPE.create());
        email.setWidth("600px");
        vl.setHeight("calc(100vh - 32px)");
        vl.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        TextArea mensaje = new TextArea();
        mensaje.setLabel("Introduzca su Consulta");
        mensaje.setPlaceholder("Consulta");
        mensaje.setHeight("300px");
        mensaje.setWidth("600px");


        TextArea asunto = new TextArea();
        asunto.setLabel("Introduzca el Asunto");
        asunto.setPlaceholder("Asunto");
        asunto.setHeight("80px");
        asunto.setWidth("600px");


        Button buton = new Button("Enviar");
        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setAlignSelf(FlexComponent.Alignment.END, buton);
        hl2.add(mensaje, buton);
        asunto.getStyle().set("padding", "var(--lumo-space-s)");
        email.getStyle().set("padding", "var(--lumo-space-s)");
        mensaje.getStyle().set("padding", "var(--lumo-space-s)");
        vl.getStyle().set("padding", "var(--lumo-space-s)");
        vl.add(email, asunto, hl2);


        add(vl);

        buton.addClickListener(e -> {
                consultaService.save(new Consulta(email.getValue(), asunto.getValue(), mensaje.getValue()));
                Notification.show("Consulta enviada");
                email.clear();
                mensaje.clear();
                asunto.clear();

        });

    }
}
