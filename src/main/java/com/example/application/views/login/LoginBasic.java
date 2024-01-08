package com.example.application.views.login;

import com.example.application.data.UsuarioRepository;
import com.example.application.services.EmailService;
import com.example.application.views.Comunes.Registro;
import com.example.application.views.LandingPage;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route(value = "login")
public class LoginBasic extends LoginOverlay {

    private final AuthenticatedUser authenticatedUser;

    private final EmailService emailService;

    private final UsuarioRepository usuarioRepository;

    public LoginBasic(AuthenticatedUser authenticatedUser, EmailService emailService, UsuarioRepository usuarioRepository) {
        VerticalLayout layout = new VerticalLayout();
        this.authenticatedUser = authenticatedUser;
        this.emailService = emailService;
        this.usuarioRepository = usuarioRepository;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("SagoSL");
        i18nForm.setUsername("Usuario");
        i18nForm.setPassword("Contraseña");
        i18nForm.setSubmit("Iniciar sesión");
        setForgotPasswordButtonVisible(false);

        i18n.getErrorMessage().setTitle("Usuario o contraseña incorrectos");
        i18n.setForm(i18nForm);
        i18n.getHeader().setTitle("SagoSL");
        i18n.getHeader().setDescription("Conectamos a todo el mundo");
        i18n.setAdditionalInformation(null);
        setI18n(i18n);

        Button registro = new Button();
        Button principal = new Button();
        Button contra = new Button();

        getFooter().add(registro, contra, principal);
        contra.setText("Recuperar contraseña");
        registro.setText("Registrarse");
        registro.addClickListener(e -> UI.getCurrent().navigate(Registro.class));
        principal.setText("Pagina principal");
        principal.addClickListener(e -> UI.getCurrent().navigate(LandingPage.class));

        contra.addClickListener(e -> {
            Dialog dialog = new Dialog();
            TextField email = new TextField("Email");
            Button enviar = new Button("Enviar");
            dialog.add(email, enviar);
            dialog.open();
            enviar.addClickListener(e2 -> {
                if (usuarioRepository.existsByEmail(email.getValue())) {
                    emailService.sendEmail(email.getValue());
                    dialog.close();
                    Notification.show("Se ha enviado un correo a su email");
                } else
                    Notification.show("El email no existe");
                email.clear();


            });

        });


        setOpened(true);
    }

}