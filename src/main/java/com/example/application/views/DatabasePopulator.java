package com.example.application.views;

import com.example.application.data.TipoRol;
import com.example.application.data.Usuario;
import com.example.application.services.UsuarioService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DatabasePopulator implements CommandLineRunner {

    UsuarioService usuarioService;

    public DatabasePopulator(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        // Creamos admin
        if (usuarioService.count() == 0) {
            Usuario user = new Usuario();
            user.setUsername("user");
            user.setContraseña("user");
            user.setEmail("admin@uca.es");
            user.setFechaNacimiento(LocalDate.of(2020, Month.JANUARY, 8));
            user.setRol(TipoRol.CLIENTE);
            usuarioService.registerUser(user);
            usuarioService.activateUser(user.getEmail(), user.gettoken());
            System.out.println("Admin created");

            Usuario user1 = new Usuario();
            user1.setUsername("user1");
            user1.setContraseña("user1");
            user1.setEmail("admin2@uca.es");
            user1.setFechaNacimiento(LocalDate.of(2020, Month.FEBRUARY, 8));
            user1.setRol(TipoRol.MARKETING);
            usuarioService.registerUser(user1);
            usuarioService.activateUser(user1.getEmail(), user1.gettoken());
            System.out.println("Marketing created");

            Usuario user2 = new Usuario();
            user2.setUsername("user2");
            user2.setContraseña("user2");
            user2.setEmail("admin3@uca.es");
            user2.setFechaNacimiento(LocalDate.of(2020, Month.MARCH, 8));
            user2.setRol(TipoRol.ATCCLT);
            usuarioService.registerUser(user2);
            usuarioService.activateUser(user2.getEmail(), user2.gettoken());
            System.out.println("ATCLT created");

        }



    }


}