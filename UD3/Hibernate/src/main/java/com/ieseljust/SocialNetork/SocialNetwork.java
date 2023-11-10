package com.ieseljust.SocialNetork;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ieseljust.Model.HibernateUtil;
import com.ieseljust.Model.Users;

public class SocialNetwork {
    public static void main(String[] args) {

        // Crea una nueva instancia de Users con los datos de la inserción
        Users user = new Users(1, "Sergio", "2222");

        // Obtiene una sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Inicia una transacción
        Transaction transaction = session.beginTransaction();

        // Guarda el objeto en la base de datos
        session.save(user);

        // Confirma la transacción
        transaction.commit();

        // Cierra la sesión
        session.close();

    }

}
