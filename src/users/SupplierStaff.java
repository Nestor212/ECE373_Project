package users;

import hardware.Department;

public class SupplierStaff extends Account
{
	public SupplierStaff()
	{
		this.accessLevel = 40;
	}

	@Override
	public Department getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}
}
