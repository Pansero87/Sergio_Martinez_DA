package com.ieseljust.App;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ieseljust.Controller.TeacherDAO;
import com.ieseljust.Model.Teacher;
import com.ieseljust.ORM.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        Teacher t1 = new Teacher();

        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.insertTeacher(t1);

    }

}
