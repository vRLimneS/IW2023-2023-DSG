package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TarjetaRepository extends JpaRepository<Tarjeta, UUID> {

    Tarjeta save(Tarjeta tarjeta);


}
