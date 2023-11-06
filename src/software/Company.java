package software;

import java.util.ArrayList;

import hardware.Corporate;
import hardware.Store;
import hardware.Supplier;
import hardware.Transport;
import hardware.Warehouse;

public class Company 
{
	private String name;
	private ArrayList<Store> storeList;
	private ArrayList<Warehouse> warehouseList;
	private ArrayList<Supplier> supplierList;
	private ArrayList<Transport> transportList;
	private Corporate corporateOffice;
	private ArrayList<Order> orderList;
	
	public Company()
	{
		name = "unknown";
		storeList = new ArrayList<Store>();
		warehouseList = new ArrayList<Warehouse>();
		supplierList = new ArrayList<Supplier>();
		transportList = new ArrayList<Transport>();
		corporateOffice = new Corporate();
		orderList = new ArrayList<Order>();
	}
	
	public void setname(String aName)
	{
		name = aName;
	}
	public String getName()
	{
		return name;
	}
	
	public void addStore(Store aStore)
	{
		storeList.add(aStore);
	}
	public void removeStore(Store aStore)
	{
		storeList.remove(aStore);
	}
	public ArrayList<Store> getStoreList()
	{
		return storeList;
	}

	public void addSWarehouse(Warehouse aWarehouse)
	{
		warehouseList.add(aWarehouse);
	}
	public void removeWarehouse(Warehouse aWarehouse)
	{
		warehouseList.remove(aWarehouse);
	}
	public ArrayList<Warehouse> getWarehouseList()
	{
		return warehouseList;
	}
	
	public void setCorporateoffice(Corporate aCorporate)
	{
		corporateOffice = aCorporate;
	}
	public Corporate getCorporateOffice()
	{
		return corporateOffice;
	}
	
	public void addOrder(Order aOrder)
	{
		orderList.add(aOrder);
	}
	public void deleteOrder(Order aOrder)
	{
		orderList.remove(aOrder);
	}
	public ArrayList<Order> getOrderList()
	{
		return orderList;
	}
	
	public void addSupplier(Supplier aSupplier)
	{
		supplierList.add(aSupplier);
	}
	public void removeSupplier(Supplier aSupplier)
	{
		supplierList.remove(aSupplier);
	}
	public Supplier findSupplier(String aName)
	{
		for(int i = 0; i < supplierList.size(); i++)
		{
			if(supplierList.get(i).getName() == aName)
			{
				return supplierList.get(i);
			}
			else
			{
				System.out.println("Supplier: " + aName + " not found."); 
				break;
			}
		}
	}
}
