package com.example.application.views.Comunes;

import com.example.application.data.Tarifa;
import com.example.application.data.TarifaService;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
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
import org.springframework.stereotype.Component;

@AnonymousAllowed
@PageTitle("Image Gallery")
public class TarifasView extends VerticalLayout implements HasComponents, HasStyle{
    @Autowired
    private TarifaService tarifaService;
    private Tarifa tarifa;

    private OrderedList imageContainer;

   public TarifasView(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
        constructUI();

        VerticalLayout vl2 = new VerticalLayout();
        VerticalLayout vl3 = new VerticalLayout();
        //mostrar todas las tarifas
        /*for (Tarifa tarifa : tarifaService.findAllEnable()) {
            H1 nombre = new H1(tarifa.getNombre());
            Paragraph descripcion = new Paragraph(tarifa.getDescripcion());
            Paragraph precio = new Paragraph(String.valueOf(tarifa.getPrecio()));
            Paragraph minutosMovil = new Paragraph(String.valueOf(tarifa.getMinutosMovil()));
            Paragraph minutosFijo = new Paragraph(String.valueOf(tarifa.getMinutosFijo()));
            Paragraph velocidadFibra = new Paragraph(String.valueOf(tarifa.getVelocidadFibra()));
            Paragraph datosMoviles = new Paragraph(String.valueOf(tarifa.getDatosMoviles()));
            vl3.add(nombre, descripcion, precio, minutosMovil, minutosFijo, velocidadFibra, datosMoviles);
            add(vl3);
        }
         */

       for (Tarifa tarifa : tarifaService.findAllEnable()) {
           imageContainer.add(new ImageGalleryViewCard(tarifa.getNombre(), tarifa.getDescripcion(), tarifa.getPrecio(),
                   tarifa.getMinutosMovil(), tarifa.getMinutosFijo(), tarifa.getVelocidadFibra(),
                   tarifa.getDatosMoviles(), tarifa.getUrl()));
       }
    }

    private void constructUI() {
        addClassNames("image-gallery-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

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

    public TarifasView() {

    }


}
