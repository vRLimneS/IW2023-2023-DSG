package com.example.application.views.Clientes;

import com.example.application.views.Comunes.TarifasView;
import com.example.application.views.Layouts.LayoutPrincipal;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("CLIENTE")
@Route(value = "TarifasPu", layout = LayoutPrincipal.class)
public class PublicTarifasView extends TarifasView {

    atccliente atc = new atccliente();


}
