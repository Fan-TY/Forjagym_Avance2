package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.EstadoLectura;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.EstadoNotificacion;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.Notificacion;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.NotificacionCliente;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.TipoNotificacion;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.model.Visibilidad;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.NotificacionClienteMemoria;
import com.forjagym.springboot_forjagym.service.ClienteService;
import com.forjagym.springboot_forjagym.service.NotificacionClienteService;
import com.forjagym.springboot_forjagym.service.NotificacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionClienteServiceImpl implements NotificacionClienteService {
    private final NotificacionService notificacionService;
    private final NotificacionClienteMemoria notificacionClienteMemoria;
    private final ClienteService clienteService;

    public NotificacionClienteServiceImpl(NotificacionService notificacionService, NotificacionClienteMemoria notificacionClienteMemoria, ClienteService clienteService) {
        this.notificacionService = notificacionService;
        this.notificacionClienteMemoria = notificacionClienteMemoria;
        this.clienteService = clienteService;
    }

    @Override
    public List<NotificacionCliente> listarParaCliente(
            String idCliente,
            String buscar,
            String tipo) {

        Cliente cliente = clienteService.buscarPorId(idCliente);

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }

        // Crear la relación cliente-notificación
        for (Notificacion n : notificacionService.listarTodas()) {

            if (n.getEstadonoti() != EstadoNotificacion.ENVIADO) {
                continue;
            }

            if (notificacionClienteMemoria.buscar(idCliente, n.getIdNoti()) == null) {

                NotificacionCliente nc = new NotificacionCliente(
                        n,
                        Visibilidad.VISIBLE,
                        cliente,
                        EstadoLectura.SIN_LEER,
                        null
                );

                notificacionClienteMemoria.guardar(nc);
            }
        }

        // Obtener todas las notificaciones visibles del cliente
        List<NotificacionCliente> resultado =
                notificacionClienteMemoria.listarPorCliente(idCliente);

        // =========================
        // FILTRO POR TEXTO
        // =========================

        if (buscar != null && !buscar.isBlank()) {

            String texto = buscar.toLowerCase().trim();

            resultado.removeIf(nc -> {

                String titulo = nc.getNoti().getTitulo() != null
                        ? nc.getNoti().getTitulo().toLowerCase()
                        : "";

                String descripcion = nc.getNoti().getDescripcion() != null
                        ? nc.getNoti().getDescripcion().toLowerCase()
                        : "";

                return !titulo.contains(texto)
                        && !descripcion.contains(texto);
            });
        }

        // =========================
        // FILTRO POR TIPO
        // =========================

        if (tipo != null && !tipo.isBlank() && !tipo.equals("TODOS")) {

            try {

                TipoNotificacion tipoSeleccionado =
                        TipoNotificacion.valueOf(tipo);

                resultado.removeIf(nc ->
                        nc.getNoti().getTiponoti() != tipoSeleccionado
                );

            } catch (IllegalArgumentException e) {
                // Si llega un tipo inválido, simplemente no filtramos.
            }
        }

        return resultado;
    }

    @Override
    public NotificacionCliente marcarLeido(String idCliente, String idNoti) {
        NotificacionCliente nc = notificacionClienteMemoria.buscar(idCliente, idNoti);
        if (nc == null) {
            throw new IllegalArgumentException("Esa notificación no existe");
        }
        nc.marcarLeido();
        return nc;
    }

    @Override
    public NotificacionCliente ocultar(String idCliente, String idNoti) {
        NotificacionCliente nc = notificacionClienteMemoria.buscar(idCliente, idNoti);
        if (nc == null) {
            throw new IllegalArgumentException("Esa notificación no existe");
        }
        nc.ocultar();
        return nc;
    }
}
