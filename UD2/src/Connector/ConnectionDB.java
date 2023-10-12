package Connector;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;

class ManageConnection {
	private Connection conn = null;

	String connectionurl = "jdbc:mysql://localhost:3308/BDJocs?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";

	public Connection openConnection() {

		try {
			conn = DriverManager.getConnection(connectionurl);
			System.out.println("Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	public Connection closeConnection() {
		try {
			conn = DriverManager.getConnection(connectionurl);
			conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	public void getSelect(String table) throws SQLException {
		ResultSet rst = this.openConnection().createStatement().executeQuery("SELECT * FROM " + table);

		while (rst.next()) {
			System.out.println("");
			System.out.println("Contingut de " + table);
			System.out.println("******************************");
			ResultSetMetaData rsmdQuery = (ResultSetMetaData) rst.getMetaData();

			for (int i = 1; i < rsmdQuery.getColumnCount(); i++) {
				System.out.print(String.format("%-25.25s", rsmdQuery.getColumnName(i)));
				System.out.println();

				while (rst.next()) {
					for (int a = 1; a <= rsmdQuery.getColumnCount(); a++)
						System.out.print(String.format("%-25.25s ", rst.getString(a)));
					System.out.println();
				}

			}

		}

	}

	public void insert() {

	}
}

public class ConnectionDB {

	public static void main(String[] args) {
		// Form for
		/*
		 * String server = "localhost"; int port = 3308; String user = "root"; String
		 * pass = "root"; String DBName = "BDJocs"; String connectionUrl =
		 * "jdbc:mysql://" + server + ":" + port; connectionUrl += "/" + DBName;
		 * connectionUrl += "?useUnicode=true&characterEncoding=UTF-8"; connectionUrl +=
		 * "&user=" + user; connectionUrl += "&password=" + pass;
		 */
		ManageConnection db = new ManageConnection();
		// db.openConnection();
		// db.closeConnection();
		try {
			db.getSelect("Joc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
