package com.forjagym.springboot_forjagym.model.NOTIFICACIONES;

import com.forjagym.springboot_forjagym.model.Visibilidad;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;

import java.time.LocalDateTime;

public class NotificacionCliente {
    private Notificacion noti;
    private Cliente cliente;
    private Visibilidad visibilidad;
    private EstadoLectura estadolectura;
    private LocalDateTime fechaLectura;

    public NotificacionCliente(Notificacion noti, Visibilidad visibilidad, Cliente cliente, EstadoLectura estadolectura, LocalDateTime fechaLectura) {
        this.noti = noti;
        this.visibilidad = visibilidad;
        this.cliente = cliente;
        this.estadolectura = estadolectura;
        this.fechaLectura = fechaLectura;
    }

    public Notificacion getNoti() {
        return noti;
    }

    public void setNoti(Notificacion noti) {
        this.noti = noti;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Visibilidad getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(Visibilidad visibilidad) {
        this.visibilidad = visibilidad;
    }

    public EstadoLectura getEstadolectura() {
        return estadolectura;
    }

    public void setEstadolectura(EstadoLectura estadolectura) {
        this.estadolectura = estadolectura;
    }

    public LocalDateTime getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(LocalDateTime fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    public void ocultar(){
        this.visibilidad = Visibilidad.OCULTO;
    }

    public void marcarLeido(){
        this.estadolectura = EstadoLectura.LEIDO;
        this.fechaLectura = LocalDateTime.now();
    }
}
