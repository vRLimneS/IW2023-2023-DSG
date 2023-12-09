package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface TarifaRepository extends JpaRepository<Tarifa, UUID> {
    Tarifa findByNombre(String name);
    Tarifa save(Tarifa tarifa);
}
