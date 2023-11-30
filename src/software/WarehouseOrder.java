package software;

import java.io.Serializable;

import hardware.Supplier;
import hardware.Warehouse;

public class WarehouseOrder extends Order implements Serializable 
{
	private static final long serialVersionUID = 380809076951904346L;
	private Warehouse orderedBy;
	private Supplier fulfilledBy;
	
	
	public WarehouseOrder(Warehouse orderedByWH, Supplier fulfilledBySupplier)
	{
		orderIdentifier = "WH";
		orderedBy = orderedByWH;
		orderedBy.addOrder(this);
		fulfilledBy = fulfilledBySupplier;
		fulfilledBySupplier.addOrder(this);
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
	public Supplier getFulfilledBy()
	{
		return fulfilledBy;
	}
}
