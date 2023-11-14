package test_client;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import software.Order;

public class OrdersFrame extends JFrame implements ActionListener
{
    Container container = getContentPane();
    
    JLabel titleLabel = new JLabel("Orders - " + GUI.getSession().getSessionAccount().getDepartment().toString());
    
    JLabel orderNumberlabel = new JLabel("Order Number");
    JLabel orderStatuslabel = new JLabel("Order Status");
    JLabel orderNumberlabel1 = new JLabel("Order Number");
    JLabel orderStatuslabel1 = new JLabel("Order Status");
    
    JButton viewOrderButton = new JButton("View");

    
    JLabel storeOrderLabel;
    JLabel whOrderLabel;

    
    ArrayList<JLabel> orders;
    ArrayList<JLabel> whOrders;

	
	public OrdersFrame()
	{
		orders = new ArrayList<JLabel>(50);
		
		populateOrderArray();
        setLayoutManager(); 
	
		switch(GUI.getSession().getSessionAccount().getDepartment().getIdentifier())
		{
			case "S":
				storeOrderLabel = new JLabel("From Store to Warehouse");
				setStoreOrderPage();
				break;
			case "WH":
				storeOrderLabel = new JLabel("From Store to Warehouse");
				whOrderLabel = new JLabel("From Warehouse to Supplier");
				setWarehouseorderPage();
				break;
			default:
				break;
		}
		setUniversalPageSettings();
    }
	

	
	public void populateOrderArray()
	{
		for(int i = 0; i < GUI.getSession().getSessionAccount().getDepartment().getOrders().size(); i++)
		{
			orders.add(new JLabel(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(i).toString()));
		}
	}
	
    public void setLayoutManager() 
    {
        container.setLayout(null);
    }
    
    public void setUniversalPageSettings()
    {
    	titleLabel.setBounds(50, 20, 1000, 30);
    	titleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
    	container.add(titleLabel);
    	
		storeOrderLabel.setBounds(50, 100, 1000, 30);
		storeOrderLabel.setFont(new Font("Lucida", Font.BOLD, 22));
		
		orderNumberlabel.setBounds(50, 150, 1000, 30);
		orderNumberlabel.setFont(new Font("Lucida", Font.BOLD, 16));

		orderStatuslabel.setBounds(200, 150, 1000, 30);
		orderStatuslabel.setFont(new Font("Lucida", Font.BOLD, 16));
		
		container.add(storeOrderLabel);
		container.add(orderNumberlabel);
		container.add(orderStatuslabel);
    }
    
	public void setStoreOrderPage()
	{	
		// Set bounds for order labels
		for(int i = 0; i < GUI.getSession().getSessionAccount().getDepartment().getOrders().size(); i++)
		{
			orders.get(i).setBounds(50, (i * 20) + 200,  600, 20);
			viewOrderButton.setBounds(400, (i * 20) + 200, 50, 20);
		}
		
		//add labels to container
		container.add(viewOrderButton);
		for(int i = 0; i < GUI.getSession().getSessionAccount().getDepartment().getOrders().size(); i++)
		{
			container.add(orders.get(i));
		}
		
		viewOrderButton.addActionListener(this);
	}
	
	public void setWarehouseorderPage()
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
				count++;
			}
			if(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(i).getOrderIdentifier().equals("WH"))
			{
				orders.get(i).setBounds(550, ((i - count) * 20) + 200,  600, 20);
			}
		}

		//add labels to container
		container.add(orderNumberlabel1);
		container.add(orderStatuslabel1);
		container.add(whOrderLabel);
		for(int i = 0; i < GUI.getSession().getSessionAccount().getDepartment().getOrders().size(); i++)
		{
			container.add(orders.get(i));
		}
	}
	
	public void viewOrder(Order aOrder)
	{
		container.removeAll();
		
		
		ArrayList<JLabel> orderItems = new ArrayList<JLabel>(aOrder.getItemList().size());
		
		for(int i = 0; i < aOrder.getItemList().size();i++)
		{
			System.out.println(aOrder.getItemList().get(i).toString());
			orderItems.add(new JLabel(aOrder.getItemList().get(i).toString()));
		}
		
		for(int i = 0; i < orderItems.size();i++)
		{
			orderItems.get(i).setBounds(50, (i * 20) + 50,  600, 20);
			container.add(orderItems.get(i));
		}
		
		container.update(getGraphics());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewOrderButton)
		{
			viewOrder(GUI.getSession().getSessionAccount().getDepartment().getOrders().get(0));
		}		
	}
}
