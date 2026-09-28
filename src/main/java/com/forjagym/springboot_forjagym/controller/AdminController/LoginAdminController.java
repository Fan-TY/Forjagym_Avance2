package com.forjagym.springboot_forjagym.controller.AdminController;

import com.forjagym.springboot_forjagym.model.USUARIOS.Administrador;
import com.forjagym.springboot_forjagym.service.AdministradorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginAdminController {

    private final AdministradorService administradorService;

    public LoginAdminController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping("/admin/login")
    public String mostrarLogin() {
        return "admin/Login_admin";
    }

    // El formulario 
    @PostMapping("/admin/login")
    public String login(@RequestParam String codigo, @RequestParam String password,
                        HttpSession sesion, Model model) {
        for (Administrador admin : administradorService.obtenerAdministradores()) {
            if (admin.getCod().equalsIgnoreCase(codigo)
                    && admin.validarCredenciales(admin.getEmail(), password)) {
                sesion.setAttribute("idAdminLogueado", admin.getIdAdmin());
                return "redirect:/admin/Contabilidad.html";
            }
        }
        model.addAttribute("error", "Código o contraseña incorrecto");
        return "admin/Login_admin";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/admin/login";
    }
}
