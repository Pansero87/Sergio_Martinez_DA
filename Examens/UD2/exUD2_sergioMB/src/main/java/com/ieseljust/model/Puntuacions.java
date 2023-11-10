package com.ieseljust.model;

public class Puntuacions {

    private int jugador_id;
    private int joc_id;
    private int puntuacio;

    public Puntuacions() {
    }

    public Puntuacions(int jugador_id, int joc_id, int puntuacio) {
        this.jugador_id = jugador_id;
        this.joc_id = joc_id;
        this.puntuacio = puntuacio;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    public int getJoc_id() {
        return joc_id;
    }

    public void setJoc_id(int joc_id) {
        this.joc_id = joc_id;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

}
