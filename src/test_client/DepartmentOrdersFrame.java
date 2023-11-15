package test_client;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import hardware.*;
import software.Order;
import software.StoreOrder;

public class DepartmentOrdersFrame extends JFrame implements ActionListener
{
    Container container = getContentPane();
    
    String accountIdentifier; // Use to taylor visual settings of page. "S" - Store Employee, "WH" - Warehouse Employee
    
    JLabel orderHomeTitleLabel;
    JLabel newOrderTitleLabel;
    JLabel viewOrderTitleLabel;

    JLabel storeOrderLabel;
    JLabel whOrderLabel;  
    JLabel orderNumberlabel = new JLabel("Order Number");
    JLabel orderStatuslabel = new JLabel("Order Status");
    JLabel orderNumberlabel1 = new JLabel("Order Number");
    JLabel orderStatuslabel1 = new JLabel("Order Status");
         
    ArrayList<JLabel> orders;
    ArrayList<JLabel> whOrders;
    
    JButton goBackButton;
    JButton newOrderButton;

    ArrayList<JButton> viewOrderButtons;

	public DepartmentOrdersFrame()
	{
		accountIdentifier = GUI.getSession().getSessionAccount().getDepartment().getIdentifier();
		
		newOrderButton = new JButton("New Order");
		
	    goBackButton = new JButton("Go Back");
	    goBackButton.setBounds(JButton.RIGHT, JButton.SOUTH, 80, 20);
	    goBackButton.addActionListener(this);
		
        setLayoutManager(); 
		setUniversalPageSettings();
    }
	
	public void populateArrays()
	{
		orders = new ArrayList<JLabel>(GUI.getSession().getSessionAccount().getDepartment().getOrders().size());
		viewOrderButtons = new ArrayList<JButton>(GUI.getSession().getSessionAccount().getDepartment().getOrders().size());
		
		for(int i = 0; i < GUI.getSession().getSessionAccount().getDepartment().getOrders().size(); i++)
		{
			orders.add(new JLabel(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(i).toString()));
			viewOrderButtons.add(new JButton("View"));
		}
	}
	
	ArrayList<Order> allOrders;
	
	public void populateArraysCorporate()
	{
		int numberOfOrders = 0;
		
		for(int i = 0; i < GUI.getSession().getCompany().getWarehouseList().size(); i++)
		{
			numberOfOrders = numberOfOrders + GUI.getSession().getCompany().getWarehouseList().get(i).getOrders().size();
		}
		
		allOrders = new ArrayList<Order>(numberOfOrders);
		orders = new ArrayList<JLabel>(numberOfOrders);		
		viewOrderButtons = new ArrayList<JButton>(numberOfOrders);


		for(int i = 0; i < GUI.getSession().getCompany().getWarehouseList().size(); i++)
		{
			for(int j = 0; j < GUI.getSession().getCompany().getWarehouseList().get(i).getOrders().size(); j++)
			{
				allOrders.add(GUI.getSession().getCompany().getWarehouseList().get(i).getOrders().get(i));
			}
		}
	}
	
	public void clearArrays()
	{
		orders.clear();
		viewOrderButtons.clear();
	}
	
    public void setLayoutManager() 
    {
        container.setLayout(null);
    }
    
    public void setUniversalPageSettings()
    {
		switch(accountIdentifier)
		{
			case "S":
				storeOrderLabel = new JLabel("From Store to Warehouse");
				populateArrays();
				setStoreOrderPage();
				break;
			case "WH":
				storeOrderLabel = new JLabel("From Store to Warehouse");
				whOrderLabel = new JLabel("From Warehouse to Supplier");
				populateArrays();
				setWarehouseOrderPage();
				break;
			case ("C"):
				storeOrderLabel = new JLabel("Store Orders");
				whOrderLabel = new JLabel("Warehouse Orders");
				populateArrays();
				setWarehouseOrderPage();
//				populateArraysCorporate();
//				setCorporateOrderPage();
				break;
			default:

				break;
		}
		
		orderHomeTitleLabel = new JLabel("Orders - " + GUI.getSession().getSessionAccount().getDepartment().toString());
		orderHomeTitleLabel.setBounds(50, 40, 1000, 30);
		orderHomeTitleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
    	
		storeOrderLabel.setBounds(50, 100, 1000, 30);
		storeOrderLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		orderNumberlabel.setBounds(50, 150, 1000, 30);
		orderNumberlabel.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel.setBounds(200, 150, 1000, 30);
		orderStatuslabel.setFont(new Font("Lucida", Font.BOLD, 16));
		
		newOrderButton.setBounds(50, 600, 100, 20);
		newOrderButton.addActionListener(this);
		
		addToContainer("OrderHome");
    }
    
