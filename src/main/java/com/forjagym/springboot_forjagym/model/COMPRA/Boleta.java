package com.forjagym.springboot_forjagym.model.COMPRA;

import com.forjagym.springboot_forjagym.model.Visibilidad;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;

import java.time.LocalDateTime;

public class Boleta {
    private String numBoleta;
    private compra Compra;
    private Cliente cliente;
    private double subtotal;
    private double total;
    private Visibilidad visibilidad;
    private LocalDateTime fechaEmision;

    public String getNumBoleta() {
        return numBoleta;
    }

    public compra getCompra() {
        return Compra;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    public Visibilidad getVisibilidad() {
        return visibilidad;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setVisibilidad(Visibilidad visibilidad) {
        this.visibilidad = visibilidad;
    }

    public void ocultar(){
        this.visibilidad= Visibilidad.OCULTO;
    }
}
