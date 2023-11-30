package software;

import java.io.Serializable;
import java.util.ArrayList;

import hardware.Item;

public abstract class Order implements Serializable 
{
	private static final long serialVersionUID = 630554898030262584L;
	protected String orderIdentifier;
	protected int orderNumber;
	protected String orderStatus;
	private ArrayList<Item> itemList;
	private static int orderCount;
	
	public Order()
	{
		orderIdentifier = "unknown";
		orderNumber = 1000 + orderCount;
		orderStatus = "New Order";
		itemList = new ArrayList<Item>();
		orderCount++;
	}
	
	public int getOrderNum()
	{
		return orderNumber;
	}
	
	public String getOrderID()
	{
		return (orderIdentifier + "-" + orderNumber);
	}
	
	public void setOrderStatus(String aStatus)
	{
		orderStatus = aStatus;
	}
	
	public String getOrderStatus()
	{
		return orderStatus;
	}
	
	public void addItemToOrder(Item aItem)
	{
		itemList.add(aItem);
	}
	public void addItemsToOrder(ArrayList<Item> items)
	{
		itemList.addAll(items);
	}
	public void removeItemToOrder(Item aItem)
	{
		itemList.remove(aItem);
	}
	public ArrayList<Item> getItemList()
	{
		return itemList;
	}
	public Item findItem(String aName)
	{
		for(int i = 0; i < itemList.size(); i++)
		{
			if(itemList.get(i).getName() == aName)
			{
				return itemList.get(i);
			}
		}
		System.out.println("Item: " + aName + " not found.");
		return null;
	}
	
	public String getOrderIdentifier()
	{
		return orderIdentifier;
	}
	
	public String toString()
	{
		return (this.orderIdentifier +"-" + this.orderNumber + "                        " + this.orderStatus);
	}
}
