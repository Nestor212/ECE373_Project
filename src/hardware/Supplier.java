package hardware;

import java.io.Serializable;
import java.util.ArrayList;

import software.Order;
import software.WarehouseOrder;

/* Class Description
 * Suppliers are partners to the company. 
 * They are primary source of any items belonging to department inventories. 
 * Any new items introduced to the system are implemented by the supplier first. 
 * 
 * Suppliers source inventory for Warehouses. 
 */

public class Supplier extends Partner implements Serializable
{
	private static final long serialVersionUID = 5749787399779713267L;
	private ArrayList<Item> itemList; // List of items made available by this supplier.  
	
	public Supplier()
	{
		itemList = new ArrayList<Item>();
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
	
	/* A supplier only fulfills Warehouse orders, therefore it takes an Order object,
	 * casts it to WarehouseOrder.
	 * 
	 * Then, the order item list is parsed through and compared to supplier inventory by item number, 
	 * and quantities are adjusted accordingly. 
	 *  
	 *  
	 *  Order status is changed to progress supply process.
	 */
	public void fulfillOrder(Order aOrder)
	{
		WarehouseOrder whOrder = (WarehouseOrder) aOrder;
		for(int i = 0; i < whOrder.getItemList().size(); i++)
		{
			for(int j = 0; j < whOrder.getfulfilledBy().getItemList().size(); j++)
			{
				if(whOrder.getItemList().get(i).getItemNum().equals(whOrder.getfulfilledBy().getItemList().get(j).getItemNum()))
				{
					//Remove Order Qty from WH Inventory Qty
					whOrder.getfulfilledBy().getItemList().get(j).addQty(-whOrder.getItemList().get(i).getQty());
				}
			}
		}
        aOrder.setOrderStatus("Awaiting Pickup");
	}
}
