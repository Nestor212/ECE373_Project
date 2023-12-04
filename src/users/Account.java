package users;

import java.io.Serializable;
import java.util.Random;

import hardware.Department;
import hardware.Partner;
import hardware.Supplier;
import hardware.Warehouse;
import software.StoreOrder;
import software.WarehouseOrder;

/* Class Description
 * Abstract method that instantiates all basic information pertaining to an account in the system. 
 * 
 * accessLevel sets up the account type, later used to taylor the GUI appearance and functionality 
 * fot a specific account type.
 * 
 * Access Levvels:
 * 
 * 10 - Admin Account
 * 20 - Inventory Staff Account
 * 30 - Transport Account
 * 40 - Supplier Account
 */

public abstract class Account implements Serializable 
{
	private static final long serialVersionUID = 2460363224194696666L;
	private String name;
	private String username;
	private String password;
	private String email;
	private Integer accountNum;
	protected Integer accessLevel;
	protected Department department;
	protected Partner partner;

	public Account() 
	{
		Random rand = new Random();
		name = "unknown";
		username = "unknown";
		password = "unknown";
		accountNum = rand.nextInt(100000);
		accessLevel = 0;
		email = "unknown@unknown.com";
	}
	
/************* Basic Getter and Setter Methods *************/

	public void setName(String aName)
	{
		name = aName;
	}
	public String getName()
	{
		return name;
	}
	public void setEmail(String aEmail)
	{
		email = aEmail;
	}
	public String getEmail()
	{
		return email;
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
	
	public Integer getAccountNumber()
	{
		return accountNum;
	}
	
	public void setAccessLevel(Integer aLevel)
	{
		accessLevel = aLevel;
	}
	public Integer getAccessLevel()
	{
		return accessLevel;
	}

	public Department getDepartment()
	{
		return department;
	}
	public  Partner getPartner()
	{
		return partner;
	}
	
/************* Abstract Methods for creating orders *************/

	abstract public StoreOrder createStoreOrder(Warehouse aWH);
	abstract public WarehouseOrder createWarehouseOrder(Supplier aSupplier);
}

