package com.ieseljust.model;

public class Joc {

    private int id;
    private String nom;
    private String descripcio;
    private String genere;

    public Joc() {
    }

    public Joc(int id, String nom, String descripcio, String genere) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.genere = genere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

}
