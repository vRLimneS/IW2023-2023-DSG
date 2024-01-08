package com.example.application.views.Admin;

import com.example.application.services.TarifaService;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"ADMIN", "MARKETING"})
@Route(value = "AdminViewTarifas", layout = LayoutPrincipal.class)
public class AdminTarifas extends AdminTarifasView{
    private TarifaService tarifaService;

    public AdminTarifas(TarifaService tarifaService) {
        super(tarifaService);
    }

}
