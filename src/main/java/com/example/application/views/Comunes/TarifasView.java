package com.example.application.views.Comunes;

import com.example.application.data.Tarifa;
import com.example.application.services.TarifaService;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.*;
import org.springframework.beans.factory.annotation.Autowired;

@AnonymousAllowed
public class TarifasView extends Main implements HasComponents, HasStyle {
    @Autowired
    private TarifaService tarifaService;
    private Tarifa tarifa;

    private OrderedList imageContainer;

    public TarifasView(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
        constructUI();

        for (Tarifa tarifa : tarifaService.findAllEnable()) {
            imageContainer.add(new CartaTarifasView(tarifa.getNombre(), tarifa.getDescripcion(), tarifa.getPrecio(),
                    tarifa.getMinutosMovil(), tarifa.getMinutosFijo(), tarifa.getVelocidadFibra(),
                    tarifa.getDatosMoviles(), tarifa.getUrl()));
        }
    }

    private void constructUI() {
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.START, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("TARIFAS DISPONIBLES");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph("Tarifas disponibles para contratar");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer);
        add(container, imageContainer);
    }
}