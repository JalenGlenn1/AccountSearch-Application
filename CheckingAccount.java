public class CheckingAccount extends Account {
	
	private double overdraft;
	static double fee;
	
	public CheckingAccount(String acctNo) {
		
		super(acctNo);
		overdraft = 0; 
	}
	
	public CheckingAccount(String acctNo, double initBalance, double overdraft) {
		super(acctNo,initBalance);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}
	
	public void setOverdraft(double newOverdraft) {
		
		this.overdraft = newOverdraft;
	}
	
	public static double getFee() {
		
		return fee;
	}
	
	public static void setFee (double newFee) {
		
		fee = newFee; 
	}
	/*
	 * search the array of a list of checking accounts 
	 * based on the given account number.
	 */
	public static int checkingAccountSearch(Account [] checkingAccount, int num, String acctNo)
		
		throws AccountNotFoundException {
			for (int i = 0; i < num; i++) {
				
				if(checkingAccount[i].getAcctNo().equals(acctNo)) {
					
					checkingAccount[i].displayInfo();
					
					return i;
				}
			}
			
		throw new AccountNotFoundException("Account with account number, " + acctNo + " was not found");
}
	
	@Override
	public String toString() {
		
		return "Account number: " + this.getAcctNo() + "\nCurrent balance: " + this.getBalance() + 
			"\nOverdraft Amount: " + this.overdraft;
	}
	
	/**
	 * This method is similar to the withdraw method in Account class 
	 * except that even if there is not enough money in the checking account, 
	 * a withdraw can be done as long as it is within overdraft limit, 
	 * but overdraft fee will be charged.
	 */
	
	@Override
	public void withdraw(double amount) {
        if (this.getBalance() - fee - amount > overdraft) {
        	
        	if (this.getBalance() < amount) {
        		amount += fee;
        	}
        	this.setBalance(this.getBalance() - amount);
        }else {
        	System.out.println("Insufficient Amount.");
        }
    }
	/**
	 * This method is similar to the transfer method in 
	 * Account class except that even if there is not enough 
	 * money in the checking account, a transfer can be done as
	 * long as it is within overdraft limit, but overdraft fee will be charged.
	 * The new balance after the fee charge cannot be lower than the overdraft limit.
	 */
	@Override
	public void transfer(Account s, double amount) {
		    
		if (this.getBalance() - fee - amount > overdraft) {
        	
			double amountPlusFee = amount;
        	if (this.getBalance() < amount) {
        		amountPlusFee += fee;
        	}
        	
        	this.setBalance(this.getBalance() - amountPlusFee);
        	s.deposit(amount);
        	
        }else {
        	System.out.println("Insufficient Amount.");
        }
    }
	 
	 /**  displays the account information in the
		 * following format:
		 *
		 */
	@Override
	public void displayInfo() {
		
		System.out.println("Account Number: \t" + this.getAcctNo());
		System.out.println("Current Balance: \t" + this.getBalance());
		System.out.println("Overdraft Amount: \t" + this.getOverdraft());
	}
	
