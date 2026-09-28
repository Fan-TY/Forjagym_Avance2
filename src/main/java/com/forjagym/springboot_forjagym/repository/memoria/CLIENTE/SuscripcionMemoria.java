package com.forjagym.springboot_forjagym.repository.memoria.CLIENTE;

import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.Beneficio;
import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.EstadoSuscripcion;
import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.MembresiaProducto;
import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.PlanEntrenamiento;
import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.Suscripcion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SuscripcionMemoria {

    private final List<Suscripcion> suscripciones = new ArrayList<>();

    public SuscripcionMemoria() {
        cargarDatos();
    }

    public Suscripcion guardar(Suscripcion suscripcion) {
        suscripciones.add(suscripcion);
        return suscripcion;
    }

    public Suscripcion buscarPorId(String id) {
        for (Suscripcion suscripcion : suscripciones) {
            if (suscripcion.getId().equals(id)) {
                return suscripcion;
            }
        }

        return null;
    }

    public List<Suscripcion> listarTodos() {
        return suscripciones;
    }

    private void cargarDatos() {

        // =========================
        // PLAN BÁSICO
        // =========================

        List<Beneficio> beneficiosBasico = Arrays.asList(
                new Beneficio(
                        "Sala de pesas",
                        "Acceso a la sala de pesas"
                ),
                new Beneficio(
                        "Zona de cardio",
                        "Acceso a la zona de cardio"
                ),
                new Beneficio(
                        "Evaluación inicial",
                        "Evaluación física inicial"
                )
        );

        guardar(new PlanEntrenamiento(
                "PLAN001",
                "Plan Básico",
                80.00,
                1,
                EstadoSuscripcion.ACTIVO,
                beneficiosBasico
        ));


        // =========================
        // PLAN FUNCIONAL
        // =========================

        List<Beneficio> beneficiosFuncional = Arrays.asList(
                new Beneficio(
                        "Clases grupales",
                        "Clases grupales tres veces por semana"
                ),
                new Beneficio(
                        "Entrenamiento funcional",
                        "Entrenamiento funcional guiado"
                )
        );

        guardar(new PlanEntrenamiento(
                "PLAN002",
                "Funcional Grupal",
                120.00,
                1,
                EstadoSuscripcion.ACTIVO,
                beneficiosFuncional
        ));


        // =========================
        // PLAN FUERZA TOTAL
        // =========================

        List<Beneficio> beneficiosFuerza = Arrays.asList(
                new Beneficio(
                        "Acceso ilimitado",
                        "Acceso ilimitado a zonas y maquinarias"
                ),
                new Beneficio(
                        "Coach personalizado",
                        "Acompañamiento de un coach personalizado"
                ),
                new Beneficio(
                        "Evaluación gratuita",
                        "Evaluación física gratuita"
                )
        );

        guardar(new PlanEntrenamiento(
                "PLAN003",
                "Fuerza Total",
                180.00,
                1,
                EstadoSuscripcion.ACTIVO,
                beneficiosFuerza
        ));


        // =========================
        // MEMBRESÍA PREMIUM
        // =========================

        List<Beneficio> beneficiosPremium = Arrays.asList(
                new Beneficio(
                        "Productos deportivos",
                        "Incluye productos deportivos"
                ),
                new Beneficio(
                        "Acceso al gimnasio",
                        "Acceso a las instalaciones del gimnasio"
                ),
                new Beneficio(
                        "Beneficios exclusivos",
                        "Beneficios especiales para miembros premium"
                )
        );

        guardar(new MembresiaProducto(
                "MEM001",
                "Membresía Premium",
                250.00,
                1,
                EstadoSuscripcion.ACTIVO,
                beneficiosPremium,
                Arrays.asList(
                        "Proteína",
                        "Shaker",
                        "Toalla deportiva"
                )
        ));


        // =========================
        // MEMBRESÍA DEPORTIVA
        // =========================

        List<Beneficio> beneficiosDeportiva = Arrays.asList(
                new Beneficio(
                        "Acceso al gimnasio",
                        "Acceso a las instalaciones del gimnasio"
                ),
                new Beneficio(
                        "Descuentos deportivos",
                        "Descuentos en productos deportivos"
                ),
                new Beneficio(
                        "Promociones exclusivas",
                        "Acceso a promociones especiales"
                )
        );

        guardar(new MembresiaProducto(
                "MEM002",
                "Membresía Deportiva",
                180.00,
                1,
                EstadoSuscripcion.ACTIVO,
                beneficiosDeportiva,
                Arrays.asList(
                        "Shaker",
                        "Toalla deportiva"
                )
        ));


        // =========================
        // MEMBRESÍA BÁSICA
        // =========================

        List<Beneficio> beneficiosBasica = Arrays.asList(
                new Beneficio(
                        "Acceso al gimnasio",
                        "Acceso a las instalaciones del gimnasio"
                ),
                new Beneficio(
                        "Descuento en productos",
                        "Descuento en productos seleccionados"
                )
        );

        guardar(new MembresiaProducto(
                "MEM003",
                "Membresía Básica",
                120.00,
                1,
                EstadoSuscripcion.ACTIVO,
                beneficiosBasica,
                Arrays.asList(
                        "Toalla deportiva"
                )
        ));
    }
}