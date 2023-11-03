package com.ieseljust.ad.myDBMS;

import java.sql.*;
import java.util.Scanner;

import java.util.ArrayList;

class DatabaseManager {

    String server;
    String port;
    String user;
    String pass;
    String dbname;

    DatabaseManager() {
        // TO-DO: Default initialization
    }

    DatabaseManager(String server, String port, String user, String pass, String dbname) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.dbname = dbname;
        // TO-DO: -init from args
    }

    /**
     * Connects to the database and returns a Connection object.
     *
     * @return a Connection object representing the connection to the database
     */
    public Connection connectDatabase() {
        // TO-DO: Connect to a specific database
        String conn = "jdbc:mysql://" + this.server + ":" + this.port + "/" + this.dbname
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
     * Shows the tables in the specified database.
     *
     * @param databaseName the name of the database
     * @throws SQLException if there is an error with the SQL connection
     */
    public void showTables(String databaseName) throws SQLException {
        // Connect to the database
        Connection connection = connectDatabase();
        if (connection == null) {
            // Handle connection error
            return;
        }

        // Get the tables in the specified database
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });

        // Show the tables in the specified database
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println(tableName);
        }

        // Close the tables ResultSet and the connection
        tables.close();
        connection.close();
    }

    /**
     * Inserts a new row into the specified table in the database.
     *
     * @param tableName the name of the table to insert the row into
     * @throws SQLException if there is an error with the SQL query execution
     */
    public void insertIntoTable(String tableName) throws SQLException {
        // Connect to the database
        Connection conn = this.connectDatabase();
        if (conn == null) {
            // Handle connection error
            return;
        }

        try {
            // Get connected to the database

            // Request the table name
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the table name: ");
            String table = scanner.nextLine();

            // Retrieve information about the table
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Display the table structure
            int columnCount = metaData.getColumnCount();
            System.out.println("Structure of the table " + table + ":");
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnType = metaData.getColumnTypeName(i);
                System.out.println(columnName + " (" + columnType + ")");
            }

            // Request data for the new row
            System.out.println("Enter data for the new row:");
            String values = "";
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + ": ");
                String value = scanner.nextLine();
                values += "'" + value + "'";
                if (i < columnCount) {
                    values += ", ";
                }
            }

            // Insert the new row into the table
            String insertQuery = "INSERT INTO " + table + " VALUES (" + values + ")";
            statement.executeUpdate(insertQuery);
            System.out.println("New row inserted successfully.");

            // Close the connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the description of a specific table, including the fields, data types,
     * primary keys, and foreign keys.
     *
     * @param tableName the name of the table to describe
     * @throws SQLException if there is an SQL exception
     */
    public void showDescTable(String tableName) throws SQLException {
        // Retrieve database metadata
        DatabaseMetaData metaData = connectDatabase().getMetaData();
        String catalog = connectDatabase().getCatalog();
        String schema = connectDatabase().getSchema();

        // Retrieve columns in the table
        ResultSet columns = metaData.getColumns(catalog, schema, tableName, null);

        // Retrieve primary keys in the table
        ResultSet primaryKeys = metaData.getPrimaryKeys(catalog, schema, tableName);

        // Print the table description
        System.out.println("Table: " + tableName);
        System.out.println("Columns:");
        while (columns.next()) {
            // Retrieve column name and type
            String columnName = columns.getString("COLUMN_NAME");
            String columnType = columns.getString("TYPE_NAME");
            System.out.println("\t" + columnName + " - " + columnType);
        }
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Primary Keys:" + ConsoleColors.RESET);
        while (primaryKeys.next()) {
            // Retrieve primary key name
            String primaryKey = primaryKeys.getString("COLUMN_NAME");
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\t" + primaryKey + ConsoleColors.RESET);
        }

        // Close the result sets
        columns.close();
        primaryKeys.close();
    }

    /**
     * Starts the shell for interacting with the database.
     *
     * @throws SQLException if there is an error with the SQL connection
     */
    public void startShell() throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        String command;

        do {
            helpInfoDatabase();

            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "# (" + this.user + ") on " + this.server + ":"
                    + this.port + "[" + this.dbname + "]" + "> " + ConsoleColors.RESET);
            command = keyboard.nextLine();

            switch (command) {
                case "st":
                case "sh tables":
                    try {
                        this.showTables(this.dbname);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
                case "quit":
                    break;

                default:
                    String[] subcommand = command.split(" ");
                    switch (subcommand[0]) {
                        case "describe":
                            String tableName = subcommand[1];
                            if (tableExists(tableName)) {
                                showDescTable(tableName);
                            } else {
                                System.out.println(
                                        ConsoleColors.RED_BOLD_BRIGHT + "Table does not exist".toUpperCase()
                                                + ConsoleColors.RESET);

                            }
                            break;

                        case "insert":
                            insertIntoTable(subcommand[1]);
                        default:
                            System.out.println(ConsoleColors.RED + "Unknown option" + ConsoleColors.RESET);
                            break;
                    }

            }

        } while (!command.equals("quit"));

        // TO-DO: Inicia la shell del mode base de dades

    }

    /**
     * Checks if a table exists in the database.
     *
     * @param tableName the name of the table to check
     * @return true if the table exists, false otherwise
     * @throws SQLException if there is an SQL exception
     */
    public boolean tableExists(String tableName) throws SQLException {
        // Retrieve database metadata
        DatabaseMetaData metaData = connectDatabase().getMetaData();
        String catalog = connectDatabase().getCatalog();
        String schema = connectDatabase().getSchema();

        // Retrieve tables in the database
        ResultSet tables = metaData.getTables(catalog, schema, tableName, null);

        // Check if the table exists
        boolean tableExists = tables.next();

        // Close the ResultSet for tables
        tables.close();

        return tableExists;
    }

    /**
     * Prints the help information for the Database.
     *
     */

    public void helpInfoDatabase() {

        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "===== Help =====" + ConsoleColors.RESET);
        System.out.println(
                ConsoleColors.PURPLE_BOLD_BRIGHT + "Available options in Database:" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "  - sh tables or st: Show tables"
                + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "  - insert: Insert a new record"
                + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "  - describe table_name: Describe a table"
                + ConsoleColors.RESET);

        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "  - quit: Quit the program" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "==================" + ConsoleColors.RESET);
        System.out.println();

    }
}
