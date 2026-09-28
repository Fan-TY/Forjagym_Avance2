package com.forjagym.springboot_forjagym.service;

import com.forjagym.springboot_forjagym.model.COMPRA.Compra;
import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;
import com.forjagym.springboot_forjagym.model.COMPRA.Tarjeta;

import java.util.List;

// F11-F13 (Pedido) + F14-F16 (Detalle-Compra-Pago): en el modelo real
// ambos viven en la misma clase Compra, por eso es un solo Service.
// D bloqueado en ambos casos -> no existe eliminar().

public interface CompraService {

    // --- Pedido: F11 crear / F12 leer / F13 actualizar ---
    Compra crearPedido(String idCompra, com.forjagym.springboot_forjagym.model.USUARIOS.Cliente cliente,
                       List<SuscripcionCliente> articulosIniciales);

    List<Compra> listarTodas();

    Compra buscarPorId(String idCompra);

    Compra agregarArticulo(String idCompra, SuscripcionCliente articulo);

    Compra quitarArticulo(String idCompra, String idSusCli);

    Compra cambiarEstadoPedido(String idCompra, String nuevoEstado, String motivoRechazo); // APROBADO o RECHAZADO (motivo solo obligatorio si es RECHAZADO)

    // --- Detalle-Compra-Pago: F14 crear / F15 leer (comparte listarTodas/buscarPorId) / F16 actualizar ---
    Compra registrarMetodoPago(String idCompra, Tarjeta tarjeta, boolean esTarjetaNueva);

    Compra cambiarTarjeta(String idCompra, Tarjeta nuevaTarjeta);
}