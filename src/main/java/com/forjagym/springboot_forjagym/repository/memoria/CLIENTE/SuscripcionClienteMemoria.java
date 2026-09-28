package com.forjagym.springboot_forjagym.repository.memoria.CLIENTE;

import com.forjagym.springboot_forjagym.model.COMPRA.EstadoSusCliente;
import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SuscripcionClienteMemoria {

    private final List<SuscripcionCliente> datos = new ArrayList<>();

    public SuscripcionCliente guardar(SuscripcionCliente suscripcionCliente) {
        datos.add(suscripcionCliente);
        return suscripcionCliente;
    }

    public List<SuscripcionCliente> listarTodas() {
        return datos;
    }

    public SuscripcionCliente buscarActivaPorCliente(String idCliente) {

        for (SuscripcionCliente sc : datos) {

            if (sc.getCliente().getIdCliente().equals(idCliente)
                    && sc.getEstadosuscliente() == EstadoSusCliente.ACTIVA) {

                return sc;
            }
        }

        return null;
    }

    public SuscripcionCliente cancelarPorCliente(String idCliente) {

        SuscripcionCliente suscripcion = buscarActivaPorCliente(idCliente);

        if (suscripcion == null) {
            return null;
        }

        suscripcion.cancelar();

        return suscripcion;
    }
}