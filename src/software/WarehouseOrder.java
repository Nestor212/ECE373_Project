package software;

import java.io.Serializable;
import hardware.Supplier;
import hardware.Warehouse;

/* 
 * Class Description
 * Warehouse orders extend Orders, and represent orders that are placed by 
 * a Warehouse and fulfilled by a Supplier. 
 */

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
	
/************* Basic Getter and Setter Methods *************/

	public void setOrderedBy(Warehouse aWH)
	{
		orderedBy = aWH; 
	}
	public void setFulfilledBy(Supplier aSupplier)
	{
		fulfilledBy = aSupplier;
	}
	public Supplier getFulfilledBy()
	{
		return fulfilledBy;
	}
	public Warehouse getOrderedBy() {
		return orderedBy;
	}
	public Supplier getfulfilledBy() {
		return fulfilledBy;
	}

/****************** Abstract Methods Defined ******************/
	
	public String getOrderedByString()
	{
		return orderedBy.toString();
	}
	public String getfulfilledByString()
	{
		return fulfilledBy.toString();
	}
	@Override
	public String getPickupAddressString() {
		return fulfilledBy.getName();
	}
	@Override
	public String getDeliveryAddressString() {
		return orderedBy.getLocation();
	}
}
