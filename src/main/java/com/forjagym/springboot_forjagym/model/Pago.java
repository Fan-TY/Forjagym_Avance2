package com.forjagym.springboot_forjagym.model;

public class Pago {
    private int id;
    private String usuarioId;
    private double monto;
    private String fecha;
    private String metodoPago;

    public Pago() {
    }

    public Pago(int id, String usuarioId, double monto, String fecha, String metodoPago) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    
}
