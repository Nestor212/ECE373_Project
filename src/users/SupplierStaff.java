package users;

import hardware.*;
import software.StoreOrder;
import software.WarehouseOrder;


public class SupplierStaff extends Account
{
	public SupplierStaff(Supplier aSupplier)
	{
		this.accessLevel = 40;
		partner = aSupplier;
		department = null;		
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
