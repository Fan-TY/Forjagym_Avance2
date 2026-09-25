package com.forjagym.springboot_forjagym.model;

import java.util.List;

public class MembresiaProducto extends Suscripcion {

    private List<String> productos;

    public MembresiaProducto() {
        super();
    }

    public MembresiaProducto(int id, String nombre, double precio,
                             int duracion, EstadoSuscripcion estado,
                             List<Beneficio> beneficios,
                             List<String> productos) {

        super(id, nombre, precio, duracion, estado, beneficios);
        this.productos = productos;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
}