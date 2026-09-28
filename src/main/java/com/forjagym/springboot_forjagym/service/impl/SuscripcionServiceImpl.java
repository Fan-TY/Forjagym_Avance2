package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.*;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.SuscripcionMemoria;
import com.forjagym.springboot_forjagym.service.SuscripcionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuscripcionServiceImpl implements SuscripcionService {
    private SuscripcionMemoria suscripcionRepository;

    public SuscripcionServiceImpl(SuscripcionMemoria suscripcionRepository) {
        this.suscripcionRepository = suscripcionRepository;
    }


    @Override
    public Suscripcion crear(Suscripcion suscripcion) {
        return suscripcionRepository.guardar(suscripcion);
    }

    @Override
    public Suscripcion buscarPorId(String id) {
        return suscripcionRepository.buscarPorId(id);
    }

    @Override
    public List<Suscripcion> listarTodos() {
        return suscripcionRepository.listarTodos();
    }

    @Override
    public Suscripcion Actualizar(String id, String nombre, double precio,
                                  int duracion, EstadoSuscripcion estado,
                                  List<Beneficio> beneficios) {
        Suscripcion suscripcion = suscripcionRepository.buscarPorId(id);

        if (suscripcion == null) {
            return null;
        }
        suscripcion.setNombre(nombre);
        suscripcion.setPrecio(precio);
        suscripcion.setDuracion(duracion);
        suscripcion.setEstado((EstadoSuscripcion) estado);
        suscripcion.setBeneficios(beneficios);
        return suscripcion;
    }

    @Override
    public Suscripcion Actualizar(String id, String nombre, double precio, int duracion, Object estado, List<Beneficio> beneficios) {
        Suscripcion suscripcion = suscripcionRepository.buscarPorId(id);

        if (suscripcion == null) {
            return null;
        }

        suscripcion.setNombre(nombre);
        suscripcion.setPrecio(precio);
        suscripcion.setDuracion(duracion);
        suscripcion.setEstado((EstadoSuscripcion) estado);
        suscripcion.setBeneficios(beneficios);

        return suscripcion;
    }

    @Override
    public List<PlanEntrenamiento> listarPlanesActivos() {
        List<PlanEntrenamiento> resultado = new ArrayList<>();
        for (Suscripcion s : listarTodos()) {
            if (s instanceof PlanEntrenamiento && s.getEstado() == EstadoSuscripcion.ACTIVO) {
                resultado.add((PlanEntrenamiento) s);
            }
        }
        return resultado;
    }

    @Override
    public List<MembresiaProducto> listarMembresiasActivas() {
        List<MembresiaProducto> resultado = new ArrayList<>();
        for (Suscripcion s : listarTodos()) {
            if (s instanceof MembresiaProducto && s.getEstado() == EstadoSuscripcion.ACTIVO) {
                resultado.add((MembresiaProducto) s);
            }
        }
        return resultado;
    }
}
