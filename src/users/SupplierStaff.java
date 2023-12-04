package users;

import java.io.Serializable;
import hardware.*;
import software.StoreOrder;
import software.WarehouseOrder;

public class SupplierStaff extends Account implements Serializable
{
	private static final long serialVersionUID = -1637712011310586703L;

	public SupplierStaff(Supplier aSupplier)
	{
		this.accessLevel = 40;
		partner = aSupplier;
		department = null;	
		aSupplier.addAccount(this);
	}

/************* Abstract Methods Defined *************/

// SupplierAccount cannot create orders at this stage, therefore return null.	
	
	@Override
	public StoreOrder createStoreOrder(Warehouse aWH) {
		return null;
	}
	@Override
	public WarehouseOrder createWarehouseOrder(Supplier aSupplier) {
		return null;
	}
}
