package com.ieseljust.sergiomb.da;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class Crud {

    public static void main(String[] args) {
        ManageConnection db = new ManageConnection();
        Connection con = db.openConnection("BDJocs");
        insertJoc(con);
        // showTableInfo(con);
        // delete(con, 1);
        // showTableData(con, "Joc");
        showTableDataWithColors(con, "Joc");
    }

    /**
     * Inserts a new record into the Joc table.
     *
     * @param conn the database connection
     */
    public static void insertJoc(Connection conn) {

        int gameId = 2;
        String gameName = "New World";
        String description = "New World es un emocionante MMO de mundo abierto repleto de peligros y oportunidades en el que forjarás tu destino en la isla sobrenatural de Aetérnum.";
        int genre = 4;
        try {

            String SQL2 = "INSERT INTO Joc values (?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(SQL2);

            pst.setInt(1, gameId);
            pst.setString(2, gameName);
            pst.setString(3, description);
            pst.setInt(4, genre);

            int result = pst.executeUpdate();
            if (result == 1) {
                System.out.println("Inserted");
            } else {
                System.out.println("Not inserted");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteJoc(Connection conn, int gameId) {
        String SQL = "DELETE FROM Joc WHERE id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setInt(1, gameId);
            int deletedRows = pst.executeUpdate();
            System.out.println(deletedRows + " row(s) deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and displays information about tables and their columns in a
     * database.
     * 
     * @param conn the database connection
     */
    public static void showTableInfo(Connection conn) {
        try {
            // Retrieve database metadata
            DatabaseMetaData metaData = conn.getMetaData();
            String catalog = conn.getCatalog();
            String schema = conn.getSchema();

            // Retrieve tables in the database
            ResultSet tables = metaData.getTables(catalog, schema, null, new String[] { "TABLE" });

            while (tables.next()) {
                // Retrieve table name
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);
                System.out.println("Columns:");

                // Retrieve columns in the table
                ResultSet columns = metaData.getColumns(null, null, tableName, null);

                while (columns.next()) {
                    // Retrieve column name and type
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("\t" + columnName + " - " + columnType);
                }

                System.out.println("******************************");

                // Close the ResultSet for columns
                columns.close();
            }

            // Close the ResultSet for tables
            tables.close();
        } catch (SQLException e) {
            // Print the stack trace if an SQL exception occurs
            e.printStackTrace();
        }
    }

    public static void showTableData(Connection con, String tableName) {
        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            // Obtener el número de columnas
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Imprimir encabezados de columnas con formato
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-25.25s", resultSet.getMetaData().getColumnName(i));

            }
            System.out.println();

            // Imprimir datos de la tabla con formato
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-25.25s", resultSet.getString(i));
                }
                System.out.println();
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showTableDataWithColors(Connection con, String tableName) {
        AnsiConsole.systemInstall(); // Instalar el sistema de colores Ansi

        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            // Obtener el número de columnas
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Imprimir encabezados de columnas con formato en azul
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);
                if (i == 1) {
                    System.out.printf("%-25.25s", Ansi.ansi().fg(Ansi.Color.YELLOW).a(columnName).reset());
                } else {
                    System.out.printf("%-25.25s", Ansi.ansi().fg(Ansi.Color.BLUE).a(columnName).reset());
                }
                System.out.print("\t");
            }
            System.out.println();

            // Imprimir datos de la tabla con formato
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnData = resultSet.getString(i);
                    System.out.printf("%-25.25s", columnData);
                }
                System.out.println();
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AnsiConsole.systemUninstall(); // Desinstalar el sistema de colores Ansi
        }
    }

}
