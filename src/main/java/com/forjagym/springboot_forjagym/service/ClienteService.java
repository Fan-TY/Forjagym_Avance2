package com.forjagym.springboot_forjagym.service;

import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente registrar(Cliente cliente);

    List<Cliente> listarTodos();

    Cliente buscarPorId(String idCliente);

    boolean existeEmail(String email);

    Cliente buscarPorEmail(String email);

    Cliente actualizarPerfil(String idCliente, String nombre, String apellidos, String telefono, String contactoEmergencia, String email);
}