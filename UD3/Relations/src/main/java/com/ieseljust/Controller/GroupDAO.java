package com.ieseljust.Controller;

import org.hibernate.Session;

import com.ieseljust.Model.Group;
import com.ieseljust.ORM.HibernateUtil;

public class GroupDAO {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void insertGroup(Group group) {

        try {
            session.getTransaction().begin();
            session.save(group);
            session.getTransaction().commit();
            System.out.println("Group inserted correctly");

        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Error inserting group " + e);
        } finally {
            session.close();
        }

    }

    public void deleteGroup(Group group) {
        try {
            session.getTransaction().begin();
            session.delete(group);
            session.getTransaction().commit();
            System.out.println("Group deleted correctly");

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error deleting group " + e);
        }

    }

}
