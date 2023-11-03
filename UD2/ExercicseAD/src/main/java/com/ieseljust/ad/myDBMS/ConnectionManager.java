package com.ieseljust.ad.myDBMS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

class ConnectionManager {

    String server;
    String port;
    String user;
    String pass;

    // Constructor with default values
    ConnectionManager() {

    }

    // Constructor with arguments
    ConnectionManager(String server, String port, String user, String pass) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;

    }

    /**
     * Connects to the DBMS using the specified server, port, user, and password.
     *
     * @return the established database connection
     */
    public Connection connectDBMS() {
        String conn = "jdbc:mysql://" + this.server + ":" + this.port + "/"
                + "?useUnicode=false&allowMultiQueries=true&characterEncoding=UTF-8&" + "user=" + this.user
                + "&password=" + this.pass;

        try {
            Connection connection = DriverManager.getConnection(conn);

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * A method that displays the server information.
     */
    public void showInfo() {
        try {
            // Get server information
            DatabaseMetaData metaData = connectDBMS().getMetaData();
            String serverName = metaData.getDatabaseProductName();
            String serverVersion = metaData.getDatabaseProductVersion();
            String driverName = metaData.getDriverName();
            String driverVersion = metaData.getDriverVersion();

            // Print server information
            System.out.println("Server Name: " + serverName);
            System.out.println("Server Version: " + serverVersion);
            System.err.println("Driver Name: " + driverName);
            System.out.println("Driver Version: " + driverVersion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and prints the list of databases in the DBMS.
     * 
     */
    public void showDatabases() {
        try {
            // Connect to the DBMS
            Connection conn = connectDBMS();

            // Retrieve the list of databases
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rst = metaData.getCatalogs();

            // Print the list of databases
            while (rst.next()) {
                String dbName = rst.getString("TABLE_CAT");
                System.out.println(dbName);
            }

            // Close the result set and the connection
            rst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the shell for user input and executes commands based on user input.
     * 
     * @throws SQLException
     * 
     */
    public void startShell() throws SQLException {

        Scanner keyboard = new Scanner(System.in);
        String command;

        do {
            helpInfoConnection();

            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "# (" + this.user + ") on " + this.server + ":"
                    + this.port + "> " + ConsoleColors.RESET);
            command = keyboard.nextLine();
            System.out.println();
            switch (command) {
                case "sh db":
                case "show databases":
                    this.showDatabases();
                    break;

                case "info":
                    this.showInfo();
                    break;

                case "quit":
                    break;
                default:
                    // Com que no podem utilitzar expressions
                    // regulars en un case (per capturar un "use *")
                    // busquem aquest cas en el default:

                    String[] subcommand = command.split(" ");
                    switch (subcommand[0]) {
                        case "use":
                            // TO-DO:
                            // Creem un objecte de tipus databaseManager per connectar-nos a
                            // la base de dades i iniciar una shell de manipulació de BD..
                            String dbName = subcommand[1];
                            if (checkDatabaseExists(dbName)) {
                                // The database exists, you can connect to it
                                DatabaseManager dbm = new DatabaseManager(server, port, user, pass, dbName);
                                System.out.println(
                                        ConsoleColors.GREEN + " Connected to database ".toUpperCase() + dbName
                                                + ConsoleColors.RESET);
                                System.out.println();
                                dbm.startShell();
                            } else {
                                System.out.println(
                                        ConsoleColors.RED + "Database not found".toUpperCase() + ConsoleColors.RESET);
                            }
                            break;
                        case "import":
                            if (loadScript(subcommand[1])) {
                                System.out.println();
                                System.out.println(
                                        ConsoleColors.GREEN + "Script loaded successfully".toUpperCase()
                                                + ConsoleColors.RESET);
                                System.out.println();
                            } else {
                                System.out.println(
                                        ConsoleColors.RED + "Script not loaded".toUpperCase() + ConsoleColors.RESET);

                            }

                            break;
                        default:
                            System.out.println(ConsoleColors.RED + "Unknown option" + ConsoleColors.RESET);
                            break;
                    }

            }

        } while (!command.equals("quit"));
        System.out.println(ConsoleColors.RED + "Bye bye" + ConsoleColors.RESET);

    }

    /**
     * Loads and executes a script file.
     *
     * @param subCommand the subcommand to identify the script file
     */
    private boolean loadScript(String subCommand) {

        File script = new File("scripts/" + subCommand + ".sql");

        try {
            Connection con = connectDBMS();

            BufferedReader bfr = new BufferedReader(new FileReader(script));
            String line = null;
            StringBuilder sb = new StringBuilder();
            String breakLine = System.getProperty("line.separator");
            while ((line = bfr.readLine()) != null) {
                sb.append(line);
                sb.append(breakLine);
            }
            bfr.close();

            String query = sb.toString();
            // generemos el Script en un String
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
            return true;
            // System.out.println("Script ejecutado con salida " + result);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Checks if a database with the given name exists.
     *
     * @param dbName the name of the database to check
     * @return true if the database exists, false otherwise
     */

    public boolean checkDatabaseExists(String dbName) {
        try {
            // Connect to the DBMS
            Connection conn = connectDBMS();

            // Retrieve the list of databases
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rst = metaData.getCatalogs();

            // Check if the database exists in the list
            while (rst.next()) {
                String existingDbName = rst.getString("TABLE_CAT");
                if (existingDbName.equals(dbName)) {
                    return true;
                }
            }

            // Close the result set and the connection
            rst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void helpInfoConnection() {

        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "===== Help =====" + ConsoleColors.RESET);
        System.out.println(
                ConsoleColors.BLUE_BOLD_BRIGHT + "Available options in Connection:" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "  - show database or sh db: Show databases"
                + ConsoleColors.RESET);
        System.out.println(
                ConsoleColors.BLUE_BOLD_BRIGHT + "  - info: Show server information" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "  - import \"script_name\": Import a script file"
                + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "  - use \"db_name\": Switch to the specified database"
                + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "  - quit: Quit the program" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "==================" + ConsoleColors.RESET);
        System.out.println();

    }

}