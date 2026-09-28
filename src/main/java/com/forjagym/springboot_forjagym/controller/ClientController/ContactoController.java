package com.forjagym.springboot_forjagym.controller.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactoController {
    @GetMapping("/client/contacto")
    public String mostrarContacto() {
        return "client/Contacto";
    }
}
