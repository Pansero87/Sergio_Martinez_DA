package com.ieseljust.Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Orchestra")
public class Orchestra implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrchestraID")
    private int orchestraID;

    @Column(name = "OrchestraName", unique = true)
    private String orchestraName;

    @OneToMany(mappedBy = "Orchestra")
    private List<Musician> musicians;

    public void addMusician(Musician musician) {
        if (musicians == null) {
            musicians.add(musician);

        }

    }

    // Constructors, getters, and setters

    // Constructors
    public Orchestra() {
    }

    public Orchestra(String orchestraName) {
        this.orchestraName = orchestraName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getOrchestraID() {
        return orchestraID;
    }

    public void setOrchestraID(int orchestraID) {
        this.orchestraID = orchestraID;
    }

    public String getOrchestraName() {
        return orchestraName;
    }

    public void setOrchestraName(String orchestraName) {
        this.orchestraName = orchestraName;
    }

    public List<Musician> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Musician> musicians) {
        this.musicians = musicians;
    }

    @Override
    public String toString() {
        return "Orchestra [orchestraID=" + orchestraID + ", orchestraName=" + orchestraName + ", musicians=" + musicians
                + "]";
    }

}