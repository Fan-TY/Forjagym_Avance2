package com.forjagym.springboot_forjagym.model.COMPRA;

import com.forjagym.springboot_forjagym.model.Visibilidad;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;

import java.time.LocalDateTime;

public class Boleta {
    private String numBoleta;
    private Compra compra;
    private Cliente cliente;
    private double igv;
    private double subtotal;
    private double total;
    private Visibilidad visibilidad;
    private LocalDateTime fechaEmision;


    public Boleta(String numBoleta, Compra compra, Cliente cliente, double igv, double total, double subtotal, Visibilidad visibilidad, LocalDateTime fechaEmision) {
        this.numBoleta = numBoleta;
        this.compra = compra;
        this.cliente = cliente;
        this.total = total;
        this.subtotal = subtotal;
        this.visibilidad = visibilidad;
        this.fechaEmision = fechaEmision;
        this.igv = igv;
    }

    public String getNumBoleta() {
        return numBoleta;
    }

    public Compra getCompra() {
        return compra;
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

    public double getIgv() {
        return igv;
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
