package users;

import java.io.Serializable;

import hardware.*;
import software.*;

public class TransportStaff extends Account implements Serializable
{
	private static final long serialVersionUID = -8829427025388283976L;

	public TransportStaff(Transport aTransport) 
	{
		this.accessLevel = 30;
		partner = aTransport;
		department = null;
		aTransport.addAccount(this);
	}
	 
/************* Abstract Methods Defined *************/

// Transport cannot create orders at this stage, therefore return null.
	
	@Override
	public StoreOrder createStoreOrder(Warehouse aWH) {
		return null;
	}

	@Override
	public WarehouseOrder createWarehouseOrder(Supplier aSupplier) {
		return null;
	}
}
