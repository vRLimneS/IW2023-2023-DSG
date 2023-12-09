package com.example.application.views.Marketing;

import com.example.application.services.TarifaService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("MARKETING")
@Route(value = "TarifasPr", layout = LayoutPrincipal.class)
public class PrivateTarifasView extends com.example.application.views.Comunes.TarifasView {

    private TarifaService tarifasService;

    public PrivateTarifasView(TarifaService tarifaService){
        super(tarifaService);

    }

}
