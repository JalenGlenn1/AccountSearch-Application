public class AccountNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor
	 */
	public AccountNotFoundException() {
		
		super("Account not found");
	}
	/**
	* One-parameter constructor
	* @param msg The given message
	*/
	public AccountNotFoundException(String msg) {
		
		super(msg);
	}
}
