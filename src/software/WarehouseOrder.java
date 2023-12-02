package software;

import java.io.Serializable;

import hardware.Department;
import hardware.Partner;
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
	public String getOrderedByString()
	{
		return orderedBy.toString();
	}
	public String getfulfilledByString()
	{
		return fulfilledBy.toString();
	}
	public void setFulfilledBy(Supplier aSupplier)
	{
		fulfilledBy = aSupplier;
	}
	public Supplier getFulfilledBy()
	{
		return fulfilledBy;
	}

	@Override
	public String getPickupAddressString() {
		return fulfilledBy.getName();
	}

	@Override
	public String getDeliveryAddressString() {
		return orderedBy.getLocation();
	}
	public Warehouse getOrderedBy() {
		return orderedBy;
	}
	public Supplier getfulfilledBy() {
		return fulfilledBy;
	}
}
