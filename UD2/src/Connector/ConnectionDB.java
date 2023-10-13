package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

class ManageConnection {
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

	public void getSelect(String table, String db) throws SQLException {
		try (Connection connection = openConnection(db);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table)) {

			ResultSetMetaData rsmdQuery = (ResultSetMetaData) resultSet.getMetaData();
			int columnCount = rsmdQuery.getColumnCount();

			System.out.println("Content of " + table);
			System.out.println("******************************");

			for (int i = 1; i <= columnCount; i++) {
				System.out.print(String.format("%-25.25s", rsmdQuery.getColumnName(i)));
			}
			System.out.println();

			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(String.format("%-25.25s", resultSet.getString(i)));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

public class ConnectionDB {

	public static void main(String[] args) {
		/*
		 * String server = "localhost"; int port = 3308; String user = "root"; String
		 * pass = "root"; String DBName = "BDJocs"; String connectionUrl =
		 * "jdbc:mysql://" + server + ":" + port; connectionUrl += "/" + DBName;
		 * connectionUrl += "?useUnicode=true&characterEncoding=UTF-8"; connectionUrl +=
		 * "&user=" + user; connectionUrl += "&password=" + pass;
		 */
		ManageConnection db = new ManageConnection();

		// db.openConnection("BDJocs");
		// db.closeConnection();
		try {
			db.getSelect("Joc", "BDJocs");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
