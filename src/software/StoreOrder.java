package software;

import hardware.Store;
import hardware.Warehouse;

public class StoreOrder extends Order
{
	private Store orderedBy;
	private Warehouse fulfilledBy;
	
	
	public StoreOrder()
	{
		orderedBy = new Store();
		fulfilledBy = new Warehouse();
	}
	
	public void setOrderedBy(Store aStore)
	{
		orderedBy = aStore; 
	}
	public Store getOrderedBy()
	{
		return orderedBy;
	}
	
	public void setFulfilledBy(Warehouse aWH)
	{
		fulfilledBy = aWH;
	}
	public Warehouse geFulfilledBy()
	{
		return fulfilledBy;
	}
}
