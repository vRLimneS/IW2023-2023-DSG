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
            System.out.println("Cliente created");

            Usuario user1 = new Usuario();
            user1.setUsername("user1");
            user1.setContraseña("user1");
            user1.setEmail("admin2@uca.es");
            user1.setFechaNacimiento(LocalDate.of(2020, Month.FEBRUARY, 8));
            user1.setRol(TipoRol.MARKETING);
            usuarioService.registerUser(user1);
            usuarioService.activateUser(user1.getEmail(), user1.gettoken());
            System.out.println("Marketing created");

            Usuario usuario = new Usuario();
            usuario.setUsername("usuario");
            usuario.setContraseña("usuario");
            usuario.setEmail("usuario@uca.es");
            usuario.setFechaNacimiento(LocalDate.of(2020, Month.FEBRUARY, 8));
            usuario.setRol(TipoRol.ADMIN);
            usuarioService.registerUser(usuario);
            usuarioService.activateUser(usuario.getEmail(), usuario.gettoken());
            System.out.println("Admin created");

            Usuario user2 = new Usuario();
            user2.setUsername("user2");
            user2.setContraseña("user2");
            user2.setEmail("admin3@uca.es");
            user2.setFechaNacimiento(LocalDate.of(2020, Month.MARCH, 8));
            user2.setRol(TipoRol.ATCCLT);
            usuarioService.registerUser(user2);
            usuarioService.activateUser(user2.getEmail(), user2.gettoken());
            System.out.println("ATCLT created");

            Usuario user3 = new Usuario();
            user3.setUsername("user3");
            user3.setContraseña("user3");
            user3.setEmail("admin4@uca.es");
            user3.setFechaNacimiento(LocalDate.of(2020, Month.MARCH, 8));
            user3.setRol(TipoRol.ATCCLT);
            usuarioService.registerUser(user3);
            usuarioService.activateUser(user3.getEmail(), user3.gettoken());
            System.out.println("ATCLT created");

        }

    }

}