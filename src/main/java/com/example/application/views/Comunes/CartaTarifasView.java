package com.example.application.views.Comunes;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.math.BigDecimal;

public class CartaTarifasView extends ListItem {

    public CartaTarifasView(String NombreTarifa, String Descripcion, BigDecimal precio, int minutosMovil, int minutosFijo, int velocidadFibra, int datosMovil, String url) {
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

        Paragraph description = new Paragraph(Descripcion);
        description.addClassName(Margin.Vertical.MEDIUM);

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText(precio.toString() + "€");

        Button button = new Button();
        button.setText("Contratar");

        add(div, header, subtitle, description, badge, button);


        //Cuando se pulse el boton vaya a la clase pasareladepago
        button.addClickListener(click -> {
            UI navigate = UI.getCurrent();
            navigate.navigate(PasareladePago.class, NombreTarifa);
        });

    }
}
