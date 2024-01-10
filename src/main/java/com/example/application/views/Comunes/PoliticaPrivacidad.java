package com.example.application.views.Comunes;

import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"CLIENTE", "ADMIN", "MARKETING", "ATCCLT"})
@Route(value = "PoliticaPrivacidad", layout = LayoutPrincipal.class)
public class PoliticaPrivacidad extends VerticalLayout {

    public PoliticaPrivacidad() {
        // Configuración del diseño
        setPadding(true);
        setSpacing(true);

        // Título
        H3 titulo = new H3("Política de Privacidad");
        add(titulo);

        // Contenido
        Div contenido = new Div();
        contenido.setText(getPolitica());
        add(contenido);

        // Estilo
        setClassName("politica-privacidad");
    }

    public static String getPolitica() {
        return "Fecha de entrada en vigencia: [Fecha]\n" +
                "La privacidad de nuestros usuarios es de suma importancia para nosotros. Esta política de privacidad describe cómo [Nombre de tu empresa] (\"nosotros\", \"nuestro\" o \"nosotros\") recopila, utiliza y comparte información personal cuando utilizas nuestra aplicación [nombre de la aplicación] (la \"Aplicación\").\n\n" +
                "Información que Recopilamos\n\n" +
                "Podemos recopilar información personal que nos proporcionas directamente, como tu nombre, dirección de correo electrónico, dirección postal, nombre de usuario y contraseña al registrarte o utilizar nuestra aplicación. También podemos recopilar información automáticamente cuando utilizas la aplicación, como la dirección IP, el tipo de dispositivo, el navegador web, la ubicación geográfica y la actividad del usuario.\n\n" +
                "Cómo Utilizamos tu Información\n\n" +
                "Utilizamos la información recopilada para proporcionar, mantener, proteger y mejorar nuestra aplicación. También podemos utilizar la información para personalizar tu experiencia, enviar notificaciones importantes y comunicarnos contigo sobre cambios en nuestra política de privacidad, términos de servicio o actualizaciones de la aplicación.\n\n" +
                "Compartir tu Información\n\n" +
                "No vendemos, comerciamos ni transferimos tu información personal a terceros sin tu consentimiento, excepto cuando sea necesario para proporcionarte los servicios solicitados o cumplir con la ley.\n\n" +
                "Cookies y Tecnologías Similares\n\n" +
                "Utilizamos cookies y tecnologías similares para recopilar información sobre tus preferencias y actividades en nuestra aplicación. Puedes configurar tu navegador para que rechace todas las cookies o te avise cuando se envíe una cookie. Sin embargo, algunas funciones de la aplicación pueden no funcionar correctamente sin cookies.\n\n" +
                "Seguridad\n\n" +
                "Implementamos medidas de seguridad para proteger tu información personal. Sin embargo, ten en cuenta que ninguna transmisión por Internet o método de almacenamiento electrónico es completamente seguro.\n\n" +
                "Cambios en esta Política de Privacidad\n\n" +
                "Nos reservamos el derecho de actualizar nuestra política de privacidad en cualquier momento. Te notificaremos sobre cualquier cambio publicando la nueva política de privacidad en esta página.\n\n" +
                "Contacto\n\n" +
                "Si tienes alguna pregunta sobre nuestra política de privacidad, contáctanos en [tu correo electrónico].\n";
    }
}
