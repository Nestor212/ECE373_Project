package software;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import hardware.Corporate;
import hardware.Department;
import hardware.Partner;
import hardware.Store;
import hardware.Supplier;
import hardware.Transport;
import hardware.Warehouse;
import users.Account;

/* Class Description
 * This class is a representation of the company at the top level. 
 * It can have any number of stores and warehouse objects, partners, however,
 * it will only have one corporate object at any given time. 
 * 
 * All system information is associated back to this company object and therefore, 
 * can be extracted and manipulated through this object.
 */

public class Company implements Serializable 
{
	private static final long serialVersionUID = -5713405951321122768L;
	private String name; // Name of the company
	private ArrayList<Store> storeList; // List of all the store objects in the company
	private ArrayList<Warehouse> warehouseList; // List of all the warehouse objects in the company
	private ArrayList<Supplier> supplierList; // List of all the supplier objects in the company
	private ArrayList<Transport> transportList; // List of all the transport objects in the company
	private Corporate corporateOffice; // The corporate office associated with this company.
	
	public Company(String companyName)
	{
		name = companyName;
		storeList = new ArrayList<Store>();
		warehouseList = new ArrayList<Warehouse>();
		supplierList = new ArrayList<Supplier>();
		transportList = new ArrayList<Transport>();
		corporateOffice = new Corporate();
	}

/************* Basic Getter and Setter Methods *************/
	
	public void setname(String aName)
	{
		name = aName;
	}
	public String getName()
	{
		return name;
	}
	
	public void addStore(Store aStore)
	{
		storeList.add(aStore);
	}
	public void removeStore(Store aStore)
	{
		storeList.remove(aStore);
	}
	public ArrayList<Store> getStoreList()
	{
		return storeList;
	}

	public void addWarehouse(Warehouse aWarehouse)
	{
		warehouseList.add(aWarehouse);
	}
	public void removeWarehouse(Warehouse aWarehouse)
	{
		warehouseList.remove(aWarehouse);
	}
	public ArrayList<Warehouse> getWarehouseList()
	{
		return warehouseList;
	}
	
	public void setCorporateoffice(Corporate aCorporate)
	{
		corporateOffice = aCorporate;
	}
	public Corporate getCorporateOffice()
	{
		return corporateOffice;
	}
		
	public void addSupplier(Supplier aSupplier)
	{
		supplierList.add(aSupplier);
	}
	public void removeSupplier(Supplier aSupplier)
	{
		supplierList.remove(aSupplier);
	}
	public Supplier findSupplier(String aName)
	{
		for(int i = 0; i < supplierList.size(); i++)
		{
			if(supplierList.get(i).getName() == aName)
			{
				return supplierList.get(i);
			}
		}
		System.out.println("Supplier: " + aName + " not found.");
		return null;
	}
	public ArrayList<Supplier> getSupplierList()
	{
		return supplierList;
	}
	
	public void addTransport(Transport aTransport)
	{
		transportList.add(aTransport);
	}
	public void removeTransport(Transport aTransport)
	{
		transportList.remove(aTransport);
	}
	public ArrayList<Transport> getTransportList()
	{
		return transportList;
	}
	
/************* Save and Load Methods *************/
	
