package com.forjagym.springboot_forjagym.model.USUARIOS;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioAdmin {
    private DayOfWeek dia;
    private LocalTime horarioInicio;
    private LocalTime horaFin;

    public HorarioAdmin(DayOfWeek dia, LocalTime horarioInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horarioInicio = horarioInicio;
        this.horaFin = horaFin;
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
}
