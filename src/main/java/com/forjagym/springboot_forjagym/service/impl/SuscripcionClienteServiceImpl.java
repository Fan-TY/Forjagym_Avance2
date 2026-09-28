package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.SuscripcionClienteMemoria;
import com.forjagym.springboot_forjagym.service.SuscripcionClienteService;
import org.springframework.stereotype.Service;

@Service
public class SuscripcionClienteServiceImpl implements SuscripcionClienteService {

    private final SuscripcionClienteMemoria suscripcionClienteMemoria;

    public SuscripcionClienteServiceImpl(
            SuscripcionClienteMemoria suscripcionClienteMemoria) {

        this.suscripcionClienteMemoria = suscripcionClienteMemoria;
    }

    @Override
    public SuscripcionCliente registrar(SuscripcionCliente suscripcionCliente) {
        return suscripcionClienteMemoria.guardar(suscripcionCliente);
    }

    @Override
    public SuscripcionCliente buscarActivaPorCliente(String idCliente) {
        return suscripcionClienteMemoria.buscarActivaPorCliente(idCliente);
    }

    @Override
    public SuscripcionCliente cancelarPorCliente(String idCliente) {
        return suscripcionClienteMemoria.cancelarPorCliente(idCliente);
    }

    @Override
    public void activarSuscripcionPagada(
            SuscripcionCliente suscripcionCliente) {

        SuscripcionCliente actual =
                suscripcionClienteMemoria.buscarActivaPorCliente(
                        suscripcionCliente.getCliente().getIdCliente()
                );

        if (actual != null) {
            actual.cancelar();
        }

        suscripcionCliente.activar();
    }
}