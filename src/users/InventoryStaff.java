package users;

import hardware.*;
import software.*;
import test_client.GUI;

public class InventoryStaff extends Account 
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
	}
	
	public Department getDepartment()
	{
		return department;
	}
	
	public void createOrder(Store aStore)
	{
	// TO DO: How will we manage data input for an order
		StoreOrder o1 = new StoreOrder();
		GUI.getSession().getCompany().getOrderList().add(o1);		
	}
	
	public void createOrder(Warehouse aWarehouse)
	{
	// TO DO: How will we manage data input for an order
		WarehouseOrder o1 = new WarehouseOrder();
		GUI.getSession().getCompany().getOrderList().add(o1);		

	}
}
