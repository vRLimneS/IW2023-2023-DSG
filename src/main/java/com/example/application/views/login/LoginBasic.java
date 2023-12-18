package com.example.application.views.login;

import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route(value = "login")
public class LoginBasic extends LoginOverlay {

    private final AuthenticatedUser authenticatedUser;

    public LoginBasic(AuthenticatedUser authenticatedUser) {
        VerticalLayout layout = new VerticalLayout();
        this.authenticatedUser = authenticatedUser;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("SagoSL");
        i18nForm.setUsername("Usuario");
        i18nForm.setPassword("Contraseña");
        i18nForm.setSubmit("Iniciar sesión");

        i18n.getErrorMessage().setTitle("Usuario o contraseña incorrectos");
        i18n.setForm(i18nForm);
        i18n.getHeader().setTitle("SagoSL");
        i18n.getHeader().setDescription("Conectamos a todo el mundo");
        i18n.setAdditionalInformation(null);
        setI18n(i18n);

        setForgotPasswordButtonVisible(false);
        setOpened(true);
    }

    /*
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            if(authenticatedUser.get().get().getRol().equals(TipoRol.CLIENTE)) {
                setOpened(false);
                event.forwardTo(ServiciosView.class);
            } else if(authenticatedUser.get().get().getRol().equals(TipoRol.ATCCLT)){
                setOpened(false);
                event.forwardTo(AtcclienteadminView.class);
                } else if(authenticatedUser.get().get().getRol().equals(TipoRol.MARKETING)) {
                setOpened(false);
                event.forwardTo(CrearTarifas.class);
                    }
                    else {
                    setOpened(false);
                    event.forwardTo("");
                        }
        }
        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
    */

}