package com.forjagym.springboot_forjagym.model.USUARIOS;

public abstract class User implements Autenticacion {
    protected String password;

    public User(String password) {
        this.password = password;
    }

    protected boolean autenticacionPass(String password){
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
