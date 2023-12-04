package hardware;

import java.io.Serializable;
import java.util.ArrayList;
import software.Order;
import users.Account;

/* Class Description
 * The Department class extends to 3 sub-classes,
 * that make up the varying departments of a given company.
 */

public abstract class Department implements Serializable
{
	private static final long serialVersionUID = -8089280143770671094L;
	private String location; // Physical Address/ Location of the department object.
	protected int idNum;	 // Unique ID number of a department.
	protected String identifier; // Top level identifier string of department type, for simplifying object interaction.
	private ArrayList<Account> accounts; // List of system accounts belonging to a department object.
	protected ArrayList<Order> orders;   // List of orders associated with a department object.
	
	public Department()
	{
		location = "unknown";
		identifier = "unknown";
		accounts = new ArrayList<Account>();
		orders = new ArrayList<Order>();
	}
	
/************* Basic Getter and Setter Methods *************/

	public void setLocation(String aLocation)
	{
		location = aLocation;
	}
	public String getLocation() 
	{
		return location;
	}
	
	public void setID(int aID)
	{
		idNum = aID;
	}
	public String getID()
	{
		return (identifier + idNum);
	}
	public String getIdentifier()
	{
		return identifier;
	}
	
	public void addAccount(Account aAccount)
	{
		accounts.add(aAccount);
	}
	public void removeAccount(Account aAccount) 
	{
		accounts.remove(aAccount);
	}
	public ArrayList<Account> getAccountList()
	{
		return accounts;
	}
	
	public void addOrder(Order aOrder)
	{
		orders.add(aOrder);
	}
	public ArrayList<Order> getOrders()
	{
		return orders;
	}
	
	public abstract ArrayList<Item> getInventory();
}
