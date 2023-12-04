package users;

import java.io.Serializable;
import hardware.*;
import software.*;

/* Class Description
 * Inventory Staff can belong to a Store Object or Warehouse. 
 */

public class InventoryStaff extends Account implements Serializable 
{	
	private static final long serialVersionUID = -5983588149750260311L;

	public InventoryStaff(Store aStore) 
	{
		this.accessLevel = 20;
		department = aStore;
		partner = null;
		aStore.addAccount(this);
	}
	
	public InventoryStaff(Warehouse aWarehouse) 
	{
		this.accessLevel = 20;
		department = aWarehouse;
		partner = null;
		aWarehouse.addAccount(this);
	}
	
/************* Abstract Methods Definitions *************/
	
	// If this object belongs to a store, a Store order will be generated
	public StoreOrder createStoreOrder(Warehouse aWH)
	{
		StoreOrder o1 = new StoreOrder((Store) this.department, aWH);
		return o1;
	}
	// If this object belongs to a Warehouse, a Warehouse order will be generated
	public WarehouseOrder createWarehouseOrder(Supplier aSupplier)
	{
		WarehouseOrder o1 = new WarehouseOrder((Warehouse) this.department, aSupplier);
		return o1;
	}
}
