package hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import software.Order;
import users.Account;

/* Class Description
 * The Partner class extends to 2 sub-classes,
 * Partner objects can be suppliers, where inventory is sourced, 
 * and contracted transportation companies responsible for moving inventory across locations. 
 */

public abstract class Partner implements Serializable
{
	private static final long serialVersionUID = 4992597619140668060L;
	private String name; // Name of the Partner company
	private Integer partnerID; // Unique ID number of a partner.
	private Float accountBalance; // Amount owed to a partner for their supply or service.
	private ArrayList<Account> accounts; // List of system accounts belonging to a department object.
	private ArrayList<Order> orders; // List of orders associated with a Suplpier Object. 

	public Partner()
	{
		Random rand = new Random();
		name = "unknown";
		partnerID = rand.nextInt(10000);
		accountBalance = rand.nextFloat(10000);
		accounts = new ArrayList<Account>();
		orders = new ArrayList<Order>();
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
	
	public void addOrder(Order aOrder)
	{
		orders.add(aOrder);
	}
	public ArrayList<Order> getOrders()
	{
		return orders;
	}	
	public String toString()
	{
		return(this.getName());
	}
}
