package users;

import hardware.Department;
import hardware.Partner;

public abstract class Account {

	private String name;
	private String username;
	private String password;
	private int accountNum;
	protected int accessLevel;
	private static int accounts;
	protected Department department;
	protected Partner partner;

	public Account() 
	{
		name = "unknown";
		username = "unknown";
		password = "unknown";
		accountNum = accounts;
		accounts++;
		accessLevel = 0;
	}
	
	public void setName(String aName)
	{
		name = aName;
	}
	public String getName()
	{
		return name;
	}
	
	public void setUsername(String aName)
	{
		username = aName;
	}
	public String getUsername()
	{
		return username;
	}
	
	public void setPassword(String aPassword)
	{
		//TO DO: Add password restrictions 
		password = aPassword;
	}
	public String getPassword()
	{
		// TO DO: Figure out secure way to deliver or reset password
		return password;
	}
	
	public String emailPassword()
	{
		// TO DO: Figure out secure way to deliver or reset password
		return password;
	}
	
	public int getAccountNumber()
	{
		return accountNum;
	}
	
	public void setAccessLevel(int aLevel)
	{
		accessLevel = aLevel;
	}
	public int getAccessLevel()
	{
		return accessLevel;
	}

	public abstract Object getDepartment();
	
}

