package com.forjagym.springboot_forjagym.model;

public class Notificacion {
    private int id;
    private int usuarioId;
    private String mensaje;
    private String fecha;
    private String estado;

    public Notificacion() {
    }

    public Notificacion(int id, int usuarioId, String mensaje, String fecha, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
