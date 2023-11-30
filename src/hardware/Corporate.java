package hardware;

import java.io.Serializable;
import java.util.ArrayList;

public class Corporate extends Department implements Serializable
{
	private static final long serialVersionUID = 917064813271901642L;

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
