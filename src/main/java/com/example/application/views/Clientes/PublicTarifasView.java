package com.example.application.views.Clientes;

import com.example.application.data.TarifaService;
import com.example.application.views.Comunes.TarifasView;
import com.example.application.views.LayoutCliente;
import com.vaadin.flow.router.Route;

@Route(value = "TarifasPu", layout = LayoutCliente.class)
public class PublicTarifasView extends TarifasView {
    private atccliente atc = new atccliente();
    private TarifaService tarifaService;
    public PublicTarifasView(TarifaService tarifaService) {
        super(tarifaService);
    }
}
