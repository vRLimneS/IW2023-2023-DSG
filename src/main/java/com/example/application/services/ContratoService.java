package com.example.application.services;

import com.example.application.data.*;
import com.example.application.views.Security.AuthenticatedUser;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ContratoService {

    private final TarifaRepository tarifaRepository;
    private final NumeroRepository numeroRepository;
    private final ContratoRepository contratoRepository;
    private final AuthenticatedUser authenticatedUser;
    private final TarjetaRepository TarjetaRepository;
    private final UsuarioRepository UsuarioRepository;

    public ContratoService(UsuarioRepository usuarioRepository, TarifaRepository tarifaRepository, ContratoRepository contratoRepository, AuthenticatedUser authenticatedUser, TarjetaRepository tarjetaRepository, NumeroRepository numeroRepository) {
        this.UsuarioRepository = usuarioRepository;
        this.tarifaRepository = tarifaRepository;
        this.contratoRepository = contratoRepository;
        this.authenticatedUser = authenticatedUser;
        this.TarjetaRepository = tarjetaRepository;
        this.numeroRepository = numeroRepository;
    }

    @Transactional
    public void contratarTarifa(String numero, String titular, String fechaCaducidad, String cvv, UUID id, String nombretarifa) {
        Tarifa tarifa = tarifaRepository.findByNombre(nombretarifa);
        fechaCaducidad = "1/" + fechaCaducidad;
        Contrato contrato = new Contrato();
        Usuario usuario = UsuarioRepository.findWithContratoById(id);
        contrato.setUsuario(usuario);
        contrato.setTarifa(tarifa);
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumeroTarjeta(numero);
        tarjeta.setFechaCaducidad(LocalDate.parse(fechaCaducidad, DateTimeFormatter.ofPattern("d/MM/yy")));
        tarjeta.setTitular(titular);
        tarjeta.setCvv(cvv);
        contrato.setTarjeta(tarjeta);
        contrato.set_estadoContrato("ACTIVO");
        contrato.setFechaInicio(LocalDate.now());
        contrato.setFechaFin(LocalDate.now().plusMonths(tarifa.getPermanencia()));
        contrato.setFijo(Fijo(tarifa));
        //contrato.setMovil(Movil(tarifa));
        usuario.setContrato(contrato);
        TarjetaRepository.save(tarjeta);
        contratoRepository.save(contrato);
        UsuarioRepository.save(usuario);
    }

    public Numero Fijo(Tarifa tarifa) {
        CustomerLine fijo = new CustomerLine();
        fijo.setName("FIJO");
        fijo.setSurname("FIJO");
        fijo.setCarrier("UCA");
        int numero = Integer.parseInt("9" + (int) (Math.random() * 10000000 + 10000000));
        fijo.setPhoneNumber(String.valueOf(numero));
        RestTemplate restTemplate = new RestTemplate();
        Notification.show("Numero fijo: " + fijo.getPhoneNumber());
        fijo = restTemplate.postForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com", fijo, CustomerLine.class);
        // = restTemplate.getForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com", CustomerLine.class);
        Notification.show("Numero fijo: " + fijo.getId());
        Numero fijo2 = new Numero();
        fijo2.setNumero(fijo.getPhoneNumber());
        fijo2.setTipo("FIJO");
        fijo2.setConsumido(0);
        fijo2.setMax(tarifa.getMinutosFijo());
        fijo2.setIdapi(fijo.getId());
        numeroRepository.save(fijo2);
        return fijo2;
    }


}
