package hardware;

import java.text.DecimalFormat;

public class Item 
{
	private String name;
	private int itemNum;
	private double retailPrice;
	private double supplierPrice;
	private int qty;
	private static int count;
	
	DecimalFormat priceFormat = new DecimalFormat("#.00");
	
	public Item()
	{
		name = "unknown";
		itemNum = count;
		count++;
		retailPrice = 0.0;
		supplierPrice = 0.0;
		qty = 0;
	}
	
	public Item(String aName, double aRetailPrice, double aSupplierPrice, int qtyOnHand)
	{
		name = aName;
		itemNum = count;
		count++;
		retailPrice = aRetailPrice;
		supplierPrice = aSupplierPrice;
		qty = qtyOnHand;
	}

	public void setName(String aName)
	{
		name = aName;
	}
	public String getName()
	{
		return name;
	}
	
	public int getItemNum()
	{
		return itemNum;
	}
	
	public void setRetailPrice(double aPrice)
	{
		retailPrice = aPrice;
	}
	public String getRetailPrice()
	{
		return priceFormat.format(retailPrice);
	}
	
	public void setSupplierPrice(double aPrice)
	{
		supplierPrice = aPrice;
	}
	public String getSupplierPrice()
	{
		return priceFormat.format(supplierPrice);
	}
	
	public void setQty(int aNum)
	{
		qty = aNum;
	}
	public int getQty()
	{
		return qty;
	}
	
	public String toString()
	{
		return(this.itemNum + " " + this.name + " " + this.qty + " $" + this.retailPrice);
	}
}
