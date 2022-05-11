public class SavingsAccount extends Account {
	
	private double interestRate;

	public SavingsAccount(String acctNo) {
		
		super(acctNo);
		interestRate = 0; 
	}
	
	public SavingsAccount(String acctNo, double balance, double interestRate) {
		
		super(acctNo, balance);
		this.interestRate = interestRate;
		
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double newInterestRate) {
		interestRate = newInterestRate;
	}
	/**
	 * Add the interest earned to the balance.
	 * New balance should be equal to (1+interestRate)*balance. 
	 * @return
	 */
	public double addInterest() {
	
		double newBalance;
		newBalance = (1 + interestRate) * getBalance();
		
		this.setBalance(newBalance);
		
		return newBalance;
	}
	/*
	 * search the array of a list of savings accounts 
	 * based on the given account number.
	 */
	public static int savingsAccountSearch(Account [] savingsAccount, int num, String acctNo)
	
			throws AccountNotFoundException {
				for (int i = 0; i < num; i++) {
					
					if(savingsAccount[i].getAcctNo().equals(acctNo)) {
						
						savingsAccount[i].displayInfo();
						
						return i;
					}
				}
				
			throw new AccountNotFoundException("Account with id, " + acctNo + " Was not found");
	}
	
	public String toString() {
	
		return "Account number: " + getAcctNo() + "\nCurrent getBalance(): " + getBalance() + 
			"\nInterest Rate: " + interestRate;
	}
	
	/**  displays the account information in the
	 * following format:
	 *
	 */
	public void displayInfo() {
	
		System.out.println("Account Number: \t" + getAcctNo());
		System.out.println("Current Balance( \t" + getBalance()); 
		System.out.println("Interest Rate: \t" + getInterestRate());
	}
}
