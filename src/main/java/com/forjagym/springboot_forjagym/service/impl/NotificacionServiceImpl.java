package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.EstadoNotificacion;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.Notificacion;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.TipoNotificacion;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.NotificacionMemoria;
import com.forjagym.springboot_forjagym.service.NotificacionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionMemoria notificacionMemoria;

    public NotificacionServiceImpl(NotificacionMemoria notificacionMemoria) {
        this.notificacionMemoria = notificacionMemoria;
    }

    @Override
    public Notificacion crear(Notificacion n) {
        if (n.getTitulo() == null || n.getTitulo().isBlank()) {
            throw new IllegalArgumentException("La notificación necesita un título");
        }
        return notificacionMemoria.guardar(n);
    }

    @Override
    public List<Notificacion> listarTodas() {
        return notificacionMemoria.listarTodas();
    }

    @Override
    public Notificacion buscarPorId(String idNoti) {
        return notificacionMemoria.buscarPorId(idNoti);
    }

    @Override
    public Notificacion actualizar(String idNoti, String nuevaDescripcion, TipoNotificacion nuevoTipo, EstadoNotificacion nuevoEstado) {
        Notificacion existente = buscarPorId(idNoti);
        if (existente == null) return null;
        existente.setDescripcion(nuevaDescripcion);
        existente.setTiponoti(nuevoTipo);
        existente.setEstadonoti(nuevoEstado);
        return existente;
    }

    @Override
    public List<Notificacion> historial() {
        List<Notificacion> copia = new ArrayList<>(notificacionMemoria.listarTodas());
        copia.sort((a, b) -> b.getFecha_envio().compareTo(a.getFecha_envio())); // desc
        return copia;
    }
}