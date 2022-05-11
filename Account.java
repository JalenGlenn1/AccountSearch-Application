public class Account  {
	
    private String acctNo;
    private double balance;
    
   
	public Account(String accountNumber) {
		
		balance = 0; 
		acctNo = accountNumber;
	}
	public Account(String accountNumber, double initBalance) {
		assert initBalance < 0 : "Violation of: initBalance must be non-negative";
		
		acctNo = accountNumber;
		balance = initBalance;
		
	}
	
	/** This is a "get" method used to retrieve the account number
     * @return acctNo
     */
    
	public String getAcctNo() {
          
    	return acctNo;
    }
    
	/** This is a "get" method used to retrieve the account balance. * @return balance
     */
   
	public double getBalance() {
          
    	return balance;
}
    /**
     * @param balance
     */
   
	public void setBalance(double newBalance) {
    
    	balance = newBalance;
    }

    /**
     * @param amount
     */
    
	public void deposit(double amount) {
          
    	if (amount >= 0) {
        	  
    		balance += amount;
    	}
     
    	else {
    		System.out.println("Insufficient Amount");
    	}
    }
    
	/**
	 * The parameter indicates the amount to be withdrawn to the account 
	 * and the balance should be decreased accordingly. 
	 * @param amount
	 */
    public void withdraw(double amount) {
          
    	if (amount < balance) {
    
    		balance -= amount;
    	}
   
    	else {
    		
    		System.out.println("Insufficient Amount");
    	}
    }
    
    /** this method allows transferring money from another Account. *
     * @param s
     * @param amount
     */
    
    public void transfer(Account s, double amount) {
    
    	if (amount > 0 && amount <= balance) {
    
    		s.balance += amount;
    
    		balance -= amount;
    	}
    
    	else {
    
    		System.out.println("Insufficient Amount");
    	}
    }

    /**  displays the account information in the
     * following format:
     *
     */
    
    public void displayInfo() {
    
    	System.out.println("Account Number: \t" + this.getAcctNo());
    
    	System.out.println("Current balance: \t" + getBalance()); 
    }
    
    @Override
    public String toString() {
    	return "Account Number: \t" + this.getAcctNo() +
    			"\nCurrent Balance: \t" + this.getBalance();
    }
    
    @Override
    public boolean equals(Object o) {
    	return (o instanceof Account) && ((Account)o).getAcctNo().equals(acctNo);
    }
}
