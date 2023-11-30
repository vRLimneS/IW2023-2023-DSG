package com.example.application.views.atcCliente;

import java.util.ArrayList;
import java.util.List;

import com.example.application.data.Consulta;
import com.example.application.services.ConsultaService;
import com.example.application.views.LayoutDepATC;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route(value = "AtcclienteadminView", layout = LayoutDepATC.class)
public class AtcclienteadminView extends Div {

    private static final List<Consulta> invitedPeople = new ArrayList<>();

    private static Grid<Consulta> grid;
    private static Div hint;
    private final ConsultaService consultaService;

    public AtcclienteadminView(ConsultaService consultaService) {
        this.consultaService = consultaService;
        this.setupGrid();

    }
    private void setupGrid() {
        // tag::snippet[]
        grid = new Grid<>(Consulta.class, false);
        grid.setAllRowsVisible(true);
        // end::snippet[]
        grid.addColumn(Consulta::getEmail).setHeader("Email");
        grid.addColumn(Consulta::getAsunto).setHeader("Asunto");
        grid.addColumn(Consulta::getMensaje).setHeader("Mensaje");
        //grid.addColumn(
        //        new ComponentRenderer<>(Button::new, (button, person) -> {
        //            button.addThemeVariants(ButtonVariant.LUMO_ICON,
        //                    ButtonVariant.LUMO_ERROR,
        //                    ButtonVariant.LUMO_TERTIARY);
        //            button.addClickListener(e -> this.removeInvitation(person));
        //            button.setIcon(new Icon(VaadinIcon.TRASH));
        //        })).setHeader("Manage");


        grid.setItems(consultaService.gettodasconsultas());


        add(grid);
    }



}
