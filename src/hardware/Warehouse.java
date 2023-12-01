package hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Warehouse extends Department implements Serializable 
{
	private static final long serialVersionUID = -6698773854835608666L;
	private ArrayList<Item> inventory;
	
	public Warehouse()
	{
		Random rand = new Random();
		inventory = new ArrayList<Item>();
		identifier = "WH";
		idNum = rand.nextInt(10000);
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
