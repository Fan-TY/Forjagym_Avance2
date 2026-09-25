package com.forjagym.springboot_forjagym.model;

public interface Autenticacion {
    boolean validarCredenciales(String identificador, String password);
}
