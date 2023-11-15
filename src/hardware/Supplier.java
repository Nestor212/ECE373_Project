package hardware;

import java.util.ArrayList;

import software.Order;
import software.WarehouseOrder;

public class Supplier extends Partner
{
	private ArrayList<Order> orders;
	private ArrayList<Item> itemList;
	
	public Supplier()
	{
		orders = new ArrayList<Order>();
		itemList = new ArrayList<Item>();
	}
	
	public void fulfillOrder(Order aOrder)
	{
		// TO DO: How will our orders be structured 
	}
	
	public void addItem(Item aItem)
	{
		itemList.add(aItem);
	}
	
	public void addItems(ArrayList<Item> aItem)
	{
		itemList.addAll(aItem);
	}
	
	public ArrayList<Item> getItemList()
	{
		return itemList;
	}
	
	public void addOrder(WarehouseOrder aOrder)
	{
		orders.add(aOrder);
	}
	public ArrayList<Order> getOrders()
	{
		return orders;
	}
	
}
