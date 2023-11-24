package com.ieseljust.Model;

import java.io.Serializable;

import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Libro")
public class Libro implements Serializable {

    public Libro(Long idLibro, String titol, String tipus, Autor elAutor) {
        this.idLibro = idLibro;
        this.titol = titol;
        this.tipus = tipus;
        this.elAutor = elAutor;
    }

    static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @Column
    private String titol;

    @Column
    private String tipus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idAutor", foreignKey = @ForeignKey(name = "FK_LIBRO_AUTOR"))
    private Autor elAutor;

    public Libro() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Autor getElAutor() {
        return elAutor;
    }

    public void setElAutor(Autor elAutor) {
        this.elAutor = elAutor;
    }

}
