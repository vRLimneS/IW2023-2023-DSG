package com.example.application.views.Security;

import com.example.application.views.LandingPage;
import com.vaadin.flow.router.AccessDeniedException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.RouteAccessDeniedError;
import com.vaadin.flow.server.HttpStatusCode;

public class CustomAccessDeniedError extends RouteAccessDeniedError {

    private AuthenticatedUser authenticatedUser;

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<AccessDeniedException> parameter) {

        event.rerouteTo(LandingPage.class);

        return HttpStatusCode.UNAUTHORIZED.getCode();
    }
}