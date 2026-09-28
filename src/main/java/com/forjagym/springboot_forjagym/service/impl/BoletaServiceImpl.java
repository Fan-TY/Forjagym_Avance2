package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.COMPRA.Boleta;
import com.forjagym.springboot_forjagym.model.COMPRA.Compra;
import com.forjagym.springboot_forjagym.model.Visibilidad;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.BoletaMemoria;
import com.forjagym.springboot_forjagym.service.BoletaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoletaServiceImpl implements BoletaService {
    private static final double IGV = 0.18;


    private final BoletaMemoria boletaMemoria;

    private int contador = 0;

    public BoletaServiceImpl(BoletaMemoria boletaMemoria) {
        this.boletaMemoria = boletaMemoria;
    }

    @Override
    public Boleta generar(Compra compra) {
        // El total de la compra ya incluye el IGV, así que hay que separarlo
        double subtotal = compra.getTotal() / (1 + IGV);
        double igv = compra.getTotal() - subtotal;

        contador++;
        String numBoleta = "B" + contador;

        Boleta boleta = new Boleta(numBoleta, compra, igv, compra.getTotal(),
                subtotal, Visibilidad.VISIBLE, LocalDateTime.now());

        return boletaMemoria.guardar(boleta);
    }

    @Override
    public Boleta buscarPorId(String numBoleta) {
        return boletaMemoria.buscarPorId(numBoleta);
    }

    @Override
    public List<Boleta> listarTodos() {
        return boletaMemoria.listarTodos();
    }

    @Override
    public Boleta actualizarVisibilidad(String numBoleta, Visibilidad nuevaVisibilidad) {
        Boleta boleta = buscarPorId(numBoleta);
        if (boleta == null) return null;
        boleta.setVisibilidad(nuevaVisibilidad);
        return boleta;
    }
}