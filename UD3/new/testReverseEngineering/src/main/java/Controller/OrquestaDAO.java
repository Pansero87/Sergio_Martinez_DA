package Controller;
import java.util.List;


import ORM.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import Model.Orchestra;

public class OrquestaDAO {
    private Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void insertOrquesta(Orchestra orchest) {

        try {
            session.getTransaction().begin();
            session.save(orchest);
            session.getTransaction().commit();
            System.out.println("Orquest inserted correctly");

        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Error inserting Orquest " + e);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }

    public void deleteOrchestra(Orchestra orchest) {
        try {
            session.getTransaction().begin();
            session.delete(orchest);
            session.getTransaction().commit();
            System.out.println("Orchest deleted correctly");

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error deleting Orchest " + e);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }

    public List<Orchestra> getAllOrchestras() {
        List<Orchestra> orchestras = null;

        try {
            session.getTransaction().begin();

            NativeQuery<Orchestra> query = session.createNativeQuery("SELECT * FROM Orchestra", Orchestra.class);
            orchestras = query.getResultList();

            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error retrieving Orchestras " + e.getMessage());

        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return orchestras;
    }

    public void displayAllOrchestras() {
        List<Orchestra> orchestras = getAllOrchestras();

        if (orchestras != null && !orchestras.isEmpty()) {
            System.out.println("List of Orchestras:");
            for (Orchestra orchestra : orchestras) {
                System.out.println("ID: " + orchestra.getOrchestraId());
                System.out.println("Name: " + orchestra.getOrchestraName());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No orchestras found in the database.");
        }
    }

}
