package hardware;

import java.util.ArrayList;

import software.Order;
import software.StoreOrder;
import users.Account;

public abstract class Department 
{
	private String location;
	private int idNum;
	protected String identifier;
	private ArrayList<Account> accounts;
	protected ArrayList<Order> orders;
	private static int departments;
	
	public Department()
	{
		location = "unknown";
		identifier = "unknown";
		idNum = departments;
		accounts = new ArrayList<Account>();
		orders = new ArrayList<Order>();
		departments++;
	}
	
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
