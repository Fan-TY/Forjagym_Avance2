package com.forjagym.springboot_forjagym.model;

import java.time.YearMonth;

public class Tarjeta {
    private String numTarjeta;
    private String numCVV;
    private YearMonth fechaVen;
    private String nombreTitular;
    private TipoTarjeta tipotarjeta;

    public Tarjeta(String numTarjeta, String numCVV, YearMonth fechaVen, String nombreTitular, TipoTarjeta tipotarjeta) {
        this.numTarjeta = numTarjeta;
        this.numCVV = numCVV;
        this.fechaVen = fechaVen;
        this.nombreTitular = nombreTitular;
        this.tipotarjeta = tipotarjeta;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public String getNumCVV() {
        return numCVV;
    }

    public YearMonth getFechaVen() {
        return fechaVen;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public TipoTarjeta getTipotarjeta() {
        return tipotarjeta;
    }
}
