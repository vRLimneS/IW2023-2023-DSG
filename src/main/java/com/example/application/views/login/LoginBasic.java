package com.example.application.views.login;

import com.example.application.views.AppLayoutNavbar;
import com.example.application.views.Registro.Registro;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.example.application.views.Contratar.ContratarView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "login-basic", layout = AppLayoutNavbar.class)
public class LoginBasic extends VerticalLayout {

    public LoginBasic() {
        getStyle().set("background-color", "var(--lumo-contrast-5pct)")
                .set("display", "flex").set("justify-content", "center")
                .set("padding", "var(--lumo-space-l)");

        LoginForm loginForm = new LoginForm();
        add(loginForm);
        loginForm.getElement().setAttribute("no-autofocus", "");
        Button buton = new Button("Registrarse");
        add(loginForm, buton);
        setHorizontalComponentAlignment(Alignment.CENTER, loginForm, buton);
        setSizeFull();

        buton.addClickListener(e ->
        buton.getUI().ifPresent(ui ->
                        ui.navigate(Registro.class)));

    }

}