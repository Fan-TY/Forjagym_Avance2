package com.forjagym.springboot_forjagym.controller.ClientController;

import com.forjagym.springboot_forjagym.model.NOTIFICACIONES.NotificacionCliente;
import com.forjagym.springboot_forjagym.service.NotificacionClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NotificacionClienteController {

    private final NotificacionClienteService notificacionClienteService;

    public NotificacionClienteController(NotificacionClienteService notificacionClienteService) {
        this.notificacionClienteService = notificacionClienteService;
    }

    @GetMapping("/client/notificaciones")
    public String mostrarNotificaciones(
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String tipo,
            Model model,
            HttpSession sesion) {

        String idCliente = (String) sesion.getAttribute("idClienteLogueado");

        if (idCliente == null) {
            return "redirect:/client/login";
        }

        List<NotificacionCliente> notificaciones =
                notificacionClienteService.listarParaCliente(
                        idCliente,
                        buscar,
                        tipo
                );

        model.addAttribute("notificaciones", notificaciones);
        model.addAttribute("buscar", buscar);
        model.addAttribute(
                "tipoSeleccionado",
                tipo == null || tipo.isBlank() ? "TODOS" : tipo
        );

        return "client/Notificaciones";
    }

    @PostMapping("/client/notificaciones/leido")
    public String marcarLeido(
            @RequestParam String idNoti,
            HttpSession sesion,
            RedirectAttributes redirectAttributes) {

        String idCliente = (String) sesion.getAttribute("idClienteLogueado");

        if (idCliente == null) {
            return "redirect:/client/login";
        }

        try {
            notificacionClienteService.marcarLeido(idCliente, idNoti);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/client/notificaciones";
    }

    @PostMapping("/client/notificaciones/ocultar")
    public String ocultar(
            @RequestParam String idNoti,
            HttpSession sesion,
            RedirectAttributes redirectAttributes) {

        String idCliente = (String) sesion.getAttribute("idClienteLogueado");

        if (idCliente == null) {
            return "redirect:/client/login";
        }

        try {
            notificacionClienteService.ocultar(idCliente, idNoti);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/client/notificaciones";
    }
}