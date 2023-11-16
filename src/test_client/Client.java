package test_client;

import software.*;

import java.util.ArrayList;

import hardware.*;
import users.*;

/*
 * Client creates data for the system to utilize. 
 * 
 * 
 * TODO: Create a database to store all Account and session information, at which point the client will extract the 
 * 		 data for the company for the user to access. 
 */

public class Client 
{
	private int sessionAccessLevel;
	private Account sessionAccount;
	
	private Company ArizonaInc;
	private Store s1;
	private Warehouse wh1;
	private Warehouse wh2;
	private Corporate c1;
	
	private Supplier TaraManufacturing;
	private Transport MohammedFreight;
	
	// User Accounts
	private Admin master;
	private InventoryStaff staff1;
	private InventoryStaff staff2;
	private SupplierStaff supplyStaff1;
	private TransportStaff transportStaff1;
	
	ArrayList<Item> virtualItems = new ArrayList<Item>(50);
	
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
		
		wh2 = new Warehouse();
		wh2.setLocation("Some Address, Dallas, TX");

		TaraManufacturing = new Supplier();
		TaraManufacturing.setName("Tara Manufacturing");
		
		MohammedFreight = new Transport();
		MohammedFreight.setName("Mohammed Freight");
				
		ArizonaInc.setCorporateoffice(c1);
		ArizonaInc.addStore(s1);
		ArizonaInc.addWarehouse(wh1);
		ArizonaInc.addWarehouse(wh2);
		ArizonaInc.addSupplier(TaraManufacturing);
		ArizonaInc.addTransport(MohammedFreight);
				
		// Admin Account Created - Belongs to Corporate
		master = new Admin(c1);
		master.setName("Nestor Garcia");
		master.setUsername("master");
		master.setPassword("test");
		c1.addAccount(master);
		
		// Staff Account Created - Belongs to Store
		staff1 = new InventoryStaff(s1);
		staff1.setName("John Doe");
		staff1.setUsername("storetest");
		staff1.setPassword("test");
		s1.addAccount(staff1);
		
		// Staff Account Created - Belongs to Warehouse
		staff2 = new InventoryStaff(wh1);
		staff2.setName("Jane Doe");
		staff2.setUsername("whtest");
		staff2.setPassword("test");
		wh1.addAccount(staff2);
		
		// Supplier Staff Account Created - Belongs to Supplier
		supplyStaff1 = new SupplierStaff(TaraManufacturing);
		supplyStaff1.setName("Tom Smith");
		supplyStaff1.setUsername("supplytest");
		supplyStaff1.setPassword("test");
		TaraManufacturing.addAccount(supplyStaff1);
		
		// Transport Staff Account Created - Belongs to Transport
		transportStaff1 = new TransportStaff(MohammedFreight);
		transportStaff1.setName("Andrew Jones");
		transportStaff1.setUsername("transporttest");
		transportStaff1.setPassword("test");
		MohammedFreight.addAccount(transportStaff1);
		
		// Create Items available from supplier
		TaraManufacturing.addItems(generateItems(6, 1200));

		// Add to Store Inventory
		
		for(int i = 0; i < TaraManufacturing.getItemList().size(); i++)
		{
			// Add to store inventory
			s1.addItemToInventory(TaraManufacturing.getItemList().get(i).cloneItem(15));	
			
			// Add to warehouse 1 inventory
			wh1.addItemToInventory(TaraManufacturing.getItemList().get(i).cloneItem(100));
			
			// Add to warehouse 1 inventory
			wh2.addItemToInventory(TaraManufacturing.getItemList().get(i).cloneItem(300));			
		}
		
		//Create new store order
		StoreOrder order1 = staff1.createStoreOrder(wh1);
		order1.setOrderStatus("New Order - Pending");

		// Create new warehouse order
		WarehouseOrder order2 = staff2.createWarehouseOrder(TaraManufacturing);
		order2.setOrderStatus("Fulfillment in progress");	

		for(int i = 0; i < s1.getInventory().size(); i = i + 2)
		{
			// Add to Store Order
			order1.addItemToOrder(s1.getInventory().get(i).cloneItem(5));
			
			// Add to warehouse inventory
			order2.addItemToOrder(s1.getInventory().get(i).cloneItem(20));
		}		
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
//			for(int i = 0; i < ArizonaInc.getOrderList().size(); i++)
//			{
//				System.out.println(ArizonaInc.getOrderList().get(i).getOrderNum() + " " + ArizonaInc.getOrderList().get(i).getOrderStatus());
//			}
		case "Suppliers":
			for(int i = 0; i < ArizonaInc.getSupplierList().size(); i++)
			{
				System.out.println(ArizonaInc.getSupplierList().get(i).getPartnerID() + " " + ArizonaInc.getSupplierList().get(i).getName());
			}
		case "Transportation":
			for(int i = 0; i < ArizonaInc.getTransportList().size(); i++)
			{
				System.out.println(ArizonaInc.getTransportList().get(i).getPartnerID() + " " + ArizonaInc.getTransportList().get(i).getName());
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
	
	public ArrayList<Item> generateItems(int numItems, int qtyEA)
	{
		ArrayList<Item> virtualItems = new ArrayList<Item>(20); 
		
		for( int i = 0; i < numItems; i++)
		{
			String itemName1 = "T-Shirt # " + (i + 1);
			Item item1 = new Item(itemName1, 11.99 + i, 2.99 + i, qtyEA + i);
			virtualItems.add(item1);
			
			String itemName2 = "Shoes # " + (i + 1);
			Item item2 = new Item(itemName2, 40.99 + i, 9.99 + i, qtyEA + i);
			virtualItems.add(item2);
			
			String itemName3 = "Jeans # " + (i + 1);
			Item item3 = new Item(itemName3, 40.99 + i, 9.99 + i, qtyEA + i);
			virtualItems.add(item3);
		}
		
		return virtualItems;
	}
}