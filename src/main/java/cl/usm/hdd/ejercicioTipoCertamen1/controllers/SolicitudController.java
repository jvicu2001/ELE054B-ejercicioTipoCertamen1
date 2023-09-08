package cl.usm.hdd.ejercicioTipoCertamen1.controllers;

import cl.usm.hdd.ejercicioTipoCertamen1.entities.Solicitud;
import cl.usm.hdd.ejercicioTipoCertamen1.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class SolicitudController {
    @PostMapping("/ingresarSolicitud")
    public ResponseEntity<Solicitud> crear(@RequestBody Solicitud solicitud) {
        if (!esTipoValido(solicitud.getTipoSolicitud())) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Solicitud sol = solicitudService.crear(solicitud);
            return ResponseEntity.ok(sol);
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/verSolicitudes")
    public ResponseEntity<List<Solicitud>> obtener() {
        try {
            return ResponseEntity.ok(this.solicitudService.obtener());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/verSolicitudes/{tipoSolicitud}")
    public ResponseEntity<List<Solicitud>> obtenerPorTipo(@PathVariable String tipoSolicitud) {
        try {
            return ResponseEntity.ok(this.solicitudService.filtrar(tipoSolicitud));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private final Stream<String> tiposValidos = Stream  .of(
            "SolicitudCedula",
            "RetiroCedula",
            "SolicitudCertificadoNac",
            "SolicitudCertificadoDef");

    @Autowired
    private SolicitudService solicitudService;

    /**
     * Verifica que el tipo de solicitud
     * sea válida.
     * @param tipoSolicitud
     * @return boolean
     */
    private boolean esTipoValido(String tipoSolicitud) {
        return (tiposValidos.anyMatch(t->t.equalsIgnoreCase(tipoSolicitud)));
    }

}
