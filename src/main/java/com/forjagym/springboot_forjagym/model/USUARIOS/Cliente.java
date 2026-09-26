package com.forjagym.springboot_forjagym.model.USUARIOS;

import java.time.LocalDate;

public class Cliente extends User {
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String contactoEmergencia;
    private Sede sede;
    private TipoDocumento tipoDocumento;
    private String numDocumento;
    private LocalDate fechaNacimiento;
    private String idCliente;

    public Cliente(String password, String nombre, String apellidos, String email, String telefono, String contactoEmergencia, TipoDocumento tipoDocumento, Sede sede, String numDocumento, LocalDate fechaNacimiento, String idCliente) {
        super(password);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.contactoEmergencia = contactoEmergencia;
        this.tipoDocumento = tipoDocumento;
        this.sede = sede;
        this.numDocumento = numDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.idCliente = idCliente;
    }

    @Override
    public boolean validarCredenciales(String identificador, String password) {
        return this.email.equals(identificador) && autenticacionPass(password);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public Sede getSede() {
        return sede;
    }


    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }


    public String getNumDocumento() {
        return numDocumento;
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getIdCliente() {
        return idCliente;
    }
}
