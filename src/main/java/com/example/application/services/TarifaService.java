package com.example.application.services;

import com.example.application.data.Tarifa;
import com.example.application.data.TarifaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarifaService {
    private static TarifaRepository tarifaRepository = null;

    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    public void save(Tarifa tarifa) throws Exception {
        if (tarifaRepository.findByNombre(tarifa.getNombre()) == null) {
            tarifaRepository.save(tarifa);
        } else {
            throw new Exception("Ya existe una tarifa con ese nombre");
        }
    }

    public void saveinicial(Tarifa tarifa) throws Exception {
        tarifaRepository.save(tarifa);
    }

    public void update(Tarifa tarifa) throws Exception {
        if (tarifaRepository.findByNombre(tarifa.getNombre()) != null) {
            tarifaRepository.save(tarifa);
        } else {
            throw new Exception("No existe una tarifa con ese nombre");
        }
    }

    public void delete(Tarifa tarifa) throws Exception {
        if (tarifaRepository.findByNombre(tarifa.getNombre()) != null) {
            tarifaRepository.delete(tarifa);
        } else {
            throw new Exception("No existe una tarifa con ese nombre");
        }
    }

    public List<Tarifa> findAllEnable() {
        List<Tarifa> tarifaEnable = new ArrayList<>();
        for (Tarifa tarifa : tarifaRepository.findAll()) {
            if (tarifa.getEstado()) {
                tarifaEnable.add(tarifa);
            }
        }
        return tarifaEnable;
    }
    public List<Tarifa> findAll() {
        return tarifaRepository.findAll();
    }

    public static Tarifa findByNombre(String nombre){
        return tarifaRepository.findByNombre(nombre);

    }

    public int count() {
        return (int) tarifaRepository.count();
    }


}
