package com.example.application.services;

import com.example.application.data.*;
import com.example.application.views.Security.AuthenticatedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContratoService {

    private final TarifaRepository tarifaRepository;
    private final NumeroRepository numeroRepository;
    private final ContratoRepository contratoRepository;
    private final AuthenticatedUser authenticatedUser;
    private final TarjetaRepository TarjetaRepository;
    private final UsuarioRepository UsuarioRepository;

    public ContratoService(UsuarioRepository usuarioRepository, TarifaRepository tarifaRepository, ContratoRepository contratoRepository, AuthenticatedUser authenticatedUser, TarjetaRepository tarjetaRepository, NumeroRepository numeroRepository) {
        this.UsuarioRepository = usuarioRepository;
        this.tarifaRepository = tarifaRepository;
        this.contratoRepository = contratoRepository;
        this.authenticatedUser = authenticatedUser;
        this.TarjetaRepository = tarjetaRepository;
        this.numeroRepository = numeroRepository;
    }


    public Contrato save(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public List<Contrato> findByUsuarioId(UUID id) {
        return contratoRepository.findByUsuarioId(id);
    }

    public Contrato findByUsuario(Usuario usuario) {
        return contratoRepository.findByUsuario(usuario);
    }

    public Contrato[] findAll() {
        return contratoRepository.findAll().toArray(new Contrato[0]);
    }

    @Transactional
    public void contratarTarifa(String numero, String titular, String fechaCaducidad, String cvv, UUID id, String nombretarifa) {
        Tarifa tarifa = tarifaRepository.findByNombre(nombretarifa);
        fechaCaducidad = "1/" + fechaCaducidad;
        Contrato contrato = new Contrato();
        Usuario usuario = UsuarioRepository.findWithContratoById(id);
        contrato.setUsuario(usuario);
        contrato.setTarifa(tarifa);
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumeroTarjeta(numero);
        tarjeta.setFechaCaducidad(LocalDate.parse(fechaCaducidad, DateTimeFormatter.ofPattern("d/MM/yy")));
        tarjeta.setTitular(titular);
        tarjeta.setCvv(cvv);
        contrato.setTarjeta(tarjeta);
        contrato.set_estadoContrato("ACTIVO");
        contrato.setFechaInicio(LocalDate.now());
        contrato.setFechaFin(LocalDate.now().plusMonths(tarifa.getPermanencia()));

        if (tarifa.getMinutosFijo() != 0) {
            contrato.setFijo(generadorNumero(tarifa, "FIJO"));
            numeroRepository.save(contrato.getFijo());
        }

        if (tarifa.getMinutosMovil() != 0) {
            contrato.setMovil(generadorNumero(tarifa, "MOVIL"));
            numeroRepository.save(contrato.getMovil());
        }

        TarjetaRepository.save(tarjeta);
        contratoRepository.save(contrato);
        usuario.setContrato(contrato);
        UsuarioRepository.save(usuario);


    }

    public Numero generadorNumero(Tarifa tarifa, String tipo) {
        CustomerLine fijo = new CustomerLine();
        fijo.setName("FIJO");
        fijo.setSurname("FIJO");
        fijo.setCarrier("UCA");
        if (tipo.equals("FIJO")) {
            fijo.setPhoneNumber("9" + (int) (Math.random() * 10000000 + 10000000));
        } else
            fijo.setPhoneNumber("6" + (int) (Math.random() * 10000000 + 10000000));

        RestTemplate restTemplate = new RestTemplate();
        fijo = restTemplate.postForObject("http://omr-simulator.us-east-1.elasticbeanstalk.com", fijo, CustomerLine.class);
        Numero fijo2 = new Numero();
        fijo2.setNumero(fijo.getPhoneNumber());
        fijo2.setTipo("FIJO");
        fijo2.setConsumido(0);
        if (tipo.equals("FIJO"))
            fijo2.setMax(tarifa.getMinutosFijo());
        else
            fijo2.setMax(tarifa.getMinutosMovil());
        fijo2.setIdapi(fijo.getId());
        fijo2.setCarrier(fijo.getCarrier());
        return fijo2;
    }

    public Contrato findById(UUID id) {

        Optional<Contrato> contrato = contratoRepository.findById(id);
        if (contrato.isPresent()) {
            return contrato.get();
        } else {
            return null;
        }
    }


    public void delete(Contrato c) {
        contratoRepository.delete(c);
    }

    public List<Contrato> findBy_estadoContratoAndUsuarioId(EstadoContrato estadoContrato, UUID id) {
        return contratoRepository.findBy_estadoContratoAndUsuarioId(estadoContrato, id);
    }
}
