package hardware;

import java.util.ArrayList;

import software.StoreOrder;
import software.WarehouseOrder;

public class Warehouse extends Department 
{
	private ArrayList<Item> inventory;
	
	public Warehouse()
	{
		inventory = new ArrayList<Item>();
		identifier = "WH";
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
		return ("Warehouse # " + this.getID() + " - " + this.getLocation());
	}

}
