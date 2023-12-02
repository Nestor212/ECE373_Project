package hardware;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;

public class Item implements Serializable
{
	private static final long serialVersionUID = 2836030970999633739L;
	private String name;
	private Integer itemNum;
	private Double retailPrice;
	private Double supplierPrice;
	private Integer qty;
	Random rand = new Random();
	
	DecimalFormat priceFormat = new DecimalFormat("#.00");
	
	public Item()
	{
		name = "unknown";
		itemNum = rand.nextInt(50000);
		retailPrice = 0.00;
		supplierPrice = 0.00;
		qty = 0;
	}
	
	public Item(String aName, double aRetailPrice, double aSupplierPrice, int qtyOnHand)
	{
		name = aName;
		itemNum = rand.nextInt(50000);
		retailPrice = aRetailPrice;
		supplierPrice = aSupplierPrice;
		qty = qtyOnHand;
	}
	
	public Item(int aItemNum, String aName, double aRetailPrice, double aSupplierPrice, int qtyOnHand)
	{
		name = aName;
		itemNum = aItemNum;
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
	
	public Integer getItemNum()
	{
		return itemNum;
	}
	
	public void setRetailPrice(Double aPrice)
	{
		retailPrice = aPrice;
	}
	public String getRetailPrice()
	{
		return priceFormat.format(retailPrice);
	}
	
	public void setSupplierPrice(Double aPrice)
	{
		supplierPrice = aPrice;
	}
	public String getSupplierPrice()
	{
		return priceFormat.format(supplierPrice);
	}
	
	public void addQty(Integer aNum)
	{
		qty = qty + aNum;
	}
	
	public void setQty(Integer aNum)
	{
		qty = aNum;
	}
	
	public Integer getQty()
	{
		return qty;
	}
	
	public Item cloneItem(Integer aQty)
	{
		Item itemClone = new Item(this.itemNum, this.name, this.retailPrice, this.supplierPrice, aQty); 	
		return itemClone;
	}
	
	public String toString()
	{
		return(this.itemNum + " " + this.name + " " + this.qty + " $" + priceFormat.format(retailPrice));
	}
}
