package test_client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener 
{
    Container container = getContentPane();
	
	JButton accountsButton;
	JButton departmentsButton;
	JButton partnersButton;
	JButton ordersButton;
	JButton ordersButton1;
	JButton reportsButton;
	JButton inventoryButton;
	JButton logoutButton;
	
	JLabel homeTitle;
	
	Dimension defaultSize = new Dimension(1152, 720);
	
	public HomeFrame()
	{
		logoutButton = new JButton("Logout");
		
    	switch(GUI.session.getSessionAccessLevel())
    	{
    		case 10: // Admin Account
    			homeTitle = new JLabel(GUI.getSession().getSessionAccount().getDepartment().toString());
    			accountsButton = new JButton("Manage Accounts");
    			departmentsButton = new JButton("Manage Departments");
    			partnersButton = new JButton("Manage Partners");
    	    	ordersButton = new JButton("Manage Orders");
    	    	reportsButton = new JButton("Reports");
    			setAdminHomepage();
    			break;
    		case 20: // Store/Warehouse Staff Account
    			homeTitle = new JLabel(GUI.getSession().getSessionAccount().getDepartment().toString());
    			inventoryButton = new JButton("Manage Inventory");
    	    	ordersButton = new JButton("Manage Orders");
    	    	reportsButton = new JButton("Reports");
    			setInventoryStaffHomepage();
    			break;    			
    		case 30: // Driver Account
    			homeTitle = new JLabel(GUI.getSession().getSessionAccount().getPartner().toString());
    	    	ordersButton1 = new JButton("Manage Orders");
    	    	reportsButton = new JButton("Reports");
    			setDriverHomepage();
    			break;
    		case 40: // Supplier Account
    			homeTitle = new JLabel(GUI.getSession().getSessionAccount().getPartner().toString());
    	    	ordersButton1 = new JButton("Manage Orders");
    	    	reportsButton = new JButton("Reports");
    			setSupplierHomepage();
    			break;
    		default:
    			break;
    	}
		setLayoutManager();
    }
	
    public void setLayoutManager() 
    {
        container.setLayout(null);
        
        logoutButton.setBounds(SwingConstants.RIGHT,SwingConstants.TOP, 100, 25);
        container.add(logoutButton);
        logoutButton.addActionListener(this);
		
        homeTitle.setSize(1344, 50);
        homeTitle.setFont(new Font("Lucida", Font.BOLD, 30));
        homeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        homeTitle.setVerticalAlignment(SwingConstants.TOP);
		container.add(homeTitle);
    }
    
    public void setAdminHomepage()
    {
    	JOptionPane.showMessageDialog(this, "TODO: Dislpay Action items needing admin attention.");

    	// Set item bounds
        accountsButton.setBounds(100, 100, 200, 50);
        departmentsButton.setBounds(330, 100, 200, 50);
        partnersButton.setBounds(560, 100, 200, 50);
        ordersButton.setBounds(790, 100, 200, 50);  
        reportsButton.setBounds(1020, 100, 200, 50);
        
        // Add items to container
		container.add(accountsButton);
		container.add(departmentsButton);
		container.add(partnersButton);
        container.add(ordersButton);
        container.add(reportsButton);
        
        // Add action events to relevant items
        ordersButton.addActionListener(this);
        reportsButton.addActionListener(this);
        accountsButton.addActionListener(this);
        departmentsButton.addActionListener(this);
        partnersButton.addActionListener(this);    
    }
    
    public void setInventoryStaffHomepage()
    {
    	JOptionPane.showMessageDialog(this, "TODO: List all orders active orders and status for location.");

    	// Set item bounds
    	inventoryButton.setBounds(300, 100, 200, 50);  
    	ordersButton.setBounds(600, 100, 200, 50); 
        reportsButton.setBounds(900, 100, 200, 50);
        
        // Add items to container
		container.add(ordersButton);
		container.add(reportsButton);
		container.add(inventoryButton);
        
        // Add action events to relevant items
        ordersButton.addActionListener(this);
        reportsButton.addActionListener(this);
        inventoryButton.addActionListener(this);
    }
    
    public void setDriverHomepage()
    {
    	JOptionPane.showMessageDialog(this, "TODO: List all orders active orders and location status.");

    	// Set item bounds
        ordersButton1.setBounds(350, 100, 200, 50);  
        reportsButton.setBounds(800, 100, 200, 50);
        
        // Add items to container
        container.add(ordersButton1);
        container.add(reportsButton);
        
        // Add action events to relevant items
        ordersButton1.addActionListener(this);
        reportsButton.addActionListener(this);   
    }
    
    public void setSupplierHomepage()
    {
    	JOptionPane.showMessageDialog(this, "TODO: List all orders pending fulfillment from this supplier.");

    	// Set item bounds
        ordersButton1.setBounds(350, 100, 200, 50);  
        reportsButton.setBounds(800, 100, 200, 50);
        
        // Add items to container
        container.add(ordersButton1);
        container.add(reportsButton);
        
        // Add action events to relevant items
        ordersButton1.addActionListener(this);
        reportsButton.addActionListener(this);
    }
     
   	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == ordersButton)
		{
            DepartmentOrdersFrame ordersFrame = new DepartmentOrdersFrame();
            ordersFrame.setTitle("Orders Manager - Arizona Incorporated");
            ordersFrame.setVisible(true);
            ordersFrame.setBounds(200, 100, 1152, 720);
            ordersFrame.setPreferredSize(defaultSize);
            ordersFrame.setDefaultCloseOperation(DepartmentOrdersFrame.DISPOSE_ON_CLOSE);
            ordersFrame.setResizable(true);     
		}
		if(e.getSource() == ordersButton1)
		{
			PartnerOrdersFrame ordersFrame = new PartnerOrdersFrame();
            ordersFrame.setTitle("Orders Manager - Arizona Incorporated");
            ordersFrame.setVisible(true);
            ordersFrame.setBounds(200, 100, 1152, 720);
            ordersFrame.setPreferredSize(defaultSize);
            ordersFrame.setDefaultCloseOperation(DepartmentOrdersFrame.DISPOSE_ON_CLOSE);
            ordersFrame.setResizable(true);     
		}
		else if(e.getSource() == reportsButton)
		{
            ReportsFrame reportsFrame = new ReportsFrame();
            reportsFrame.setTitle("Reports Manager - Arizona Incorporated");
            reportsFrame.setVisible(true);
            reportsFrame.setBounds(200, 100, 370, 600);
            reportsFrame.setDefaultCloseOperation(ReportsFrame.DISPOSE_ON_CLOSE);
            reportsFrame.setResizable(true);   
		}
		else if(e.getSource() == accountsButton)
		{
            AccountsFrame accountsFrame = new AccountsFrame();
            accountsFrame.setTitle("Accounts Manager - Arizona Incorporated");
            accountsFrame.setVisible(true);
            accountsFrame.setBounds(200, 100, 1152, 720);
            accountsFrame.setDefaultCloseOperation(AccountsFrame.DISPOSE_ON_CLOSE);
            accountsFrame.setResizable(true);  
		}
		else if(e.getSource() == departmentsButton)
		{
            DepartmentsFrame departmentsFrame = new DepartmentsFrame();
            departmentsFrame.setTitle("Departments Manager - Arizona Incorporated");
            departmentsFrame.setVisible(true);
            departmentsFrame.setBounds(200, 100, 1152, 600);
            departmentsFrame.setDefaultCloseOperation(DepartmentsFrame.DISPOSE_ON_CLOSE);
            departmentsFrame.setResizable(true);   
		}
		else if(e.getSource() == partnersButton)
		{
            PartnersFrame partnersFrame = new PartnersFrame();
            partnersFrame.setTitle("Partners Manager - Arizona Incorporated");
            partnersFrame.setVisible(true);
            partnersFrame.setBounds(200, 100, 1152, 720);
            partnersFrame.setDefaultCloseOperation(PartnersFrame.DISPOSE_ON_CLOSE);
            partnersFrame.setResizable(true);  
		}
		else if(e.getSource() == inventoryButton)
		{
            InventoryFrame inventoryFrame = new InventoryFrame();
            inventoryFrame.setTitle("Inventory Manager - Arizona Incorporated");
            inventoryFrame.setVisible(true);
            inventoryFrame.setBounds(200, 100, 1152, 720);
            inventoryFrame.setDefaultCloseOperation(InventoryFrame.DISPOSE_ON_CLOSE);
            inventoryFrame.setResizable(true);  

		}
		else if(e.getSource() == logoutButton)
		{
            int result = JOptionPane.showConfirmDialog(this,"Are you sure you want to logout?", "Logout",
                    								   JOptionPane.YES_NO_OPTION,
                    								   JOptionPane.QUESTION_MESSAGE);
	        if(result == JOptionPane.YES_OPTION)
	        {
                JOptionPane.showMessageDialog(this, "Logout Successful");
                this.dispose();
                
		        LoginFrame loginFrame = new LoginFrame();
		        loginFrame.setTitle("Login - Arizona Incorporated");
		        loginFrame.setVisible(true);
		        loginFrame.setBounds(600, 200, 370, 600);
		        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        loginFrame.setResizable(false);
	        }
	        else if (result == JOptionPane.NO_OPTION)
	        {
	        }
		}
	}
}
