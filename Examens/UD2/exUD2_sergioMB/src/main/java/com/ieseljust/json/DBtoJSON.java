package com.ieseljust.json;

import java.io.FileWriter;
import java.sql.*;
import org.json.*;

public class DBtoJSON {

    public static void saveToJson(Connection conn, String jsonFileName) {
        try {

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "joc", null);

            if (tables.next()) {

                Statement statement = conn.createStatement();
                String query = "SELECT * FROM joc";
                ResultSet resultSet = statement.executeQuery(query);

                JSONArray usersArray = new JSONArray();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("nom");
                    String description = resultSet.getString("descripció");
                    int genereId = resultSet.getInt("Genere_id");

                    JSONObject userObject = new JSONObject();
                    userObject.put("id", id);
                    userObject.put("name", name);
                    userObject.put("descripció", description);
                    userObject.put("Gnere_id", genereId);

                    usersArray.put(userObject);
                }

                resultSet.close();
                statement.close();

                try (FileWriter fileWriter = new FileWriter(jsonFileName)) {
                    usersArray.write(fileWriter);
                }

                System.out.println("Table saved to " + jsonFileName);
            } else {
                System.out.println("This able does not exist in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
