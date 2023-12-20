package com.example.application.services;

import com.example.application.data.*;
import com.example.application.views.Security.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ContratoService {

    private final TarifaRepository tarifaRepository;

    private final ContratoRepository contratoRepository;
    private final AuthenticatedUser authenticatedUser;
    private final TarjetaRepository TarjetaRepository;

    private final UsuarioRepository UsuarioRepository;

    public ContratoService(UsuarioRepository usuarioRepository, TarifaRepository tarifaRepository, ContratoRepository contratoRepository, AuthenticatedUser authenticatedUser, TarjetaRepository tarjetaRepository) {
        this.UsuarioRepository = usuarioRepository;
        this.tarifaRepository = tarifaRepository;
        this.contratoRepository = contratoRepository;
        this.authenticatedUser = authenticatedUser;
        this.TarjetaRepository = tarjetaRepository;
    }

    public void contratarTarifa(String numero, String titular, String fechaCaducidad, String cvv, UUID id, String nombretarifa) {
        Tarifa tarifa = tarifaRepository.findByNombre(nombretarifa);
        fechaCaducidad = "1/" + fechaCaducidad;
        Contrato contrato = new Contrato();
        Usuario usuario = UsuarioRepository.findById(id);
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
        contrato.setFijo((int) (Math.random() * (999999999 - 600000000 + 1) + 600000000));
        contrato.setMovil((int) (Math.random() * (999999999 - 600000000 + 1) + 600000000));
        usuario.setContrato(contrato);
        usuario.setTarjeta(tarjeta);
        tarjeta.setUsuario(usuario);
        TarjetaRepository.save(tarjeta);
        contratoRepository.save(contrato);
        UsuarioRepository.save(usuario);
    }


}
