package test_client;

import software.*;
import hardware.*;
import users.*;

/*
 * TODO: Create a database to store all Account and session information 
 */

public class Client 
{
	private int sessionAccessLevel;
	private Account sessionAccount;
	
	private Company ArizonaInc;
	private Store s1;
	private Warehouse wh1;
	private Corporate c1;
	
	private Supplier TaraManufacturing;
	private Transport MohammedFreight;
	
	// User Accounts
	private Admin master;
	private InventoryStaff staff1;
	private InventoryStaff staff2;
	private SupplierStaff supplyStaff1;
	private TransportStaff transportStaff1;
	

	
	public Client()
	{
		sessionAccessLevel = 0;
		
		ArizonaInc = new Company();
		
		c1 = new Corporate();
		c1.setLocation("Some Address, Phoenix, AZ");
		
		s1 = new Store();
		s1.setLocation("Some Address, Tucson, AZ");
		
		wh1 = new Warehouse();
		wh1.setLocation("Some Address, Phoenix, AZ");
		
		TaraManufacturing = new Supplier();
		TaraManufacturing.setName("Tara Manufacturing");
		
		MohammedFreight = new Transport();
		MohammedFreight.setName("Mohammed Freight");
				
		ArizonaInc.setCorporateoffice(c1);
		ArizonaInc.addStore(s1);
		ArizonaInc.addWarehouse(wh1);
		ArizonaInc.addSupplier(TaraManufacturing);
		ArizonaInc.addTransport(MohammedFreight);
				
		// Admin Account Created - Belongs to Corporate
		master = new Admin();
		master.setName("Nestor Garcia");
		master.setUsername("master");
		master.setPassword("password");
		c1.addAccount(master);
		
		// Staff Account Created - Belongs to Store
		staff1 = new InventoryStaff(s1);
		staff1.setName("John Doe");
		staff1.setUsername("stafftest");
		staff1.setPassword("test");
		s1.addAccount(staff1);
		
		// Staff Account Created - Belongs to Warehouse
		staff2 = new InventoryStaff(wh1);
		staff2.setName("Jane Doe");
		wh1.addAccount(staff2);
		
		// Supplier Staff Account Created - Belongs to Supplier
		supplyStaff1 = new SupplierStaff();
		supplyStaff1.setName("Tom Smith");
		TaraManufacturing.addAccount(supplyStaff1);
		
		// Transport Staff Account Created - Belongs to Transport
		transportStaff1 = new TransportStaff();
		transportStaff1.setName("Andrew Jones");
		MohammedFreight.addAccount(transportStaff1);
	}	
	
	// Return boolean based on results, then GUI will redirect based on response
	public int login(String username, String password)
	{
		// Parse through accounts to find 
		// Parse Corporate Accounts
		for(int i = 0; i < ArizonaInc.getCorporateOffice().getAccountList().size(); i++)
		{
			if(ArizonaInc.getCorporateOffice().getAccountList().get(i).getUsername().equals(username))
			{
				if(ArizonaInc.getCorporateOffice().getAccountList().get(i).getPassword().equals(password))
				{
					sessionAccount = ArizonaInc.getCorporateOffice().getAccountList().get(i);
					return ArizonaInc.getCorporateOffice().getAccountList().get(i).getAccessLevel();
				}
			}
		}
		// Parse Store Accounts
		for(int i = 0; i < ArizonaInc.getStoreList().size(); i++)
		{
			for(int j = 0; j < ArizonaInc.getStoreList().get(i).getAccountList().size(); j++)
			{
				if(ArizonaInc.getStoreList().get(i).getAccountList().get(j).getUsername().equals(username))
				{
					if(ArizonaInc.getStoreList().get(i).getAccountList().get(j).getPassword().equals(password))
					{
						sessionAccount = ArizonaInc.getStoreList().get(i).getAccountList().get(j);
						return ArizonaInc.getStoreList().get(i).getAccountList().get(j).getAccessLevel();
					}
				}		
			}
		}
		// Parse Warehouse Accounts
		for(int i = 0; i < ArizonaInc.getWarehouseList().size(); i++)
		{
			for(int j = 0; j < ArizonaInc.getWarehouseList().get(i).getAccountList().size(); j++)
			{
				if(ArizonaInc.getWarehouseList().get(i).getAccountList().get(j).getUsername().equals(username))
				{
					if(ArizonaInc.getWarehouseList().get(i).getAccountList().get(j).getPassword().equals(password))
					{
						sessionAccount = ArizonaInc.getWarehouseList().get(i).getAccountList().get(j);
						return ArizonaInc.getWarehouseList().get(i).getAccountList().get(j).getAccessLevel();
					}
				}			
			}
		}
		// Parse Supplier Accounts
		for(int i = 0; i < ArizonaInc.getSupplierList().size(); i++)
		{
			for(int j = 0; j < ArizonaInc.getSupplierList().get(i).getAccountList().size(); j++)
			{
				if(ArizonaInc.getSupplierList().get(i).getAccountList().get(j).getUsername().equals(username))
				{
					if(ArizonaInc.getSupplierList().get(i).getAccountList().get(j).getPassword().equals(password))
					{
						sessionAccount = ArizonaInc.getSupplierList().get(i).getAccountList().get(j);
						return ArizonaInc.getSupplierList().get(i).getAccountList().get(j).getAccessLevel();
					}
				}			
			}
		}
		// Parse Transport Accounts
		for(int i = 0; i < ArizonaInc.getTransportList().size(); i++)
		{
			for(int j = 0; j < ArizonaInc.getTransportList().get(i).getAccountList().size(); j++)
			{
				if(ArizonaInc.getTransportList().get(i).getAccountList().get(j).getUsername().equals(username))
				{
					if(ArizonaInc.getTransportList().get(i).getAccountList().get(j).getPassword().equals(password))
					{
						sessionAccount = ArizonaInc.getTransportList().get(i).getAccountList().get(j);
						return ArizonaInc.getTransportList().get(i).getAccountList().get(j).getAccessLevel();
					}
				}				
			}
		}
		return -1;	
	}
	
