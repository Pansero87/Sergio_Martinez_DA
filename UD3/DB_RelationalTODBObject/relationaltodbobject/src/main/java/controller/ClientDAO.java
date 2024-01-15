package controller;

import java.sql.Connection;
import connection.PostgreSQLJDBCConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAO {

    static public void insertClient() {

        String address = "123 Example Street";
        String phone = "123456789";
        String phone1 = "987654321";
        String email = "client@example.com";
        String idNumber = "12345678A";
        String firstName = "Juan";
        String lastName = "Perez Gomez";
        String city = "Barcelona";
        int postalCode = 8001;
        String province = "Barcelona";
        String country = "Spain";

        try (Connection conn = PostgreSQLJDBCConnection.getConnection()) {
            // SQL query to insert the client into the Empleats schema
            String sqlInsertClient = "INSERT INTO Empleats.clients (contact_info, nom, cognoms, dni, poblacio, cp, provincia, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println("Inserting client into the database...");
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertClient)) {
                pstmt.setObject(1, new String[] { address, phone, phone1, email, idNumber });
                pstmt.setString(2, firstName);
                pstmt.setString(3, lastName);
                pstmt.setString(4, idNumber);
                pstmt.setString(5, city);
                pstmt.setInt(6, postalCode);
                pstmt.setString(7, province);
                pstmt.setString(8, country);

                // Execute the query
                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " rows inserted.");

                if (rowsInserted > 0) {
                    System.out.println("Client inserted successfully.");
                } else {
                    System.out.println("Failed to insert the client.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }

    }

    public static void deleteClientById(String clientId) {
        try (Connection conn = PostgreSQLJDBCConnection.getConnection()) {
            // SQL query para borrar el cliente basado en su ID
            String sqlDeleteClient = "DELETE FROM Empleats.clients WHERE dni = ?";
            System.out.println("Deleting client from the database...");

            try (PreparedStatement pstmt = conn.prepareStatement(sqlDeleteClient)) {
                pstmt.setString(1, clientId);

                // Ejecutar la consulta
                int rowsDeleted = pstmt.executeUpdate();
                System.out.println(rowsDeleted + " rows deleted.");

                if (rowsDeleted > 0) {
                    System.out.println("Client with ID " + clientId + " deleted successfully.");
                } else {
                    System.out.println("No client found with ID " + clientId + ".");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database or deleting client: " + e.getMessage());
        }
    }

}
