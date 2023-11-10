package software;

import hardware.Supplier;
import hardware.Warehouse;

public class WarehouseOrder extends Order 
{
	private Warehouse orderedBy;
	private Supplier fulfilledBy;
	
	
	public WarehouseOrder()
	{
		orderedBy = new Warehouse();
		fulfilledBy = new Supplier();
	}
	
	public void setOrderedBy(Warehouse aWH)
	{
		orderedBy = aWH; 
	}
	public Warehouse getOrderedBy()
	{
		return orderedBy;
	}
	
	public void setFulfilledBy(Supplier aSupplier)
	{
		fulfilledBy = aSupplier;
	}
	public Supplier geFulfilledBy()
	{
		return fulfilledBy;
	}
}
