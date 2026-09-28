package com.forjagym.springboot_forjagym.model.SUSCRIPCIONES;

import java.util.List;

public class PlanEntrenamiento extends Suscripcion {

    public PlanEntrenamiento() {
        super();
    }

    public PlanEntrenamiento(String id, String nombre, double precio,
                             int duracion, EstadoSuscripcion estado,
                             List<Beneficio> beneficios) {

        super(id, nombre, precio, duracion, estado, beneficios);
    }
}