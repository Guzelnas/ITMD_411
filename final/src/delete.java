
/*
 * Final Project - Trouble Ticket System
 * ITMD 411
 * Programmed by Guzel Nasybullina
 * 12.09.2017
 * 
 */
//import needed libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class delete {

	public static void main(String[] args) {
		// DELETE FROM GNasybullina
		final String delTable = "DELETE FROM GNasybullina";
		Connection connect = null;
		Statement statement = null;
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Create connection with mysql server
				connect = DriverManager.getConnection(
						"jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");
				// delete table
				statement = connect.createStatement();
				statement.executeUpdate(delTable);
				System.out.println("Table deleted");
				// close connection
				statement.close();
				connect.close();
				// catch exception
			} catch (Exception exception) {
				System.out.println(exception.getMessage());
			}

		}
	}
}