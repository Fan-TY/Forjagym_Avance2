package com.forjagym.springboot_forjagym.config;

import com.forjagym.springboot_forjagym.model.COMPRA.Compra;
import com.forjagym.springboot_forjagym.model.COMPRA.Tarjeta;
import com.forjagym.springboot_forjagym.model.COMPRA.TipoTarjeta;
import com.forjagym.springboot_forjagym.model.COMPRA.EstadoSusCliente;
import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.PlanEntrenamiento;
import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.model.USUARIOS.TipoDocumento;
import com.forjagym.springboot_forjagym.service.BoletaService;
import com.forjagym.springboot_forjagym.service.ClienteService;
import com.forjagym.springboot_forjagym.service.SuscripcionClienteService;
import com.forjagym.springboot_forjagym.service.CompraService;
import com.forjagym.springboot_forjagym.service.SuscripcionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Component
public class DatosIniciales implements CommandLineRunner {

    private final ClienteService clienteService;
    private final SuscripcionService suscripcionService;
    private final CompraService compraService;
    private final SuscripcionClienteService suscripcionClienteService;
    private final BoletaService boletaService;

    public DatosIniciales(
            ClienteService clienteService,
            SuscripcionService suscripcionService,
            CompraService compraService,
            SuscripcionClienteService suscripcionClienteService,
            BoletaService boletaService) {

        this.clienteService = clienteService;
        this.suscripcionService = suscripcionService;
        this.compraService = compraService;
        this.suscripcionClienteService = suscripcionClienteService;
        this.boletaService = boletaService;
    }

    @Override
    public void run(String... args) {

        // =========================
        // 1. CREAR CLIENTE
        // =========================

        Cliente cliente = clienteService.buscarPorEmail("ana.garcia@gmail.com");

        if (cliente == null) {
            cliente = new Cliente(
                    "123456",
                    "Ana",
                    "García",
                    "ana.garcia@gmail.com",
                    "987654321",
                    "Carlos García",
                    TipoDocumento.DNI,
                    null,
                    "74839201",
                    LocalDate.of(2000, 5, 15),
                    "CLI001"
            );

            clienteService.registrar(cliente);
        }


        // =========================
        // 2. CREAR PLAN
        // =========================

        PlanEntrenamiento fuerzaTotal =
                (PlanEntrenamiento) suscripcionService.buscarPorId("PLAN003");


        // =========================
        // 3. CREAR SUSCRIPCIÓN DEL CLIENTE
        // =========================

        SuscripcionCliente suscripcionCliente = new SuscripcionCliente(
                "SUSCLI001",
                cliente,
                fuerzaTotal,
                EstadoSusCliente.ACTIVA,
                LocalDate.now(),
                null,
                null
        );
        suscripcionClienteService.registrar(suscripcionCliente);


        // =========================
// 4. CREAR COMPRA
// =========================

        compraService.crearPedido(
                "CMP001",
                cliente,
                List.of(suscripcionCliente)
        );

// =========================
// 5. APROBAR COMPRA
// =========================

        compraService.cambiarEstadoPedido(
                "CMP001",
                "APROBADO",
                null
        );

// =========================
// 6. CREAR TARJETA
// =========================

        Tarjeta tarjeta = new Tarjeta(
                "1111111111",
                "123",
                YearMonth.of(2027, 12),
                "ANA GARCIA",
                TipoTarjeta.VISA
        );

// =========================
// 7. REALIZAR PAGO
// =========================

        Compra compra = compraService.registrarMetodoPago(
                "CMP001",
                tarjeta,
                false
        );

// =========================
// 8. GENERAR BOLETA
// =========================

        boletaService.generar(compra);
    }
}
