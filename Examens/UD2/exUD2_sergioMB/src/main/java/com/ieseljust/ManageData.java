package com.ieseljust;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageData {

    public static void deleteData(Connection conn) {

        if (conn != null) {
            try {
                Statement stm = conn.createStatement();

                // Get a list of all tables in the database
                String lisTables = "SHOW TABLES";
                stm.executeQuery(lisTables);
                java.sql.ResultSet resultSet = stm.getResultSet();

                while (resultSet.next()) {
                    String tableName = resultSet.getString(1);

                    String truncTables = "TRUNCATE TABLE " + tableName;
                    stm.executeUpdate(truncTables);
                    System.out.println("Table '" + tableName + "' truncated.");
                }

                System.out.println("All tables truncated.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void fillData(Connection conn, String script) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(script));
            String line;
            while ((line = bfr.readLine()) != null) {
                Statement stm = conn.createStatement();
                stm.executeUpdate(line);
                stm.close();
            }
            bfr.close();
            System.out.println("Script executed.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
