package com.ieseljust.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Musician")
public class Musician implements Serializable {

    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MusicianID")
    private int musicianID;

    @Column(name = "MusicianName")
    private String musicianName;

    @Column(name = "Instrument")
    private String instrument;

    @ManyToOne
    @JoinColumn(name = "idOrchestra")
    private Orchestra orchestra;

    // Constructors, getters, and setters

    public Musician(String musicianName, String instrument) {
        this.musicianName = musicianName;
        this.instrument = instrument;
    }

    // Constructors
    public Musician() {
    }

    public Musician(String musicianName, String instrument, Orchestra orchestra) {
        this.musicianName = musicianName;
        this.instrument = instrument;
        this.orchestra = orchestra;
    }

    public Musician(String name, String instrument, int orchestraId) {
        this.musicianName = name;
        this.instrument = instrument;
        this.orchestra = new Orchestra();
        this.orchestra.setOrchestraID(orchestraId);
    }

    // Getters and Setters
    public int getMusicianID() {
        return musicianID;
    }

    public void setMusicianID(int musicianID) {
        this.musicianID = musicianID;
    }

    public String getMusicianName() {
        return musicianName;
    }

    public void setMusicianName(String musicianName) {
        this.musicianName = musicianName;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Orchestra getOrchestra() {
        return orchestra;
    }

    public void setOrchestra(Orchestra orchestra) {
        this.orchestra = orchestra;
    }

    @Override
    public String toString() {
        return "Musician [musicianID=" + musicianID + ", musicianName=" + musicianName + ", instrument=" + instrument
                + ", orchestra=" + orchestra + "]";
    }

}
