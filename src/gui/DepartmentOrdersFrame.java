package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import hardware.*;
import software.Order;
import software.StoreOrder;
import software.WarehouseOrder;

// This class handles the window for department user

public class DepartmentOrdersFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1342965027533132960L;
	SupplyManagerGUI session;

    Container container = getContentPane();
    
    String accountIdentifier; // Use to tailor visual settings of page. "S" - Store Employee, "WH" - Warehouse Employee
    
    JLabel orderHomeTitleLabel;
    JLabel newOrderTitleLabel;
    JLabel viewOrderTitleLabel;
    JLabel placedOrdersLabel;
    JLabel recievedOrdersLabel;  
    JLabel orderNumberlabel = new JLabel("Order #");
    JLabel orderedBy = new JLabel("Order By");
    JLabel orderStatuslabel = new JLabel("Order Status");
    JLabel orderNumberlabel1 = new JLabel("Order #");
    JLabel orderedByTo = new JLabel("Order By");
    JLabel orderStatuslabel1 = new JLabel("Order Status");
         
    JButton goBackButton = new JButton("Go Back");
    JButton newOrderButton = new JButton("New Order");
    JButton recieveOrderButton = new JButton("Recieve Order");
    JButton fulfillOrderButton = new JButton("Fulfill Order");

    Order receieveableOrder;


    ArrayList<Order> orders;
    ArrayList<JLabel> orderNumLabels;
    ArrayList<JLabel> orderStatusLabels;
    ArrayList<JLabel> orderByToLabel;
    ArrayList<JButton> viewOrderButtons;
    
    
	public DepartmentOrdersFrame(SupplyManagerGUI aSession)
	{
    	session = aSession;
		accountIdentifier = session.sessionAccount.getDepartment().getIdentifier();
		
	    goBackButton.setBounds(JButton.RIGHT, JButton.SOUTH, 80, 20);
	    goBackButton.addActionListener(this);
	    
		recieveOrderButton.setBounds(50, 600, 700, 30);
		recieveOrderButton.addActionListener(this);
		fulfillOrderButton.setBounds(50, 600, 700, 30);
		fulfillOrderButton.addActionListener(this);
		
        setLayoutManager(); 
		setUniversalPageSettings();
    }
	
	public void clearArrays()
	{
		orders.clear();
		orderNumLabels.clear();
		viewOrderButtons.clear();
	}
	
	public void populateArrays()
	{
		if(accountIdentifier.equals("S") || accountIdentifier.equals("WH"))
		{
			orders = new ArrayList<Order>(session.sessionAccount.getDepartment().getOrders().size());
			orderNumLabels = new ArrayList<JLabel>(session.sessionAccount.getDepartment().getOrders().size());
			orderStatusLabels = new ArrayList<JLabel>(session.sessionAccount.getDepartment().getOrders().size());
			orderByToLabel = new ArrayList<JLabel>(session.sessionAccount.getDepartment().getOrders().size());
			viewOrderButtons = new ArrayList<JButton>(session.sessionAccount.getDepartment().getOrders().size());
			
			for(int i = 0; i < session.sessionAccount.getDepartment().getOrders().size(); i++)
			{
				orders.add(session.sessionAccount.getDepartment().getOrders().get(i));
				orderNumLabels.add(new JLabel(session.sessionAccount.getDepartment().getOrders().get(i).toString()));
				orderStatusLabels.add(new JLabel(session.sessionAccount.getDepartment().getOrders().get(i).getOrderStatus()));
				viewOrderButtons.add(new JButton("View"));
				switch(orders.get(i).getOrderIdentifier())
				{
					case("S"):
						orderByToLabel.add(new JLabel(orders.get(i).getOrderedByString()));
						break;
					case("WH"):
						orderByToLabel.add(new JLabel(orders.get(i).getfulfilledByString()));
						break;
					default:
						break;
				}
			}
		}
		else if(accountIdentifier.equals("C"))
		{
			orders = new ArrayList<Order>();
			orderNumLabels = new ArrayList<JLabel>();
			orderStatusLabels = new ArrayList<JLabel>();
			orderByToLabel = new ArrayList<JLabel>();
			viewOrderButtons = new ArrayList<JButton>();
			
			for(int i = 0; i < session.company.getWarehouseList().size(); i++)
			{
				for(int j = 0; j < session.company.getWarehouseList().get(i).getOrders().size(); j++)
				{
					orders.add(session.company.getWarehouseList().get(i).getOrders().get(j));
					orderNumLabels.add(new JLabel(session.company.getWarehouseList().get(i).getOrders().get(j).getOrderID()));
					orderStatusLabels.add(new JLabel(session.company.getWarehouseList().get(i).getOrders().get(j).getOrderStatus()));
					orderByToLabel.add(new JLabel(session.company.getWarehouseList().get(i).getOrders().get(j).getOrderedByString()));
					viewOrderButtons.add(new JButton("View"));
				}
			}
		}
		else
		{
			System.out.println("Error populating arrays.");
		}
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
				orderHomeTitleLabel = new JLabel("Orders - " + session.sessionAccount.getDepartment().toString());
				orderedByTo.setText("Ordered To");
				populateArrays();
				setStoreOrderPage();
				break;
			case "WH":
				orderHomeTitleLabel = new JLabel("Orders - " + session.sessionAccount.getDepartment().toString());
				recievedOrdersLabel = new JLabel("Orders Recieved");
				placedOrdersLabel = new JLabel("Orders Placed");
				orderedBy.setText("Ordered To");
				populateArrays();
				setWarehouseOrderPage();
				break;
			case ("C"):
				orderHomeTitleLabel = new JLabel("All Orders");
				recievedOrdersLabel = new JLabel("Store Orders");
				placedOrdersLabel = new JLabel("Warehouse Orders");
				populateArrays();
				setCorporateOrderPage();
				break;
			default:

				break;
		}
		
		orderHomeTitleLabel.setBounds(50, 40, 1000, 30);
		orderHomeTitleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		orderedByTo.setBounds(50, 150, 1000, 30);
		orderedByTo.setFont(new Font("Lucida", Font.BOLD, 16));
		
		orderNumberlabel.setBounds(200, 150, 1000, 30);
		orderNumberlabel.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel.setBounds(300, 150, 1000, 30);
		orderStatuslabel.setFont(new Font("Lucida", Font.BOLD, 16));
		
		newOrderButton.setBounds(50, 600, 100, 20);
		newOrderButton.addActionListener(this);
		
		addToContainer("OrderHome");
    }
    
	public void setStoreOrderPage()
	{
		for(int i = 0; i < session.sessionAccount.getDepartment().getOrders().size(); i++)
		{
			// Set bounds for order labels
			orderByToLabel.get(i).setBounds(50, (i * 20) + 200,  600, 20);
			orderNumLabels.get(i).setBounds(200, (i * 20) + 200,  600, 20);
			orderStatusLabels.get(i).setBounds(300, (i * 20) + 200,  600, 20);
			viewOrderButtons.get(i).setBounds(475, (i * 20) + 200, 50, 20);
			viewOrderButtons.get(i).addActionListener(this);
		}
	}
	
	public void setWarehouseOrderPage()
	{
		
		recievedOrdersLabel.setBounds(50, 100, 1000, 30);
		recievedOrdersLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		placedOrdersLabel.setBounds(600, 100, 1000, 30);
		placedOrdersLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		orderedBy.setBounds(600, 150, 1000, 30);
		orderedBy.setFont(new Font("Lucida", Font.BOLD, 16));
		
		orderNumberlabel1.setBounds(750, 150, 1000, 30);
		orderNumberlabel1.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel1.setBounds(850, 150, 1000, 30);
		orderStatuslabel1.setFont(new Font("Lucida", Font.BOLD, 16));
	
		// Set bounds for order labels
		int leftPos = 0;
		int rightPos = 0;
		for(int i = 0; i < session.sessionAccount.getDepartment().getOrders().size(); i++)
		{
			if(session.sessionAccount.getDepartment().getOrders().get(i).getOrderIdentifier().equals("S"))
			{
				orderByToLabel.get(i).setBounds(50, (leftPos * 20) + 200,  600, 20);
				orderNumLabels.get(i).setBounds(200, (leftPos * 20) + 200,  600, 20);
				orderStatusLabels.get(i).setBounds(300, (leftPos * 20) + 200,  600, 20);
				viewOrderButtons.get(i).setBounds(475, (leftPos * 20) + 200, 50, 20);
				leftPos++;
			}
			else if(session.sessionAccount.getDepartment().getOrders().get(i).getOrderIdentifier().equals("WH"))
			{
				orderByToLabel.get(i).setBounds(600, (rightPos * 20) + 200,  600, 20);
				orderNumLabels.get(i).setBounds(750, (rightPos * 20) + 200,  600, 20);
				orderStatusLabels.get(i).setBounds(850, (rightPos * 20) + 200,  600, 20);
				viewOrderButtons.get(i).setBounds(1025, (rightPos * 20) + 200, 50, 20);
				rightPos++;
			}
			viewOrderButtons.get(i).addActionListener(this);
		}
	}
	
	public void setCorporateOrderPage()
	{
		recievedOrdersLabel.setBounds(50, 100, 1000, 30);
		recievedOrdersLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		placedOrdersLabel.setBounds(600, 100, 1000, 30);
		placedOrdersLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		orderedBy.setBounds(600, 150, 1000, 30);
		orderedBy.setFont(new Font("Lucida", Font.BOLD, 16));
		
		orderNumberlabel1.setBounds(750, 150, 1000, 30);
		orderNumberlabel1.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel1.setBounds(850, 150, 1000, 30);
		orderStatuslabel1.setFont(new Font("Lucida", Font.BOLD, 16));
	
		// Set bounds for order labels
		int leftPos = 0;
		int rightPos = 0;
		for(int i = 0; i < orders.size(); i++)
		{
			if(orders.get(i).getOrderIdentifier().equals("S"))
			{
				orderByToLabel.get(i).setBounds(50, (leftPos * 20) + 200,  600, 20);
				orderNumLabels.get(i).setBounds(200, (leftPos * 20) + 200,  600, 20);
				orderStatusLabels.get(i).setBounds(300, (leftPos * 20) + 200,  600, 20);
				viewOrderButtons.get(i).setBounds(475, (leftPos * 20) + 200, 50, 20);
				leftPos++;
			}
			else if(orders.get(i).getOrderIdentifier().equals("WH"))
			{
				orderByToLabel.get(i).setBounds(600, (rightPos * 20) + 200,  600, 20);
				orderNumLabels.get(i).setBounds(750, (rightPos * 20) + 200,  600, 20);
				orderStatusLabels.get(i).setBounds(850, (rightPos * 20) + 200,  600, 20);
				viewOrderButtons.get(i).setBounds(1025, (rightPos * 20) + 200, 50, 20);
				rightPos++;
			}
			viewOrderButtons.get(i).addActionListener(this);
		}
	}
	
	public void addToContainer(String page)
	{
		container.removeAll();
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
			container.add(orderNumberlabel);
			container.add(orderStatuslabel);
			container.add(orderedByTo);
			container.add(newOrderButton);
			
			for(int i = 0; i < orderNumLabels.size(); i++) 
			{
				container.add(orderByToLabel.get(i));
				container.add(orderNumLabels.get(i));
				container.add(orderStatusLabels.get(i));
				container.add(viewOrderButtons.get(i));
			}
			if(accountIdentifier.equals("WH") ||accountIdentifier.equals("C"))
			{
				// Add additional labels to container
				container.add(placedOrdersLabel);	
				container.add(recievedOrdersLabel);	
				container.add(orderNumberlabel1);
				container.add(orderStatuslabel1);
				container.add(orderedBy);
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
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0; i < viewOrderButtons.size(); i++)
		{
			if(e.getSource() == viewOrderButtons.get(i))
			{
				container.removeAll();
				viewOrderPage(orders.get(i));
				super.update(getGraphics());
			}	
		}			
		if(e.getSource() == goBackButton)
		{
			//container.removeAll();
			addToContainer("OrderHome");
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
		else if (e.getSource() == recieveOrderButton)
		{
			reciveveOrder(receieveableOrder);
		}
		else if (e.getSource() == fulfillOrderButton)
		{
			handleFulfill(receieveableOrder);
		}
	}
	
	//************************ Action Methods ************************\\
	
	public void viewOrderPage(Order aOrder)
	{	
		
		this.setSize(800, 720);
		viewOrderTitleLabel = new JLabel("Order # " + aOrder.toString());
		viewOrderTitleLabel.setBounds(50, 50, 1000, 30);
		viewOrderTitleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
    	container.add(viewOrderTitleLabel);
		container.add(goBackButton);		
		
		JLabel itemNumLabel = new JLabel("Item Number");
		JLabel itemNameLabel = new JLabel("Name");
		JLabel QtyLabel = new JLabel("Qty");
		JLabel retailPriceLabel = new JLabel("Retail Price");
		JLabel supplierPriceLabel = new JLabel("Supplier Price");
		
    	itemNumLabel.setBounds(50, 80,  200, 20);
    	itemNumLabel.setFont(new Font("Lucida", Font.BOLD, 18));
        container.add(itemNumLabel);
    	itemNameLabel.setBounds(200, 80,  200, 20);
    	itemNameLabel.setFont(new Font("Lucida", Font.BOLD, 18));
        container.add(itemNameLabel);
    	QtyLabel.setBounds(350, 80,  200, 20);
    	QtyLabel.setFont(new Font("Lucida", Font.BOLD, 18));
        container.add(QtyLabel);
    	retailPriceLabel.setBounds(450, 80,  200, 20);
    	retailPriceLabel.setFont(new Font("Lucida", Font.BOLD, 18));
        container.add(retailPriceLabel);
    	supplierPriceLabel.setBounds(600, 80,  200, 20);
    	supplierPriceLabel.setFont(new Font("Lucida", Font.BOLD, 18));
        container.add(supplierPriceLabel);
		
		ArrayList<JLabel> itemNumbers = new ArrayList<JLabel>(100);
		ArrayList<JLabel> itemNames = new ArrayList<JLabel>(100);
		ArrayList<JLabel> itemQtys = new ArrayList<JLabel>(100);
		ArrayList<JLabel> retailPrices = new ArrayList<JLabel>(100);
		ArrayList<JLabel> supplierPrices = new ArrayList<JLabel>(100);
				
		for(int i = 0; i < aOrder.getItemList().size();i++)
		{
			itemNumbers.add(new JLabel(String.valueOf(aOrder.getItemList().get(i).getItemNum())));
			itemNumbers.get(i).setBounds(50, (i * 20) + 100,  600, 20);
	        container.add(itemNumbers.get(i));
			itemNames.add(new JLabel(aOrder.getItemList().get(i).getName()));
			itemNames.get(i).setBounds(200, (i * 20) + 100,  600, 20);
	        container.add(itemNames.get(i));
			itemQtys.add(new JLabel(String.valueOf(aOrder.getItemList().get(i).getQty())));
			itemQtys.get(i).setBounds(350, (i * 20) + 100,  600, 20);
	        container.add(itemQtys.get(i));
			retailPrices.add(new JLabel("$" + aOrder.getItemList().get(i).getRetailPrice()));
			retailPrices.get(i).setBounds(450, (i * 20) + 100,  600, 20);
	        container.add(retailPrices.get(i));
			supplierPrices.add(new JLabel("$" + aOrder.getItemList().get(i).getSupplierPrice()));
	    	supplierPrices.get(i).setBounds(600, (i * 20) + 100,  200, 20);
	        container.add(supplierPrices.get(i));
		}
		
		receieveableOrder = aOrder;
		switch(accountIdentifier)
		{
			case "S":				
				if(aOrder.getOrderStatus().equals("Delivered"))
				{
					container.add(recieveOrderButton);
				}
				break;
			case "WH":
				if(aOrder.getOrderStatus().equals("Delivered") && aOrder.getOrderIdentifier().equals("WH"))
				{
					container.add(recieveOrderButton);
				}
				else if(aOrder.getOrderStatus().equals("Awaiting Fulfillment") && aOrder.getOrderIdentifier().equals("S"))
				{
					container.add(fulfillOrderButton);
				}
				break;
			default:
				break;
		}
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
				for(int i = 0; i < session.company.getWarehouseList().size(); i++)
				{
					orderFromComboBox.addItem(session.company.getWarehouseList().get(i).getID());
				}
				break;
			case "WH":
				for(int i = 0; i < session.company.getSupplierList().size(); i++)
				{
					orderFromComboBox.addItem(session.company.getSupplierList().get(i).getName());
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
	Supplier aSupp;
			
	public void displayInventoryForOrder()
	{
		int yPos = 0;
		
		switch(accountIdentifier)
		{
		case "S":
			for(int i = 0; i < session.company.getWarehouseList().size(); i++)
			{
				if(session.company.getWarehouseList().get(i).getID().equals(orderFromComboBox.getSelectedItem())) 
				{
					aWH = session.company.getWarehouseList().get(i);
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
			for(int i = 0; i < session.company.getSupplierList().size(); i++)
			{
				if(session.company.getSupplierList().get(i).getName().equals(orderFromComboBox.getSelectedItem())) 
				{
					aSupp = session.company.getSupplierList().get(i);
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
	
	public void generateOrder()
	{
		ArrayList<Item> orderList = new ArrayList<Item>(orderQty.size());
		Order newOrder;
				
		switch(accountIdentifier)
		{
			case ("S"):
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
			        container.removeAll();
					newOrder = session.sessionAccount.createStoreOrder(aWH);
					newOrder.addItemsToOrder(orderList);
			        JOptionPane.showMessageDialog(this, "Order " + newOrder.getOrderID() + " Created");
			        clearArrays();
					populateArrays();
					setStoreOrderPage();
					addToContainer("OrderHome");	
				}
				break;
			case ("WH"):
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
					container.removeAll();
					newOrder = session.sessionAccount.createWarehouseOrder(aSupp);
					newOrder.addItemsToOrder(orderList);
			        JOptionPane.showMessageDialog(this, "Order " + newOrder.getOrderID() + " Created");
			        clearArrays();
					populateArrays();
					setWarehouseOrderPage();
					addToContainer("OrderHome");	
				}
				break;
			default:
				break;
		}
	}
	
	public void reciveveOrder(Order aOrder)
	{
		boolean itemDone = false;
		switch(accountIdentifier)
		{
			case"S":
				StoreOrder sOrder = (StoreOrder) aOrder;
				for(int i = 0; i < sOrder.getItemList().size(); i++)
				{
					itemDone = false;
					for(int j = 0; j < sOrder.getOrderedBy().getInventory().size(); j++)
					{
						if(sOrder.getItemList().get(i).getItemNum().equals(sOrder.getOrderedBy().getInventory().get(j).getItemNum()))
						{
							//Add Order Qty to Store Inventory Qty
							sOrder.getOrderedBy().getInventory().get(j).addQty(sOrder.getItemList().get(i).getQty());
							itemDone = true;
						}
						if(sOrder.getItemList().get(i).getItemNum().equals(sOrder.getfulfilledBy().getInventory().get(j).getItemNum()))
						{
							//Remove Order Qty from WH Inventory Qty
							sOrder.getfulfilledBy().getInventory().get(j).addQty(-sOrder.getItemList().get(i).getQty());
						}
					}
					//Add item New item to inventory
					if(!itemDone)
					{
						sOrder.getOrderedBy().addItemToInventory(sOrder.getItemList().get(i).cloneItem(sOrder.getItemList().get(i).getQty()));	
					}
				}
				break;
			case "WH":
				WarehouseOrder whOrder = (WarehouseOrder) aOrder;
				for(int i = 0; i < whOrder.getItemList().size(); i++)
				{
					itemDone = false;
					for(int j = 0; j < whOrder.getOrderedBy().getInventory().size(); j++)
					{
						if(whOrder.getItemList().get(i).getItemNum().equals(whOrder.getOrderedBy().getInventory().get(j).getItemNum()))
						{
							//Add Order Qty to Store Inventory Qty
							whOrder.getOrderedBy().getInventory().get(j).addQty(whOrder.getItemList().get(i).getQty());
							itemDone = true;
						}
						if(whOrder.getItemList().get(i).getItemNum().equals(whOrder.getfulfilledBy().getItemList().get(j).getItemNum()))
						{
							//Remove Order Qty from WH Inventory Qty
							whOrder.getfulfilledBy().getItemList().get(j).addQty(-whOrder.getItemList().get(i).getQty());
						}
					}
					//Add item New item to inventory
					if(!itemDone)
					{
						whOrder.getOrderedBy().addItemToInventory(whOrder.getItemList().get(i).cloneItem(whOrder.getItemList().get(i).getQty()));
					}
				}
				break;
			default:
				break;
		}
		aOrder.setOrderStatus("Complete");
        JOptionPane.showMessageDialog(this, "Order has been recieved.");
		setUniversalPageSettings();
	}
	
	public void handleFulfill(Order aOrder)
	{
		StoreOrder whOrder = (StoreOrder) aOrder;
		for(int i = 0; i < whOrder.getItemList().size(); i++)
		{
			for(int j = 0; j < whOrder.getfulfilledBy().getInventory().size(); j++)
			{
				if(whOrder.getItemList().get(i).getItemNum().equals(whOrder.getfulfilledBy().getInventory().get(j).getItemNum()))
				{
					//Remove Order Qty from WH Inventory Qty
					whOrder.getfulfilledBy().getInventory().get(j).addQty(-whOrder.getItemList().get(i).getQty());
				}
			}

		}
        aOrder.setOrderStatus("Awaiting Pickup");
        JOptionPane.showMessageDialog(this, "Order has been fulfilled.");
		setUniversalPageSettings();
	}
}
