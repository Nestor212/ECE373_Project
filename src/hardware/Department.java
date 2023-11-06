package hardware;

import java.util.ArrayList;

import users.Account;

public abstract class Department 
{
	private String location;
	private int idNum;
	private ArrayList<Account> accounts;
	private ArrayList<Item> inventory;
	
	public Department()
	{
		location = "unknown";
		idNum = 0;
		accounts = new ArrayList<Account>();
		inventory = new ArrayList<Item>();
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
	
	public void addToInventory(Item aItem)
	{
		inventory.add(aItem);
	}
	public ArrayList<Item> getInventory()
	{
		return inventory;
	}
}
