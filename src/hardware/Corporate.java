package hardware;

import java.util.ArrayList;

public class Corporate extends Department
{
	public Corporate()
	{
		identifier = "C";
	}
	
	public String toString()
	{
		return ("Corporate Office - " + this.getLocation());
	}

	@Override
	public ArrayList<Item> getInventory() {
		// No inventory in corporate office
		return null;
	}
}
