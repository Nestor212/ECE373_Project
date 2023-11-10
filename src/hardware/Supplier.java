package hardware;

import java.util.ArrayList;

import software.Order;

public class Supplier extends Partner
{
	private ArrayList<Order> orders;
	
	public Supplier()
	{
		orders = new ArrayList<Order>();
	}
	
	public void fulfillOrder(Order aOrder)
	{
		// TO DO: How will our orders be structured 
	}
}
