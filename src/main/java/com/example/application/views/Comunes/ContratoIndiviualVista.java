package com.example.application.views.Comunes;

import com.example.application.data.*;
import com.example.application.services.ContratoService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@RolesAllowed({"CLIENTE", "ATCCLT", "ADMIN", "MARKETING"})
@Route(value = "ContratoIndiviualVista", layout = LayoutPrincipal.class)
public class ContratoIndiviualVista extends VerticalLayout implements HasUrlParameter<String> {

    private final AuthenticatedUser authenticatedUser;

    private final ContratoService contratoService;

    private final NumeroRepository numeroRepository;

    public ContratoIndiviualVista(ContratoService contratoService, AuthenticatedUser authenticatedUser, NumeroRepository numeroRepository) {
        this.contratoService = contratoService;
        this.authenticatedUser = authenticatedUser;
        this.numeroRepository = numeroRepository;


    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        Contrato c = contratoService.findById(UUID.fromString(parameter));
        H1 titulo = new H1("Contrato " + c.getTarifanombre());
        Optional<Usuario> user = authenticatedUser.get();
        Numero n = c.getMovil();
        Tarifa t = c.getTarifa();
        Button inicio = new Button(VaadinIcon.HOME.create());
        Button llamadas = new Button(VaadinIcon.PHONE.create());
        Button mensajes = new Button(VaadinIcon.COMMENT.create());
        Button datos = new Button(VaadinIcon.DASHBOARD.create());
        Button llamadasfijo = new Button(VaadinIcon.PHONE_LANDLINE.create());
        Button atras = new Button(VaadinIcon.ARROW_CIRCLE_LEFT.create());
        HorizontalLayout hl = new HorizontalLayout();
        Grid<Contrato> grid = new Grid<>(Contrato.class, false);
        grid.setAllRowsVisible(true);

        H5 especificacion = new H5("Los minutos consumidos de más se cobraran a 0,50€ el minuto y el Gigabyte a 2€");
        HorizontalLayout hl4 = new HorizontalLayout();
        hl4.setAlignSelf(Alignment.END, especificacion);
        hl4.add(titulo, especificacion);

        VerticalLayout vl = new VerticalLayout();
        VerticalLayout vl2 = new VerticalLayout();
        VerticalLayout vl3 = new VerticalLayout();
        VerticalLayout vl4 = new VerticalLayout();
        VerticalLayout vl5 = new VerticalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();

        Checkbox roaming = new Checkbox("Activar Roaming");
        roaming.setValue(n.getRoaming());
        Checkbox compartir = new Checkbox("Compartir Datos");
        compartir.setValue(n.getCompartir());
        Button bloquear = new Button("Bloquear Numero");
        HorizontalLayout hl3 = new HorizontalLayout();
        Button baja = new Button("Dar de Baja Contrato");
        hl3.setAlignSelf(Alignment.CENTER, roaming);
        hl3.setAlignSelf(Alignment.CENTER, compartir);
        hl3.add(baja, bloquear, roaming, compartir);


        baja.addClickListener(e -> {
            Dialog dialog = new Dialog();
            dialog.add(new H5("¿Estas seguro de que quieres dar de baja el contrato?"));
            dialog.add(new H5("Se te cobrara una penalizacion de los meses que faltan por pagar, más el 20% del total del contrato"));
            HorizontalLayout hl5 = new HorizontalLayout();
            Button cancelButton = new Button("Cancelar");
            cancelButton.addClickListener(h -> {
                dialog.close();
            });
            Button saveButton = new Button("Dar de baja");
            saveButton.getStyle().set("color", "red");
            saveButton.addClickListener(h -> {
                dardebaja(c);
                dialog.close();
                UI.getCurrent().navigate(ServiciosView.class);
            });
            hl5.add(cancelButton, saveButton);
            dialog.add(hl5);
            dialog.open();
        });

        roaming.addClickListener(e -> {
            n.setRoaming(roaming.getValue());
            numeroRepository.save(n);
            UI.getCurrent().getPage().reload();
        });

        compartir.addClickListener(e -> {
            n.setCompartir(compartir.getValue());
            numeroRepository.save(n);
            UI.getCurrent().getPage().reload();
        });

        H2 Fijo = new H2("Fijo");
        H4 NumeroFijo = new H4(c.getFijonumero().toString());
        vl.add(Fijo, NumeroFijo, new H2("Mins Consumidos"), new H4(c.getMinutosFijo().toString()));
        vl.add(new H2("Mins Totales"), new H4(Integer.toString(t.getMinutosFijo())));
        vl2.add(new H2("Movil"), new H4(c.getMovilnumero().toString()), new H2("Mins Consumidos"), new H4(c.getMinutosMovil().toString()));
        vl2.add(new H2("Mins Totales"), new H4(Integer.toString(t.getMinutosMovil())));
        vl3.add(new H2("Datos"), new H4(c.getDatosMoviles() + "GB"));
        vl3.add(new H2("Datos Totales"), new H4(t.getDatosMoviles() + "GB"));
        vl4.add(new H2("Velocidad"), new H4(c.getVelocidadFibra() + "MB"));
        vl5.add(new H2("Fecha Inicio"), new H4(c.getFechaInicio().toString()), new H2("Fecha Fin"), new H4(c.getFechaFin().toString()));

        hl2.add(vl, vl2, vl3, vl4, vl5);
        hl2.setWidthFull();


        if (c.getFijo() == null) {
            if (c.getMovil() == null) {
                hl.add(atras, inicio);
            } else
                hl.add(atras, inicio, llamadas, datos, mensajes);
        } else if (c.getMovil() == null) {
            hl.add(atras, inicio, llamadasfijo);
        } else
            hl.add(atras, inicio, llamadas, llamadasfijo, datos, mensajes);
        grid.setItems(c);

        atras.addClickListener(e -> {
            UI.getCurrent().navigate(ServiciosView.class);
        });

        inicio.addClickListener(e -> {
            removeAll();
            add(hl4, hl, hl2, hl3);
        });

        llamadas.addClickListener(e -> {
            removeAll();
            add(hl4, hl);
            Grid<CallRecord> grid4 = new Grid<>(CallRecord.class, false);
            grid4.setItems(c.getMovil().getCallRecords());
            grid4.addColumn(CallRecord::getFecha).setHeader("Fecha");
            grid4.addColumn(CallRecord::getDestinationPhoneNumber).setHeader("Numero Destino");
            grid4.addColumn(CallRecord::getDuracion).setHeader("Duracion");
            add(grid4);
        });

        datos.addClickListener(e -> {
            removeAll();
            add(hl4, hl);
            Grid<DataUsageRecord> grid3 = new Grid<>(DataUsageRecord.class, false);
            grid3.setItems(c.getMovil().getDataUsageRecords());
            grid3.addColumn(DataUsageRecord::getDate).setHeader("Fecha");
            grid3.addColumn(DataUsageRecord::getMegaBytes).setHeader("Megas Consumidos");
            add(grid3);
        });

        llamadasfijo.addClickListener(e -> {
            removeAll();
            add(hl4, hl);
            Grid<CallRecord> grid2 = new Grid<>(CallRecord.class, false);
            grid2.setItems(c.getFijo().getCallRecords());
            grid2.addColumn(CallRecord::getFecha).setHeader("Fecha");
            grid2.addColumn(CallRecord::getDestinationPhoneNumber).setHeader("Numero Destino");
            grid2.addColumn(CallRecord::getDuracion).setHeader("Duracion");
            add(grid2);
        });

        bloquear.addClickListener(e -> {

            TextField numero = new TextField("Numero de Telefono");

            VerticalLayout dialogLayout = new VerticalLayout(numero);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");


            Dialog dialog = new Dialog();
            Button cancelButton = new Button("Cancelar");
            cancelButton.addClickListener(h -> {
                dialog.close();
            });
            Button saveButton = new Button("Guardar");
            saveButton.addClickListener(h -> {
                n.setNumerosBloqueados(numero.getValue());
                numeroRepository.save(n);
                dialog.close();
            });
            dialog.open();

            dialog.setHeaderTitle("Bloquear Numero");
            dialog.add(dialogLayout);

            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);
            add(dialog);

        });


        add(hl4, hl, hl2, hl3);

    }

    public void dardebaja(Contrato c) {
        c.set_estadoContrato("TERMINADO");
        if (c.getFijo() != null) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + c.getFijo().getIdapi() + "?carrier=" + c.getFijo().getCarrier());

        }
        if (c.getMovil() != null) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + c.getMovil().getIdapi() + "?carrier=" + c.getMovil().getCarrier());
        }
        contratoService.save(c);
    }


}



