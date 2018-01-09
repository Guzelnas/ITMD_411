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

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class hashMapper {

	// Declaring hashmap
	static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	// Declaring Arraylist
	static ArrayList<String> arrayValue = new ArrayList<String>();

	public Map<String, ArrayList<String>> getBankRecords() {
		String line = "";
		StringTokenizer s = null;
		int token = 0;
		int lineNumber = 0;
		Date date = new Date();
		String tl = null, tr = null;

		try {
			@SuppressWarnings("resource")
			// Read bank-Details.csv
			BufferedReader br = new BufferedReader(new FileReader("bank-Detail.csv"));

			// read the record
			while ((line = br.readLine()) != null) {
				lineNumber++;

				// To separate values
				s = new StringTokenizer(line, ",");
				token = 0;
				arrayValue = new ArrayList<String>();

				while (s.hasMoreTokens()) {
					token++;
					if (token == 1)
						tl = s.nextToken();
					tr = s.nextToken();
					arrayValue.add(tr);
				}
				map.put(tl, arrayValue);
			}
			// exception handling
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}