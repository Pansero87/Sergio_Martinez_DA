package com.ieseljust.model;

public class Jugador {

    private int id;
    private String nick;
    private String dataRegistre;

    public Jugador() {
    }

    public Jugador(int id, String nick, String dataRegistre) {
        this.id = id;
        this.nick = nick;
        this.dataRegistre = dataRegistre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDataRegistre() {
        return dataRegistre;
    }

    public void setDataRegistre(String dataRegistre) {
        this.dataRegistre = dataRegistre;
    }

}
