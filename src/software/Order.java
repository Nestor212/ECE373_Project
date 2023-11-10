package software;

import java.util.ArrayList;

import hardware.Item;

public abstract class Order 
{
	private int orderNumber;
	private String orderStatus;
	private ArrayList<Item> itemList;
	private static int orderCount;
	
	public Order()
	{
		orderNumber = orderCount;
		orderStatus = "New Order";
		itemList = new ArrayList<Item>();
		orderCount++;
	}
	
	public int getOrderNum()
	{
		return orderNumber;
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

}
