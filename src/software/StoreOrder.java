package software;

import java.io.Serializable;

import hardware.Store;
import hardware.Warehouse;

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
	
	public void setOrderedBy(Store aStore)
	{
		orderedBy = aStore; 
	}
	public String getOrderedBy()
	{
		return orderedBy.toString();
	}
	
	public void setFulfilledBy(Warehouse aWH)
	{
		fulfilledBy = aWH;
	}
	public String getfulfilledBy()
	{
		return fulfilledBy.toString();
	}

	@Override
	public String getPickupAddress() {

		return fulfilledBy.getLocation();
	}

	@Override
	public String getDeliveryAddress() {
		return orderedBy.getLocation();
	}
}
