
/*   Add to your existing project files from lab 2, a new class called Records.
 *   Have the class extend the BankRecords class to grab hold its instance 
 *   methods plus the BankRecord object array.
 *   Provide at least 2 comparator classes implementing the java.util.Comparator
 *   interface for comparing various fields for the following data analysis requirements. 
 *   Perform the following analysis requirements and output detail for the Records class 
 *   Display the following data analytics in a coherent manner to the console: 
 *   average income for males vs. females 
 *   number of females with a mortgage and savings account
 *   number of males with both a car and 1 child per location
 *   
 *   Lab3
 *   Guzel Nasybullina
 *   ITMD 411
 *   10/25/2017
 * 
 *
 */

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.util.*;

public class Records extends BankRecords {
	// creating object to write output to BankOfIITRecords.txt
	private static FileWriter file = null;

	public Records() {
		try {
			file = new FileWriter("BankOfIITRecords.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Records records = new Records();
		records.readData();
		// calling function to perform analytics
		femaleComparator();// analyze avg income
		maleComparator();// analyze avg income
		averageComparator();// analyze avg income

		// closing file
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\nCur dt=" + timeStamp + "\nProgrammed by Guzel Nasybullina\n");

	}

	// method to find females with savings account and mortgage:
	public static void femaleComparator() {

		Arrays.sort(robjs, new genderComparator());

		int femaleSum = 0;

		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getSex().equals("FEMALE") && robjs[i].getMortgage().equals("YES")
					&& robjs[i].getSave_act().equals("YES")) {
				femaleSum++;
			}
		}

		// console: print results
		System.out.println("Data analytic results: " + "\n");
		System.out.println("Numbers of females with mortgage and saving account: " + femaleSum + "\r\n");
		// write results to a file
		try {

			file.write("Numbers of females with mortgage and saving account: " + femaleSum + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// method to find number of males with car and children living in different
	// areas:
	public static void maleComparator() {

		Arrays.sort(robjs, new genderComparator());
		int rural = 0;
		int inner = 0;
		int town = 0;
		int suburbs = 0;

		// males with car and children rural
		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equals("RURAL")
					&& robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {

				rural++;
			}
		}
		// console: print results
		System.out.println("Number of males living in rural region with car and children: " + rural);

		// males with car and children inner city
		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equals("INNER_CITY")
					&& robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {

				inner++;
			}
		}
		// console: print results
		System.out.println("Number of males living in inner city region with car and children: " + inner);

		// males with car and children town
		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equals("TOWN")
					&& robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {

				town++;
			}
		}
		// console: print results
		System.out.println("Number of males living in town region with car and children: " + town);

		// males with car and children suburbs
		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equals("SUBURBAN")
					&& robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {

				suburbs++;
			}
		}
		// console: print results
		System.out.println("Number of males living in suburban region with car and children: " + suburbs + "\n");

		// write results to a file
		try {
			file.write("Number of males living in rural region with a car and children: " + rural + "\r\n");
			file.write("Number of males living in inner city region with a car and children: " + inner + "\r\n");
			file.write("Number of males living in town region with a car and children: " + town + "\r\n");
			file.write("Number of males living in suburban region with a car and children: " + suburbs + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// method to find average income for females and males
	public static void averageComparator() {
		Arrays.sort(robjs, new averageComparator());
		Arrays.sort(robjs, new genderComparator());
		// formating output to 2 decimal places
		DecimalFormat df = new DecimalFormat("###.##");

		double incomeFemale = 0, averageFemale = 0;
		double incomeMale = 0, averageMale = 0;
		int femaleCount = 0, maleCount = 0;

		for (int i = 0; i < robjs.length; i++) {

			if (robjs[i].getSex().equals("FEMALE")) {

				incomeFemale += robjs[i].getIncome();
				femaleCount++;
			} else {
				incomeMale += robjs[i].getIncome();
				maleCount++;
			}

		}
		// calculating average
		averageFemale = incomeFemale / femaleCount;
		averageMale = incomeMale / maleCount;

		System.out.println("The average female income: " + df.format(averageFemale));
		System.out.println("The average male income: " + df.format(averageMale));
		// write results to a file
		try {
			file.write("The average female income: " + df.format(averageFemale) + "\r\n");
			file.write("The average male income: " + df.format(averageMale) + "\r\n");


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
