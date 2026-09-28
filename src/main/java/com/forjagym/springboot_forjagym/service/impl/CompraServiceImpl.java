package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.COMPRA.Compra;
import com.forjagym.springboot_forjagym.model.COMPRA.EstadoPago;
import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;
import com.forjagym.springboot_forjagym.model.COMPRA.Tarjeta;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.CompraMemoria;
import com.forjagym.springboot_forjagym.service.CompraService;
import com.forjagym.springboot_forjagym.service.TarjetaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraMemoria compraMemoria;
    private final TarjetaService tarjetaservice;

    public CompraServiceImpl(CompraMemoria compraMemoria, TarjetaService tarjetaservice) {
        this.compraMemoria = compraMemoria;
        this.tarjetaservice = tarjetaservice;
    }

    // ---------- F11: Crear pedido ----------
    @Override
    public Compra crearPedido(String idCompra, Cliente cliente, List<SuscripcionCliente> articulosIniciales) {
        if (articulosIniciales == null || articulosIniciales.isEmpty()) {
            throw new IllegalArgumentException("Un pedido necesita al menos un producto o plan");
        }
        double total = calcularTotal(articulosIniciales);
        Compra compra = new Compra(idCompra, new ArrayList<>(articulosIniciales), null, cliente,
                total, EstadoPago.PENDIENTE, null);
        return compraMemoria.guardar(compra);
    }

    // ---------- F12: Leer pedido ----------
    @Override
    public List<Compra> listarTodas() {
        return compraMemoria.listarTodas();
    }

    @Override
    public Compra buscarPorId(String idCompra) {
        return compraMemoria.buscarPorId(idCompra);
    }

    // ---------- F13: Actualizar pedido ----------
    @Override
    public Compra agregarArticulo(String idCompra, SuscripcionCliente articulo) {
        Compra compra = obligatorioPendiente(idCompra);
        compra.getArticulos().add(articulo);
        compra.setTotal(calcularTotal(compra.getArticulos()));
        return compra;
    }

    @Override
    public Compra quitarArticulo(String idCompra, String idSusCli) {
        Compra compra = obligatorioPendiente(idCompra);
        SuscripcionCliente aEliminar = null;
        for (SuscripcionCliente item : compra.getArticulos()) {
            if (item.getIdSusCli().equals(idSusCli)) {
                aEliminar = item;
                break;
            }
        }
        if (aEliminar == null) {
            throw new IllegalArgumentException("Ese producto/plan no está en el pedido");
        }
        compra.getArticulos().remove(aEliminar);
        if (compra.getArticulos().isEmpty()) {
            throw new IllegalStateException("El pedido no puede quedar sin productos");
        }
        compra.setTotal(calcularTotal(compra.getArticulos()));
        return compra;
    }

    @Override
    public Compra cambiarEstadoPedido(String idCompra, String nuevoEstado, String motivoRechazo) {
        Compra compra = buscarPorId(idCompra);
        if (compra == null) return null;
        if (!"APROBADO".equalsIgnoreCase(nuevoEstado) && !"RECHAZADO".equalsIgnoreCase(nuevoEstado)) {
            throw new IllegalArgumentException("Estado inválido, solo APROBADO o RECHAZADO");
        }
        if (compra.getEstadopago() != EstadoPago.PENDIENTE) {
            throw new IllegalStateException("Solo se puede aprobar/rechazar un pedido PENDIENTE");
        }
        if ("RECHAZADO".equalsIgnoreCase(nuevoEstado)) {
            if (motivoRechazo == null || motivoRechazo.isBlank()) {
                throw new IllegalArgumentException("Debe indicar un motivo de rechazo");
            }
            compra.setMotivoRechazo(motivoRechazo);
        }
        compra.setEstadopago(EstadoPago.valueOf(nuevoEstado.toUpperCase()));
        return compra;
    }

    // ---------- F14: Crear compra (agregar tarjeta/método de pago) ----------
    @Override
    public Compra registrarMetodoPago(String idCompra, Tarjeta tarjeta, boolean esTarjetaNueva) {
        Compra compra = buscarPorId(idCompra);
        if (compra == null) return null;
        if (compra.getEstadopago() != EstadoPago.APROBADO) {
            throw new IllegalStateException("El pedido debe estar APROBADO antes de registrar el pago");
        }

        if (esTarjetaNueva) {
            tarjetaservice.registrarTarjeta(tarjeta);
        }
        tarjetaservice.validar(tarjeta, compra.getTotal());
        compra.setTarjeta(tarjeta);
        compra.setEstadopago(EstadoPago.PAGADO);
        return compra;
    }

    // ---------- F15: Leer compra (ya cubierto por listarTodas/buscarPorId) ----------

    // ---------- F16: Actualizar compra (cambiar de tarjeta) ----------
    @Override
    public Compra cambiarTarjeta(String idCompra, Tarjeta nuevaTarjeta) {
        Compra compra = buscarPorId(idCompra);
        if (compra == null) return null;
        if (compra.getTarjeta() == null) {
            throw new IllegalStateException("Este pedido todavía no tiene un método de pago registrado");
        }
        tarjetaservice.validar(nuevaTarjeta, compra.getTotal());
        compra.setTarjeta(nuevaTarjeta);
        return compra;
    }

    // ---------- Ayudantes internos (no streams) ----------
    private double calcularTotal(List<SuscripcionCliente> articulos) {
        double total = 0;
        for (SuscripcionCliente item : articulos) {
            total += item.getSuscripcion().getPrecio();
        }
        return total;
    }

    private Compra obligatorioPendiente(String idCompra) {
        Compra compra = buscarPorId(idCompra);
        if (compra == null) {
            throw new IllegalArgumentException("No existe un pedido con ese id");
        }
        if (compra.getEstadopago() != EstadoPago.PENDIENTE) {
            throw new IllegalStateException("Solo se puede modificar un pedido PENDIENTE");
        }
        return compra;
    }
}