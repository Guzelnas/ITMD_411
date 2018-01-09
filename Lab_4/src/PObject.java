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

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
// Encapsulate java.util.Date and hashmap objects
// into a class called PObject

public class PObject implements Serializable {
	private Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	private java.util.Date date;

	public PObject(java.util.Date date, Map<String, ArrayList<String>> records) {

		this.setDate(date);
		this.setBankRecords(records);
	}

	public Map<String, ArrayList<String>> getBankRecords() {
		return map;
	}

	public void setBankRecords(Map<String, ArrayList<String>> BankRecords) {
		this.map = BankRecords;
	}

	public Date getDate() {
		return (Date) date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

}
