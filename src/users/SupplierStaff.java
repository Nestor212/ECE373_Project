package users;

import hardware.*;


public class SupplierStaff extends Account
{
	public SupplierStaff(Supplier aSupplier)
	{
		this.accessLevel = 40;
		partner = aSupplier;
		department = null;		
	}

	
	public Partner getDepartment() 
	{
		return partner;
	}
}
