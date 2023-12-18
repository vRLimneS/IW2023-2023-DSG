package com.example.application.services;

import com.example.application.data.*;
import com.example.application.views.Security.AuthenticatedUser;

import java.time.LocalDate;

public class ContratoService {

    private final TarifaRepository tarifaRepository;
    private final ContratoRepository contratoRepository;
    private final AuthenticatedUser authenticatedUser;
    private final TarjetaRepository TarjetaRepository;

    public ContratoService(TarifaRepository tarifaRepository, ContratoRepository contratoRepository, AuthenticatedUser authenticatedUser, TarjetaRepository tarjetaRepository) {
        this.tarifaRepository = tarifaRepository;
        this.contratoRepository = contratoRepository;
        this.authenticatedUser = authenticatedUser;
        this.TarjetaRepository = tarjetaRepository;
    }

    public void contratarTarifa(String numero, String titular, String fechaCaducidad, String cvv, Usuario usuario, String nombretarifa) {
        Tarifa tarifa = tarifaRepository.findByNombre(nombretarifa);
        Contrato contrato = new Contrato();
        contrato.setUsuario(usuario);
        contrato.setTarifa(tarifa);
        Tarjeta tarjeta = new Tarjeta(Integer.getInteger(numero), titular, LocalDate.parse(fechaCaducidad), Integer.getInteger(cvv));
        //contrato.setTarjeta(tarjeta);
        //contrato.set_estadoContrato("ACTIVO");
        contrato.setFechaInicio(LocalDate.now());
        contrato.setFechaFin(LocalDate.now().plusMonths(tarifa.getPermanencia()));
        TarjetaRepository.save(tarjeta);
        contratoRepository.save(contrato);
        tarjeta.setContrato(contrato);
    }


}
