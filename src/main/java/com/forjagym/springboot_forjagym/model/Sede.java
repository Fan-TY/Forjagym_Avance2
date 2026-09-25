package com.forjagym.springboot_forjagym.model;

public class Sede {
    private String idSede;
    private String nombreSede;
    private String direccion;

    public Sede(String nombreSede, String idSede, String direccion) {
        this.nombreSede = nombreSede;
        this.idSede = idSede;
        this.direccion = direccion;
    }

    public String getId_Sede() {
        return idSede;
    }

    public String getNombre_Sede() {
        return nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }
}
