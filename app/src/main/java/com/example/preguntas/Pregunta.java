package com.example.preguntas;

import java.io.Serializable;

public class Pregunta implements Serializable {
    public String pregunta;
    public String respuesta;

    public Pregunta (String p, String r){
        this.pregunta = p ;
        this.respuesta = r;
    }


    public void setPregunta(String p) {
        this.pregunta = p;
    }

    public void setRespuesta(String r) {
        this.respuesta = r;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {return respuesta;}


}
