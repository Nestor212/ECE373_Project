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
		aTransport.addAccount(this);
	}
	
	public void confirmPickup(Order aOrder)
	{
		// TO DO update order status
	}
	public void confirmDelivery(Order aOrder)
	{
		// TO DO update order status
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
