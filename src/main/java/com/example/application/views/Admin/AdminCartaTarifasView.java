package com.example.application.views.Admin;

import com.example.application.data.Tarifa;
import com.example.application.services.TarifaService;
import com.example.application.views.Marketing.CrearTarifas;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.math.BigDecimal;

public class AdminCartaTarifasView extends ListItem {

    private final TarifaService tarifaService;


    public AdminCartaTarifasView(String NombreTarifa, String Descripcion, BigDecimal precio, int minutosMovil,
                                 int minutosFijo, int velocidadFibra, int datosMovil, boolean estado, int permanencia,
                                 String url, TarifaService tarifaService) {
        this.tarifaService = tarifaService;
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE);

        Div div = new Div();
        div.addClassNames(Background.CONTRAST, Display.FLEX, AlignItems.CENTER, JustifyContent.CENTER,
                Margin.Bottom.MEDIUM, Overflow.HIDDEN, BorderRadius.SMALL, Width.FULL);
        div.setHeight("160px");

        Image image = new Image();
        image.setWidth("160%");
        image.setSrc(url);
        image.setAlt(NombreTarifa);

        div.add(image);

        Span header = new Span();
        header.addClassNames(FontSize.XLARGE, FontWeight.SEMIBOLD);
        header.setText(NombreTarifa);

        Span subtitle = new Span();
        subtitle.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        subtitle.setText(Descripcion);

        Span MostrarEstado = new Span();
        MostrarEstado.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        if (estado == true) {
            MostrarEstado.setText("Estado: Visible");
        } else {
            MostrarEstado.setText("Estado: No Visible");
        }

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText(precio.toString() + "€");

        Button button = new Button();
        button.setText("Modificar");

        Button eliminarButton = new Button("Eliminar Tarifa");
        eliminarButton.addClickListener(event -> eliminarTarifa(NombreTarifa, Descripcion, precio, minutosMovil,
                minutosFijo, velocidadFibra, datosMovil, estado, permanencia, url));

        add(div, header, subtitle, MostrarEstado, badge, button, eliminarButton);

        button.addClickListener(click -> {
            UI navigate = UI.getCurrent();
            navigate.navigate(ModificarTarifaView.class, NombreTarifa);
        });
    }

    private void eliminarTarifa(String NombreTarifa, String Descripcion, BigDecimal precio, int minutosMovil,
                                int minutosFijo, int velocidadFibra, int datosMovil, boolean estado, int permanencia,
                                String url) {
        try {
            // Construye el objeto Tarifa con los parámetros del constructor
            Tarifa tarifaToDelete = new Tarifa(NombreTarifa, Descripcion, precio, minutosMovil, minutosFijo,
                    velocidadFibra, datosMovil, estado, permanencia, url);

            // Busca y elimina la tarifa
            Tarifa existingTarifa = tarifaService.findByNombre(tarifaToDelete.getNombre());

            if (existingTarifa != null) {
                tarifaService.delete(existingTarifa);
                Notification.show("Tarifa eliminada correctamente");

                // Recarga la página actual
                UI.getCurrent().getPage().reload();
            } else {
                Notification.show("No se encontró la tarifa a eliminar");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Notification.show("Error al eliminar la tarifa: " + e.getMessage());
        }
    }


}
