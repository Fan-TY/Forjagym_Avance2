package com.forjagym.springboot_forjagym.model.USUARIOS;

import java.util.List;

public class Administrador extends User {
    private String idAdmin;
    private String nombre;
    private String apellido;
    private String cod;
    private String email;
    private String tel;
    private String cargo;
    private TipoDocumento tipoDoc;
    private String numDoc;
    private Sede sede;
    private String area;
    private List<HorarioAdmin>horarioChamba;

    public Administrador(String password, String nombre, String apellido, String cod, String email, String tel, String cargo, TipoDocumento tipoDoc, String numDoc, Sede sede, String area, List<HorarioAdmin> horarioChamba, String idAdmin) {
        super(password);
        this.nombre = nombre;
        this.apellido = apellido;
        this.cod = cod;
        this.email = email;
        this.tel = tel;
        this.cargo = cargo;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.sede = sede;
        this.area = area;
        this.horarioChamba = horarioChamba;
        this.idAdmin = idAdmin;
    }

    @Override
    public boolean validarCredenciales(String identificador, String password) {
        return this.email.equals(identificador) && autenticacionPass(password);
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCod() {
        return cod;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public String getTel() {
        return tel;
    }

    public TipoDocumento getTipoDoc() {
        return tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public Sede getSede() {
        return sede;
    }

    public String getArea() {
        return area;
    }

    public List<HorarioAdmin> getHorarioChamba() {
        return horarioChamba;
    }
}
