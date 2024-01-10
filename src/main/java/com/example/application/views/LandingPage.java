package com.example.application.views;

import com.example.application.views.Clientes.PublicTarifasView;
import com.example.application.views.Comunes.Registro;
import com.example.application.views.Layouts.LayoutInicial;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "", layout = LayoutInicial.class)
@AnonymousAllowed
public class LandingPage extends VerticalLayout {

    public LandingPage() {
        addClassName("inicio-view");
        H1 sago = new H1("Bienvenido a SAGO");
        sago.getStyle().set("margin-top", "var(--lumo-space-l)");
        sago.getStyle().set("margin-left", "var(--lumo-space-l)");
        add(sago);

        VerticalLayout titleContainer = new VerticalLayout();
        titleContainer.setAlignItems(Alignment.CENTER);
        titleContainer.setWidthFull();

        Span titulo = new Span("Futuras Tarifas");
        titulo.addClassNames(LumoUtility.FontSize.XXLARGE, LumoUtility.FontWeight.BOLD);
        titleContainer.add(titulo);

        Span subtitulo = new Span("Estas son las futuras tarifas que se van a añadir el 14 de febrero");
        subtitulo.addClassNames(LumoUtility.FontSize.MEDIUM, LumoUtility.FontWeight.BOLD);
        titleContainer.add(subtitulo);

        titleContainer.getStyle().set("margin-top", "var(--lumo-space-l)");

        add(titleContainer);

        HorizontalLayout cardContainer = new HorizontalLayout();
        cardContainer.setWidthFull();
        cardContainer.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        ImageGallery("Movil+Fibra+DisneyPlus",
                "https://i.ytimg.com/vi/m2GgV-xAiA0/maxresdefault.jpg", cardContainer);
        ImageGallery("Movil+Fibra+HBO",
                "https://th.bing.com/th/id/OIP.4ecNRgWr_3RyWdfmjhiCWQHaEU?rs=1&pid=ImgDetMain", cardContainer);
        ImageGallery("Movil+Fibra+Netflix",
                "https://wallpaperaccess.com/full/6075689.png", cardContainer);

        add(cardContainer);
    }
    public void ImageGallery(String text, String url, HorizontalLayout container) {
        Div card = new Div();
        card.addClassNames(
                LumoUtility.Background.CONTRAST_5,
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.AlignItems.START,
                LumoUtility.Padding.MEDIUM,
                LumoUtility.BorderRadius.LARGE
        );
        card.setWidth("600px");
        card.setHeight("420px");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(url);
        image.setAlt(text);

        Span header = new Span();
        header.addClassNames(LumoUtility.FontSize.XLARGE, LumoUtility.FontWeight.SEMIBOLD);
        header.setText(text);

        Span subtitle = new Span();
        subtitle.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);
        subtitle.setText("Proximamente");

        Paragraph description = new Paragraph(
                "Estas son las futuras tarifas que se van a añadir el 14 de febrero");
        description.addClassName(LumoUtility.Margin.Vertical.MEDIUM);

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText("Activar aviso");

        card.add(image, header, subtitle, description, badge);
        container.add(card);
    }
}
