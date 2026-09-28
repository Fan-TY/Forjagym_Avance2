package com.forjagym.springboot_forjagym.service;

import com.forjagym.springboot_forjagym.model.USUARIOS.Administrador;

import java.util.List;

public interface AdministradorService {
    Administrador buscarAdministrador(String email);

    List<Administrador> obtenerAdministradores();
}
