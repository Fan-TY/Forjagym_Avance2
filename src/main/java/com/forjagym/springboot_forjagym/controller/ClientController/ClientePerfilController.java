package com.forjagym.springboot_forjagym.controller.ClientController;

import com.forjagym.springboot_forjagym.model.COMPRA.Boleta;
import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.model.Visibilidad;
import com.forjagym.springboot_forjagym.service.BoletaService;
import com.forjagym.springboot_forjagym.service.ClienteService;
import com.forjagym.springboot_forjagym.model.COMPRA.SuscripcionCliente;
import com.forjagym.springboot_forjagym.service.SuscripcionClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientePerfilController {
    private final ClienteService clienteService;
    private final BoletaService boletaService;
    private final SuscripcionClienteService suscripcionClienteService;

    public ClientePerfilController(
            ClienteService clienteService,
            BoletaService boletaService,
            SuscripcionClienteService suscripcionClienteService) {

        this.clienteService = clienteService;
        this.boletaService = boletaService;
        this.suscripcionClienteService = suscripcionClienteService;
    }

    @GetMapping("/client/perfil")
    public String mostrarPerfilCliente(HttpSession sesion, Model model) {

        String idCliente = (String) sesion.getAttribute("idClienteLogueado");

        if (idCliente == null) {
            return "redirect:/client/login";
        }

        Cliente cliente = clienteService.buscarPorId(idCliente);

        if (cliente == null) {
            return "redirect:/client/login";
        }

        SuscripcionCliente suscripcionActual =
                suscripcionClienteService.buscarActivaPorCliente(idCliente);

        model.addAttribute("cliente", cliente);
        model.addAttribute("suscripcionActual", suscripcionActual);

        return "client/perfil_Cliente";
    }

    @PostMapping("/client/perfil/actualizar")
    public String actualizarPerfil(HttpSession sesion, @RequestParam String nombre, @RequestParam String apellido,
                                   @RequestParam String telefono, @RequestParam String contactoEmergencia, @RequestParam String email,
                                   RedirectAttributes redirectAttributes) {
        String idCliente = (String) sesion.getAttribute("idClienteLogueado");
        if (idCliente == null) {
            return "redirect:/client/login";
        }
        try {
            clienteService.actualizarPerfil(idCliente, nombre, apellido, telefono, contactoEmergencia, email);
            redirectAttributes.addFlashAttribute("exito", "Perfil actualizado");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/client/perfil";
    }

    @GetMapping("/client/historialcomprobantes")
    public String mostrarHistorial(HttpSession sesion, Model model) {
        String idCliente = (String) sesion.getAttribute("idClienteLogueado");
        if (idCliente == null) {
            return "redirect:/client/login";
        }

        List<Boleta> boletasDelCliente = new ArrayList<>();
        for (Boleta boleta : boletaService.listarTodos()) {
            boolean esDeEsteCliente = boleta.getCompra().getCliente().getIdCliente().equals(idCliente);
            boolean esVisible = boleta.getVisibilidad() == Visibilidad.VISIBLE;
            if (esDeEsteCliente && esVisible) {
                boletasDelCliente.add(boleta);
            }
        }

        model.addAttribute("boletas", boletasDelCliente);
        return "client/Historial_Pago";
    }

    @GetMapping("/client/historialcomprobantes/comprobante")
    public String mostrarComprobante(@RequestParam String numBoleta, HttpSession sesion, Model model,
                                     RedirectAttributes redirectAttributes) {
        String idCliente = (String) sesion.getAttribute("idClienteLogueado");
        if (idCliente == null) {
            return "redirect:/client/login";
        }

        Boleta boleta = boletaService.buscarPorId(numBoleta);
        if (boleta == null || !boleta.getCompra().getCliente().getIdCliente().equals(idCliente)) {
            redirectAttributes.addFlashAttribute("error", "Ese comprobante no existe o no te pertenece");
            return "redirect:/client/historialcomprobantes";
        }

        model.addAttribute("boleta", boleta);
        return "client/Comprobante";
    }

    @PostMapping("/client/perfil/cancelar-plan")
    public String cancelarPlan(
            HttpSession sesion,
            RedirectAttributes redirectAttributes) {

        String idCliente = (String) sesion.getAttribute("idClienteLogueado");

        if (idCliente == null) {
            return "redirect:/client/login";
        }

        SuscripcionCliente suscripcion =
                suscripcionClienteService.cancelarPorCliente(idCliente);

        if (suscripcion == null) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "No tienes un plan activo para cancelar"
            );
        } else {
            redirectAttributes.addFlashAttribute(
                    "exito",
                    "Tu plan ha sido cancelado correctamente"
            );
        }

        return "redirect:/client/perfil";
    }
}