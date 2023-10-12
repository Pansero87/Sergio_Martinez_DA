package Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;

import Connector.ConnectionDB;
import Connector.ColorRGB;

public class SolvedExercise {

	public static void main(String[] args) {
		ManageConnection db = new ManageConnection();
		Connection con = db.openConnection();
		insert(con);

		/*
		 * try { DatabaseMetaData dbmd = (DatabaseMetaData)
		 * db.openConnection().getMetaData(); System.out.println(ColorRGB.BLUE +
		 * "\nDBMS information--------"); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

	public static void insert(Connection con) {

		int gameId = 1;
		String gameName = "Space Invaders";
		String description = "Space Invaders is a fixed shooter in which the player moves a laser cannon horizontally across the bottom of the screen and fires at aliens overhead";
		int genere = 2;
		try {
			/*
			 * String SQL = "INSERT INTO Joc VALUES (" + gameId + ", '" + gameName + "', '"
			 * + description + "', " + genere + ");";
			 */

			String SQL2 = "INSERT INTO Joc values (?,?,?,?);";
			PreparedStatement pst = con.prepareStatement(SQL2);
			
			pst.setInt(1, gameId);
			pst.setString(2, gameName);
			pst.setString(3, description);
			pst.setInt(4, genere);
			
			System.out.println("Inserted");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
