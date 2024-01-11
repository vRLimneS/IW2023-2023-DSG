package com.example.application.views.Comunes;

import com.example.application.data.Usuario;
import com.example.application.services.ContratoService;
import com.example.application.services.TarifaService;
import com.example.application.views.Clientes.PublicTarifasView;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.Optional;
import java.util.UUID;

@RolesAllowed({"CLIENTE", "ATCCLT", "ADMIN", "MARKETING"})
@Route(value = "PasareladePago", layout = LayoutPrincipal.class)
public class PasareladePago extends HorizontalLayout implements HasUrlParameter<String> {

    private final ContratoService contratoService;
    private final TarifaService tarifaService;
    private final AuthenticatedUser authenticatedUser;
    TextField Nombre = new TextField("Nombre");
    TextArea Descripcion = new TextArea("Descripcion");
    TextField Precio = new TextField("Precio");
    TextField minutosFijos = new TextField("Minutos Fijos");
    TextField minutosMoviles = new TextField("Minutos Moviles");
    TextField velocidad = new TextField("Velocidad");
    TextField gigas = new TextField("Gigas");
    TextField permanencia = new TextField("Permanencia");
    private String nombtarifa;


    public PasareladePago(ContratoService contratoService, AuthenticatedUser authenticatedUser, TarifaService tarifaService) {

        this.contratoService = contratoService;
        this.authenticatedUser = authenticatedUser;
        this.tarifaService = tarifaService;
        Optional<Usuario> user = authenticatedUser.get();

        if (user.isPresent()) {
            UUID id = user.get().getId();


            VerticalLayout vl = new VerticalLayout();
            VerticalLayout vl2 = new VerticalLayout();
            VerticalLayout vl3 = new VerticalLayout();

            TextField NumeroTarjeta = new TextField("Numero Tarjeta");
            NumeroTarjeta.setWidth("300px");
            NumeroTarjeta.setHeight("65px");
            NumeroTarjeta.setPlaceholder("0000 0000 0000 0000");
            TextField Titular = new TextField("Titular");
            Titular.setPlaceholder("Nombre Apellido");
            Titular.setWidth("300px");
            TextField FechaCaducidad = new TextField("Fecha Caducidad");
            FechaCaducidad.setWidth("300px");
            FechaCaducidad.setPlaceholder("MM/AA");
            TextField CVV = new TextField("CVV");
            CVV.setWidth("300px");
            CVV.setPlaceholder("000");
            Button boton = new Button("Contratar");

            NumeroTarjeta.getStyle().setPadding("var(--lumo-space-s)");
            Titular.getStyle().setPadding("var(--lumo-space-s)");
            FechaCaducidad.getStyle().setPadding("var(--lumo-space-s)");
            CVV.getStyle().setPadding("var(--lumo-space-s)");
            NumeroTarjeta.getStyle().set("margin", "auto");
            Titular.getStyle().set("margin", "auto");
            FechaCaducidad.getStyle().set("margin", "auto");
            CVV.getStyle().set("margin", "auto");
            boton.getStyle().set("margin", "auto");


            boton.addClickListener(click -> {
                if (!NumeroTarjeta.getValue().matches("\\d\\d\\d\\d \\d\\d\\d\\d \\d\\d\\d\\d \\d\\d\\d\\d") || NumeroTarjeta.isEmpty() || Titular.isEmpty() || !FechaCaducidad.getValue().matches("\\d\\d/\\d\\d") || FechaCaducidad.isEmpty() || CVV.isEmpty() || !CVV.getValue().matches("\\d\\d\\d")) {
                    Notification.show("Revise todos los campos");
                } else {
                    ConfirmDialog dialog = new ConfirmDialog();
                    dialog.setHeader("Confirmar Pago");
                    dialog.setText(
                            "Esta seguro de que desea contratar esta tarifa?");

                    dialog.setCancelable(true);
                    dialog.addCancelListener(event -> Notification.show("Cancelado"));


                    dialog.setRejectable(true);
                    dialog.setRejectText("Descartar");
                    dialog.addRejectListener(event -> {
                        Notification.show("Descartar");
                        UI.getCurrent().navigate(PublicTarifasView.class);
                    });

                    dialog.setConfirmText("Guardar");
                    dialog.addConfirmListener(event -> {
                        contratar(contratoService, NumeroTarjeta.getValue(), Titular.getValue(), FechaCaducidad.getValue(), CVV.getValue(), id, nombtarifa);
                        UI.getCurrent().navigate(PublicTarifasView.class);
                    });

                    dialog.open();

                    getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                            .set("bottom", "0").set("left", "0").set("display", "flex")
                            .set("align-items", "center").set("justify-content", "center");
                }
            });

            vl.setSpacing(false);
            vl2.setSpacing(false);

            vl.add(Nombre, Descripcion, permanencia);
            vl2.add(Precio, minutosFijos, minutosMoviles, gigas, velocidad);
            vl3.add(NumeroTarjeta, Titular, FechaCaducidad, CVV, boton);

            vl2.setAlignItems(Alignment.CENTER);

            add(vl, vl2, vl3);
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        nombtarifa = s;
        Nombre.setValue(nombtarifa);
        Descripcion.setValue(tarifaService.findByNombre(nombtarifa).getDescripcion());
        Precio.setValue(String.valueOf(tarifaService.findByNombre(nombtarifa).getPrecio()));
        minutosFijos.setValue(String.valueOf(tarifaService.findByNombre(nombtarifa).getMinutosFijo()));
        minutosMoviles.setValue(String.valueOf(tarifaService.findByNombre(nombtarifa).getMinutosMovil()));
        velocidad.setValue(String.valueOf(tarifaService.findByNombre(nombtarifa).getVelocidadFibra()));
        gigas.setValue(String.valueOf(tarifaService.findByNombre(nombtarifa).getDatosMoviles()));
        permanencia.setValue(tarifaService.findByNombre(nombtarifa).getPermanencia() + " meses");

        Nombre.setWidth("400px");
        Descripcion.setWidth("400px");
        Descripcion.setHeight("200px");
        permanencia.setWidth("400px");
        Precio.setWidth("150px");
        minutosFijos.setWidth("150px");
        minutosMoviles.setWidth("150px");
        velocidad.setWidth("150px");
        gigas.setWidth("150px");

        Nombre.setReadOnly(true);
        Descripcion.setReadOnly(true);
        permanencia.setReadOnly(true);
        Precio.setReadOnly(true);
        minutosFijos.setReadOnly(true);
        minutosMoviles.setReadOnly(true);
        velocidad.setReadOnly(true);
        gigas.setReadOnly(true);

        Nombre.getStyle().set("padding", "var(--lumo-space-s)");
        Descripcion.getStyle().set("padding", "var(--lumo-space-s)");
        permanencia.getStyle().set("padding", "var(--lumo-space-s)");
        Precio.getStyle().set("padding", "var(--lumo-space-s)");
        minutosFijos.getStyle().set("padding", "var(--lumo-space-s)");
        minutosMoviles.getStyle().set("padding", "var(--lumo-space-s)");
        velocidad.getStyle().set("padding", "var(--lumo-space-s)");
        gigas.getStyle().set("padding", "var(--lumo-space-s)");

    }

    public void contratar(ContratoService contratoService, String numero, String titular, String fechaCaducidad, String cvv, UUID id, String nombretarifa) {
        contratoService.contratarTarifa(numero, titular, fechaCaducidad, cvv, id, nombretarifa);
    }
}
