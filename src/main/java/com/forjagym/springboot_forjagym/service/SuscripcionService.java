package com.forjagym.springboot_forjagym.service;

import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.*;

import java.util.List;

public interface SuscripcionService {
    //F5
    Suscripcion crear(Suscripcion suscripcion);

    //F6
    Suscripcion buscarPorId(String ID);

    List<Suscripcion> listarTodos();

    //F7
    Suscripcion Actualizar(String id,
                           String nombre,
                           double precio,
                           int duracion,
                           EstadoSuscripcion estado,
                           List<Beneficio> beneficios);

    Suscripcion Actualizar(String id,
                           String nombre,
                           double precio,
                           int duracion,
                           Object estado,
                           List<Beneficio>
                                   beneficios);

    List<PlanEntrenamiento> listarPlanesActivos();

    List<MembresiaProducto> listarMembresiasActivas();

}
