package com.forjagym.springboot_forjagym.repository.memoria.ADMIN;

import com.forjagym.springboot_forjagym.model.USUARIOS.Administrador;
import com.forjagym.springboot_forjagym.model.USUARIOS.HorarioAdmin;
import com.forjagym.springboot_forjagym.model.USUARIOS.Sede;
import com.forjagym.springboot_forjagym.model.USUARIOS.TipoDocumento;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AdministradorRepositorry {
    private  final List<Administrador> administradores = new ArrayList<>();

    public AdministradorRepositorry() {

        // Sedes de prueba
        Sede sede1 = new Sede(
                "ForjaGym Centro",
                "S001",
                "Av. Principal 123"
        );

        Sede sede2 = new Sede(
                "ForjaGym Norte",
                "S002",
                "Av. Los Olivos 456"
        );
        //horarios
        HorarioAdmin horarioParte1 = new HorarioAdmin("Lunes - Miércoles", "8:00 - 14:00");
        HorarioAdmin horarioParte2 = new HorarioAdmin("Jueves - Viernes", "14:00 - 20:00");
        HorarioAdmin horarioParte3 = new HorarioAdmin("Sábado", "8:00 - 17:00");
        List<HorarioAdmin> horarioAmdmin1 = Arrays.asList(horarioParte1, horarioParte2, horarioParte3);

        HorarioAdmin horario2Parte1 = new HorarioAdmin("Lunes - Miércoles", "8:00 - 14:00");
        HorarioAdmin horario2Parte2 = new HorarioAdmin("Jueves - Viernes", "14:00 - 20:00");
        HorarioAdmin horario2Parte3 = new HorarioAdmin("Sábado", "8:00 - 17:00");
        List<HorarioAdmin> horarioAmdmin2= Arrays.asList(horario2Parte1, horario2Parte2, horario2Parte3);

        // Administrador 1
        Administrador admin1 = new Administrador(
                "123456",
                "Carlos",
                "Ramirez",
                "ADM001",
                "carlos@forjagym.com",
                "999111222",
                "Administrador",
                TipoDocumento.DNI,
                "12345678",
                sede1,
                "Administracion",
                horarioAmdmin1,
                "A001"
        );

        // Administrador 2
        Administrador admin2 = new Administrador(
                "654321",
                "Maria",
                "Lopez",
                "ADM002",
                "maria@forjagym.com",
                "999333444",
                "Recepcionista",
                TipoDocumento.DNI,
                "87654321",
                sede2,
                "Recepcion",
                horarioAmdmin2,
                "A002"
        );

        // Agregamos los administradores a la memoria
        administradores.add(admin1);
        administradores.add(admin2);
    }
    //F4 BUSCAR
    public Administrador buscarAdministrador(String email) {
        for (Administrador administrador : administradores) {
            if (administrador.getEmail().equals(email)) {
                return administrador;
            }
        }
        return null;
    }
    // F4 RETORNAR

    public List<Administrador> getAdministradores() {

        return administradores;
    }
}
