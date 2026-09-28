package com.forjagym.springboot_forjagym.repository.memoria.CLIENTE;

import com.forjagym.springboot_forjagym.model.COMPRA.Compra;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompraMemoria {

    private final List<Compra> datos = new ArrayList<>();

    public Compra guardar(Compra compra) {
        datos.add(compra);
        return compra;
    }

    public List<Compra> listarTodas() {
        return datos;
    }

    public Compra buscarPorId(String idCompra) {
        for (Compra c : datos) {
            if (c.getIdCompra().equals(idCompra)) return c;
        }
        return null;
    }
}