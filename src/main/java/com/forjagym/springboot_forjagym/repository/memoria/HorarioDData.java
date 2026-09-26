package com.forjagym.springboot_forjagym.repository.memoria;

import com.forjagym.springboot_forjagym.model.USUARIOS.HorarioAdmin;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class HorarioDData {
    public static List<HorarioAdmin>generarHorariodiurno(){
        return List.of(
                new HorarioAdmin(DayOfWeek.MONDAY, LocalTime.of(6,0), LocalTime.of(14,0)),
                new HorarioAdmin(DayOfWeek.TUESDAY, LocalTime.of(6, 0), LocalTime.of(14, 0)),
                new HorarioAdmin(DayOfWeek.WEDNESDAY, LocalTime.of(6, 0), LocalTime.of(14, 0)),
                new HorarioAdmin(DayOfWeek.THURSDAY, LocalTime.of(6, 0), LocalTime.of(14, 0)),
                new HorarioAdmin(DayOfWeek.FRIDAY, LocalTime.of(6, 0), LocalTime.of(14, 0))
        );
    }

    public static List<HorarioAdmin>generarHorarionocturno(){
        return List.of(
                new HorarioAdmin(DayOfWeek.MONDAY, LocalTime.of(14,0), LocalTime.of(22,0)),
                new HorarioAdmin(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(22, 0)),
                new HorarioAdmin(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(22, 0)),
                new HorarioAdmin(DayOfWeek.THURSDAY, LocalTime.of(14, 0), LocalTime.of(22, 0)),
                new HorarioAdmin(DayOfWeek.FRIDAY, LocalTime.of(14, 0), LocalTime.of(22, 0))
        );
    }
}
