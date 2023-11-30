package users;

import java.io.Serializable;

import hardware.Corporate;
import hardware.Department;
import hardware.Supplier;
import hardware.Warehouse;
import software.StoreOrder;
import software.WarehouseOrder;

public class Admin extends Account implements Serializable
{
	private static final long serialVersionUID = 5000866564605162258L;

	public Admin(Corporate aCorporate) 
	{
		this.accessLevel = 10;
		department = aCorporate;
		partner = null;
	}

	public Department getDepartment() 
	{
		return department;
	}

	@Override
	public StoreOrder createStoreOrder(Warehouse aWH) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarehouseOrder createWarehouseOrder(Supplier aSupplier) {
		// TODO Auto-generated method stub
		return null;
	}	
}
