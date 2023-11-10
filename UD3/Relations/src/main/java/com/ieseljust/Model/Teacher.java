package com.ieseljust.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Teachers")
public class Teacher {

    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idTeacher")
    private int idTeacher;

    @Column(name = "name")
    private String name;

    public Teacher() {
    }

    public Teacher(int idTeacher, String name) {
        this.idTeacher = idTeacher;
        this.name = name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher [name=" + name + "]";
    }
}
