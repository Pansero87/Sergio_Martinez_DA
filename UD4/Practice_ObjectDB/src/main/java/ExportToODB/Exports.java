package ExportToODB;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import Model.Books;
import Model.Users;
import ModelODB.BooksODB;
import ModelODB.UsersODB;
import ORM.HibernateUtil;

/**
 * The Exports class is responsible for exporting data to an ObjectDB database.
 * It provides methods to record books and users to the database.
 */
public class Exports {

    /**
     * Records books to ObjectDB.
     * This method retrieves books from a database using Hibernate and persists them
     * to ObjectDB.
     * It creates a new BooksODB object for each book and saves it to the ObjectDB
     * database.
     * If an exception occurs during the process, it handles it appropriately and
     * rolls back the transaction.
     */
    public static void recordBooksToODB() {
        String urlObjectDB = "libraryODB.odb";
        EntityManagerFactory connectionObjectDB = null;
        EntityManager comandObjectDB = null;

        try {
            connectionObjectDB = Persistence.createEntityManagerFactory(urlObjectDB);
            comandObjectDB = connectionObjectDB.createEntityManager();
            comandObjectDB.getTransaction().begin();

            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Books> booksList = session.createQuery("SELECT b FROM Books b", Books.class).getResultList();

            for (Books book : booksList) {
                System.out.println("Processing book: " + book.getIdBook() + " " + book.getTitle() + " "
                        + book.getAuthor());
                BooksODB newBook = new BooksODB();

                newBook.setTitle(book.getTitle());
                newBook.setAuthor(book.getAuthor());
                newBook.setPublicationYear(book.getPublicationYear());

                comandObjectDB.persist(newBook);
                System.out.println("Book created.");

            }

            comandObjectDB.getTransaction().commit();

        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            if (comandObjectDB != null && comandObjectDB.getTransaction().isActive()) {
                comandObjectDB.getTransaction().rollback();
            }
        } finally {
            if (comandObjectDB != null) {
                comandObjectDB.close();
            }
            if (connectionObjectDB != null) {
                connectionObjectDB.close();
            }
        }
    }

    /**
     * Records users to ObjectDB.
     * This method retrieves users from a database using Hibernate and persists them
     * to ObjectDB.
     * It creates a new UsersODB object for each user and saves it to the ObjectDB
     * database.
     * If an exception occurs during the process, it handles it appropriately and
     * rolls back the transaction.
     */
    public static void recordUsersToODB() {
        String urlObjectDB = "libraryODB.odb";
        EntityManagerFactory connectionObjectDB = null;
        EntityManager comandObjectDB = null;

        try {
            connectionObjectDB = Persistence.createEntityManagerFactory(urlObjectDB);
            comandObjectDB = connectionObjectDB.createEntityManager();
            comandObjectDB.getTransaction().begin();

            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Users> usersList = session.createQuery("SELECT u FROM Users u", Users.class).getResultList();

            for (Users user : usersList) {
                System.out
                        .println("Processing user: " + user.getName() + " " + user.getIdUser() + " " + user.getEmail());
                UsersODB newUser = new UsersODB();

                // newUser.setIdUser(user.getIdUser());
                newUser.setName(user.getName());
                newUser.setEmail(user.getEmail());

                comandObjectDB.persist(newUser);
                System.out.println("User created.");

            }

            comandObjectDB.getTransaction().commit();

        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            if (comandObjectDB != null && comandObjectDB.getTransaction().isActive()) {
                comandObjectDB.getTransaction().rollback();
            }
        } finally {
            if (comandObjectDB != null) {
                comandObjectDB.close();
            }
            if (connectionObjectDB != null) {
                connectionObjectDB.close();
            }
        }
    }

}
