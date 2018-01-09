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

public abstract class Client {
	// abstract methods inheritable for BankRecords class
	public abstract void readData();// read data

	public abstract void processData();// process data

	public abstract void printData();// print data

}
