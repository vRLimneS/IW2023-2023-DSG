package com.example.application.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    Usuario findByDNI(String name);

    Optional<Usuario> findByEmail(String name);

    Usuario findByUsername(String name);

    Optional<Usuario> findById(UUID userId);

    List<Usuario> findByActiveTrue();
}
