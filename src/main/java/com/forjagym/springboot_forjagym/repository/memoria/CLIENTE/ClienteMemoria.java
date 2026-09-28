package com.forjagym.springboot_forjagym.repository.memoria.CLIENTE;

import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.model.USUARIOS.TipoDocumento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Esta es la "BD dummy" de Cliente: solo guarda y devuelve datos,
// no valida nada (eso vive en ClienteServiceImpl).

@Repository
public class ClienteMemoria {

    private final List<Cliente> datos = new ArrayList<>();

    public ClienteMemoria() {

        datos.add(new Cliente(
                "123456",
                "Ana",
                "García",
                "ana.garcia@gmail.com",
                "987654321",
                "Carlos García",
                TipoDocumento.DNI,
                null,
                "74839201",
                java.time.LocalDate.of(2000, 5, 15),
                "CLI001"
        ));

        datos.add(new Cliente(
                "123456",
                "Carlos",
                "Ramírez",
                "carlos.ramirez@gmail.com",
                "986543210",
                "María Ramírez",
                TipoDocumento.DNI,
                null,
                "72518463",
                java.time.LocalDate.of(1998, 8, 22),
                "CLI002"
        ));

        datos.add(new Cliente(
                "123456",
                "María",
                "López",
                "maria.lopez@gmail.com",
                "985123456",
                "Pedro López",
                TipoDocumento.DNI,
                null,
                "71234568",
                java.time.LocalDate.of(2002, 2, 10),
                "CLI003"
        ));

        datos.add(new Cliente(
                "123456",
                "Juan",
                "Torres",
                "juan.torres@gmail.com",
                "984567123",
                "Laura Torres",
                TipoDocumento.DNI,
                null,
                "75678912",
                java.time.LocalDate.of(1995, 11, 30),
                "CLI004"
        ));

        datos.add(new Cliente(
                "123456",
                "Sofía",
                "Martínez",
                "sofia.martinez@gmail.com",
                "983456789",
                "Diego Martínez",
                TipoDocumento.DNI,
                null,
                "76981234",
                java.time.LocalDate.of(2001, 7, 5),
                "CLI005"
        ));
    }

    public Cliente guardar(Cliente cliente) {
        datos.add(cliente);
        return cliente;
    }

    public List<Cliente> listarTodos() {
        return datos;
    }

    public Cliente buscarPorId(String idCliente) {
        for (Cliente c : datos) {
            if (c.getIdCliente().equals(idCliente)) {
                return c;
            }
        }
        return null;
    }

    public Cliente buscarPorEmail(String email) {
        for (Cliente c : datos) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }
}
