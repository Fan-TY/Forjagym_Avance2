package com.forjagym.springboot_forjagym.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioAdmin {
    private DayOfWeek dia;
    private LocalTime horarioInicio;
    private LocalTime HoraFin;

    public HorarioAdmin(LocalTime horarioInicio, DayOfWeek dia, LocalTime horaFin) {
        this.horarioInicio = horarioInicio;
        this.dia = dia;
        HoraFin = horaFin;
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public LocalTime getHoraFin() {
        return HoraFin;
    }
}
