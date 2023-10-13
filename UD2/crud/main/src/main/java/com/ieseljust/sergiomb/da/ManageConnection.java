package com.ieseljust.sergiomb.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManageConnection {
    private Connection conn = null;

    public Connection openConnection(String db) {
        String connectionurl = "jdbc:mysql://localhost:3308/" + db
                + "?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";

        try {
            conn = DriverManager.getConnection(connectionurl);
            System.out.println("Connected to DataBase " + db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}