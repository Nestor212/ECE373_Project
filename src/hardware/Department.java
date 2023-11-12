package hardware;

import java.util.ArrayList;

import software.Order;
import users.Account;

public abstract class Department 
{
	private String location;
	private int idNum;
	private ArrayList<Account> accounts;
	protected ArrayList<Order> orders;
	private static int departments;
	
	public Department()
	{
		location = "unknown";
		idNum = departments;
		accounts = new ArrayList<Account>();
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
	public int getID()
	{
		return idNum;
	}
	
	public void addAccount(Account aAccount)
	{
		accounts.add(aAccount);
	}
	public ArrayList<Account> getAccountList()
	{
		return accounts;
	}
}
