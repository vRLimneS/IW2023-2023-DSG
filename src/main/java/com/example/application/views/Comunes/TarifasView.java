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

        //mostrar tarifa sago
       /*tarifa = tarifaService.findByNombre("Tarifa SAGO");
         H1 nombre = new H1(tarifa.getNombre());
            Paragraph descripcion = new Paragraph(tarifa.getDescripcion());
            Paragraph precio = new Paragraph(String.valueOf(tarifa.getPrecio()));
            Paragraph minutosMovil = new Paragraph(String.valueOf(tarifa.getMinutosMovil()));
            Paragraph minutosFijo = new Paragraph(String.valueOf(tarifa.getMinutosFijo()));
            Paragraph velocidadFibra = new Paragraph(String.valueOf(tarifa.getVelocidadFibra()));
            Paragraph datosMoviles = new Paragraph(String.valueOf(tarifa.getDatosMoviles()));
            vl3.add(nombre, descripcion, precio, minutosMovil, minutosFijo, velocidadFibra, datosMoviles);
            add(vl3);*/

        //mostrar todas las tarifas
        for (Tarifa tarifa : tarifaService.findAllEnable()) {
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
    }

    private void constructUI() {
        addClassNames("image-gallery-view");
        addClassNames(LumoUtility.MaxWidth.SCREEN_LARGE, LumoUtility.Margin.Horizontal.AUTO, LumoUtility.Padding.Bottom.LARGE, LumoUtility.Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(LumoUtility.AlignItems.CENTER, LumoUtility.JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Tarifas actuales");
        header.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.XLARGE, LumoUtility.FontSize.XXXLARGE);
        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
        description.addClassNames(LumoUtility.Margin.Bottom.XLARGE, LumoUtility.Margin.Top.NONE, LumoUtility.TextColor.SECONDARY);
        headerContainer.add(header, description);

        OrderedList imageContainer = new OrderedList();
        imageContainer.addClassNames(LumoUtility.Gap.MEDIUM, LumoUtility.Display.GRID, LumoUtility.ListStyleType.NONE, LumoUtility.Margin.NONE, LumoUtility.Padding.NONE);

        container.add(headerContainer);
        add(container, imageContainer);
    }

    public TarifasView() {

    }


}
