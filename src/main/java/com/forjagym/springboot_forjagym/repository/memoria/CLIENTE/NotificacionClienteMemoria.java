package com.forjagym.springboot_forjagym.repository.memoria.CLIENTE;

import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.NotificacionCliente;
import com.forjagym.springboot_forjagym.model.Visibilidad;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificacionClienteMemoria {
    private final List<NotificacionCliente> datos = new ArrayList<>();


    public NotificacionCliente guardar(NotificacionCliente nc) {
        datos.add(nc);
        return nc;
    }

    public List<NotificacionCliente> listarPorCliente(String idCliente) {
        List<NotificacionCliente> resultado = new ArrayList<>();
        for (NotificacionCliente nc : datos) {
            if (nc.getCliente().getIdCliente().equals(idCliente) && nc.getVisibilidad() == Visibilidad.VISIBLE) {
                resultado.add(nc);
            }
        }
        return resultado;
    }

    public NotificacionCliente buscar(String idCliente, String idNoti) {
        for (NotificacionCliente nc : datos) {
            if (nc.getCliente().getIdCliente().equals(idCliente) && nc.getNoti().getIdNoti().equals(idNoti)) {
                return nc;
            }
        }
        return null;
    }


}
