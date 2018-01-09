import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTest {

	AccountHolder a = new AccountHolder(1);
	
	@Test
	public void testBalance() {
		assertTrue(a.getbalance() > 0);
	}
	
	@Test
	public void testInitialInterest() {
	//test start value for interest
	double val = AccountHolder.getAnnualInterestRate();
	assertTrue( val == 0);
	}


	@Test
	public void testModifyInterest() {
	//test interest rate range (between 0 && 1)
	AccountHolder.modifymonthlyInterest(.2);
	double val = AccountHolder.getAnnualInterestRate();
	assertTrue( val > 0);
	}
	

	
}


