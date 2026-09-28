package com.forjagym.springboot_forjagym.service;

import java.util.Map;

// F17: Leer métricas clave. Solo lectura -> no hay crear/editar/eliminar.

public interface MetricasService {
    Map<String, Object> obtenerMetricas();
}