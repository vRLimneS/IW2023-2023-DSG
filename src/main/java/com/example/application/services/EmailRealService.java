package com.example.application.services;


import com.example.application.data.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public class EmailRealService implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String defaultMail;

    @Value("${server.port}")
    private int serverPort;

    public EmailRealService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    private String getServerUrl() {

        // Generate the server URL
        String serverUrl = "http://";
        serverUrl += InetAddress.getLoopbackAddress().getHostAddress();
        serverUrl += ":" + serverPort + "/web/";
        return serverUrl;

    }

    @Override
    public boolean sendRegistrationEmail(Usuario user) {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        String subject = "Bienvenido a la compañia";
        String body = "Debes activar tu cuenta. "
                + "Vuelve a la pagina e introduce el siguiente codigo: "
                + user.gettoken();


        try {
            helper.setFrom(defaultMail);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            this.mailSender.send(message);
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean sendEmail(String to) {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        String titulo = "Bienvenido a la compañia";
        String cuerpo = "Para recuperar la contraseña vaya a la siguiente direccion: "
                + getServerUrl() + "useractivation";

        try {
            helper.setFrom(defaultMail);
            helper.setTo(to);
            helper.setSubject(titulo);
            helper.setText(cuerpo);
            this.mailSender.send(message);
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

}