package com.ieseljust.Controller;

import org.hibernate.Session;

import com.ieseljust.Model.Autor;
import com.ieseljust.ORM.HibernateUtil;

public class AutorDAO {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void insertAutor(Autor professor) {

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

    public void deleteAutor(Autor professor) {
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
