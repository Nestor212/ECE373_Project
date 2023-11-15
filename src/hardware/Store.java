package hardware;

import java.util.ArrayList;

public class Store extends Department 
{
	private ArrayList<Item> inventory;
	
	public Store()
	{
		inventory = new ArrayList<Item>();
		identifier = "S";
	}
	
	public void addItemToInventory(Item aItem)
	{
		inventory.add(aItem);
	}
	
	public void addItemsToInventory(ArrayList<Item> items)
	{
		inventory.addAll(items);
	}
	
	public void removeFromInventory(Item aItem)
	{
		inventory.remove(aItem);
	}
	public ArrayList<Item> getInventory()
	{
		return inventory;
	}
	
	public String toString()
	{
		return ("Store # " + this.getID() + " - " + this.getLocation());
	}
}
