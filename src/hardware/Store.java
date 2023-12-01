package hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Store extends Department implements Serializable 
{
	private static final long serialVersionUID = 8003303552133226538L;
	private ArrayList<Item> inventory;
	
	public Store()
	{
		Random rand = new Random();
		inventory = new ArrayList<Item>();
		identifier = "S";
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
		return ("Store # " + this.getID());
	}
}
