package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

    List<Consulta> findAll();

    List<Consulta> findBy_estadoConsulta(Estadoconsulta estado);

    List<Consulta> findBy_estadoConsultaAndUsername(Estadoconsulta estado, String username);

    List<Consulta> findByCliente(Usuario cliente);
}
