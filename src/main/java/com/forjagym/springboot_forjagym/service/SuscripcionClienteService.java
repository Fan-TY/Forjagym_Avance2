package com.forjagym.springboot_forjagym.service;

import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;

public interface SuscripcionClienteService {

    SuscripcionCliente registrar(SuscripcionCliente suscripcionCliente);

    SuscripcionCliente buscarActivaPorCliente(String idCliente);

    SuscripcionCliente cancelarPorCliente(String idCliente);

    void activarSuscripcionPagada(SuscripcionCliente suscripcionCliente);
}