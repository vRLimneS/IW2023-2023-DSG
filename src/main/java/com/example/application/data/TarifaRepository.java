package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TarifaRepository extends JpaRepository<Tarifa, UUID> {
    Tarifa findByNombre(String nombre);
    Tarifa save(Tarifa tarifa);
    void delete(Tarifa tarifa);
}
