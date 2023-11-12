package test_client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener 
{
    Container container = getContentPane();
	
	JButton accountsButton = new JButton("Manage Accounts");
	JButton ordersButton = new JButton("Manage Orders");
	JButton reportsButton = new JButton("Reports");
	
	public HomeFrame()
	{
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
	
    public void setLayoutManager() 
    {
        container.setLayout(null);
    }
    
    public void setLocationAndSize() 
    {
        accountsButton.setBounds(200, 50, 200, 50);
        ordersButton.setBounds(600, 50, 200, 50);  
        reportsButton.setBounds(1000, 50, 200, 50);

    }

    public void addComponentsToContainer() 
    {
    	switch(GUI.session.getSessionAccessLevel())
    	{
    		case 10: // Admin Account
    			container.add(accountsButton);
    			break;
    		case 20: // Store/Warehouse Staff Account
    			
    			break;    			
    		case 30: // Driver Account
    			
    			break;
    		case 40: // Supplier Account
    			
    			break;
    		default:
    			
    			break;
    	}	
        container.add(ordersButton);
        container.add(reportsButton);
    }

    public void addActionEvent() 
    {
        ordersButton.addActionListener(this);
        reportsButton.addActionListener(this);
        accountsButton.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == ordersButton)
		{
            OrdersFrame ordersFrame = new OrdersFrame();
            ordersFrame.setTitle("Order Manager - Arizona Incorporated");
            ordersFrame.setVisible(true);
            ordersFrame.setBounds(100, 100, 1152, 720);
            ordersFrame.setDefaultCloseOperation(OrdersFrame.DISPOSE_ON_CLOSE);
            ordersFrame.setResizable(true);     
		}
		else if(e.getSource() == reportsButton)
		{
            ReportsFrame reportsFrame = new ReportsFrame();
            reportsFrame.setTitle("Report Manager - Arizona Incorporated");
            reportsFrame.setVisible(true);
            reportsFrame.setBounds(100, 100, 370, 600);
            reportsFrame.setDefaultCloseOperation(ReportsFrame.DISPOSE_ON_CLOSE);
            reportsFrame.setResizable(true);   
		}
		else if(e.getSource() == accountsButton)
		{
            ReportsFrame reportsFrame = new ReportsFrame();
            reportsFrame.setTitle("Account Manager - Arizona Incorporated");
            reportsFrame.setVisible(true);
            reportsFrame.setBounds(100, 100, 1152, 720);
            reportsFrame.setDefaultCloseOperation(AccountsFrame.DISPOSE_ON_CLOSE);
            reportsFrame.setResizable(true);  
			
		}
	}
}
