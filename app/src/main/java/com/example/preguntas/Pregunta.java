package com.example.preguntas;

import java.io.Serializable;

public class Pregunta implements Serializable {
    private String p;
    private boolean r;

    public Pregunta (String pregunta, boolean respuesta){
        this.p = pregunta;
        this.r = respuesta;
    }


    public void setPregunta(String p) {
        this.p = p;
    }

    public boolean getRespuesta() {
        return r;
    }

    public void setRespuesta(boolean r) {
        this.r = r;
    }

    public String getPregunta() {
        return p;
    }
}
