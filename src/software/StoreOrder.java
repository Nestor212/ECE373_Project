package software;

import java.io.Serializable;
import hardware.Store;
import hardware.Warehouse;

/* Class Description
 * Store orders extend Orders, and represent orders that are placed by stores
 * and fulfilled by warehouses. 
 */

public class StoreOrder extends Order implements Serializable
{
	private static final long serialVersionUID = -5950051896434766378L;
	private Store orderedBy;
	private Warehouse fulfilledBy;
		
	public StoreOrder(Store orderedByStore, Warehouse fulfilledByWH)
	{
		orderIdentifier = "S";
		orderedBy = orderedByStore;
		orderedByStore.addOrder(this);
		fulfilledBy = fulfilledByWH;
		fulfilledByWH.addOrder(this);
	}
	
/************* Basic Getter and Setter Methods *************/

	public void setOrderedBy(Store aStore)
	{
		orderedBy = aStore; 
	}
	public void setFulfilledBy(Warehouse aWH)
	{
		fulfilledBy = aWH;
	}
	public Store getOrderedBy() {
		return orderedBy;
	}
	public Warehouse getfulfilledBy() {
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

		return fulfilledBy.getLocation();
	}
	@Override
	public String getDeliveryAddressString() {
		return orderedBy.getLocation();
	}
}
