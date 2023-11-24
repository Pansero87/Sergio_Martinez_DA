package com.ieseljust.Model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Autor")
public class Autor implements Serializable {

    static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column
    private String nom;

    @Column
    private String nacionalitat;

    @OneToMany(mappedBy = "elAutor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    // private Set<Libro> elsLlibres;

    public Autor(int i, String string, String string2) {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNacionalitat() {
        return nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    public Iterable<Libro> getElsLlibres() {
        return elsLlibres;
    }

    public void setElsLlibres(Iterable<Libro> elsLlibres) {
        this.elsLlibres = elsLlibres;
    }

}
