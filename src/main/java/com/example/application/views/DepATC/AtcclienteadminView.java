package com.example.application.views.DepATC;

import java.util.ArrayList;
import java.util.List;

import com.example.application.data.Consulta;
import com.example.application.services.ConsultaService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.checkbox.Checkbox;



import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("ATCCLT")
@Route(value = "AtcclienteadminView", layout = LayoutPrincipal.class)
public class AtcclienteadminView extends Div {

    private static final List<Consulta> consultas = new ArrayList<>();

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
        //grid.addColumn(Consulta::getEstado).setHeader("Estado");
        grid.addComponentColumn(select -> {
            Checkbox checkbox = new Checkbox();
            checkbox.addValueChangeListener(event -> {
                if (event.getValue()) {
                    consultas.add(select);
                } else {
                    consultas.remove(select);
                }
            });
            return checkbox;
        }).setHeader("Seleccionar").setAutoWidth(true);





        //grid.addColumn(
        //        new ComponentRenderer<>(Button::new, (button, person) -> {
        //            button.addThemeVariants(ButtonVariant.LUMO_ICON,
        //                    ButtonVariant.LUMO_ERROR,
        //                    ButtonVariant.LUMO_TERTIARY);
        //            button.addClickListener(e -> this.removeInvitation(person));
        //            button.setIcon(new Icon(VaadinIcon.TRASH));
        //        })).setHeader("Manage");


        grid.setItems(consultaService.findAll());


        add(grid);
    }



}
