package com.forjagym.springboot_forjagym.service;

import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.NotificacionCliente;

import java.util.List;

public interface NotificacionClienteService {
    List<NotificacionCliente> listarParaCliente(String idCliente, String buscar, String tipo);

    NotificacionCliente marcarLeido(String idCliente, String idNoti);

    NotificacionCliente ocultar(String idCliente, String idNoti);
}
