package com.example.preguntas;

public class Usuario {
    String usuario;
    String contrasenia;
    String nombresCompletos;

    public Usuario(String usuario, String contrasenia, String nombresCompletos) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombresCompletos = nombresCompletos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }
}
