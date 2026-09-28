package com.forjagym.springboot_forjagym.controller.ClientController;

import com.forjagym.springboot_forjagym.model.USUARIOS.Cliente;
import com.forjagym.springboot_forjagym.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginClienteController {
    private final ClienteService clienteService;

    public LoginClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/client/login")
    public String mostrarLogin() {
        return "client/Login_cliente";
    }

    @PostMapping("/client/login")
    public String login(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes, HttpSession sesion) {
        Cliente cliente = clienteService.buscarPorEmail(email);
        if (cliente == null || !cliente.validarCredenciales(email, password)) {
            redirectAttributes.addFlashAttribute("error", "Correo o contraseña incorrecto");
            return "redirect:/client/login";
        }
        sesion.setAttribute("idClienteLogueado", cliente.getIdCliente());
        return "redirect:/client/inicio";
    }

    @GetMapping("/client/logout")
    public String logout(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/client/login";
    }

}
