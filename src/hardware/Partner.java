package hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import software.Order;
import users.Account;

public abstract class Partner implements Serializable
{
	private static final long serialVersionUID = 4992597619140668060L;
	private String name;
	private Integer partnerID;
	private Float accountBalance;
	private ArrayList<Account> accounts;
	
	public Partner()
	{
		Random rand = new Random();
		name = "unknown";
		partnerID = rand.nextInt(10000);
		accountBalance = rand.nextFloat(10000);
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
	
	public void setPartnerID(Integer aNum)
	{
		partnerID = aNum;
	}
	public Integer getPartnerID()
	{
		return partnerID;
	}
	
	public void addToBalance(Float dollars)
	{
		accountBalance = accountBalance + dollars;
	}
	
	public Float getAccountBalance()
	{
		return accountBalance;
	}
	
	public void makePayment(Float aNum)
	{
		accountBalance = accountBalance - aNum;
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
	
	public abstract ArrayList<Order> getOrders();
	
	public String toString()
	{
		return(this.getName());
	}
}
