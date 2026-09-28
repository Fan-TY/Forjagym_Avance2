package com.forjagym.springboot_forjagym.service;

import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.Notificacion;

import java.util.List;

// F18: Crear | F19: Leer | F20: Actualizar (contenido, tipo, estado)
// D bloqueado -> no existe eliminar().

public interface NotificacionService {
    Notificacion crear(Notificacion notificacion);                 // F18

    List<Notificacion> listarTodas();                              // F19

    Notificacion buscarPorId(String idNoti);                       // F19

    Notificacion actualizar(String idNoti, String nuevaDescripcion,
                            com.forjagym.springboot_forjagym.model.NOTIFICACIONES.TipoNotificacion nuevoTipo,
                            com.forjagym.springboot_forjagym.model.NOTIFICACIONES.EstadoNotificacion nuevoEstado); // F20

    List<Notificacion> historial();
}