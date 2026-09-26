package com.forjagym.springboot_forjagym.model.SUSCRIPCIONES;

import java.util.List;

public abstract class Suscripcion {

    private int id;
    private String nombre;
    private double precio;
    private int duracion;
    private EstadoSuscripcion estado;
    private List<Beneficio> beneficios;

    public Suscripcion() {
    }

    public Suscripcion(int id, String nombre, double precio, int duracion,
                       EstadoSuscripcion estado, List<Beneficio> beneficios) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.duracion = duracion;
        this.estado = estado;
        this.beneficios = beneficios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public EstadoSuscripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoSuscripcion estado) {
        this.estado = estado;
    }

    public List<Beneficio> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(List<Beneficio> beneficios) {
        this.beneficios = beneficios;
    }
}