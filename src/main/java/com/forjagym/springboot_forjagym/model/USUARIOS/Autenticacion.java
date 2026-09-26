package com.forjagym.springboot_forjagym.model.USUARIOS;

public interface Autenticacion {
    boolean validarCredenciales(String identificador, String password);
}
