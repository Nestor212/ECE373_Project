package hardware;

import java.util.ArrayList;

public class Warehouse extends Department 
{
	private ArrayList<Item> inventory;
	
	public Warehouse()
	{
		inventory = new ArrayList<Item>();
	}
	
	public void addToInventory(Item aItem)
	{
		inventory.add(aItem);
	}
	public void removeFromInventory(Item aItem)
	{
		inventory.remove(aItem);
	}
	public ArrayList<Item> getInventory()
	{
		return inventory;
	}

}
