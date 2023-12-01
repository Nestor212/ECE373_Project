package gui;

import software.*;
import java.util.ArrayList;
import hardware.*;
import users.*;

/*
 * Client creates data for the system to utilize. 
 * Run this once to instantiate system data, 
 * then SupplyManagerDriver.java can be used to load the saved data.
 */

public class Driver 
{	
	ArrayList<Item> virtualItems = new ArrayList<Item>(50);
	
	public static void main(String[] args)
	{	
		Company ArizonaInc;
		Store s1;
		Warehouse wh1;
		Warehouse wh2;
		Corporate c1;
		
		Supplier TaraManufacturing;
		Transport MohammedFreight;
		
		// User Accounts
		Admin master;
		InventoryStaff staff1;
		InventoryStaff staff2;
		SupplierStaff supplyStaff1;
		TransportStaff transportStaff1;
		
		ArizonaInc = new Company();
		
		c1 = new Corporate();
		c1.setLocation("Some Address, New York NY");
		
		s1 = new Store();
		s1.setLocation("Some Address, Tucson AZ");	
		
		wh1 = new Warehouse();
		wh1.setLocation("Some Address, Phoenix AZ");
		
		wh2 = new Warehouse();
		wh2.setLocation("Some Address, Dallas TX");

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
		
		Company.saveData(ArizonaInc);
		
		SupplyManagerGUI session = new SupplyManagerGUI();
	}	
	
	
	public static ArrayList<Item> generateItems(int numItems, int qtyEA)
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