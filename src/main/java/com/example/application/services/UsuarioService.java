package com.example.application.services;

import com.example.application.data.Usuario;
import com.example.application.data.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final EmailRealService emailService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, EmailRealService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public Usuario update(@Valid Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public int count() {
        return (int) usuarioRepository.count();
    }

    public boolean registerUser(Usuario user) {
        user.setContraseña(passwordEncoder.encode(user.getContraseña()));
        user.settoken(UUID.randomUUID().toString().substring(0, 5));
        user.setRol(user.getRol());
        try {
            usuarioRepository.save(user);
            //emailService.sendRegistrationEmail(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public boolean activateUser(String email, String registerCode) {
        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        if (user.isPresent() && user.get().gettoken().equals(registerCode)) {
            user.get().setActive(true);
            usuarioRepository.save(user.get());
            return true;
        }
        return false;
    }


    public Usuario loadUserById(UUID userId) {
        return usuarioRepository.findById(userId);
    }

    public List<Usuario> loadActiveUsers() {
        return usuarioRepository.findByActiveTrue();
    }

    public void delete(Usuario testUser) {
        usuarioRepository.delete(testUser);

    }
}
