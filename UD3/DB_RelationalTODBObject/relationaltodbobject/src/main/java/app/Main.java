package app;

import controller.ClientDAO;

public class Main {
    public static void main(String[] args) {

        // Test insertClient with the PostgreSQLJDBCConnection

        ClientDAO.insertClient();

        // Test deleteClientById with the PostgreSQLJDBCConnection

        ClientDAO.deleteClientById("12345678A");

        // After creating the tables in the PostgreSQL database, I am having issues when
        // trying to insert, delete, etc.,
        // as it doesn't seem to connect properly to the tables I've created.

        /*
         * 
         * Successfully connected to the database.
         * Inserting client into the database...
         * Error connecting to the database: ERROR: relation "empleats.clients" does not
         * exist
         * Position: 13
         * Successfully connected to the database.
         * Deleting client from the database...
         * Error connecting to the database or deleting client: ERROR: relation
         * "empleats.clients" does not exist
         * 
         * 
         */

    }

}
