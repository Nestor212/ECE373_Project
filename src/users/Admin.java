package users;

import java.io.Serializable;

import hardware.Corporate;
import hardware.Department;
import hardware.Supplier;
import hardware.Warehouse;
import software.StoreOrder;
import software.WarehouseOrder;

/* Class Description
 * Admin accounts belong to the corporate office object. 
 * Admins access level grant them the management of the system, 
 * including adding and removing objects such as accounts and departments. 
 */

public class Admin extends Account implements Serializable
{
	private static final long serialVersionUID = 5000866564605162258L;

	public Admin(Corporate aCorporate) 
	{
		this.accessLevel = 10;
		department = aCorporate;
		partner = null;
		aCorporate.addAccount(this);
	}

	public Department getDepartment() 
	{
		return department;
	}

/************* Abstract Methods Defined *************/

// Admin cannot create orders at this stage, therefore return null.
	
	@Override
	public StoreOrder createStoreOrder(Warehouse aWH) {
		return null;
	}
	@Override
	public WarehouseOrder createWarehouseOrder(Supplier aSupplier) {
		return null;
	}	
}
