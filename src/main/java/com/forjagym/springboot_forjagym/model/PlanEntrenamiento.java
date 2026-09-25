package com.forjagym.springboot_forjagym.model;

import java.util.List;

public class PlanEntrenamiento extends Suscripcion {

    public PlanEntrenamiento() {
        super();
    }

    public PlanEntrenamiento(int id, String nombre, double precio,
                             int duracion, EstadoSuscripcion estado,
                             List<Beneficio> beneficios) {

        super(id, nombre, precio, duracion, estado, beneficios);
    }
}