package com.ieseljust.Controller;

import org.hibernate.Session;

import com.ieseljust.Model.Libro;
import com.ieseljust.ORM.HibernateUtil;

public class LibroDAO {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void inserLibro(Libro group) {

        try {
            session.getTransaction().begin();
            session.save(group);
            session.getTransaction().commit();
            System.out.println("Libro inserted correctly");

        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Error inserting Libro " + e);
        } finally {
            session.close();
        }

    }

    public void deleteLibro(Libro group) {
        try {
            session.getTransaction().begin();
            session.delete(group);
            session.getTransaction().commit();
            System.out.println("Libro deleted correctly");

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error deleting Libro" + e);
        }

    }

}
