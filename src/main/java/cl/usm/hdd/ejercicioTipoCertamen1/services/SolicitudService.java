package cl.usm.hdd.ejercicioTipoCertamen1.services;

import cl.usm.hdd.ejercicioTipoCertamen1.entities.Solicitud;

import java.util.List;

public interface SolicitudService {
    public Solicitud crear(Solicitud solicitud);
    public List<Solicitud> obtener();
    public List<Solicitud> filtrar(String tipo);
}