	public void setStoreOrderPage()
	{	
		for(int i = 0; i < GUI.getSession().getSessionAccount().getDepartment().getOrders().size(); i++)
		{
			// Set bounds for order labels
			orders.get(i).setBounds(50, (i * 20) + 200,  600, 20);
			viewOrderButtons.get(i).setBounds(400, (i * 20) + 200, 50, 20);
			viewOrderButtons.get(i).addActionListener(this);
		}
	}
	
	public void setWarehouseOrderPage()
	{
		whOrderLabel.setBounds(550, 100, 1000, 30);
		whOrderLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		orderNumberlabel1.setBounds(550, 150, 1000, 30);
		orderNumberlabel1.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel1.setBounds(700, 150, 1000, 30);
		orderStatuslabel1.setFont(new Font("Lucida", Font.BOLD, 16));
	
		// Set bounds for order labels
		int leftPos = 0;
		int rightPos = 0;
		for(int i = 0; i < GUI.getSession().getSessionAccount().getDepartment().getOrders().size(); i++)
		{
			if(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(i).getOrderIdentifier().equals("S"))
			{
				orders.get(i).setBounds(50, (leftPos * 20) + 200,  600, 20);
				viewOrderButtons.get(i).setBounds(400, (leftPos * 20) + 200, 50, 20);
				leftPos++;
			}
			else if(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(i).getOrderIdentifier().equals("WH"))
			{
				orders.get(i).setBounds(550, (rightPos * 20) + 200,  600, 20);
				viewOrderButtons.get(i).setBounds(950, (rightPos * 20) + 200, 50, 20);
				rightPos++;
			}
			viewOrderButtons.get(i).addActionListener(this);
		}
	}
	
	public void setCorporateOrderPage()
	{
		whOrderLabel.setBounds(550, 100, 1000, 30);
		whOrderLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		orderNumberlabel1.setBounds(550, 150, 1000, 30);
		orderNumberlabel1.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel1.setBounds(700, 150, 1000, 30);
		orderStatuslabel1.setFont(new Font("Lucida", Font.BOLD, 16));
	
		// Set bounds for order labels
		int leftPos = 0;
		int rightPos = 0;
		for(int i = 0; i < allOrders.size(); i++)
		{
			if(allOrders.get(i).getOrderIdentifier().equals("S"))
			{
				orders.add(new JLabel(allOrders.get(i).getOrderID()));
				orders.get(i).setBounds(50, (leftPos * 20) + 200,  600, 20);
				viewOrderButtons.add(new JButton("View"));
				viewOrderButtons.get(i).setBounds(400, (leftPos * 20) + 200, 50, 20);
				leftPos++;
			}
			else if(allOrders.get(i).getOrderIdentifier().equals("WH"))
			{
				orders.add(new JLabel(allOrders.get(i).getOrderID()));
				orders.get(i).setBounds(550, (rightPos * 20) + 200,  600, 20);
				viewOrderButtons.add(new JButton("View"));
				viewOrderButtons.get(i).setBounds(950, (rightPos * 20) + 200, 50, 20);
				rightPos++;
			}
			viewOrderButtons.get(i).addActionListener(this);
		}
	}
	
	public void viewOrderPage(Order aOrder)
	{	
		this.setSize(800, 720);
		viewOrderTitleLabel = new JLabel("Order # " + aOrder.toString());
		viewOrderTitleLabel.setBounds(50, 50, 1000, 30);
		viewOrderTitleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		ArrayList<JLabel> orderItems = new ArrayList<JLabel>(aOrder.getItemList().size());
		
		for(int i = 0; i < aOrder.getItemList().size();i++)
		{
			orderItems.add(new JLabel(aOrder.getItemList().get(i).toString()));
			orderItems.get(i).setBounds(50, (i * 20) + 100,  600, 20);
			container.add(orderItems.get(i));
		}
		
    	container.add(viewOrderTitleLabel);
		container.add(goBackButton);		
	}
	
	JButton orderFromButton;
	JComboBox<String> orderFromComboBox;
	JLabel orderFromLabel;
	
