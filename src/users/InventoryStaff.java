package users;

import java.io.Serializable;

import gui.SupplyManagerGUI;
import hardware.*;
import software.*;

public class InventoryStaff extends Account implements Serializable 
{	
	public InventoryStaff(Store aStore) 
	{
		this.accessLevel = 20;
		department = aStore;
		partner = null;
	}
	
	public InventoryStaff(Warehouse aWarehouse) 
	{
		this.accessLevel = 20;
		department = aWarehouse;
		partner = null;
	}
		
	public StoreOrder createStoreOrder(Warehouse aWH)
	{
	// TO DO: How will we manage data input for an order
		StoreOrder o1 = new StoreOrder((Store) this.department, aWH);
		return o1;
	}
	
	public WarehouseOrder createWarehouseOrder(Supplier aSupplier)
	{
	// TO DO: How will we manage data input for an order
		WarehouseOrder o1 = new WarehouseOrder((Warehouse) this.department, aSupplier);
		return o1;
	}
}
