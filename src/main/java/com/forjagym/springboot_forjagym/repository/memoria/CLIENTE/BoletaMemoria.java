package com.forjagym.springboot_forjagym.repository.memoria.CLIENTE;

import com.forjagym.springboot_forjagym.model.COMPRA.Boleta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoletaMemoria {
    private final List<Boleta> boletas = new ArrayList<>();

    public Boleta guardar(Boleta boleta) {
        boletas.add(boleta);
        return boleta;
    }

    public Boleta buscarPorId(String numBoleta) {
        for (Boleta boleta : boletas) {
            if (boleta.getNumBoleta().equals(numBoleta)) {
                return boleta;
            }
        }
        return null;
    }

    //LISTAR
    public List<Boleta> listarTodos() {
        return boletas;
    }

}

