package com.forjagym.springboot_forjagym.model.USUARIOS;

public class Sede {
    private String idSede;
    private String nombreSede;
    private String direccion;

    public Sede(String nombreSede, String idSede, String direccion) {
        this.nombreSede = nombreSede;
        this.idSede = idSede;
        this.direccion = direccion;
    }

    public String getIdSede() {
        return idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }
}
