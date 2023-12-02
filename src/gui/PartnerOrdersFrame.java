package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import software.Order;

public class PartnerOrdersFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 8650183100780228577L;
	SupplyManagerGUI session;
	
    Container container = getContentPane();
    JLabel orderHomeTitleLabel;
    JLabel viewOrderTitleLabel;

    JLabel orderNumberlabel = new JLabel("Order Number");
    JLabel orderStatuslabel = new JLabel("Order Status");
    JLabel pickupFromLabel = new JLabel("Pickup From");
    JLabel deliverToLabel = new JLabel("Deliver To");
         
    JButton goBackButton;

    ArrayList<Order> orders;
    ArrayList<JLabel> ordeNumLabels;
    ArrayList<JLabel> orderStatusLabels;  
    ArrayList<JLabel> pickupFromLabels;    
    ArrayList<JLabel> deliverToLabels;    
    ArrayList<JButton> viewOrderButtons;

	public PartnerOrdersFrame(SupplyManagerGUI aSession)
	{				
		session = aSession;
		
	    goBackButton = new JButton("Go Back");
	    goBackButton.setBounds(JButton.RIGHT, JButton.SOUTH, 80, 20);
	    goBackButton.addActionListener(this);
		
        setLayoutManager(); 
	    populateArrays();
		setUniversalPageSettings();
    }
	
	public void clearArrays()
	{
		orders.clear();
		ordeNumLabels.clear();
		orderStatusLabels.clear();
		viewOrderButtons.clear();
	}
	
	public void populateArrays()
	{
		if(session.sessionAccount.getAccessLevel().equals(30)) // Transport Account
		{
			orders = new ArrayList<Order>();
			ordeNumLabels = new ArrayList<JLabel>();
			orderStatusLabels = new ArrayList<JLabel>();
			pickupFromLabels = new ArrayList<JLabel>();
			deliverToLabels = new ArrayList<JLabel>();
			viewOrderButtons = new ArrayList<JButton>();
			
			for(int i = 0; i < session.company.getWarehouseList().size(); i++)
			{
				for(int j = 0; j < session.company.getWarehouseList().get(i).getOrders().size(); j++)
				{
					if(((session.company.getWarehouseList().get(i).getOrders().get(j).getDeliveredBy() == null) && 
					    (session.company.getWarehouseList().get(i).getOrders().get(j).getOrderStatus().equals("Awaiting Pickup"))) ||
					   ((session.company.getWarehouseList().get(i).getOrders().get(j).getDeliveredBy() == session.sessionAccount.getPartner()) && 
						(session.company.getWarehouseList().get(i).getOrders().get(j).getOrderStatus().equals("Delivery in progress"))))	
					{
						orders.add(session.company.getWarehouseList().get(i).getOrders().get(j));
						ordeNumLabels.add(new JLabel(session.company.getWarehouseList().get(i).getOrders().get(j).getOrderID()));
						orderStatusLabels.add(new JLabel(session.company.getWarehouseList().get(i).getOrders().get(j).getOrderStatus()));
						pickupFromLabels.add(new JLabel(session.company.getWarehouseList().get(i).getOrders().get(j).getPickupAddressString()));
						deliverToLabels.add(new JLabel(session.company.getWarehouseList().get(i).getOrders().get(j).getDeliveryAddressString()));
						viewOrderButtons.add(new JButton("View"));
					}
				}
			}
		}
		else if(session.sessionAccount.getAccessLevel().equals(40)) // Supplier Account
		{
			orders = new ArrayList<Order>(session.sessionAccount.getPartner().getOrders().size());
			ordeNumLabels = new ArrayList<JLabel>(session.sessionAccount.getPartner().getOrders().size());
			orderStatusLabels = new ArrayList<JLabel>(session.sessionAccount.getPartner().getOrders().size());
			viewOrderButtons = new ArrayList<JButton>(session.sessionAccount.getPartner().getOrders().size());
			
			for(int i = 0; i < session.sessionAccount.getPartner().getOrders().size(); i++)
			{
				orders.add(session.sessionAccount.getPartner().getOrders().get(i));
				ordeNumLabels.add(new JLabel(session.sessionAccount.getPartner().getOrders().get(i).getOrderID()));
				orderStatusLabels.add(new JLabel(session.sessionAccount.getPartner().getOrders().get(i).getOrderStatus()));
				viewOrderButtons.add(new JButton("View"));
			}	
		}
	}
		

    public void setLayoutManager() 
    {
        container.setLayout(null);
    }
    
    public void setUniversalPageSettings()
    {
		orderHomeTitleLabel = new JLabel("Orders - " + session.sessionAccount.getPartner().toString());
		orderHomeTitleLabel.setBounds(50, 40, 1000, 30);
		orderHomeTitleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
			
		if(session.sessionAccount.getAccessLevel().equals(30)) // Transport Account
		{
			setTransportOrderPage();
		}
		else if(session.sessionAccount.getAccessLevel().equals(40)) // Transport Account
		{
			setSupplierOrderPage();
		}
    }
	
	public void setSupplierOrderPage()
	{
		orderNumberlabel.setBounds(50, 150, 1000, 30);
		orderNumberlabel.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel.setBounds(200, 150, 1000, 30);
		orderStatuslabel.setFont(new Font("Lucida", Font.BOLD, 16));
		
		// Set bounds for labels
		for(int i = 0; i < orders.size(); i++)
		{
			ordeNumLabels.get(i).setBounds(50, (i * 20) + 200,  600, 20);
			orderStatusLabels.get(i).setBounds(200, (i * 20) + 200,  600, 20);
			viewOrderButtons.get(i).setBounds(400, (i * 20) + 200, 50, 20);
			viewOrderButtons.get(i).addActionListener(this);
		}
		addToContainer("SupplierOrders");
	}
	
	public void setTransportOrderPage()
	{	
		orderNumberlabel.setBounds(50, 150, 1000, 30);
		orderNumberlabel.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel.setBounds(200, 150, 1000, 30);
		orderStatuslabel.setFont(new Font("Lucida", Font.BOLD, 16));
		
		pickupFromLabel.setBounds(400, 150, 1000, 30);
		pickupFromLabel.setFont(new Font("Lucida", Font.BOLD, 16));

		deliverToLabel.setBounds(600, 150, 1000, 30);
		deliverToLabel.setFont(new Font("Lucida", Font.BOLD, 16));
		
		// Set bounds for labels
		for(int i = 0; i < orders.size(); i++)
		{
			ordeNumLabels.get(i).setBounds(50, (i * 20) + 200,  600, 20);
			orderStatusLabels.get(i).setBounds(200, (i * 20) + 200,  600, 20);
			pickupFromLabels.get(i).setBounds(400, (i * 20) + 200,  600, 20);
			deliverToLabels.get(i).setBounds(600, (i * 20) + 200,  600, 20);
			viewOrderButtons.get(i).setBounds(900, (i * 20) + 200, 50, 20);
			viewOrderButtons.get(i).addActionListener(this);
		}
		addToContainer("TransportOrders");
	}
		
	public void addToContainer(String page)
	{
		container.removeAll();
		this.setSize(getPreferredSize());
		switch(page) 
		{
			case "SupplierOrders":
		    	container.add(orderHomeTitleLabel);
				container.add(orderNumberlabel);
				container.add(orderStatuslabel);
				
				for(int i = 0; i < orders.size(); i++) {
					container.add(ordeNumLabels.get(i));
					container.add(orderStatusLabels.get(i));
					container.add(viewOrderButtons.get(i));
				}
				break;
			case "TransportOrders":
		    	container.add(orderHomeTitleLabel);
				container.add(orderNumberlabel);
				container.add(orderStatuslabel);
		    	container.add(pickupFromLabel);
				container.add(deliverToLabel);
				for(int i = 0; i < orders.size(); i++) 
				{
					container.add(ordeNumLabels.get(i));
					container.add(orderStatusLabels.get(i));
					container.add(pickupFromLabels.get(i));
					container.add(deliverToLabels.get(i));					
					container.add(viewOrderButtons.get(i));
				}
				
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
				viewOrderPage(orders.get(i));
			}	
		}			
		if(e.getSource() == goBackButton)
		{
			clearArrays();
		    populateArrays();
			setUniversalPageSettings();
		}	
	}
	
	public void viewOrderPage(Order aOrder)
	{	
		container.removeAll();
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
		super.update(getGraphics());

	}
}
