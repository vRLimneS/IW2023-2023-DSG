package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NumeroRepository extends JpaRepository<Numero, UUID> {
}
