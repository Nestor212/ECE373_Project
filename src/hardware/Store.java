package hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/* Class Description
 * Store extends department. These objects represent physical store locations
 * where a small amount of inventory is displayed, and sold. 
 * 
 *  Stores replenish their inventory from Warehouses. 
 */

public class Store extends Department implements Serializable 
{
	private static final long serialVersionUID = 8003303552133226538L;
	private ArrayList<Item> inventory; // List of inventory available in the store.
	
	public Store()
	{
		Random rand = new Random();
		inventory = new ArrayList<Item>();
		identifier = "S"; // Unique Store object identifier, for simplifying interactions with Department objects.
		idNum = rand.nextInt(10000); // Random ID number is generated to represent this store. Eventually ID's will be sequential and tracked. 
	}
	
/************* Inventory Management Methods *************/

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
