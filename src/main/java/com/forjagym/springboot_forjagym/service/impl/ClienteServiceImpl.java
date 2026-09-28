package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.ClienteMemoria;
import com.forjagym.springboot_forjagym.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteMemoria clienteMemoria;


    public ClienteServiceImpl(ClienteMemoria clienteMemoria) {
        this.clienteMemoria = clienteMemoria;
    }

    @Override
    public Cliente registrar(Cliente cliente) {
        // Lógica de negocio: validar antes de guardar.
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }
        if (existeEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese correo");
        }
        return clienteMemoria.guardar(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteMemoria.listarTodos();
    }

    @Override
    public Cliente buscarPorId(String idCliente) {
        return clienteMemoria.buscarPorId(idCliente);
    }

    @Override
    public boolean existeEmail(String email) {
        return clienteMemoria.buscarPorEmail(email) != null;
    }


    @Override
    public Cliente buscarPorEmail(String email) {
        return clienteMemoria.buscarPorEmail(email);
    }

    @Override
    public Cliente actualizarPerfil(String idCliente, String nombre, String apellidos, String telefono, String contactoEmergencia, String email) {
        Cliente cliente = buscarPorId(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setEmail(email);
        cliente.setContactoEmergencia(contactoEmergencia);
        cliente.setTelefono(telefono);
        return cliente;
    }
}