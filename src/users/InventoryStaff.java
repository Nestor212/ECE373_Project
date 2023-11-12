package users;

import hardware.*;
import software.*;

public class InventoryStaff extends Account 
{
	public InventoryStaff() 
	{
		this.accessLevel = 20;
	}
	
	public void createOrder(Store aStore)
	{
	// TO DO: How will we manage data input for an order
		StoreOrder o1 = new StoreOrder();
	}
	
	public void createOrder(Warehouse aWarehouse)
	{
	// TO DO: How will we manage data input for an order
		WarehouseOrder o1 = new WarehouseOrder();
	}
}
