
/*Guzel Nasybullina
* ITMD 411
* 11/19/2017
* 
* For this lab you will continue using your current
* project folder created for labs 2 & 3 and create
* a new file called Dao where Dao stands for Data
* Access Object. This will allow for CRUD database
* connectivity and operations. Create also a file
* called LoanProcessing as well which will act 
* as a driver file (i.e., includes main function)
* to call your database CRUD methods and create 
* some resulting output.
*/

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

//class to connect to papaserver
public class Connector {
	public Connection getConnection() {
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://www.papademas.net:3306/411labs?user=db411&password=411&useSSL=false");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}
}