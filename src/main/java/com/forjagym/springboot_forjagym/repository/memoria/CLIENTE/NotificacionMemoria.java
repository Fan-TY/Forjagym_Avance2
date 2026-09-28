package com.forjagym.springboot_forjagym.repository.memoria.CLIENTE;

import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.EstadoNotificacion;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.Notificacion;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.TipoNotificacion;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.TipoPublico;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificacionMemoria {

    private final List<Notificacion> datos = new ArrayList<>();

    public NotificacionMemoria() {
        datos.add(new Notificacion("N001", "Nuevo Plan de entrenamiento Funcional Grupal",
                TipoNotificacion.NUEVA_SUSCRIPCION, TipoPublico.TODOS, EstadoNotificacion.ENVIADO,
                "Entrenamiento en grupo enfocado en fuerza y movilidad.",
                LocalDate.of(2026, 8, 25)));

        datos.add(new Notificacion("N002", "Suscripción actualizada: Fuerza Total",
                TipoNotificacion.ACTUALZIACION, TipoPublico.TODOS, EstadoNotificacion.ENVIADO,
                "Se modificó la suscripción al plan Fuerza total",
                LocalDate.of(2026, 8, 10)));

        datos.add(new Notificacion("N003", "Nuevo plan de entrenamiento: Plan Básico",
                TipoNotificacion.NUEVA_SUSCRIPCION, TipoPublico.TODOS, EstadoNotificacion.BORRADOR,
                "Se modificó la suscripción al plan Fuerza total",
                LocalDate.of(2026, 8, 1)));

        datos.add(new Notificacion("N004", "Recordatorio de pago de suscripción",
                TipoNotificacion.RECORDATORIO, TipoPublico.POR_VENCER, EstadoNotificacion.ENVIADO,
                "Su plan esta por vencer en 7 días",
                LocalDate.of(2026, 8, 1)));
    }

    public Notificacion guardar(Notificacion n) {
        datos.add(n);
        return n;
    }

    public List<Notificacion> listarTodas() {
        return datos;
    }

    public Notificacion buscarPorId(String idNoti) {
        for (Notificacion n : datos) {
            if (n.getIdNoti().equals(idNoti)) return n;
        }
        return null;
    }
}