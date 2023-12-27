package com.example.application.views.Clientes;

import com.example.application.services.TarifaService;
import com.example.application.views.Comunes.TarifasView;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"CLIENTE", "ATCCLT", "ADMIN", "MARKETING"})
@Route(value = "TarifasPu", layout = LayoutPrincipal.class)
public class PublicTarifasView extends TarifasView {
    private TarifaService tarifaService;

    public PublicTarifasView(TarifaService tarifaService) {
        super(tarifaService);
    }
}
