package hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/* Class Description
 * Warehouse extends department. These objects represent physical warehouse locations
 * where a large amount of inventory is displayed, and sold. 
 * 
 *  Warehouses replenish their inventory from Suppliers.
 *  Warehouses provide inventory to any number of Stores. 
 */

public class Warehouse extends Department implements Serializable 
{
	private static final long serialVersionUID = -6698773854835608666L;
	private ArrayList<Item> inventory;
	
	public Warehouse()
	{
		Random rand = new Random();
		inventory = new ArrayList<Item>();
		identifier = "WH"; // Unique Warehouse object identifier, for simplifying interactions with Department objects.
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
		return ("Warehouse # " + this.getID());
	}

}
