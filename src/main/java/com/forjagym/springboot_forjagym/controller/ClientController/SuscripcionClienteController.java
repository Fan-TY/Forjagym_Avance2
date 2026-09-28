package com.forjagym.springboot_forjagym.controller.ClientController;

import com.forjagym.springboot_forjagym.model.COMPRA.EstadoSusCliente;
import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;
import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.Suscripcion;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.service.ClienteService;
import com.forjagym.springboot_forjagym.service.SuscripcionClienteService;
import com.forjagym.springboot_forjagym.service.SuscripcionService;
import com.forjagym.springboot_forjagym.service.CompraService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class SuscripcionClienteController {

    private final SuscripcionService suscripcionService;
    private final SuscripcionClienteService suscripcionClienteService;
    private final ClienteService clienteService;
    private final CompraService compraService;

    public SuscripcionClienteController(
            SuscripcionService suscripcionService,
            SuscripcionClienteService suscripcionClienteService,
            ClienteService clienteService,
            CompraService compraService) {

        this.suscripcionService = suscripcionService;
        this.suscripcionClienteService = suscripcionClienteService;
        this.clienteService = clienteService;
        this.compraService = compraService;
    }

    @GetMapping("/client/inicio")
    public String mostrarInicio(Model model) {

        model.addAttribute(
                "planes",
                suscripcionService.listarPlanesActivos()
        );

        model.addAttribute(
                "membresias",
                suscripcionService.listarMembresiasActivas()
        );

        return "client/inicio";
    }

    @GetMapping("/client/planes")
    public String mostrarPlanes(Model model) {

        model.addAttribute(
                "planes",
                suscripcionService.listarPlanesActivos()
        );

        return "client/sus_planes";
    }

    @GetMapping("/client/membresiasproductos")
    public String mostrarMembresiasProductos(Model model) {

        model.addAttribute(
                "membresias",
                suscripcionService.listarMembresiasActivas()
        );

        return "client/sus_productos";
    }

    @PostMapping("/client/planes/cambiar")
    public String cambiarPlan(
            @RequestParam String idSuscripcion,
            HttpSession sesion,
            RedirectAttributes redirectAttributes) {

        String idCliente =
                (String) sesion.getAttribute("idClienteLogueado");

        if (idCliente == null) {
            return "redirect:/client/login";
        }

        try {

            Cliente cliente =
                    clienteService.buscarPorId(idCliente);

            if (cliente == null) {
                throw new IllegalArgumentException(
                        "No se encontró el cliente"
                );
            }

            Suscripcion suscripcion =
                    suscripcionService.buscarPorId(idSuscripcion);

            if (suscripcion == null) {
                throw new IllegalArgumentException(
                        "No existe el plan seleccionado"
                );
            }

            SuscripcionCliente nueva =
                    new SuscripcionCliente(
                            UUID.randomUUID().toString(),
                            cliente,
                            suscripcion,
                            EstadoSusCliente.PENDIENTE,
                            LocalDate.now(),
                            null,
                            null
                    );

            suscripcionClienteService.registrar(nueva);

            String idCompra = UUID.randomUUID().toString();

            compraService.crearPedido(
                    idCompra,
                    cliente,
                    java.util.List.of(nueva)
            );

            sesion.setAttribute("idCompraActual", idCompra);

            return "redirect:/client/pedido";

        } catch (IllegalArgumentException | IllegalStateException e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );

            return "redirect:/client/planes";
        }
    }
}