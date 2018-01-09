
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//class DAO extends BankRecords
public class DAO extends BankRecords {
	// connect to papaserver
	Connector connect = new Connector();
	private Statement statement = null;

	// Creating table - g_nasy_tab
	public void createTable() throws Exception {
		try {
			statement = connect.getConnection().createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS g_nasy_tab" + "(pid Integer not NULL AUTO_INCREMENT,"
					+ "ID varchar(7)," + "INCOME numeric(8,2)," + "PEP varchar(3)," + "PRIMARY KEY(pid))";
			statement.executeUpdate(sql);
			System.out.println("Table is successfully created!");
			statement.close();
			// catch exception
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Inserting data into table
	public void inserts(BankRecords[] robjs) throws Exception {
		try {
			statement = connect.getConnection().createStatement();
			String sql = null;
			int i;
			for (i = 0; i < robjs.length; ++i) {
				sql = "INSERT INTO g_nasy_tab(ID,INCOME,PEP)" + "VALUES('" + robjs[i].getId() + "','"
						+ robjs[i].getIncome() + "','" + robjs[i].getPep() + "')";
				statement.executeUpdate(sql);
			}
			System.out.println("Records are inserted!");
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// retrieveRecords mehtod
	public ResultSet retrieveRecords() throws Exception {
		ResultSet result = null;
		try {
			statement = connect.getConnection().createStatement();
			String sql = "Select id, income, pep from g_nasy_tab order by pep desc";
			result = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}