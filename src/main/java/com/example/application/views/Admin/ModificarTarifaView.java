package com.example.application.views.Admin;

import com.example.application.data.Tarifa;
import com.example.application.data.Usuario;
import com.example.application.services.ContratoService;
import com.example.application.services.TarifaService;
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

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RolesAllowed({"CLIENTE", "ADMIN", "MARKETING"})
@Route(value = "ModificarTarifa", layout = LayoutPrincipal.class)
public class ModificarTarifaView extends HorizontalLayout implements HasUrlParameter<String> {

    private final ContratoService contratoService;
    TextField Nombre = new TextField("Nombre");
    TextArea Descripcion = new TextArea("Descripcion");
    TextField Precio = new TextField("Precio");
    TextField minutosFijos = new TextField("Minutos Fijos");
    TextField minutosMoviles = new TextField("Minutos Moviles");
    TextField sms = new TextField("SMS");
    TextField velocidad = new TextField("Velocidad");
    TextField gigas = new TextField("Gigas");
    TextField permanencia = new TextField("Permanencia(meses)");
    TextField estado = new TextField("Estado(false no se muestra, true se muestra)");
    TextField url = new TextField("Url");
    private final TarifaService tarifaService;
    private final AuthenticatedUser authenticatedUser;
    private String nombtarifa;

