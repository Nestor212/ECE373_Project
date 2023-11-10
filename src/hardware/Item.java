package hardware;

public class Item 
{
	private String name;
	private int itemNum;
	private double retailPrice;
	private double supplierPrice;
	private int qty;
	private static int count;
	
	public Item()
	{
		name = "unknown";
		itemNum = count;
		count++;
		retailPrice = 0.0;
		supplierPrice = 0.0;
		qty = 0;
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
	public double getRetailPrice()
	{
		return retailPrice;
	}
	
	public void setSupplierPrice(double aPrice)
	{
		supplierPrice = aPrice;
	}
	public double getSupplierPrice()
	{
		return supplierPrice;
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
