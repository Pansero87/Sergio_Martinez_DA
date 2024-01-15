package Controller;

import org.hibernate.Session;

import Model.Users;
import ORM.HibernateUtil;

public class UserDAO {
    private Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void insertUser(Users user) {

        try {
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User inserted correctly");

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error inserting User " + e.getMessage());
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

}
