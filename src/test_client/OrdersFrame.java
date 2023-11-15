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

public class OrdersFrame extends JFrame implements ActionListener
{
    Container container = getContentPane();
    
    JLabel titleLabel;
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

	public OrdersFrame()
	{
		newOrderButton = new JButton("New Order");
		
	    goBackButton = new JButton("Go Back");
	    goBackButton.setBounds(JButton.RIGHT, JButton.SOUTH, 80, 20);
	    goBackButton.addActionListener(this);
		
		populateArrays();
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
		switch(GUI.getSession().getSessionAccount().getDepartment().getIdentifier())
		{
			case "S":
				this.setSize(800, 720);
				storeOrderLabel = new JLabel("From Store to Warehouse");
				setStoreOrderPage();
				break;
			case "WH":
		    	this.setSize(getPreferredSize());
				storeOrderLabel = new JLabel("From Store to Warehouse");
				whOrderLabel = new JLabel("From Warehouse to Supplier");
				setWarehouseOrderPage();
				break;
			default:
				break;
		}
		
		titleLabel = new JLabel("Orders - " + GUI.getSession().getSessionAccount().getDepartment().toString());
    	titleLabel.setBounds(50, 40, 1000, 30);
    	titleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
    	container.add(titleLabel);
    	
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
		
		orderNumberlabel1.setBounds(550, 150, 1000, 22);
		orderNumberlabel1.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel1.setBounds(700, 150, 1000, 22);
		orderStatuslabel1.setFont(new Font("Lucida", Font.BOLD, 16));
	
		// Set bounds for order labels
		int count = 0;
		for(int i = 0; i < GUI.getSession().getSessionAccount().getDepartment().getOrders().size(); i++)
		{
			if(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(i).getOrderIdentifier().equals("S"))
			{
				orders.get(i).setBounds(50, (i * 20) + 200,  600, 20);
				viewOrderButtons.get(i).setBounds(400, (i * 20) + 200, 50, 20);

				count++;
			}
			else if(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(i).getOrderIdentifier().equals("WH"))
			{
				orders.get(i).setBounds(550, ((i - count) * 20) + 200,  600, 20);
				viewOrderButtons.get(i).setBounds(950, ((i - count) * 20) + 200, 50, 20);
			}
			viewOrderButtons.get(i).addActionListener(this);
		}
	}
	
	public void viewOrderPage(Order aOrder)
	{	
		this.setSize(800, 720);
		titleLabel = new JLabel("Order # " + aOrder.toString());
    	titleLabel.setBounds(50, 50, 1000, 30);
    	titleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		ArrayList<JLabel> orderItems = new ArrayList<JLabel>(aOrder.getItemList().size());
		
		for(int i = 0; i < aOrder.getItemList().size();i++)
		{
			orderItems.add(new JLabel(aOrder.getItemList().get(i).toString()));
			orderItems.get(i).setBounds(50, (i * 20) + 100,  600, 20);
			container.add(orderItems.get(i));
		}
		
    	container.add(titleLabel);
		container.add(goBackButton);		
	}
	
	JButton orderFromButton;
	JComboBox<String> orderFromComboBox;
	JLabel orderFromLabel;
	
	public void newOrderPage()
	{
		this.setSize(800, 720);
		orderFromLabel = new JLabel("Order From Selection: ");
		orderFromComboBox = new JComboBox<String>();
		orderFromComboBox.addActionListener(this);
		orderFromButton = new JButton("Okay");
		
		titleLabel.setText("New Order");

		switch(GUI.getSession().getSessionAccount().getDepartment().getIdentifier())
		{
			case "S":				
				for(int i = 0; i < GUI.getSession().getCompany().getWarehouseList().size(); i++)
				{
					orderFromComboBox.addItem(GUI.getSession().getCompany().getWarehouseList().get(i).getID());
				}
				//aOrder = GUI.getSession().getSessionAccount().createStoreOrder();
				break;
			case "WH":
				
				//aOrder = GUI.getSession().getSessionAccount().createWarehouseOrder((Supplier)GUI.getSession().getSessionAccount().getPartner());;
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
			
	public void displayInventoryForOrder(Warehouse aWH)
	{
		orderLabel = new JLabel("Ordering from: " + aWH.toString());
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
		
		itemNameList = new ArrayList<JLabel>(aWH.getInventory().size());
		itemQtyList = new ArrayList<JLabel>(aWH.getInventory().size());
		orderQty = new ArrayList<JTextField>(aWH.getInventory().size());		
		
		int yPos = 0;
		
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
		submitOrderButton.setBounds(330, yPos + 40, 150, 30);
		
		addToContainer("NewOrder");
		addToContainer("DisplayInventory");
	}
	
	public void addToContainer(String page)
	{
		switch(page) 
		{
		case "NewOrder":
			container.add(titleLabel);
			container.add(orderFromLabel);
			container.add(goBackButton);		
			container.add(orderFromComboBox);
			container.add(orderFromButton);
			break;
		case "OrderHome":
			// Univeral Items
			container.add(storeOrderLabel);
			container.add(orderNumberlabel);
			container.add(orderStatuslabel);
			container.add(newOrderButton);
			
			for(int i = 0; i < orders.size(); i++) {
				container.add(orders.get(i));
				container.add(viewOrderButtons.get(i));
			}
			
			if(GUI.getSession().getSessionAccount().getDepartment().getIdentifier().equals("WH"))
			{
				// Add remaining labels to container
				container.add(orderNumberlabel1);
				container.add(orderStatuslabel1);
				container.add(whOrderLabel);	
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
	
	
	public void generateOrder(Warehouse aWH)
	{
		ArrayList<Item> orderList = new ArrayList<Item>(orderQty.size());
		StoreOrder newOrder;
		
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
		newOrder = GUI.getSession().getSessionAccount().createStoreOrder(aWH);
		newOrder.addItemsToOrder(orderList);
		
        JOptionPane.showMessageDialog(this, "Order " + newOrder.getOrderID() + " Created");
        
        container.removeAll();
		clearArrays();
		populateArrays();
		if(GUI.getSession().getSessionAccount().getDepartment().getIdentifier().equals("WH"))
		{
			setWarehouseOrderPage();
		}
		else
		{
			setStoreOrderPage();
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
			}
			else
			{
				addToContainer("NewOrder");
			}
			super.update(getGraphics());
		}
		else if(e.getSource() == orderFromButton)
		{
			container.removeAll();
			for(int i = 0; i < GUI.getSession().getCompany().getWarehouseList().size(); i++)
			{
				if(GUI.getSession().getCompany().getWarehouseList().get(i).getID().equals(orderFromComboBox.getSelectedItem())) 
				{
					displayInventoryForOrder(GUI.getSession().getCompany().getWarehouseList().get(i));
				}
			}
			super.update(getGraphics());
		}
		else if (e.getSource() == submitOrderButton)
		{
			for(int i = 0; i < GUI.getSession().getCompany().getWarehouseList().size(); i++)
			{
				if(GUI.getSession().getCompany().getWarehouseList().get(i).getID().equals(orderFromComboBox.getSelectedItem())) 
				{
					generateOrder(GUI.getSession().getCompany().getWarehouseList().get(i));
				}
			}		
		}
	}
}
