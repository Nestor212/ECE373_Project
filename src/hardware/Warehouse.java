package hardware;

import java.io.Serializable;
import java.util.ArrayList;

public class Warehouse extends Department implements Serializable 
{
	private static final long serialVersionUID = -6698773854835608666L;
	private ArrayList<Item> inventory;
	private static int whCount;
	
	public Warehouse()
	{
		inventory = new ArrayList<Item>();
		identifier = "WH";
		idNum = whCount + 1;
		whCount++;
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
		return ("Warehouse # " + this.getID());
	}

}
