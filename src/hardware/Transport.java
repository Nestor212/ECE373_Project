package hardware;

import java.util.ArrayList;

import software.Order;

public class Transport extends Partner 
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
}
