package com.example.application.views.Comunes;

import com.example.application.data.*;
import com.example.application.services.ContratoService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.example.application.views.Security.AuthenticatedUser;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        Button pdf = new Button("Descargar PDF");
        hl3.setAlignSelf(Alignment.CENTER, roaming);
        hl3.setAlignSelf(Alignment.CENTER, compartir);
        hl3.add(pdf, baja, bloquear, roaming, compartir);


        baja.addClickListener(e -> {
            Dialog dialog = new Dialog();
            dialog.add(new H5("¿Estas seguro de que quieres dar de baja el contrato?"));
            dialog.add(new H5("Se le cobrara una penalización de los meses que faltan por pagar, más el 20% del total del contrato"));
            dialog.add(new H5(penalizacion(c)));
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
        vl5.add(new H2("Precio"), new H4(c.getTarifa().getPrecio() + "€/mes"));
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


        pdf.addClickListener(e -> {
            try {
                GenerarPDF(c);
            } catch (DocumentException ex) {
                throw new RuntimeException(ex);
            }
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

    public String penalizacion(Contrato c) {

        int year = c.getFechaFin().getYear() - LocalDate.now().getYear();
        int meses = year * 12 + c.getFechaFin().getMonthValue() - LocalDate.now().getMonthValue();

        long porcentaje = c.getTarifa().getPermanencia() * c.getTarifa().getPrecio().longValue();
        BigDecimal cien = BigDecimal.valueOf(100);
        BigDecimal total;
        if (meses == 0) {
            total = c.getTarifa().getPrecio();
        } else {
            total = c.getTarifa().getPrecio().multiply(BigDecimal.valueOf(meses));
        }
        total = total.add(BigDecimal.valueOf(porcentaje * 20).divide(cien));
        return "La penalizacion es de " + total + "€";
    }


    public void GenerarPDF(Contrato c) throws DocumentException {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Contrato.pdf"));
        } catch (DocumentException ex) {
            throw new RuntimeException(ex);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk titular = new Chunk("Nombre Titular: " + c.getUsuario().getNombre() + "\n", font);
        Chunk dni = new Chunk("DNI: " + c.getUsuario().getDNI() + "\n", font);
        Chunk numerofijo = new Chunk("Registro de llamadas del numero: " + c.getFijo().getNumero(), font);
        Chunk numeromovil = new Chunk("Registro de llamadas del numero: " + c.getMovil().getNumero(), font);

        Paragraph paragraph = new Paragraph("Contrato " + c.getTarifanombre(), font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);


        RestTemplate restTemplate = new RestTemplate();
        CallRecord[] fijorecord = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + c.getFijo().getIdapi() + "/callrecords?carrier=" + c.getFijo().getCarrier(), CallRecord[].class);

        PdfPTable table = new PdfPTable(3);
        table.addCell("Numero Destino");
        table.addCell("Duracion");
        table.addCell("Fecha");


        for (int i = 0; i < fijorecord.length; i++) {
            table.addCell(fijorecord[i].getDestinationPhoneNumber());
            table.addCell(fijorecord[i].getDuracion());
            table.addCell(fijorecord[i].getFecha().toString());
        }

        CallRecord[] movilrecord = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + c.getMovil().getIdapi() + "/callrecords?carrier=" + c.getMovil().getCarrier(), CallRecord[].class);
        PdfPTable table2 = new PdfPTable(3);
        table2.addCell("Numero Destino");
        table2.addCell("Duracion");
        table2.addCell("Fecha");

        for (int i = 0; i < movilrecord.length; i++) {
            table2.addCell(movilrecord[i].getDestinationPhoneNumber());
            table2.addCell(movilrecord[i].getDuracion());
            table2.addCell(movilrecord[i].getFecha().toString());
        }

        DataUsageRecord[] datosmovil = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com/" + c.getMovil().getIdapi() + "/datausagerecords?carrier=" + c.getMovil().getCarrier(), DataUsageRecord[].class);

        PdfPTable table3 = new PdfPTable(2);
        table3.addCell("Megas Consumidos");
        table3.addCell("Fecha");

        for (int i = 0; i < datosmovil.length; i++) {
            table3.addCell(String.valueOf(datosmovil[i].getMegaBytes()));
            table3.addCell(datosmovil[i].getDate());
        }

        document.add(titular);
        document.add(dni);
        document.add(numerofijo);
        document.add(table);

        document.newPage();
        document.add(numeromovil);
        document.add(table2);

        document.newPage();
        document.add(new Chunk("Registro de datos del numero: " + c.getMovil().getNumero(), font));
        document.add(table3);


        document.close();


    }

}