	public void setSessionAccessLevel(int aNum)
	{
		sessionAccessLevel = aNum;
	}
	public int getSessionAccessLevel()
	{
		return sessionAccessLevel;
	}
	
	public Company getCompany()
	{
		return ArizonaInc;
	}
	
	public Account getSessionAccount()
	{
		return sessionAccount;
	}
	
	public void generateReport(String aType)
	{
		switch(aType)
		{
		case "Orders":
			for(int i = 0; i < ArizonaInc.getOrderList().size(); i++)
			{
				System.out.println(ArizonaInc.getOrderList().get(i).getOrderNum() + " " + ArizonaInc.getOrderList().get(i).getOrderStatus());
			}
		case "Suppliers":
			for(int i = 0; i < ArizonaInc.getSupplierList().size(); i++)
			{
				System.out.println(ArizonaInc.getSupplierList().get(i).getAccountNum() + " " + ArizonaInc.getSupplierList().get(i).getName());
			}
		case "Transportation":
			for(int i = 0; i < ArizonaInc.getTransportList().size(); i++)
			{
				System.out.println(ArizonaInc.getTransportList().get(i).getAccountNum() + " " + ArizonaInc.getTransportList().get(i).getName());
			}
		case "Stores":
			for(int i = 0; i < ArizonaInc.getStoreList().size(); i++)
			{
				System.out.println(ArizonaInc.getStoreList().get(i).getID() + " " + ArizonaInc.getStoreList().get(i).getLocation());
			}
		case "Warehouses":
			for(int i = 0; i < ArizonaInc.getWarehouseList().size(); i++)
			{
				System.out.println(ArizonaInc.getWarehouseList().get(i).getID() + " " + ArizonaInc.getWarehouseList().get(i).getLocation());
			}
		case "Accounts": // Print all accounts belonging to the Company
			// Print Corporate Accounts
			for(int i = 0; i < ArizonaInc.getCorporateOffice().getAccountList().size(); i++)
			{
				System.out.println(ArizonaInc.getCorporateOffice().getAccountList().get(i).getAccountNumber() + " " + ArizonaInc.getCorporateOffice().getAccountList().get(i).getName());
			}
			// Print Store Accounts
			for(int i = 0; i < ArizonaInc.getStoreList().size(); i++)
			{
				for(int j = 0; j < ArizonaInc.getStoreList().get(i).getAccountList().size(); j++)
				{
					System.out.println(ArizonaInc.getStoreList().get(i).getAccountList().get(j).getAccountNumber() + " " + ArizonaInc.getStoreList().get(i).getAccountList().get(j).getAccountNumber());
				}
			}
			// Print Warehouse Accounts
			for(int i = 0; i < ArizonaInc.getWarehouseList().size(); i++)
			{
				for(int j = 0; j < ArizonaInc.getWarehouseList().get(i).getAccountList().size(); j++)
				{
					System.out.println(ArizonaInc.getWarehouseList().get(i).getAccountList().get(j).getAccountNumber() + " " + ArizonaInc.getWarehouseList().get(i).getAccountList().get(j).getAccountNumber());
				}
			}
			// Print Supplier Accounts
			for(int i = 0; i < ArizonaInc.getSupplierList().size(); i++)
			{
				for(int j = 0; j < ArizonaInc.getSupplierList().get(i).getAccountList().size(); j++)
				{
					System.out.println(ArizonaInc.getSupplierList().get(i).getAccountList().get(j).getAccountNumber() + " " + ArizonaInc.getSupplierList().get(i).getAccountList().get(j).getAccountNumber());
				}
			}
			// Print Transport Accounts
			for(int i = 0; i < ArizonaInc.getTransportList().size(); i++)
			{
				for(int j = 0; j < ArizonaInc.getTransportList().get(i).getAccountList().size(); j++)
				{
					System.out.println(ArizonaInc.getTransportList().get(i).getAccountList().get(j).getAccountNumber() + " " + ArizonaInc.getTransportList().get(i).getAccountList().get(j).getAccountNumber());
				}
			}	
		default:
			break;
		}
	}
}