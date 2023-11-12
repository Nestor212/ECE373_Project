package users;

import hardware.Department;
import software.Order;

public class TransportStaff extends Account
{
	public TransportStaff() 
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

	@Override
	public Department getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}
}
