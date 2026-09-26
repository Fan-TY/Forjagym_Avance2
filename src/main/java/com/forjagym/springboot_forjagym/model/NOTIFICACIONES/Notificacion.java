package com.forjagym.springboot_forjagym.model.NOTIFICACIONES;

import java.time.LocalDate;

public class Notificacion {
    private String idNoti;
    private String titulo;
    private TipoNotificacion tiponoti;
    private TipoPublico tipopublico;
    private EstadoNotificacion estadonoti;
    private String Descripcion;
    private LocalDate fecha_envio;


    public Notificacion(String idNoti, String titulo, TipoNotificacion tiponoti, TipoPublico tipopublico, EstadoNotificacion estadonoti, String descripcion, LocalDate fecha_envio) {
        this.idNoti = idNoti;
        this.titulo = titulo;
        this.tiponoti = tiponoti;
        this.tipopublico = tipopublico;
        this.estadonoti = estadonoti;
        this.Descripcion = descripcion;
        this.fecha_envio = fecha_envio;

    }

    public String getIdNoti() {
        return idNoti;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoNotificacion getTiponoti() {
        return tiponoti;
    }

    public void setTiponoti(TipoNotificacion tiponoti) {
        this.tiponoti = tiponoti;
    }

    public TipoPublico getTipopublico() {
        return tipopublico;
    }

    public void setTipopublico(TipoPublico tipopublico) {
        this.tipopublico = tipopublico;
    }

    public EstadoNotificacion getEstadonoti() {
        return estadonoti;
    }

    public void setEstadonoti(EstadoNotificacion estadonoti) {
        this.estadonoti = estadonoti;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public LocalDate getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(LocalDate fecha_envio) {
        this.fecha_envio = fecha_envio;
    }
}
