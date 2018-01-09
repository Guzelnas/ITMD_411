
/* Guzel Nasybullina
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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoanProcessing extends BankRecords {
	private static long d = 0;
	private static long d1 = 0;
	private static long d2 = 0;

	// Serializing object to a file
	public static void SerializeObject(PObject object) {
		long begin;
		long end;
		begin = System.currentTimeMillis();
		try {
			// To serialize the object
			FileOutputStream fileoutput = new FileOutputStream("bankrecords.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileoutput);
			out.writeObject(object);
			out.close();
			fileoutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Calculate time to deserialize
		end = System.currentTimeMillis();
		d = end - begin;
	}

	// Calculate time to deserialize
	public static PObject DeSerializeObject() {

		// Deserializing the object
		long begin, end;
		begin = System.currentTimeMillis();
		PObject newBankRecords = null;
		try {
			FileInputStream fin = new FileInputStream("bankrecords.txt");
			ObjectInputStream inObject = new ObjectInputStream(fin);
			newBankRecords = (PObject) inObject.readObject();
			inObject.close();
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Calculate time to deserialize
		end = System.currentTimeMillis();
		d1 = end - begin;
		return newBankRecords;
	}

	public static void main(String args[]) {
		Date date = new Date();
		// BankRecord object is created
		BankRecords br = new BankRecords();
		// DAO object created
		DAO dao = new DAO();
		br.readData();
		// HashMapper object created to call HashMaped data from bankrecords
		hashMapper bankRecords = new hashMapper();
		Map<String, ArrayList<String>> records = bankRecords.getBankRecords();
		// Object is created to encapsulating hashmap and date
		PObject object = new PObject(date, records);
		// calling serialization method
		SerializeObject(object);
		// DeSerialization method called
		DeSerializeObject();
		d2 = d1 - d;
		try {
			// different values are displayed in this section of code
			System.out.println("Current Date= " + date + "\n" + "Programmed by Guzel Nasybullina" + "\n");
			// Table Created in Database
			dao.createTable();
			System.out.println();
			// Value Inserted in Database from Bank-Detail.csv
			dao.inserts(robjs);
			System.out.println();
			// Sleep for 5 seconds..
			Thread.sleep(5000);
			// Show serialized time
			System.out.println(" The file is serialized from bankrecords.txt in " + d);
			System.out.println();
			// Show deserialized time
			System.out.println(" The file is deserialized from bankrecords.txt in " + d1);
			System.out.println();
			// Show the difference in serialization and deserialization
			System.out.println(" The file is serialized and deserialized from bankrecords.txt in " + d2);
			System.out.println();
			System.out.println(" The final hashmap size: " + records.size() + " " + date);
			System.out.println();
			System.out.println(" The final hashmap values:" + records);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}