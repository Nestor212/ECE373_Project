package hardware;

import java.io.Serializable;
import java.util.ArrayList;

public class Store extends Department implements Serializable 
{
	private ArrayList<Item> inventory;
	private static int storeCount;
	
	public Store()
	{
		inventory = new ArrayList<Item>();
		identifier = "S";
		idNum = storeCount + 1;
		storeCount++;
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
		return ("Store # " + this.getID());
	}
}
