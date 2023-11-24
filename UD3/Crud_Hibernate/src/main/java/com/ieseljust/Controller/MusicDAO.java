package com.ieseljust.Controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.ieseljust.Model.Musician;
import com.ieseljust.ORM.HibernateUtil;

public class MusicDAO {

    private Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void insertMusic(Musician music) {

        try {
            session.getTransaction().begin();
            session.save(music);
            session.getTransaction().commit();
            System.out.println("Music inserted correctly");

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error inserting Music " + e.getMessage());
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteMusic(Musician music) {
        try {
            session.getTransaction().begin();
            session.delete(music);
            session.getTransaction().commit();
            System.out.println("Music deleted correctly");

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error deleting Music " + e.getMessage());
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Musician> getAllMusicians() {
        List<Musician> musicians = null;

        try {
            session.getTransaction().begin();

            NativeQuery<Musician> query = session.createNativeQuery("SELECT * FROM Musician", Musician.class);
            musicians = query.getResultList();

            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error retrieving Musicians " + e.getMessage());

        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return musicians;
    }

    public void displayAllMusicians() {
        List<Musician> musicians = getAllMusicians();

        if (musicians != null && !musicians.isEmpty()) {
            System.out.println("List of Musicians:");
            for (Musician musician : musicians) {
                System.out.println("ID: " + musician.getMusicianID());
                System.out.println("Name: " + musician.getMusicianName());
                System.out.println("Instrument: " + musician.getInstrument());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No musicians found in the database.");
        }
    }

}
