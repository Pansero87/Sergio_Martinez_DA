package com.ieseljust.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Grupo")
public class Group implements Serializable {

    static final long serialVersionUID = 137L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGroup;
    @Column
    private String level;
    @Column
    private String course;
    @Column
    private int year;

    @OneToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "idTeacher", referencedColumnName = "idTeacher", unique = true, foreignKey = @ForeignKey(name = "FK_Teacher_Group"))
    private Teacher teacher;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(long idGropup) {
        this.idGroup = idGropup;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
