package gui;

import software.*;
import java.util.ArrayList;
import hardware.*;
import users.*;

/* Class Description
 * Driver creates data for the system to utilize. 
 * Run this once to instantiate system data, 
 * then 'SupplyManagerClient.java' can be used to load the saved data.
 */

public class Driver 
{	
	ArrayList<Item> virtualItems = new ArrayList<Item>(50);
	
	public static void main(String[] args)
	{	
		// Create Company
		Company ArizonaInc = new Company("ArizonaInc");
		
		// Create Departments
		Corporate c1 = new Corporate();
		c1.setLocation("Some Address, New York NY");
		Store s1 = new Store();
		s1.setLocation("Some Address, Tucson AZ");	
		Warehouse wh1 = new Warehouse();
		wh1.setLocation("Some Address, Phoenix AZ");
		Warehouse wh2 = new Warehouse();
		wh2.setLocation("Some Address, Dallas TX");
		
		// Create Partners
		Supplier TaraManufacturing = new Supplier();
		TaraManufacturing.setName("Tara Manufacturing");
		Transport MohammedFreight = new Transport();
		MohammedFreight.setName("Mohammed Freight");
		
		// Add Departments and Partners to Company lists
		ArizonaInc.setCorporateoffice(c1);
		ArizonaInc.addStore(s1);
		ArizonaInc.addWarehouse(wh1);
		ArizonaInc.addWarehouse(wh2);
		ArizonaInc.addSupplier(TaraManufacturing);
		ArizonaInc.addTransport(MohammedFreight);
			
		// Admin Account Created - Belongs to Corporate
		Admin master = new Admin(c1);
		master.setName("Nestor Garcia");
		master.setUsername("master");
		master.setPassword("test");
		
		// Staff Account Created - Belongs to Store
		InventoryStaff staff1 = new InventoryStaff(s1);
		staff1.setName("John Doe");
		staff1.setUsername("storetest");
		staff1.setPassword("test");
		
		// Staff Account Created - Belongs to Warehouse 1
		InventoryStaff staff2 = new InventoryStaff(wh1);
		staff2.setName("Jane Doe");
		staff2.setUsername("whtest1");
		staff2.setPassword("test");
		
		// Staff Account Created - Belongs to Warehouse 2
		InventoryStaff staff3 = new InventoryStaff(wh2);
		staff3.setName("Michael West");
		staff3.setUsername("whtest2");
		staff3.setPassword("test");
		
		// Supplier Staff Account Created - Belongs to Supplier
		SupplierStaff supplyStaff1 = new SupplierStaff(TaraManufacturing);
		supplyStaff1.setName("Tom Smith");
		supplyStaff1.setUsername("supplytest");
		supplyStaff1.setPassword("test");
		
		// Transport Staff Account Created - Belongs to Transport
		TransportStaff transportStaff1 = new TransportStaff(MohammedFreight);
		transportStaff1.setName("Andrew Jones");
		transportStaff1.setUsername("transporttest");
		transportStaff1.setPassword("test");
		
		// Create Items available from supplier
		TaraManufacturing.addItems(generateItems(6, 1200));

		// Add to Store Inventory
		for(int i = 0; i < TaraManufacturing.getItemList().size(); i++)
		{
			// Add to store inventory
			s1.addItemToInventory(TaraManufacturing.getItemList().get(i).cloneItem(15));	
			
			// Add to warehouse 1 inventory
			wh1.addItemToInventory(TaraManufacturing.getItemList().get(i).cloneItem(100));
			
			// Add to warehouse 2 inventory
			wh2.addItemToInventory(TaraManufacturing.getItemList().get(i).cloneItem(300));			
		}
		
		//Create new store orders
		StoreOrder order1 = staff1.createStoreOrder(wh1);
		order1.setOrderStatus("Awaiting Fulfillment");
		
		StoreOrder order2 = staff1.createStoreOrder(wh1);
		order2.setOrderStatus("Awaiting Pickup");
		
		StoreOrder order3 = staff1.createStoreOrder(wh2);
		order3.setOrderStatus("Delivered");

		// Create new warehouse orders
		WarehouseOrder whorder1 = staff2.createWarehouseOrder(TaraManufacturing);
		whorder1.setOrderStatus("Awaiting Fulfillment");	
		
		WarehouseOrder whorder2 = staff3.createWarehouseOrder(TaraManufacturing);
		MohammedFreight.confirmPickup(whorder2);;

		for(int i = 0; i < s1.getInventory().size(); i = i + 2)
		{
			// Add to Store Orders
			order1.addItemToOrder(s1.getInventory().get(i).cloneItem(5));
			order2.addItemToOrder(s1.getInventory().get(i).cloneItem(6));
			order3.addItemToOrder(s1.getInventory().get(i).cloneItem(7));
			
			// Add to warehouse orders
			whorder1.addItemToOrder(wh1.getInventory().get(i).cloneItem(20));
			whorder2.addItemToOrder(wh2.getInventory().get(i).cloneItem(40));		
		}
		
		// Save Company data created by this driver.
		Company.saveData(ArizonaInc);
		
		// Instantiate a session of the GUI 
		SupplyManagerGUI session = new SupplyManagerGUI();
	}	
	
	// Generate a list of random predefined items for testing
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