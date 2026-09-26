package com.forjagym.springboot_forjagym.model.COMPRA;

import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;

import java.util.List;

public class Compra {
    private String idCompra;
    private List<SuscripcionCliente> articulos;
    private Tarjeta tarjeta;
    private Cliente cliente;
    private double total;
    private EstadoPago estadopago;
    private String motivoRechazo;

    public Compra(String idCompra, List<SuscripcionCliente> articulos, Tarjeta tarjeta, Cliente cliente, double total, EstadoPago estadopago, String motivoRechazo) {
        this.idCompra = idCompra;
        this.articulos = articulos;
        this.tarjeta = tarjeta;
        this.cliente = cliente;
        this.total = total;
        this.estadopago = estadopago;
        this.motivoRechazo = motivoRechazo;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public List<SuscripcionCliente> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<SuscripcionCliente> articulos) {
        this.articulos = articulos;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoPago getEstadopago() {
        return estadopago;
    }

    public void setEstadopago(EstadoPago estadopago) {
        this.estadopago = estadopago;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }
}