	public void newOrderPage()
	{
		orderFromLabel = new JLabel("Order From Selection: ");
		orderFromComboBox = new JComboBox<String>();
		orderFromComboBox.addActionListener(this);
		orderFromButton = new JButton("Okay");
		
		newOrderTitleLabel = new JLabel("New Order");
		newOrderTitleLabel.setBounds(50, 40, 1000, 30);
		newOrderTitleLabel.setFont(new Font("Lucida", Font.BOLD, 22));

		switch(accountIdentifier)
		{
			case "S":				
				for(int i = 0; i < GUI.getSession().getCompany().getWarehouseList().size(); i++)
				{
					orderFromComboBox.addItem(GUI.getSession().getCompany().getWarehouseList().get(i).getID());
				}
				break;
			case "WH":
				for(int i = 0; i < GUI.getSession().getCompany().getSupplierList().size(); i++)
				{
					orderFromComboBox.addItem(GUI.getSession().getCompany().getSupplierList().get(i).getName());
				}
				break;
			default:
				break;
		}
		orderFromLabel.setBounds(50, 100, 300, 40);
		orderFromComboBox.setBounds(220, 100, 300, 40);
		orderFromButton.setBounds(550, 100, 100, 30);
		orderFromButton.addActionListener(this);
		
		addToContainer("NewOrder");
	}
	
	ArrayList<JLabel> itemNameList; 
	ArrayList<JLabel> itemQtyList;
	ArrayList<JTextField> orderQty;
	JButton submitOrderButton;
	
	JLabel orderLabel;
	JLabel itemNameLabel;
	JLabel availableQtyLabel;
	JLabel desiredQtyLabel;
	
	Warehouse aWH;
	hardware.Supplier aSupp;
			
	public void displayInventoryForOrder()
	{
		int yPos = 0;
		
		switch(accountIdentifier)
		{
		case "S":
			for(int i = 0; i < GUI.getSession().getCompany().getWarehouseList().size(); i++)
			{
				if(GUI.getSession().getCompany().getWarehouseList().get(i).getID().equals(orderFromComboBox.getSelectedItem())) 
				{
					aWH = GUI.getSession().getCompany().getWarehouseList().get(i);
				}
			}
			orderLabel = new JLabel("Ordering from: " + aWH.toString());
			itemNameList = new ArrayList<JLabel>(aWH.getInventory().size());
			itemQtyList = new ArrayList<JLabel>(aWH.getInventory().size());
			orderQty = new ArrayList<JTextField>(aWH.getInventory().size());	
			
			for(int i = 0; i < aWH.getInventory().size(); i++) 
			{
				yPos = (i * 20) + 200;
				itemNameList.add(new JLabel(aWH.getInventory().get(i).getName()));
				itemQtyList.add(new JLabel(String.valueOf(aWH.getInventory().get(i).getQty())));
				orderQty.add(new JTextField());
				
				itemNameList.get(i).setBounds(50, yPos, 100, 30);
				itemQtyList.get(i).setBounds(200, yPos, 100, 30);
				orderQty.get(i).setBounds(350, yPos, 100, 30);
			}
			break;
		case "WH":
			for(int i = 0; i < GUI.getSession().getCompany().getSupplierList().size(); i++)
			{
				if(GUI.getSession().getCompany().getSupplierList().get(i).getName().equals(orderFromComboBox.getSelectedItem())) 
				{
					aSupp = GUI.getSession().getCompany().getSupplierList().get(i);
				}
			}
			orderLabel = new JLabel("Ordering from: " + aSupp.toString());
			itemNameList = new ArrayList<JLabel>(aSupp.getItemList().size());
			itemQtyList = new ArrayList<JLabel>(aSupp.getItemList().size());
			orderQty = new ArrayList<JTextField>(aSupp.getItemList().size());	
			
			for(int i = 0; i < aSupp.getItemList().size(); i++) 
			{
				yPos = (i * 20) + 200;
				itemNameList.add(new JLabel(aSupp.getItemList().get(i).getName()));
				itemQtyList.add(new JLabel(String.valueOf(aSupp.getItemList().get(i).getQty())));
				orderQty.add(new JTextField());
				
				itemNameList.get(i).setBounds(50, yPos, 100, 30);
				itemQtyList.get(i).setBounds(200, yPos, 100, 30);
				orderQty.get(i).setBounds(350, yPos, 100, 30);
			}
			break;
		default:
			break;
		}
		orderLabel.setBounds(50, 130, 1000, 30);
    	orderLabel.setFont(new Font("Lucida", Font.BOLD, 18));
		itemNameLabel = new JLabel("Item Name");
		itemNameLabel.setBounds(50, 175, 200, 30);
		itemNameLabel.setFont(new Font("Lucida", Font.BOLD, 14));
		availableQtyLabel = new JLabel("Available Qty");
		availableQtyLabel.setBounds(200, 175, 200, 30);
		availableQtyLabel.setFont(new Font("Lucida", Font.BOLD, 14));
		desiredQtyLabel = new JLabel("Desired Qty");
		desiredQtyLabel.setBounds(350, 175, 200, 30);
		desiredQtyLabel.setFont(new Font("Lucida", Font.BOLD, 14));
		submitOrderButton = new JButton("Submit Order");
		submitOrderButton.addActionListener(this);
		submitOrderButton.setBounds(330, yPos + 40, 150, 30);
		addToContainer("NewOrder");
		addToContainer("DisplayInventory");
	}
	
