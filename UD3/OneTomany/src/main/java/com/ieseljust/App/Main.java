package com.ieseljust.App;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ieseljust.Controller.AutorDAO;
import com.ieseljust.Controller.LibroDAO;
import com.ieseljust.Model.Autor;
import com.ieseljust.Model.Libro;
import com.ieseljust.ORM.HibernateUtil;

public class Main {
    public static void main(String[] args) {

        Libro libro = new Libro(1, "Libro 1", "Tipus 1", null);
        Libro libro2 = new Libro(2, "Libro 2", "Tipus 2", new Autor(2, "Autor 2", "Nacionalitat 2"));
        Libro libro3 = new Libro(3, "Libro 3", "Tipus 3", new Autor(3, "Autor 3", "Nacionalitat 3"));

        LibroDAO libroDAO = new LibroDAO();
        libroDAO.inserLibro(libro);

    }

}
