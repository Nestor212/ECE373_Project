package users;

import software.Order;

public class Driver extends Account
{
	public Driver() 
	{
		this.accessLevel = 30;
	}
	
	public void confirmPickup(Order aOrder)
	{
		// TO DO update order status
	}
	public void confirmDelivery(Order aOrder)
	{
		// TO DO update order status
	}
}