    public ModificarTarifaView(ContratoService contratoService, AuthenticatedUser authenticatedUser, TarifaService tarifaService) {

        this.contratoService = contratoService;
        this.authenticatedUser = authenticatedUser;
        Optional<Usuario> user = authenticatedUser.get();
        UUID id = user.get().getId();
        this.tarifaService = tarifaService;

        VerticalLayout vl = new VerticalLayout();
        VerticalLayout vl2 = new VerticalLayout();
        VerticalLayout vl3 = new VerticalLayout();

        Button boton = new Button("Modificar");

        boton.getStyle().set("margin", "auto");

        boton.addClickListener(click -> {
                    ConfirmDialog dialog = new ConfirmDialog();
                    dialog.setHeader("Confirmar Modificacion");
                    dialog.setText(
                            "¿Está seguro?");

                    dialog.setCancelable(true);
                    dialog.addCancelListener(event -> Notification.show("Cancelado"));


                    dialog.setRejectable(true);
                    dialog.setRejectText("Descartar");
                    dialog.addRejectListener(event -> {
                        Notification.show("Descartar");
                        UI.getCurrent().navigate(AdminTarifas.class);
                    });

                    dialog.setConfirmText("Guardar");
                    dialog.addConfirmListener(event -> {
                        if (Nombre.getValue().isEmpty() && Precio.getValue().isEmpty()) {
                            Notification.show("Rellene todos los campos");
                        } else {
                            try {
                                // Construye el objeto Tarifa con los parámetros del constructor
                                Tarifa tarifaToUpdate = new Tarifa(Nombre.getValue(), Descripcion.getValue(),
                                        new BigDecimal(Precio.getValue().replace(",", "")),
                                        Integer.parseInt(minutosMoviles.getValue()), Integer.parseInt(minutosFijos.getValue()),
                                        Integer.parseInt(velocidad.getValue()), Integer.parseInt(gigas.getValue()),
                                        Boolean.parseBoolean(estado.getValue()), Integer.parseInt(permanencia.getValue()),
                                        url.getValue());

                                // Actualiza la tarifa existente
                                Tarifa existingTarifa = TarifaService.findByNombre(nombtarifa);
                                if (existingTarifa != null) {
                                    existingTarifa.setNombre(tarifaToUpdate.getNombre());
                                    existingTarifa.setDescripcion(tarifaToUpdate.getDescripcion());
                                    existingTarifa.setPrecio(tarifaToUpdate.getPrecio());
                                    existingTarifa.setMinutosMovil(tarifaToUpdate.getMinutosMovil());
                                    existingTarifa.setMinutosFijo(tarifaToUpdate.getMinutosFijo());
                                    existingTarifa.setVelocidadFibra(tarifaToUpdate.getVelocidadFibra());
                                    existingTarifa.setDatosMoviles(tarifaToUpdate.getDatosMoviles());
                                    existingTarifa.setEstado(tarifaToUpdate.getEstado());
                                    existingTarifa.setPermanencia(tarifaToUpdate.getPermanencia());
                                    existingTarifa.setUrl(tarifaToUpdate.getUrl());

                                    tarifaService.save(existingTarifa);
                                    Notification.show("Tarifa modificada correctamente");
                                } else {
                                    Notification.show("No se encontró la tarifa a modificar");
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                Notification.show("Error al intentar modificar la tarifa: " + ex.getMessage(), 5000, Notification.Position.MIDDLE);
                            }
                            UI navigate = UI.getCurrent();
                            navigate.navigate(AdminTarifas.class);
                        }
                    });

                    dialog.open();

                    dialog.open();

                    getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                            .set("bottom", "0").set("left", "0").set("display", "flex")
                            .set("align-items", "center").set("justify-content", "center");
                }
        );

        vl.setSpacing(false);
        vl2.setSpacing(false);

        vl.add(Nombre, Descripcion, permanencia);
        vl2.add(Precio, minutosFijos, minutosMoviles, velocidad, gigas);
        vl3.add(estado, url, boton);

        vl2.setAlignItems(Alignment.CENTER);

        add(vl, vl2, vl3);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        nombtarifa = s;
        Nombre.setValue(nombtarifa);
        Descripcion.setValue(TarifaService.findByNombre(nombtarifa).getDescripcion());
        Precio.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getPrecio()));
        minutosFijos.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getMinutosFijo()));
        minutosMoviles.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getMinutosMovil()));
        velocidad.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getVelocidadFibra()));
        gigas.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getDatosMoviles()));
        permanencia.setValue(TarifaService.findByNombre(nombtarifa).getPermanencia() + " meses");
        estado.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getEstado()));
        url.setValue(TarifaService.findByNombre(nombtarifa).getUrl());


        Nombre.setWidth("400px");
        Descripcion.setWidth("400px");
        Descripcion.setHeight("200px");
        permanencia.setWidth("400px");
        Precio.setWidth("150px");
        minutosFijos.setWidth("150px");
        minutosMoviles.setWidth("150px");
        velocidad.setWidth("150px");
        gigas.setWidth("150px");
        estado.setWidth("400px");
        url.setWidth("400px");

        Nombre.setValue(TarifaService.findByNombre(nombtarifa).getNombre());
        Descripcion.setValue(TarifaService.findByNombre(nombtarifa).getDescripcion());
        permanencia.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getPermanencia()));
        Precio.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getPrecio()));
        minutosFijos.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getMinutosFijo()));
        minutosMoviles.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getMinutosMovil()));
        velocidad.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getVelocidadFibra()));
        gigas.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getDatosMoviles()));
        estado.setValue(String.valueOf(TarifaService.findByNombre(nombtarifa).getEstado()));
        url.setValue(TarifaService.findByNombre(nombtarifa).getUrl());

        Nombre.getStyle().set("padding", "var(--lumo-space-s)");
        Descripcion.getStyle().set("padding", "var(--lumo-space-s)");
        permanencia.getStyle().set("padding", "var(--lumo-space-s)");
        Precio.getStyle().set("padding", "var(--lumo-space-s)");
        minutosFijos.getStyle().set("padding", "var(--lumo-space-s)");
        minutosMoviles.getStyle().set("padding", "var(--lumo-space-s)");
        velocidad.getStyle().set("padding", "var(--lumo-space-s)");
        gigas.getStyle().set("padding", "var(--lumo-space-s)");
        estado.getStyle().set("padding", "var(--lumo-space-s)");
        url.getStyle().set("padding", "var(--lumo-space-s)");
    }
}
