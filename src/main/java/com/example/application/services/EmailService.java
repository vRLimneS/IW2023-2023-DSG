package com.example.application.services;

import com.example.application.data.Usuario;

public interface EmailService {

    boolean sendRegistrationEmail(Usuario user);
}
