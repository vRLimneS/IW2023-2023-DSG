package com.example.application.views.Clientes;

import com.example.application.data.TarifaService;
import com.example.application.views.Comunes.TarifasView;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("CLIENTE")
@Route(value = "TarifasPu", layout = LayoutPrincipal.class)
public class PublicTarifasView extends TarifasView {
    private atccliente atc = new atccliente();
    private TarifaService tarifaService;
    public PublicTarifasView(TarifaService tarifaService) {
        super(tarifaService);
    }
}
