package com.forjagym.springboot_forjagym.controller.AdminController;

import com.forjagym.springboot_forjagym.model.USUARIOS.Administrador;
import com.forjagym.springboot_forjagym.repository.memoria.ADMIN.AdministradorRepositorry;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.NotificacionMemoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministradorController {
    private final AdministradorRepositorry administradorRepository;
    private NotificacionMemoria notificacionMemoria = new NotificacionMemoria();

    public AdministradorController(AdministradorRepositorry administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @GetMapping("/admin_perfil")
    public String verPerfil(Model model) {
        Administrador admin = administradorRepository.buscarAdministrador("carlos@forjagym.com");
        model.addAttribute("admin", admin);
        return "admin/admin_perfil";
    }

    @GetMapping("/Noti_admin")
    public String verNotificaciones(Model model) {
        model.addAttribute("notificaciones", notificacionMemoria.listarTodas());
        return "admin/Noti_admin";
    }

    @GetMapping("/Contabilidad.html")
    public String inicioAdministrador() {
        return "admin/Contabilidad";
    }

    @GetMapping("/Registrar_clienteP1.html")
    public String registrarClienteP1() {
        return "admin/Registrar_clientesP1";
    }

    @GetMapping("/Registrar_clienteP2.html")
    public String registrarClienteP2() {
        return "admin/Registrar_clienteP2";
    }

    @GetMapping("/Registrar_clienteP3.html")
    public String registrarClienteP3() {
        return "admin/Registrar_clienteP3";
    }

    @GetMapping("/Registrar_clienteP4.html")
    public String registrarClienteP4() {
        return "admin/Registrar_clienteP4";
    }

    @GetMapping("/Sus_crear.html")
    public String crearSuscripcion() {
        return "admin/Sus_crear";
    }

    @GetMapping("/Sus_administrar.html")
    public String administrarSuscripcion() {
        return "admin/Sus_administrar";
    }

    @GetMapping("/Sus_editar.html")
    public String editarSuscripcion() {
        return "admin/Sus_editar";
    }

    @GetMapping("/Noti_crear.html")
    public String crearNotificacion() {
        return "admin/Noti_crear";
    }

    @GetMapping("/Noti_editar.html")
    public String editarNotificacion() {
        return "admin/Noti_editar";
    }

    @GetMapping("/ADHistorial_Pagos.html")
    public String historialPagos() {
        return "admin/ADHistorial_Pagos";
    }
}