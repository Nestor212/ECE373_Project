package hardware;

import java.util.ArrayList;

import users.Account;

public abstract class Partner 
{
	private String name;
	private int accountNum;
	private double accountBalance;
	private ArrayList<Account> accounts;
	
	public Partner()
	{
		name = "unknown";
		accountNum = 0;
		accountBalance = 0.0;
		accounts = new ArrayList<Account>();
	}
	
	public void setName(String aName)
	{
		name = aName;
	}
	public String getName()
	{
		return name;
	}
	
	public void setAccountNum(int aNum)
	{
		accountNum = aNum;
	}
	public int getAccountNum()
	{
		return accountNum;
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
}