	public static void saveData(Company e)
	{
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;
		
		try 
		{
			System.out.println("Saving");
			fileOut = new FileOutputStream(e.getName() + ".ser");
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(e);
			objOut.close();
			fileOut.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
	}
	public static Company loadData(String companyName)
	{
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		Company company = null;
		
		try 
		{
			System.out.println("Loading");
			fileIn = new FileInputStream(companyName + ".ser");
			objIn = new ObjectInputStream(fileIn);
			company = (Company) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		return company;
	}

/************* Functionality Support Methods *************/

	// Return boolean based on results, then GUI will redirect based on response
	public Account login(String username, String password)
	{
		if (findAccountUsername(username) != null)
		{
			if (findAccountUsername(username).getPassword().equals(password))
			{
				return findAccountUsername(username);
			}
		}
		return null;	
	}
	
	// Input name of a Transportation company as a String, return the Transport object if found. 
	public Transport findTransport(String aName)
	{
		for(int i = 0; i < transportList.size(); i++)
		{
			if(transportList.get(i).getName() == aName)
			{
				return transportList.get(i);
			}
		}
		System.out.println("Transport: " + aName + " not found.");
		return null;
	}
	
	// Input username of an Account as a String, return the account object if found. 
	public Account findAccountUsername(String username)
	{
		// Parse through accounts to find 
		// Parse Corporate Accounts
		for(int i = 0; i < this.getCorporateOffice().getAccountList().size(); i++)
		{
			if(this.getCorporateOffice().getAccountList().get(i).getUsername().equals(username))
			{
				return this.getCorporateOffice().getAccountList().get(i);
			}
		}
		// Parse Store Accounts
		for(int i = 0; i < this.getStoreList().size(); i++)
		{
			for(int j = 0; j < this.getStoreList().get(i).getAccountList().size(); j++)
			{
				if(this.getStoreList().get(i).getAccountList().get(j).getUsername().equals(username))
				{
					return this.getStoreList().get(i).getAccountList().get(j);
				}		
			}
		}
		// Parse Warehouse Accounts
		for(int i = 0; i < this.getWarehouseList().size(); i++)
		{
			for(int j = 0; j < this.getWarehouseList().get(i).getAccountList().size(); j++)
			{
				if(this.getWarehouseList().get(i).getAccountList().get(j).getUsername().equals(username))
				{
					return this.getWarehouseList().get(i).getAccountList().get(j);
				}			
			}
		}
		// Parse Supplier Accounts
		for(int i = 0; i < this.getSupplierList().size(); i++)
		{
			for(int j = 0; j < this.getSupplierList().get(i).getAccountList().size(); j++)
			{
				if(this.getSupplierList().get(i).getAccountList().get(j).getUsername().equals(username))
				{
					return this.getSupplierList().get(i).getAccountList().get(j);
				}			
			}
		}
		// Parse Transport Accounts
		for(int i = 0; i < this.getTransportList().size(); i++)
		{
			for(int j = 0; j < this.getTransportList().get(i).getAccountList().size(); j++)
			{
				if(this.getTransportList().get(i).getAccountList().get(j).getUsername().equals(username))
				{
					return this.getTransportList().get(i).getAccountList().get(j);
				}				
			}
		}
		return null;
	}
	
	// Input name of an Account as a String, return the account object if found. 
	public Account findAccountName(String name)
	{
		// Parse through accounts to find 
		// Parse Corporate Accounts
		for(int i = 0; i < this.getCorporateOffice().getAccountList().size(); i++)
		{
			if(this.getCorporateOffice().getAccountList().get(i).getName().equals(name))
			{
				return this.getCorporateOffice().getAccountList().get(i);
			}
		}
		// Parse Store Accounts
		for(int i = 0; i < this.getStoreList().size(); i++)
		{
			for(int j = 0; j < this.getStoreList().get(i).getAccountList().size(); j++)
			{
				if(this.getStoreList().get(i).getAccountList().get(j).getName().equals(name))
				{
					return this.getStoreList().get(i).getAccountList().get(j);
				}		
			}
		}
		// Parse Warehouse Accounts
		for(int i = 0; i < this.getWarehouseList().size(); i++)
		{
			for(int j = 0; j < this.getWarehouseList().get(i).getAccountList().size(); j++)
			{
				if(this.getWarehouseList().get(i).getAccountList().get(j).getName().equals(name))
				{
					return this.getWarehouseList().get(i).getAccountList().get(j);
				}			
			}
		}
		// Parse Supplier Accounts
		for(int i = 0; i < this.getSupplierList().size(); i++)
		{
			for(int j = 0; j < this.getSupplierList().get(i).getAccountList().size(); j++)
			{
				if(this.getSupplierList().get(i).getAccountList().get(j).getName().equals(name))
				{
					return this.getSupplierList().get(i).getAccountList().get(j);
				}			
			}
		}
		// Parse Transport Accounts
		for(int i = 0; i < this.getTransportList().size(); i++)
		{
			for(int j = 0; j < this.getTransportList().get(i).getAccountList().size(); j++)
			{
				if(this.getTransportList().get(i).getAccountList().get(j).getName().equals(name))
				{
					return this.getTransportList().get(i).getAccountList().get(j);
				}				
			}
		}
		return null;
	}
	
	// Input location of a Department as a String, return the Department object if found. 
	public Department findDepartment(String aLocation)
	{
		if(aLocation.equals("Corporate"))
		{
			return corporateOffice;
		}
		
		// Parse Stores
		for(int i = 0; i < this.getStoreList().size(); i++)
		{
			if(this.getStoreList().get(i).getLocation().equals(aLocation))
			{
				return this.getStoreList().get(i);
			}		
		}
		// Parse Warehouses
		for(int i = 0; i < this.getWarehouseList().size(); i++)
		{
			if(this.getWarehouseList().get(i).getLocation().equals(aLocation))
			{
				return this.getWarehouseList().get(i);
			}			
		}
		return null;
	}
	
	// Input partner ID of a Partner as a String, return the Partner object if found. 
	public Partner findPartner(Integer aPartnerID)
	{
		// Parse Suppliers
		for(int i = 0; i < this.getSupplierList().size(); i++)
		{
			if(this.getSupplierList().get(i).getPartnerID().equals(aPartnerID))
			{
				return this.getSupplierList().get(i);
			}		
		}
		// Parse Transports
		for(int i = 0; i < this.getTransportList().size(); i++)
		{
			if(this.getTransportList().get(i).getPartnerID().equals(aPartnerID))
			{
				return this.getTransportList().get(i);
			}			
		}
		return null;
	}
}
