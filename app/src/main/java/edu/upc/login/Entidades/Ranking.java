package edu.upc.login.Entidades;

import java.io.Serializable;

public class Ranking implements Serializable {

    private String username;
    private int puntos;
    private int imagen;

    public Ranking(){}

    public Ranking ( String username, int puntos, int imagen){
        this();
        this.username=username;
        this.puntos=puntos;
        this.imagen=imagen;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
