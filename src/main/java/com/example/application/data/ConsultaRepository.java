package com.example.application.data;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
    List<Consulta> findByTerminadoFalse();


}
