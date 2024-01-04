package com.example.application.views.Admin;

import com.example.application.data.Tarifa;
import com.example.application.services.TarifaService;
import com.example.application.views.Marketing.CrearTarifas;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

@AnonymousAllowed
public class AdminTarifasView extends VerticalLayout implements HasComponents, HasStyle{
    @Autowired
    private TarifaService tarifaService;
    private Tarifa tarifa;

    private OrderedList imageContainer;

    public AdminTarifasView(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
        constructUI();

        for (Tarifa tarifa : tarifaService.findAll()) {
            imageContainer.add(new AdminCartaTarifasView(tarifa.getNombre(), tarifa.getDescripcion(), tarifa.getPrecio(),
                    tarifa.getMinutosMovil(), tarifa.getMinutosFijo(), tarifa.getVelocidadFibra(),
                    tarifa.getDatosMoviles(),tarifa.getEstado(), tarifa.getPermanencia(), tarifa.getUrl(), tarifaService));
        }
    }

    public AdminTarifasView() {
    }

    private void constructUI() {
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.START, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("TODAS LAS TARIFAS");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        add(header);

        Button BotonCrear = new Button();
        BotonCrear.setText("Crear Tarifa");


        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        add(headerContainer);
        add(BotonCrear);
        add(container, imageContainer);

        BotonCrear.addClickListener(click -> {
            UI navigate = UI.getCurrent();
            navigate.navigate(CrearTarifas.class);
        });
    }
}