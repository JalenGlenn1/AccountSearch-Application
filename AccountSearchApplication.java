/*
 * Jalen Glenn 
 * 10/20/2020
 * 
 * program reads a list of accounts from an input file and allows 
 * user to search for one or more accounts. 
 * It will also handle exceptions, including I/O related 
 * exceptions and exception when an account is not found.  
 */

import java.util.*; 
import java.io.*;

public class AccountSearchApplication {
	
	/**
	 *  Allows the first n checking accounts and the first n savings accounts to be searched.
	 */
	public static final int MAX_ACCOUNTS = 100;
	

	public static void main(String[] args) throws IOException {
		
		
		
		String fileName = "assg2_input.txt"; 
		Scanner inputStream = null; 
		String line;
		
		try {
              inputStream = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
        	System.out.println("Error opening the file " + fileName);
        	System.exit(1); 
        }
		
	
		
		CheckingAccount[] checkingAccounts = new CheckingAccount[MAX_ACCOUNTS];
		SavingsAccount[] savingsAccounts = new SavingsAccount[MAX_ACCOUNTS];
		
		double checkingBalanceSum = 0;
		double savingsBalanceSum = 0;
		String checkingAllAccounts = ""; 
		String savingsAllAccounts = "";
		
        int numChecking = 0;
        int numSavings = 0;
        
        while(inputStream.hasNextLine()) {
        
        	
        	line = inputStream.nextLine(); 
        	String[] data = line.split("\\s+");
        	
        	
        	if (data.length == 4) {
        		String type = data[0];
        		String acctNo = data[1];
        		String balanceString = data[2];
        		String overdraftOrRateString = data[3];
        		
        		
        		
        		// Check if the account type is correct
        		if (!type.equals("C") && !type.equals("S")) {
        			throw new IOException("Account is incorrect.");
        		}
        		
        		// Check if the account number is correct
        		if (!acctNo.matches("[0-9]+")) {
        			throw new IOException("Account number must be a positive integer");
        		}
        		
        		try {
        			// Check if the balance is correct
        			double bal = Double.parseDouble(balanceString);
        			
            		
            		try {
        				double overdraftOrRate = Double.parseDouble(overdraftOrRateString);
        				
        				// If this is a checking account, ensure the overdraft rate is negative
        				if (overdraftOrRate > 0 && type.equals("C")) {
        					System.out.println("Overdraft amount must be negative.");
        				}else {
        					
        					
        					// The account data was formatted properly, create the account if there's a space left.
        					if (type.equals("C")) {
        						
        						if (numChecking == MAX_ACCOUNTS) {
        							System.out.println("Only first " + MAX_ACCOUNTS + " accounts indexed.");
        							break;
        						}
        						CheckingAccount newAcct = new CheckingAccount(acctNo, bal, overdraftOrRate);
    	        				checkingAccounts[numChecking] = newAcct; 
    	        				
    	        				// Add this to the total balance of checking accounts;
    	        				checkingBalanceSum += newAcct.getBalance();
    	        				
    	        				// Add this account to the list of checking accounts;
    	        				checkingAllAccounts = checkingAllAccounts.concat(
    	        							'\t' +
    	        							newAcct.getAcctNo() + ", " +
    	        							newAcct.getBalance() + "\n"
    	        						);
    	        				
    	        				numChecking++;
    	        			}else {
    	        				if (numSavings == MAX_ACCOUNTS) {
        							System.out.println("Only first " + MAX_ACCOUNTS + " accounts indexed.");
        							break;
        						}
    	        				SavingsAccount newAcct = new SavingsAccount(acctNo, bal, overdraftOrRate);
    	        				savingsAccounts[numSavings] = newAcct;
    	        				
    	        				// Add this to the total balance of checking accounts;
    	        				savingsBalanceSum += newAcct.getBalance();
    	        				
    	        				// Add this account to the list of checking accounts;
    	        				savingsAllAccounts = savingsAllAccounts.concat(
    	        							'\t' +
    	        							newAcct.getAcctNo() + ", " +
    	        							newAcct.getBalance() + "\n"
    	        						);
    	        				
    	        				numSavings++;
    	        			}
        					
        				}
        			}catch (NumberFormatException e) {
        				// The overdraft or rate is not correctly formatted
        				if (type.equals("C")) {
        					System.out.println("Overdraft amount must be in the form -XXX or -XXX.XX");
        				}else {
        					System.out.println("Rate must be in the form XXX or XXX.XX");
        				}
        			}
        			
        			
        		}catch(NumberFormatException e) {
        			// The balance is not correctly formatted
        			System.out.println("Balance must be in the form XXX or XXX.XX");
        		}
    			     		
        	}
        	
        }
        
        inputStream.close();
        
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("Number of checking accounts: " + numChecking);
        
        System.out.print("\nAverage balance of all checking accounts: " + (checkingBalanceSum / numChecking ));
        
        System.out.print("\nNumber of savings accounts: " + numSavings);
        
        System.out.println("\nAverage balance of all savings accounts: " + (savingsBalanceSum / numSavings));
        
        System.out.print("\nAll the checking accounts:\n" + checkingAllAccounts);
        
        System.out.print("\nAll the savings accounts:\n" + savingsAllAccounts);
        
        System.out.print("\nStart searching ....");
        
        boolean search = true;
        
        // Search for an account
        while (search) {
        	
        	System.out.print("\nEnter account type (C/S – C for Checking and S for Savings) to search: "); 
            char x = kbd.nextLine().toUpperCase().charAt(0);
            
            // Make sure the account type is S or C
            if (x == 'C' || x =='S') {
            	System.out.print("\nEnter account number: "); 
                String y = kbd.nextLine();
                
                // Decide whether to use CheckingAccount or SavingsAccount search
                if (x == 'C') {
                	try {
						CheckingAccount.checkingAccountSearch(checkingAccounts, numChecking, y);
					} catch (AccountNotFoundException e) {
						// Show the checking account was not found and continue the program.
						System.out.println("No such checking account.");
					}
                }else {
                	try {
						SavingsAccount.savingsAccountSearch(savingsAccounts, numSavings, y);
					} catch (AccountNotFoundException e) {
						// Show the savings account was not found and continue the program.
						System.out.println("No such savings account.");
					}
                }
            }else {
            	// Show the error with the account type
            	System.out.println("\n" + x + " is an invalid Account Type\n");
            }
           
            System.out.println("Do you want to search for another account? (Y/N)");
            
            // Search again if the user inputs a Y as the first character in a word. (y / yes)...
            search = kbd.nextLine().toUpperCase().charAt(0) == 'Y';
           
        }
        kbd.close();
	}
}
