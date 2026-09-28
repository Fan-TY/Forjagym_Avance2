package com.forjagym.springboot_forjagym.model.USUARIOS;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioAdmin {
    private String dias;
    private String horas;

    public HorarioAdmin(String dias, String horas) {
        this.dias = dias;
        this.horas = horas;
    }

    public String getDias() { return dias; }

    public String getHoras() { return horas; }
}
