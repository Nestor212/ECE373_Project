package hardware;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;

/* Class Description
 * Item objects that make up the inventrory accross the company. 
 * 
 * Items are created by a Supplier object to represent their manufacturing of any item.
 * 
 * Item tranfers accross objects are handled by cloning the object,
 * such that, a new item object with a new qty can be owned and modified
 *  by other objects without affecting the item accross the systme. 
 */

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
	
/************* Multiple class instantiation methods *************/

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

/************* Basic Getter and Setter Methods *************/

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
	
	/* Clones the object to create unique instances as an object it moved throughout the supply process. */
	public Item cloneItem(Integer aQty)
	{
		Item itemClone = new Item(this.itemNum, this.name, this.retailPrice, this.supplierPrice, aQty); 	
		return itemClone;
	}
	
	/* Returns a string of the general item information with basic formatting. */
	public String toString()
	{
		return(this.itemNum + " " + this.name + " " + this.qty + " $" + priceFormat.format(retailPrice));
	}
}
