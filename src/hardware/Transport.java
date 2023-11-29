package hardware;

import java.io.Serializable;
import java.util.ArrayList;

import software.Order;

public class Transport extends Partner implements Serializable 
{
	private ArrayList<Order> orders;
	
	public Transport()
	{
		orders = new ArrayList<Order>();
	}
	
	public void confirmPickup(Order aOrder)
	{
		// TO DO; how will we handle orders
	}
	public void confirmDelivery(Order aOrder)
	{
		// TO DO; how will we handle orders
	}

	@Override
	public ArrayList<Order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}
}
