package com.example.application.views.Comunes;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;


public class TarifasView extends VerticalLayout {

    public TarifasView() {
        Tab analytics = new Tab("Analytics");
        Tab customers = new Tab("Customers");
        Tab dashboards = new Tab("Dashboards");
        Tab documents = new Tab("Documents");
        Tab orders = new Tab("Orders");
        Tab products = new Tab("Products");
        Tab tasks = new Tab("Tasks");

        Tabs tabs = new Tabs(analytics, customers, dashboards, documents,
                orders, products, tasks);
        tabs.addThemeVariants(TabsVariant.LUMO_HIDE_SCROLL_BUTTONS);
        tabs.setMaxWidth("100%");
        VerticalLayout layout = new VerticalLayout();
        layout.add(tabs);
        layout.setAlignItems(Alignment.CENTER);
        layout.getElement().getStyle().set("padding", "0px");


        VerticalLayout vl2 = new VerticalLayout();
        tabs.addSelectedChangeListener(event -> {
            mostrarTab(event.getSelectedTab(), vl2);
        });
        layout.add(vl2);
        add(layout);
    }

    public void mostrarTab(Tab tab, VerticalLayout vl2){
        vl2.removeAll();
        TextField text = new TextField();
        text.setValue(tab.getLabel());
        text.setReadOnly(true);
        vl2.setAlignItems(Alignment.START);
        vl2.add(text);
    }

}
