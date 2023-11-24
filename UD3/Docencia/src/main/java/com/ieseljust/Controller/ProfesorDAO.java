package com.ieseljust.Controller;

import org.hibernate.Session;

import com.ieseljust.Model.Teacher;
import com.ieseljust.ORM.HibernateUtil;

public class TeacherDAO {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void insertTeacher(Teacher professor) {

        try {
            session.getTransaction().begin();
            session.save(professor);
            session.getTransaction().commit();
            System.out.println("Teacher inserted correctly");

        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Error inserting teacher " + e);
        } finally {
            session.close();
        }
    }

    public void deleteTeacher(Teacher professor) {
        try {
            session.getTransaction().begin();
            session.delete(professor);
            session.getTransaction().commit();
            System.out.println("Teacher deleted correctly");

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error deleting teacher " + e);
        } finally {
            session.close();
        }

    }

}
