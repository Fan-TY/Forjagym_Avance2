package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.COMPRA.Compra;
import com.forjagym.springboot_forjagym.model.COMPRA.EstadoPago;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.EstadoNotificacion;
import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.Notificacion;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.CompraMemoria;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.NotificacionMemoria;
import com.forjagym.springboot_forjagym.service.MetricasService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

// No guarda nada propio: solo LEE de las Memoria de Compra y Notificacion y arma el resumen.


@Service
public class MetricasServiceImpl implements MetricasService {

    private final CompraMemoria compraMemoria;
    private final NotificacionMemoria notificacionMemoria;

    public MetricasServiceImpl(CompraMemoria compraMemoria, NotificacionMemoria notificacionMemoria) {
        this.compraMemoria = compraMemoria;
        this.notificacionMemoria = notificacionMemoria;
    }

    @Override
    public Map<String, Object> obtenerMetricas() {
        double ingresosTotales = 0;
        long pedidosPendientes = 0;
        long pedidosRechazados = 0;

        for (Compra c : compraMemoria.listarTodas()) {
            if (c.getEstadopago() == EstadoPago.PAGADO) {
                ingresosTotales += c.getTotal();
            }
            if (c.getEstadopago() == EstadoPago.PENDIENTE) {
                pedidosPendientes++;
            }
            if (c.getEstadopago() == EstadoPago.RECHAZADO) {
                pedidosRechazados++;
            }
        }

        long notificacionesEnviadas = 0;
        for (Notificacion n : notificacionMemoria.listarTodas()) {
            if (n.getEstadonoti() == EstadoNotificacion.ENVIADO) {
                notificacionesEnviadas++;
            }
        }

        Map<String, Object> metricas = new HashMap<>();
        metricas.put("ingresosTotales", ingresosTotales);
        metricas.put("pedidosPendientes", pedidosPendientes);
        metricas.put("pedidosRechazados", pedidosRechazados);
        metricas.put("notificacionesEnviadas", notificacionesEnviadas);
        return metricas;
    }
}