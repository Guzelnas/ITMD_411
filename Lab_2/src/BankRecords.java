/*
 * Guzel Nasybullina
 * ITMD 411
 * 09/30/2017
 * 
 * PROJECT DESCRIPTION:
 * Bank of IIT has gotten their hands on some interesting data 
 * which will allow for possible loans to various clients from various regions.
 * Accompanying the labs specs is a csv (comma separated value) file named 
 * bank-Detail.csv which contains valuable raw data to allow the bank to process
 * loans based on client details from the file.
 * You need to parse the data and print record data for future loan considerations
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BankRecords extends Client 
{	
	//static objects for IO processing
	static BankRecords robjs[]=new BankRecords[600];
	//arraylist to hold spreadsheet rows & columns
	static ArrayList<List<String>> array = new ArrayList<>();
	
	//instance fields 
	private String id;
	private int age;
	private String sex;
	protected String region;
	private double income;
	private String married;
	private int children;
	private String car;
	private String save_act;
	private String current_act;
	private String mortgage;
	private String pep;
	
	//read data from file
	public void readData() 
	{
		BufferedReader br;
		String line;
		
		try 
		{
		    //initialize reader object and set file path to root of project
			br = new BufferedReader(new FileReader(new File("bank-Detail.csv")));
			
			
			//read each record in csv file
			while((line=br.readLine()) !=null) 
			{
				//parse each record in csv file by a comma ( , )
	             //into a list stored in the arraylist-> Arrays
				array.add(Arrays.asList(line.split(",")));		
			}
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        //System.out.println(array.get(0));
			processData(); //call function for processing record data
		}
		
	
	    //process data
		public void processData() 
		{
			//create index for array while iterating thru arraylist
			int idx=0;
			
			//create for each loop to cycle thru arraylist of values 
		    //and PASS that data into your record objects' setters 
			for (List<String>rowData:array) 
			{
				
			//create loop to grab each array index containing a list of values
			//and pass that data into your record object's setters
				//initialize array of objects
				robjs[idx]=new BankRecords();
				//call setters and populate them, item by item
				robjs[idx].setId(rowData.get(0));
				robjs[idx].setAge(Integer.parseInt(rowData.get(1)));
				robjs[idx].setSex(rowData.get(2));
				robjs[idx].setRegion(rowData.get(3));
				robjs[idx].setIncome(Double.parseDouble(rowData.get(4)));
				robjs[idx].setMarried(rowData.get(5));
				robjs[idx].setChildren(Integer.parseInt(rowData.get(6)));
				robjs[idx].setCar(rowData.get(7));
				robjs[idx].setSave_act(rowData.get(8));
				robjs[idx].setCurrent_act(rowData.get(9));
				robjs[idx].setMortgage(rowData.get(10));
				robjs[idx].setPep(rowData.get(11));
				idx++;
					
			}
			printData();//calling function to print objects held in memory
		}
		
		//print data
		public void printData()
		{
			//1. Set appropriate headings for displaying first 25 records

			System.out.println("ID\t\tAge\t\tSex\t\tRegion\t\tIncome\t\tMortgage");
			//2. Create for loop and print each record objects instance data 
			for (int i=0; i<25; i++)
			{
		    //3. Within for loop use appropriate formatting techniques to print
			//   out record detail  			
			String s = String.format("%s\t\t%s\t\t%s\t\t%-10s\t%8.2f\t%s",robjs[i].getId(),robjs[i].getAge(),robjs[i].getSex(),
					robjs[i].getRegion(),robjs[i].getIncome(),robjs[i].getMortgage());
			System.out.println(s);
			}
		}

		/**
		 * @return the id
		 */
		public String getId() 
		{
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) 
		{
			this.id = id;
		}

		/**
		 * @return the age
		 */
		public int getAge() 
		{
			return age;
		}

		/**
		 * @param age the age to set
		 */
		public void setAge(int age) 
		{
			this.age = age;
		}

		/**
		 * @return the sex
		 */
		public String getSex() 
		{
			return sex;
		}

		/**
		 * @param sex the sex to set
		 */
		public void setSex(String sex) 
		{
			this.sex = sex;
		}

		/**
		 * @return the region
		 */
		public String getRegion() 
		{
			return region;
		}

		/**
		 * @param region the region to set
		 */
		public void setRegion(String region) 
		{
			this.region = region;
		}

		/**
		 * @return the income
		 */
		public double getIncome() 
		{
			return income;
		}

		/**
		 * @param income the income to set
		 */
		public void setIncome(double income) 
		{
			this.income = income;
		}

		/**
		 * @return the married
		 */
		public String getMarried() 
		{
			return married;
		}

		/**
		 * @param married the married to set
		 */
		public void setMarried(String married) 
		{
			this.married = married;
		}

		/**
		 * @return the children
		 */
		public int getChildren() 
		{
			return children;
		}

		/**
		 * @param children the children to set
		 */
		public void setChildren(int children) 
		{
			this.children = children;
		}

		/**
		 * @return the car
		 */
		public String getCar() 
		{
			return car;
		}

		/**
		 * @param car the car to set
		 */
		public void setCar(String car) 
		{
			this.car = car;
		}

		/**
		 * @return the save_act
		 */
		public String getSave_act() 
		{
			return save_act;
		}

		/**
		 * @param save_act the save_act to set
		 */
		public void setSave_act(String save_act) 
		{
			this.save_act = save_act;
		}

		/**
		 * @return the current_act
		 */
		public String getCurrent_act() 
		{
			return current_act;
		}

		/**
		 * @param current_act the current_act to set
		 */
		public void setCurrent_act(String current_act) 
		{
			this.current_act = current_act;
		}

		/**
		 * @return the mortgage
		 */
		public String getMortgage() 
		{
			return mortgage;
		}

		/**
		 * @param mortgage the mortgage to set
		 */
		public void setMortgage(String mortgage) 
		{
			this.mortgage = mortgage;
		}

		/**
		 * @return the pep
		 */
		public String getPep() 
		{
			return pep;
		}

		/**
		 * @param pep the pep to set
		 */
		public void setPep(String pep) 
		{
			this.pep = pep;
		}
		
		//main to run the application
		public static void main(String[] args) 
		{
			BankRecords br = new BankRecords();
			br.readData();
			
		}
	}


	
		
		
	
