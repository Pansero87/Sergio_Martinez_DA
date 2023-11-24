package app;

import org.hibernate.Session;


import java.util.List;
import java.util.Scanner;

import Model.Empleados;
import ORM.HibernateUtil;

/**
 * This class represents the main program for managing and displaying a paginated list of employees using Hibernate.
 */
public class MainProgram {

    /**
     * The number of records to display per page.
     */
    private static final int PAGE_SIZE = 10;

    /**
     * The main entry point for the program. Displays a paginated list of employees and allows user interaction
     * to navigate through the pages or quit the program.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Scanner scanner = new Scanner(System.in);
            char choice;
            int currentPage = 1;

            do {
                System.out.println("Fetching page " + currentPage + "...");
                List<Empleados> empleadosList = fetchEmpleados(session, currentPage, PAGE_SIZE);

                displayPage(empleadosList, currentPage);

                // Use upper-case letters to navigate through the pages

                System.out.print("Enter choice (S or s) next, (A) previous, (G n) Go to n, (Q) quit: ");
                choice = scanner.next().charAt(0);

                switch (choice) {
                    case 'S':
                        currentPage++;
                        break;
                    case 'A':
                        if (currentPage > 1) {
                            currentPage--;
                        }
                        break;
                    case 'G':
                        currentPage = scanner.nextInt();
                        break;
                    case 'Q':
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 'Q');
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Fetches a paginated list of employees from the database using Hibernate.
     *
     * @param session    The Hibernate session.
     * @param pageNumber The page number to fetch.
     * @param pageSize   The number of records per page.
     * @return A list of employees for the specified page.
     */
    private static List<Empleados> fetchEmpleados(Session session, int pageNumber, int pageSize) {
        String hql = "FROM Empleados";
        return session.createQuery(hql, Empleados.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    /**
     * Displays the information of a paginated list of employees.
     *
     * @param empleadosList The list of employees to display.
     * @param currentPage   The current page being displayed.
     */
    private static void displayPage(List<Empleados> empleadosList, int currentPage) {

        System.out.println(String.format("%-5s %-20s %-15s %-30s %-5s %-15s",
                "ID", "Name", "DNI", "Email", "Age", "Phone Number"));

        for (Empleados empleados : empleadosList) {
            System.out.println(String.format("%-5s %-20s %-15s %-30s %-5s %-15s",
                    empleados.getIdEmpleado(),
                    empleados.getNombre(),
                    empleados.getDni(),
                    empleados.getEmail(),
                    empleados.getEdad(),
                    empleados.getTelefono()));
        }

        System.out.println("<Page " + currentPage + " of Y>");
    }
}
