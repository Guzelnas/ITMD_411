import java.util.Comparator;

public class averageComparator implements Comparator<BankRecords> {

	@Override
	public int compare(BankRecords o1, BankRecords o2) {
		// use compareTo to compare strings
		int result = String.valueOf(o1.getIncome()).compareTo(String.valueOf(o2.getIncome()));
		return result;
	}
}
