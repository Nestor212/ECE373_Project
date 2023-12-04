package hardware;

import java.io.Serializable;
import software.Order;

/* Class Description
 * Transport represent transportation partners responsible for the movement of orders from one location to another
 * 
 * Transport will see orders available for pickup and orders being delivered by the transportation object.
 */

public class Transport extends Partner implements Serializable 
{
	private static final long serialVersionUID = 6334604795818632620L;
	
	public Transport()
	{
	}
	
	// Confirms pickup of order, adds order to this objects order list for tracking & order status is updated. 
	public void confirmPickup(Order aOrder)
	{
		this.addOrder(aOrder);
		aOrder.setDeliveredBy(this);
	}
	
	// Order status is updated. 
	public void confirmDelivery(Order aOrder)
	{
		aOrder.setOrderStatus("Delivered");
	}
}
