package com.example.application.services;

import com.example.application.data.Consulta;
import com.example.application.data.ConsultaRepository;
import com.example.application.data.Estadoconsulta;
import com.example.application.data.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public List<Consulta> findByEstado(String estado) {
        if (estado.equals("PENDIENTE")) {
            return consultaRepository.findBy_estadoConsulta(Estadoconsulta.PENDIENTE);
        } else if (estado.equals("ATENDIDO")) {
            return consultaRepository.findBy_estadoConsulta(Estadoconsulta.ATENDIDO);
        } else if (estado.equals("RESUELTO")) {
            return consultaRepository.findBy_estadoConsulta(Estadoconsulta.RESUELTO);
        }
        return null;
    }

    public void save(Consulta consulta) {
        consultaRepository.save(consulta);
    }

    public List<Consulta> findByCliente(Usuario username) {
        return consultaRepository.findByCliente(username);
    }

    public List<Consulta> findBy_estadoConsultaAndUsername(String estado, String username) {
        if (estado.equals("PENDIENTE")) {
            return consultaRepository.findBy_estadoConsultaAndUsername(Estadoconsulta.PENDIENTE, username);
        } else if (estado.equals("ATENDIDO")) {
            return consultaRepository.findBy_estadoConsultaAndUsername(Estadoconsulta.ATENDIDO, username);
        } else if (estado.equals("RESUELTO")) {
            return consultaRepository.findBy_estadoConsultaAndUsername(Estadoconsulta.RESUELTO, username);
        }
        return null;
    }
}
