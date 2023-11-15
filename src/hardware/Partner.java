package hardware;

import java.util.ArrayList;

import software.Order;
import users.Account;

public abstract class Partner 
{
	private String name;
	private int partnerID;
	private double accountBalance;
	private ArrayList<Account> accounts;
	private static int partners;
	
	public Partner()
	{
		name = "unknown";
		partnerID = partners;
		accountBalance = 0.0;
		accounts = new ArrayList<Account>();
		partners++;
	}
	
	public void setName(String aName)
	{
		name = aName;
	}
	public String getName()
	{
		return name;
	}
	
	public void setPartnerID(int aNum)
	{
		partnerID = aNum;
	}
	public int getPartnerID()
	{
		return partnerID;
	}
	
	public void addToBalance(double dollars)
	{
		accountBalance = accountBalance + dollars;
	}
	
	public double getAccountBalance()
	{
		return accountBalance;
	}
	
	public void addAccount(Account aAccount)
	{
		accounts.add(aAccount);
	}
	public ArrayList<Account> getAccountList()
	{
		return accounts;
	}
	
	public void addSupplier()
	{
		
	}
	
	public abstract ArrayList<Order> getOrders();
	
	public String toString()
	{
		return(this.getName());
	}
}
