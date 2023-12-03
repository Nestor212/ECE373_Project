package software;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import hardware.Department;
import hardware.Item;
import hardware.Partner;
import hardware.Transport;

public abstract class Order implements Serializable 
{
	private static final long serialVersionUID = 630554898030262584L;
	protected String orderIdentifier;
	protected Integer orderNumber;
	protected String orderStatus;
	private ArrayList<Item> itemList;
	private Transport deliveredBy;
	
	public Order()
	{
		Random rand = new Random();
		orderIdentifier = "unknown";
		orderNumber = rand.nextInt(1000);
		orderStatus = "Awaiting Fulfillment";
		itemList = new ArrayList<Item>();
		deliveredBy = null;
	}
	
	public Integer getOrderNum()
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
		return (this.orderIdentifier +"-" + this.orderNumber);
	}
	
	public void setDeliveredBy(Transport aTransport)
	{
		deliveredBy = aTransport;
		orderStatus = "Delivery in progress";
	}
	
	public Transport getDeliveredBy()
	{
		return deliveredBy;
	}
	
	public abstract String getOrderedByString();
	public abstract String getfulfilledByString();
	
	public abstract String getPickupAddressString();
	public abstract String getDeliveryAddressString();
}
