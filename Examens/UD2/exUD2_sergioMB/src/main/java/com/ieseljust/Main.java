package com.ieseljust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.ieseljust.json.DBtoJSON;

public class Main {
    public static void main(String[] args) {

        ManageConnections dbm = new ManageConnections("localhost", "3308", "root", "root", "DBJocs");
        Connection conn = dbm.connectDatabase();

        // Exercise 4
        addJugador(conn, "Sergio");
        deleteJugador(conn, 1);
        updateJugador(conn, 1, "Sergio");

        // exercise 6
        ManageData.deleteData(conn);
        ManageData.fillData(conn, "scripts/inserts.sql");

        // Exercise 7
        String jsonFileName = "dbjson-date-time-sergioMB.json";
        DBtoJSON.saveToJson(conn, jsonFileName);
    }

    public static void addJugador(Connection connection, String nick) {
        try {
            Statement statement = connection.createStatement();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String registerDate = formatDate(new Date());
            String query = "INSERT INTO jugador (nick, dataregistre) VALUES ('" + nick + "', '" + registerDate
                    + "')";
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Player added successfully.");
            } else {
                System.out.println("Failed to add the player.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJugador(Connection conn, int id) {

        String deleteQ = "DELETE FROM jugador WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(deleteQ);
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Rows deleted successfully");
            } else {
                System.out.println("No rows deleted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateJugador(Connection conn, int id, String newnick) {

        String updateQ = "UPDATE jugador SET nick = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateQ);
            pstmt.setString(1, newnick);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Rows updated successfully");

            } else {
                System.out.println("No rows updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
