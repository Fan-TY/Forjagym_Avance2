package com.forjagym.springboot_forjagym.service;
import com.forjagym.springboot_forjagym.model.COMPRA.Boleta;
import com.forjagym.springboot_forjagym.model.COMPRA.Compra;
import com.forjagym.springboot_forjagym.model.Visibilidad;

import java.util.List;

public interface BoletaService {


    // F08 - Generar boleta a partir de una compra
    Boleta generar(Compra compra);

    // F09 - Buscar boleta por número
    Boleta buscarPorId(String numBoleta);

    // F09 - Listar todas las boletas
    List<Boleta> listarTodos();

    // F10 - Actualizar visibilidad de la boleta
    Boleta actualizarVisibilidad(String numBoleta, Visibilidad nuevaVisibilidad);
}
