package com.example.application.views.Clientes;

import com.example.application.views.Comunes.TarifasView;
import com.example.application.views.LayoutInicial;
import com.example.application.views.Comunes.TarifasView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "TarifasPu", layout = LayoutInicial.class)
public class PublicTarifasView extends TarifasView {

    atccliente atc = new atccliente();


}
