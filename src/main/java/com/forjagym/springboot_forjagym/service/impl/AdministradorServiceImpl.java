package com.forjagym.springboot_forjagym.service.impl;


import com.forjagym.springboot_forjagym.model.USUARIOS.Administrador;
import com.forjagym.springboot_forjagym.repository.memoria.ADMIN.AdministradorRepositorry;
import com.forjagym.springboot_forjagym.service.AdministradorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {
    private final AdministradorRepositorry administradorRepositorry;

    public AdministradorServiceImpl(AdministradorRepositorry administradorRepositorry) {
        this.administradorRepositorry = administradorRepositorry;
    }

    @Override
    public Administrador buscarAdministrador(String email) {
        return administradorRepositorry.buscarAdministrador(email);
    }

    @Override
    public List<Administrador> obtenerAdministradores() {
        return administradorRepositorry.getAdministradores();
    }
}
