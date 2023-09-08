package cl.usm.hdd.ejercicioTipoCertamen1.services;

import cl.usm.hdd.ejercicioTipoCertamen1.controllers.SolicitudController;
import cl.usm.hdd.ejercicioTipoCertamen1.entities.Solicitud;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SolicitudServiceImpl implements SolicitudService {


    private static List<Solicitud> solicitudes = new ArrayList<>();

    public Solicitud crear(Solicitud solicitud) {
        solicitudes.add(solicitud);
        return solicitud;
    }

    public List<Solicitud> obtener() {
        return solicitudes;
    }

    public List<Solicitud> filtrar(String tipo) {
        return solicitudes.stream().
                filter(s->s.getTipoSolicitud().equalsIgnoreCase(tipo)).collect(Collectors.toList());
    }
}