	public void addToContainer(String page)
	{
		switch(page) 
		{
		case "NewOrder":
			this.setSize(800, 720);
			container.add(newOrderTitleLabel);
			container.add(orderFromLabel);
			container.add(goBackButton);		
			container.add(orderFromComboBox);
			container.add(orderFromButton);
			break;
		case "OrderHome":
			this.setSize(getPreferredSize());
	    	container.add(orderHomeTitleLabel);
			container.add(storeOrderLabel);
			container.add(orderNumberlabel);
			container.add(orderStatuslabel);
			container.add(newOrderButton);
			
			for(int i = 0; i < orders.size(); i++) {
				container.add(orders.get(i));
				container.add(viewOrderButtons.get(i));
			}
			if(accountIdentifier.equals("WH") ||accountIdentifier.equals("C"))
			{
				// Add additional labels to container
				container.add(orderNumberlabel1);
				container.add(orderStatuslabel1);
				container.add(whOrderLabel);	
			}		
			if(accountIdentifier.equals("C"))
			{
				container.remove(newOrderButton);
			}
			break;
		case "DisplayInventory":
			for(int i = 0; i < itemNameList.size(); i++)
			{
				container.add(itemNameList.get(i));
				container.add(itemQtyList.get(i));
				container.add(orderQty.get(i));
			}
			container.add(itemNameLabel);
			container.add(availableQtyLabel);
			container.add(desiredQtyLabel);
			container.add(orderLabel);
			container.add(submitOrderButton);
			break; 
		default:
			break;
		}
		super.update(getGraphics());
	}
	
	
	public void generateOrder()
	{
        container.removeAll();
		clearArrays();

		ArrayList<Item> orderList = new ArrayList<Item>(orderQty.size());
		Order newOrder;
				
		switch(accountIdentifier)
		{
			case ("S"):
				newOrder = GUI.getSession().getSessionAccount().createStoreOrder(aWH);
				for(int i = 0; i < orderQty.size(); i++)
				{
					if(orderQty.get(i).getText().equals(""))
					{
						//empty qty field - do nothing
					}
					else 
					{
						orderList.add(aWH.getInventory().get(i).cloneItem(Integer.parseInt(orderQty.get(i).getText())));
					}
				}
				if(orderList.isEmpty()) 
				{
			        JOptionPane.showMessageDialog(this, "No items selected.");
				}
				else
				{
					newOrder.addItemsToOrder(orderList);
			        JOptionPane.showMessageDialog(this, "Order " + newOrder.getOrderID() + " Created");
					populateArrays();
					setStoreOrderPage();
				}
				break;
			case ("WH"):
				newOrder = GUI.getSession().getSessionAccount().createWarehouseOrder(aSupp);
				for(int i = 0; i < orderQty.size(); i++)
				{
					if(orderQty.get(i).getText().equals(""))
					{
						//empty qty field - do nothing
					}
					else 
					{
						orderList.add(aSupp.getItemList().get(i).cloneItem(Integer.parseInt(orderQty.get(i).getText())));
					}
				}
				if(orderList.isEmpty()) 
				{
			        JOptionPane.showMessageDialog(this, "No items selected.");
				}
				else
				{
					newOrder.addItemsToOrder(orderList);
			        JOptionPane.showMessageDialog(this, "Order " + newOrder.getOrderID() + " Created");
					populateArrays();
					setWarehouseOrderPage();
				}
				break;
			default:
				break;
		}
		addToContainer("OrderHome");	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0; i < viewOrderButtons.size(); i++)
		{
			if(e.getSource() == viewOrderButtons.get(i))
			{
				container.removeAll();
				viewOrderPage(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(i));
				super.update(getGraphics());
			}	
		}			
		if(e.getSource() == goBackButton)
		{
			container.removeAll();
			addToContainer("OrderHome");
			super.update(getGraphics());
		}	
		else if(e.getSource() == newOrderButton)
		{
			container.removeAll();
			if(orderFromLabel == null)
			{
				newOrderPage();
				super.update(getGraphics());
			}
			else
			{
				addToContainer("NewOrder");	
			}
		}
		else if(e.getSource() == orderFromButton)
		{
			container.removeAll();
			displayInventoryForOrder();
			super.update(getGraphics());
		}
		else if (e.getSource() == submitOrderButton)
		{
			generateOrder();				
		}
	}
}
