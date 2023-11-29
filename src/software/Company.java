package software;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import hardware.Corporate;
import hardware.Store;
import hardware.Supplier;
import hardware.Transport;
import hardware.Warehouse;
import users.Account;

public class Company implements Serializable 
{
	private String name;
	private ArrayList<Store> storeList;
	private ArrayList<Warehouse> warehouseList;
	private ArrayList<Supplier> supplierList;
	private ArrayList<Transport> transportList;
	private Corporate corporateOffice;
	
	public Company()
	{
		name = "unknown";
		storeList = new ArrayList<Store>();
		warehouseList = new ArrayList<Warehouse>();
		supplierList = new ArrayList<Supplier>();
		transportList = new ArrayList<Transport>();
		corporateOffice = new Corporate();
	}
	
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
	public ArrayList<Transport> getTransportList()
	{
		return transportList;
	}
	
	public static void saveData(Company e)
	{
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;
		
		try 
		{
			System.out.println("Saving");
			fileOut = new FileOutputStream("Company.ser");
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
	
	public static Company loadData()
	{
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		Company company = null;
		
		try 
		{
			System.out.println("Loading");
			fileIn = new FileInputStream("Company.ser");
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
	
	// Return boolean based on results, then GUI will redirect based on response
	public Account login(String username, String password)
	{
		if (findAccount(username) != null)
		{
			if (findAccount(username).getPassword().equals(password))
			{
				return findAccount(username);
			}
		}
		return null;	
	}
	
	public Account findAccount(String username)
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
}
