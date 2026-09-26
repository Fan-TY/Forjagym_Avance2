package com.forjagym.springboot_forjagym.model.COMPRA;

import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.Suscripcion;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class suscripcionCliente {
    private String idSusCli;
    private Cliente cliente;
    private Suscripcion suscripcion;
    private EstadoSusCliente estadosuscliente;
    private LocalDate fehcaInicio;
    private LocalDate fechaFin;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public suscripcionCliente(String idSusCli, Cliente cliente, Suscripcion suscripcion, EstadoSusCliente estadosuscliente, LocalDate fehcaInicio, LocalDate fechaFin, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.idSusCli = idSusCli;
        this.cliente = cliente;
        this.suscripcion = suscripcion;
        this.estadosuscliente = estadosuscliente;
        this.fehcaInicio = fehcaInicio;
        this.fechaFin = fechaFin.plusMonths(suscripcion.getDuracion());
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void activar() {
        this.estadosuscliente = EstadoSusCliente.ACTIVA;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public String getIdSusCli() {
        return idSusCli;
    }

    public void setIdSusCli(String idSusCli) {
        this.idSusCli = idSusCli;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public LocalDate getFehcaInicio() {
        return fehcaInicio;
    }

    public void setFehcaInicio(LocalDate fehcaInicio) {
        this.fehcaInicio = fehcaInicio;
    }

    public EstadoSusCliente getEstadosuscliente() {
        return estadosuscliente;
    }

    public void setEstadosuscliente(EstadoSusCliente estadosuscliente) {
        this.estadosuscliente = estadosuscliente;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
