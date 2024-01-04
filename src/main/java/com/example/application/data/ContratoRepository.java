package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
    Contrato findByUsuario(Usuario usuario);

    Contrato save(Contrato contrato);

    List<Contrato> findAll();

    List<Contrato> findByUsuarioId(UUID id);

    List<Contrato> findBy_estadoContratoAndUsuarioId(EstadoContrato estadoContrato, UUID id);

}
