package com.forjagym.springboot_forjagym.controller.ClientController;

import com.forjagym.springboot_forjagym.model.COMPRA.*;
import com.forjagym.springboot_forjagym.model.SUSCRIPCIONES.Suscripcion;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.service.*;
import com.forjagym.springboot_forjagym.service.SuscripcionClienteService;
import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;
import com.forjagym.springboot_forjagym.model.COMPRA.EstadoSusCliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.UUID;

@Controller
public class CarritoController {

    private static final String ATRIB_ID_COMPRA = "idCompraActual";
    private static final String ATRIB_NUM_BOLETA = "numBoletaActual";

    private final CompraService compraService;
    private final SuscripcionService suscripcionService;
    private final ClienteService clienteService;
    private final BoletaService boletaService;
    private final SuscripcionClienteService suscripcionClienteService;

    public CarritoController(
            CompraService compraService,
            SuscripcionService suscripcionService,
            ClienteService clienteService,
            BoletaService boletaService,
            SuscripcionClienteService suscripcionClienteService) {

        this.compraService = compraService;
        this.suscripcionService = suscripcionService;
        this.clienteService = clienteService;
        this.boletaService = boletaService;
        this.suscripcionClienteService = suscripcionClienteService;
    }

    // PASO 1 - PEDIDO

    @GetMapping("/client/pedido")
    public String mostrarPedido(Model model, HttpSession sesion) {
        String idCliente = (String) sesion.getAttribute("idClienteLogueado");
        if (idCliente == null) {
            return "redirect:/client/login";
        }

        String idCompra = (String) sesion.getAttribute(ATRIB_ID_COMPRA);
        Compra compra = idCompra != null ? compraService.buscarPorId(idCompra) : null;

        model.addAttribute("compra", compra);
        model.addAttribute("carritoVacio", compra == null || compra.getArticulos().isEmpty());
        return "client/Compra_Cliente1";
    }

