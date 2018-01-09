/**
 * Guzel Nasybullina
 * ITMD 411
 * 9/9/17
 * Bank of IIT has contacted you to write, compile and execute 
 * a complete program that creates bank account information and
 * executes various transaction details for their clients. Your
 * program will prompt users for options such as creating an
 * initial balance, entering deposits or withdrawals. Also your
 * program will allow for the printing of account information
 * including interest at various interest rates. Use loops,
 * user defined methods, conditional and relational logic and
 * the basics of OOP to accomplish the objectives of this progam.
 * Error trapping will be part of your grade so don’t forget 
 * to include some basic error trapping logic! Comment your 
 * code thoroughly as well for maximum points.
 *
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;


public class AccountHolderTest 
{
	public static void main(String args[]) 
	{
		
//declare variables	
		double newBalance;
		double deposit;
		double withdrawal;
		double newRate;

		Scanner input = new Scanner(System.in);
		
//prompt user to enter initial balance
		System.out.println("Enter the initial balance");
		newBalance = input.nextDouble();
		AccountHolder acctObj = new AccountHolder(newBalance);
		
//prompt user to enter a deposit amount
		System.out.println("Enter the amount to be deposited");
		deposit = input.nextDouble();
		acctObj.deposit(deposit);
		System.out.println("Your balance now is " + acctObj.getbalance());
		
//prompt user to enter a withdrawal amount
		System.out.println("\nEnter the amount for withdrawl");
		withdrawal = input.nextDouble();
		acctObj.withdraw(withdrawal);
		System.out.println("Your balance now is " + acctObj.getbalance());
		
//Show monthly balances with 0.4 interest rate
		acctObj.modifymonthlyInterest(0.04);
		System.out.println("\nMonthly balances for one year at .04 interest rate are: ");

		for (int month = 1; month <= 12; month++) 
		{
			String monthLabel = String.format("Month%d: ", month);
			acctObj.monthlyInterest();
			System.out.printf("%-10s%10s%n", monthLabel, acctObj.toString());
		}
		
//allow user to enter new interest rate and show an updated balance
		System.out.println("\nEnter new interest rate: ");
		newRate = input.nextDouble();
		System.out.println("Account Balance w. Interest ");
		acctObj.modifymonthlyInterest(newRate);
				
		acctObj.monthlyInterest();
		System.out.printf("%.2f", acctObj.getbalance());
		
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\nCur dt=" + timeStamp + "\nProgrammed by Guzel Nasybullina\n");

	}
}

