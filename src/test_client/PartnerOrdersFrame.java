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

public class PartnerOrdersFrame extends JFrame implements ActionListener
{
    Container container = getContentPane();
    
    JLabel orderHomeTitleLabel;
    JLabel viewOrderTitleLabel;

    JLabel orderNumberlabel = new JLabel("Order Number");
    JLabel orderStatuslabel = new JLabel("Order Status");
         
    JButton goBackButton;

    ArrayList<JLabel> orders;
    ArrayList<JButton> viewOrderButtons;

	public PartnerOrdersFrame()
	{				
	    goBackButton = new JButton("Go Back");
	    goBackButton.setBounds(JButton.RIGHT, JButton.SOUTH, 80, 20);
	    goBackButton.addActionListener(this);
		
        setLayoutManager(); 
		setUniversalPageSettings();
    }
	
	public void populateArrays()
	{
		orders = new ArrayList<JLabel>(GUI.getSession().getSessionAccount().getPartner().getOrders().size());
		viewOrderButtons = new ArrayList<JButton>(GUI.getSession().getSessionAccount().getPartner().getOrders().size());
		
		for(int i = 0; i < GUI.getSession().getSessionAccount().getPartner().getOrders().size(); i++)
		{
			orders.add(new JLabel(GUI.getSession().getSessionAccount().getPartner().getOrders().get(i).toString()));
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
		orderHomeTitleLabel = new JLabel("Orders - " + GUI.getSession().getSessionAccount().getPartner().toString());
		orderHomeTitleLabel.setBounds(50, 40, 1000, 30);
		orderHomeTitleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		orderNumberlabel.setBounds(50, 150, 1000, 30);
		orderNumberlabel.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel.setBounds(200, 150, 1000, 30);
		orderStatuslabel.setFont(new Font("Lucida", Font.BOLD, 16));
		
		populateArrays();
		setSupplierOrderPage();
		addToContainer("OrderHome");
    }
	
	public void setSupplierOrderPage()
	{
		// Set bounds for labels
		for(int i = 0; i < GUI.getSession().getSessionAccount().getPartner().getOrders().size(); i++)
		{
			orders.add(new JLabel(GUI.getSession().getSessionAccount().getPartner().getOrders().get(i).getOrderID()));
			orders.get(i).setBounds(50, (i * 20) + 200,  600, 20);
			viewOrderButtons.add(new JButton("View"));
			viewOrderButtons.get(i).setBounds(400, (i * 20) + 200, 50, 20);
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

	
	public void addToContainer(String page)
	{
		switch(page) 
		{
		case "OrderHome":
			this.setSize(getPreferredSize());
	    	container.add(orderHomeTitleLabel);
			container.add(orderNumberlabel);
			container.add(orderStatuslabel);
			
			for(int i = 0; i < orders.size(); i++) {
				container.add(orders.get(i));
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
				container.removeAll();
				viewOrderPage(GUI.getSession().getSessionAccount().getPartner().getOrders().get(i));
				super.update(getGraphics());
			}	
		}			
		if(e.getSource() == goBackButton)
		{
			container.removeAll();
			addToContainer("OrderHome");
			super.update(getGraphics());
		}	
	}
}