    @PostMapping("/client/pedido/agregar")
    public String agregarArticulo(@RequestParam String idSuscripcion, HttpSession sesion, RedirectAttributes redirectAttributes) {
        String idCliente = (String) sesion.getAttribute("idClienteLogueado");
        if (idCliente == null) {
            return "redirect:/client/login";
        }

        try {
            Cliente cliente = clienteService.buscarPorId(idCliente);

            if (cliente == null) {
                throw new IllegalArgumentException("No se encontró el cliente logueado");
            }

            Suscripcion suscripcion = suscripcionService.buscarPorId(idSuscripcion);

            if (suscripcion == null) {
                throw new IllegalArgumentException(
                        "No existe la suscripción con ID: " + idSuscripcion
                );
            }
            SuscripcionCliente articulo = new SuscripcionCliente(
                    UUID.randomUUID().toString(),
                    cliente,
                    suscripcion,
                    EstadoSusCliente.ACTIVA,
                    LocalDate.now(),
                    null,
                    null
            );

            String idCompra = (String) sesion.getAttribute(ATRIB_ID_COMPRA);
            Compra compra = idCompra != null ? compraService.buscarPorId(idCompra) : null;

            if (compra == null) {
                idCompra = UUID.randomUUID().toString();
                ArrayList<SuscripcionCliente> articulos = new ArrayList<>();
                articulos.add(articulo);
                compraService.crearPedido(idCompra, cliente, articulos);
                sesion.setAttribute(ATRIB_ID_COMPRA, idCompra);
            } else {
                compraService.agregarArticulo(idCompra, articulo);
            }

            redirectAttributes.addFlashAttribute("exito", "Artículo agregado al carrito");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/client/pedido";
    }

    @PostMapping("/client/pedido/eliminar")
    public String eliminarArticulo(@RequestParam String idSusCli, HttpSession sesion, RedirectAttributes redirectAttributes) {
        String idCompra = (String) sesion.getAttribute(ATRIB_ID_COMPRA);
        if (idCompra == null) {
            return "redirect:/client/pedido";
        }

        try {
            compraService.quitarArticulo(idCompra, idSusCli);
            redirectAttributes.addFlashAttribute("exito", "Artículo eliminado del carrito");
        } catch (IllegalStateException e) {
            sesion.removeAttribute(ATRIB_ID_COMPRA);
            redirectAttributes.addFlashAttribute("exito", "Carrito vaciado");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/client/pedido";
    }


    @PostMapping("/client/pedido/confirmar")
    public String confirmarPedido(HttpSession sesion, RedirectAttributes redirectAttributes) {
        String idCompra = (String) sesion.getAttribute(ATRIB_ID_COMPRA);
        if (idCompra == null) {
            redirectAttributes.addFlashAttribute("error", "Tu carrito está vacío");
            return "redirect:/client/pedido";
        }

        try {
            compraService.cambiarEstadoPedido(idCompra, "APROBADO", null);
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/client/pedido";
        }

        return "redirect:/client/pago";
    }

    // P2 - PAGO

    @GetMapping("/client/pago")
    public String mostrarPago(Model model, HttpSession sesion) {
        String idCompra = (String) sesion.getAttribute(ATRIB_ID_COMPRA);
        Compra compra = idCompra != null ? compraService.buscarPorId(idCompra) : null;

        if (compra == null) {
            return "redirect:/client/pedido";
        }

        model.addAttribute("compra", compra);
        return "client/Compra_Cliente2";
    }

    @PostMapping("/client/pago")
    public String procesarPago(
            @RequestParam String numTarjeta,
            @RequestParam String numCVV,
            @RequestParam int mesVencimiento,
            @RequestParam int anioVencimiento,
            @RequestParam String nombreTitular,
            @RequestParam TipoTarjeta tipoTarjeta,
            @RequestParam(defaultValue = "false") boolean esTarjetaNueva,
            HttpSession sesion,
            RedirectAttributes redirectAttributes) {

        String idCompra = (String) sesion.getAttribute(ATRIB_ID_COMPRA);

        if (idCompra == null) {
            return "redirect:/client/pedido";
        }

        try {

            Tarjeta tarjeta = new Tarjeta(
                    numTarjeta,
                    numCVV,
                    YearMonth.of(anioVencimiento, mesVencimiento),
                    nombreTitular,
                    tipoTarjeta
            );

            Compra compra = compraService.registrarMetodoPago(
                    idCompra,
                    tarjeta,
                    esTarjetaNueva
            );

            // Activar el nuevo plan solamente después del pago
            for (SuscripcionCliente articulo : compra.getArticulos()) {

                if (articulo.getEstadosuscliente() == EstadoSusCliente.PENDIENTE) {

                    suscripcionClienteService.activarSuscripcionPagada(
                            articulo
                    );
                }
            }

            Boleta boleta = boletaService.generar(compra);

            sesion.setAttribute(
                    ATRIB_NUM_BOLETA,
                    boleta.getNumBoleta()
            );

            sesion.setAttribute("boletaActual", boleta);

        } catch (IllegalArgumentException | IllegalStateException e) {

            e.printStackTrace();

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );

            return "redirect:/client/pago";
        }

        return "redirect:/client/confirmacion";
    }

    @GetMapping("/client/confirmacion")
    public String mostrarConfirmacion(Model model, HttpSession sesion) {

        String idCompra = (String) sesion.getAttribute(ATRIB_ID_COMPRA);

        if (idCompra == null) {
            return "redirect:/client/inicio";
        }

        Compra compra = compraService.buscarPorId(idCompra);

        if (compra == null) {
            return "redirect:/client/inicio";
        }

        String numBoleta =
                (String) sesion.getAttribute(ATRIB_NUM_BOLETA);

        Boleta boleta =
                (Boleta) sesion.getAttribute("boletaActual");

        model.addAttribute("compra", compra);
        model.addAttribute("numBoleta", numBoleta);
        model.addAttribute("boleta", boleta);

        return "client/Compra_Cliente3";
    }
}