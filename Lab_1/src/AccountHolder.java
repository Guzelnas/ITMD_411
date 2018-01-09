/**
 * 
 * @author Guzel Nasybullina
 * ITMD 411, Lab 1
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
public class AccountHolder 
{
	//declaring class fields:
	private static double annualInterestRate;//interest rate 
	private double balance;//balance for current account

//constructor, creates a new account with the specified balance, checks for negative numbers
	public AccountHolder(double newBalance) 
	{
		if (newBalance > 0) 
		{
			balance = newBalance;
		} 
		else
		{
			throw new IllegalArgumentException("Balance cannot be negative!");
			
		}
	}
	
//method to show balance
	public double getbalance() 
	{
		return balance;
	}
	
//method to calculate monthly interest based on balance and annualInterestRate
	public void monthlyInterest() 
	{
		balance += balance * (annualInterestRate / 12.0);
	}
	
//method to modify interest rate
	public static void modifymonthlyInterest(double newRate) 
	{
		if (newRate > 0.0 && newRate <= 1.0) 
		{
			annualInterestRate = newRate;
		}
		else
		{
			throw new IllegalArgumentException("Interest rate cannot be lower than 0 and over than 1!");
		}
	}
	
//method to make a deposit
	public void deposit(double depositAmount) 
	{
		balance += depositAmount;
	}
	
//method to make a withdrawal, also check if balance is low
	public void withdraw(double withdrawalAmount) 
	{
		balance -= withdrawalAmount;
		if (balance < 100) 
		{
			throw new IllegalArgumentException("Your balance is too low!");
		}
		else if (balance < 500) 
		{
			balance = balance - 50;
		}
	}
	
//get string representation of account
	public String toString() 
	{
		return String.format("$%.2f", balance);
	}
	
	public static double getAnnualInterestRate() 
	{
		return annualInterestRate;
	}
		
	
}
