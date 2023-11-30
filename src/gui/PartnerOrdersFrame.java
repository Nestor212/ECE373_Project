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
         
    JButton goBackButton;

    ArrayList<JLabel> orders;
    ArrayList<JButton> viewOrderButtons;

	public PartnerOrdersFrame(SupplyManagerGUI aSession)
	{				
		session = aSession;
		
	    goBackButton = new JButton("Go Back");
	    goBackButton.setBounds(JButton.RIGHT, JButton.SOUTH, 80, 20);
	    goBackButton.addActionListener(this);
		
        setLayoutManager(); 
		setUniversalPageSettings();
    }
	
	public void populateArrays()
	{
		orders = new ArrayList<JLabel>(session.sessionAccount.getPartner().getOrders().size());
		viewOrderButtons = new ArrayList<JButton>(session.sessionAccount.getPartner().getOrders().size());
		
		for(int i = 0; i < session.sessionAccount.getPartner().getOrders().size(); i++)
		{
			orders.add(new JLabel(session.sessionAccount.getPartner().getOrders().get(i).toString()));
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
		orderHomeTitleLabel = new JLabel("Orders - " + session.sessionAccount.getPartner().toString());
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
		for(int i = 0; i < session.sessionAccount.getPartner().getOrders().size(); i++)
		{
			orders.add(new JLabel(session.sessionAccount.getPartner().getOrders().get(i).getOrderID()));
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
				viewOrderPage(session.sessionAccount.getPartner().getOrders().get(i));
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
