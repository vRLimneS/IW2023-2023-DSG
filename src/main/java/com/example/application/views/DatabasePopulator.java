package com.example.application.views;

import com.example.application.data.Tarifa;
import com.example.application.data.TipoRol;
import com.example.application.data.Usuario;
import com.example.application.services.TarifaService;
import com.example.application.services.UsuarioService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@Component
public class DatabasePopulator implements CommandLineRunner {

    UsuarioService usuarioService;

    TarifaService tarifaService;

    public DatabasePopulator(UsuarioService usuarioService, TarifaService tarifaService) {
        this.usuarioService = usuarioService;
        this.tarifaService = tarifaService;
    }

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();


        if (tarifaService.count() == 1) {

            Tarifa tarifa = new Tarifa();
            tarifa.setNombre("LA COMPLETA");
            tarifa.setPrecio(BigDecimal.valueOf(10));
            tarifa.setDescripcion("LA COMPLETA");
            tarifa.setDatosMoviles(10);
            tarifa.setMinutosFijo(10);
            tarifa.setMinutosMovil(10);
            tarifa.setVelocidadFibra(10);
            tarifa.setPermanencia(24);
            tarifa.setEstado(true);
            tarifa.setUrl("https://s1.eestatic.com/2023/08/17/enclave-ods/historias/787431882_235429173_1706x960.jpg");
            tarifaService.saveinicial(tarifa);


            Tarifa tarifa1 = new Tarifa();
            tarifa1.setNombre("LA NOMADA");
            tarifa1.setPrecio(BigDecimal.valueOf(20));
            tarifa1.setDescripcion("LA NOMADA");
            tarifa1.setDatosMoviles(20);
            tarifa1.setMinutosFijo(20);
            tarifa1.setMinutosMovil(20);
            tarifa1.setPermanencia(12);
            tarifa1.setEstado(true);
            tarifa1.setUrl("https://concepto.de/wp-content/uploads/2018/08/monta%C3%B1as-e1533762816593.jpg");
            tarifaService.saveinicial(tarifa1);

            Tarifa tarifa2 = new Tarifa();
            tarifa2.setNombre("EL VIAJERO");
            tarifa2.setPrecio(BigDecimal.valueOf(30));
            tarifa2.setDescripcion("EL VIAJERO");
            tarifa2.setDatosMoviles(30);
            tarifa2.setMinutosMovil(30);
            tarifa2.setVelocidadFibra(30);
            tarifa2.setPermanencia(6);
            tarifa2.setEstado(true);
            tarifa2.setUrl("https://cadenaser.com/resizer/J4EekPc7MTmEdGU1Vdm8XUiG_2w=/736x414/filters:format(jpg):quality(70)/cloudfront-eu-central-1.images.arcpublishing.com/prisaradio/UC7G52GLYVF3ZLCAH5MJNZPSEY.jpg");
            tarifaService.saveinicial(tarifa2);

            Tarifa tarifa3 = new Tarifa();
            tarifa3.setNombre("COMBI-CASA");
            tarifa3.setPrecio(BigDecimal.valueOf(40));
            tarifa3.setDescripcion("COMBI-CASA");
            tarifa3.setMinutosFijo(40);
            tarifa3.setVelocidadFibra(40);
            tarifa3.setPermanencia(12);
            tarifa3.setEstado(true);
            tarifa3.setUrl("https://img.freepik.com/foto-gratis/pintura-lago-montana-montana-al-fondo_188544-9126.jpg");
            tarifaService.saveinicial(tarifa3);

            Tarifa tarifa4 = new Tarifa();
            tarifa4.setNombre("MOVIL");
            tarifa4.setPrecio(BigDecimal.valueOf(50));
            tarifa4.setDescripcion("MOVIL");
            tarifa4.setDatosMoviles(50);
            tarifa4.setMinutosMovil(50);
            tarifa4.setPermanencia(6);
            tarifa4.setEstado(true);
            tarifa4.setUrl("https://img.freepik.com/vector-gratis/paisaje-bosque-desertico-escena-atardecer-oasis_1308-56109.jpg");
            tarifaService.saveinicial(tarifa4);

            Tarifa tarifa5 = new Tarifa();
            tarifa5.setNombre("FIBRA");
            tarifa5.setPrecio(BigDecimal.valueOf(60));
            tarifa5.setDescripcion("FIBRA");
            tarifa5.setVelocidadFibra(60);
            tarifa5.setPermanencia(12);
            tarifa5.setEstado(true);
            tarifa5.setUrl("https://img.freepik.com/foto-gratis/gran-paisaje-verde-cubierto-cesped-rodeado-arboles_181624-14827.jpg");
            tarifaService.saveinicial(tarifa5);

            Tarifa tarifa6 = new Tarifa();
            tarifa6.setNombre("FIJO");
            tarifa6.setPrecio(BigDecimal.valueOf(70));
            tarifa6.setDescripcion("FIJO");
            tarifa6.setMinutosFijo(70);
            tarifa6.setPermanencia(6);
            tarifa6.setEstado(true);
            tarifa6.setUrl("https://img.freepik.com/foto-gratis/paisaje-hermoso-campo-verde_181624-14826.jpg");
            tarifaService.saveinicial(tarifa6);

        }

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