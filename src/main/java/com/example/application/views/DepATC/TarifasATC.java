package com.example.application.views.DepATC;

import com.example.application.data.TarifaService;
import com.example.application.views.Comunes.TarifasView;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;


@RolesAllowed("ATCCLT")
@Route(value = "TarifasATC", layout = LayoutPrincipal.class)
public class TarifasATC extends TarifasView {

    private TarifaService tarifaService;

    public TarifasATC(TarifaService tarifaService) {
        super(tarifaService);
    }
}
