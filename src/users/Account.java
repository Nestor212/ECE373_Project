package users;

public abstract class Account {

	private String name;
	private String username;
	private String password;
	private int accountNum;
	private int accessLevel;
	private static int accounts;
	
	public Account() 
	{
		name = "unknown";
		username = "unknown";
		password = "unknown";
		accountNum = accounts;
		accounts++;
		accessLevel = 0;
	}
	
	
}

