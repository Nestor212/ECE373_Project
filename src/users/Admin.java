package users;

import hardware.Department;
import hardware.Partner;

public class Admin extends Account 
{
	
	public Admin() 
	{
		this.accessLevel = 10;
	}
	
	public void addAccount(Department aDepartment, Account aAccount) 
	{
		aDepartment.getAccountList().add(aAccount);
	}
	
	public void removeAccount(Department aDepartment, Account aAccount) 
	{
		aDepartment.getAccountList().remove(aAccount);
	}
	
	public Account findAccount(Department aDepartment, String aName)
	{
		for(int i = 0; i < aDepartment.getAccountList().size(); i++)
		{
			if (aDepartment.getAccountList().get(i).getName() == aName)
			{
				return aDepartment.getAccountList().get(i);
			}
		}
		return null;
	}
	public void addAccount(Partner aPartner, Account aAccount) 
	{
		aPartner.getAccountList().add(aAccount);
	}
	
	public void removeAccount(Partner aPartner, Account aAccount) 
	{
		aPartner.getAccountList().remove(aAccount);
	}
	
	public Account findAccount(Partner aPartner, String aName)
	{
		for(int i = 0; i < aPartner.getAccountList().size(); i++)
		{
			if (aPartner.getAccountList().get(i).getName() == aName)
			{
				return 	aPartner.getAccountList().get(i);
			}
		}
		return null;
	}

	@Override
	public Department getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
