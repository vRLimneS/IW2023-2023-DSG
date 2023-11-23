package com.example.application.views.contratar;

import com.example.application.views.AppLayoutNavbar;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Contratar")
@Route(value = "Contratar", layout = AppLayoutNavbar.class)
public class ContratarView extends HorizontalLayout {

    private final TextField name;
    private final Button sayHello;

    public ContratarView() {
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(name, sayHello);

        IntegerField integerField = new IntegerField();
        integerField.setLabel("Quantity");
        integerField.setHelperText("Max 10 items");
        integerField.setMin(0);
        integerField.setMax(10);
        integerField.setValue(2);
        integerField.setStepButtonsVisible(true);
        add(integerField);


    }

}