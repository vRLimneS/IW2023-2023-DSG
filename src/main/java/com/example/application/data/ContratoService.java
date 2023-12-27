package com.example.application.data;

import org.springframework.stereotype.Service;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;

    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public Contrato save(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public Contrato findByUsuario(Usuario usuario) {
        return contratoRepository.findByUsuario(usuario);
    }

    public Contrato[] findAll() {
        return contratoRepository.findAll().toArray(new Contrato[0]);
    }

    //encontrar todos los contratos de un usuario


}
