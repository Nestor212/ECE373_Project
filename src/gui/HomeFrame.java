package gui;

import javax.swing.*;
import software.Company;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* Class Description
 * Home Frame is main window for the user. 
 * This window tailors the layout and functions based on the account type,
 * which is defined by the session.account access level. 
 * 
 * Main buttons displayed instantiate a new Frame found in the GUI package, 
 * that pertains to button description. (ex. 'Reports' button instantiates 'ReportsFrame.java')
 * 
 * If home frame is exited or closed the entire system is saved and closed. 
 * 
 * All other frames windows will be discarded when exited, and home Frame will remain.  
 */

public class HomeFrame extends JFrame implements ActionListener 
{ 
	private static final long serialVersionUID = 9195283884703151569L;
	SupplyManagerGUI session;
		
	Container container = getContentPane();
	
	JLabel imageUofA = new JLabel(new ImageIcon("supplyChain120.jpg"));
	
	private JMenuBar menuBar;		//the horizontal container
	
	//File Menu Declarations
	private JMenu fileMenu;
	private JMenuItem fileSave;
	private JMenuItem fileLogout;
	private JMenuItem fileExit;
    
	JButton accountsButton;
	JButton departmentsButton;
	JButton partnersButton;
	JButton ordersButton;
	JButton ordersButton1;
	JButton reportsButton;
	JButton departmentInventoryButton;
	JButton supplyInventoryButton;
	
	JLabel homeTitle;
	
	Dimension defaultSize = new Dimension(1152, 720);
	
	public HomeFrame(SupplyManagerGUI aSession)
	{
		// Listener for File Exits (red x button), to confirm action and save data.
		this.addWindowListener(new WindowAdapter()  
	   {
			@Override
			public void windowClosing(WindowEvent event) 
			{
				confirmAndExit();
			}
	   });

    	session = aSession;
		
		menuBar = new JMenuBar();
		//***** File Menu + Drop-down Options ****\\
		fileMenu = new JMenu("File");
		fileSave = new JMenuItem("Save");
		fileLogout = new JMenuItem("Logout");
		fileExit = new JMenuItem("Exit");
		// Add listeners to file menu options
		fileSave.addActionListener(this);
		fileLogout.addActionListener(this);
		fileExit.addActionListener(this);
		// Add options to file menu
		fileMenu.add(fileSave);
		fileMenu.add(fileLogout);
		fileMenu.add(fileExit);		
		
	    menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
    	switch(session.sessionAccount.getAccessLevel())
    	{
    		case 10: // Admin Account
    			homeTitle = new JLabel(session.sessionAccount.getDepartment().toString());
    			accountsButton = new JButton("Manage Accounts");
    			departmentsButton = new JButton("Manage Departments");
    			partnersButton = new JButton("Manage Partners");
    	    	ordersButton = new JButton("Manage Orders");
    	    	reportsButton = new JButton("Reports");
    			setAdminHomepage();
    			break;
    		case 20: // Store/Warehouse Staff Account
    			homeTitle = new JLabel(session.sessionAccount.getDepartment().toString());
    			departmentInventoryButton = new JButton("Manage Inventory");
    	    	ordersButton = new JButton("Manage Orders");
    	    	reportsButton = new JButton("Reports");
    			setInventoryStaffHomepage();
    			break;    			
    		case 30: // Driver Account
    			homeTitle = new JLabel(session.sessionAccount.getPartner().toString());
    	    	ordersButton1 = new JButton("Manage Orders");
    	    	reportsButton = new JButton("Reports");
    			setDriverHomepage();
    			break;
    		case 40: // Supplier Account
    			homeTitle = new JLabel(session.sessionAccount.getPartner().toString());
    			supplyInventoryButton = new JButton("Manage Inventory");
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
		
        homeTitle.setSize(1344, 50);
        homeTitle.setFont(new Font("Lucida", Font.BOLD, 30));
        homeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        homeTitle.setVerticalAlignment(SwingConstants.TOP);
		container.add(homeTitle);
		
    	imageUofA.setBounds(1222, 2, 120, 62);
    	container.add(imageUofA);
    }
    
    public void setAdminHomepage()
    {
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
    	// Set item bounds
    	departmentInventoryButton.setBounds(300, 100, 200, 50);  
    	ordersButton.setBounds(600, 100, 200, 50); 
        reportsButton.setBounds(900, 100, 200, 50);
        
        // Add items to container
		container.add(ordersButton);
		container.add(reportsButton);
		container.add(departmentInventoryButton);
        
        // Add action events to relevant items
        ordersButton.addActionListener(this);
        reportsButton.addActionListener(this);
        departmentInventoryButton.addActionListener(this);
    }
    
    public void setDriverHomepage()
    {
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
    	// Set item bounds
    	supplyInventoryButton.setBounds(300, 100, 200, 50);  
        ordersButton1.setBounds(600, 100, 200, 50);  
        reportsButton.setBounds(900, 100, 200, 50);
        
        // Add items to container
		container.add(supplyInventoryButton);
        container.add(ordersButton1);
        container.add(reportsButton);
        
        // Add action events to relevant items
        supplyInventoryButton.addActionListener(this);
        ordersButton1.addActionListener(this);
        reportsButton.addActionListener(this);
    }
     
   	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(fileSave))
		{
			handleFileSave();	
		}
		else if(e.getSource().equals(fileExit))
		{
			handleFileExit();
		}
		else if(e.getSource() == ordersButton)
		{
            DepartmentOrdersFrame ordersFrame = new DepartmentOrdersFrame(session);
            ordersFrame.setTitle("Orders Manager - Arizona Incorporated");
            ordersFrame.setVisible(true);
            ordersFrame.setBounds(200, 100, 1152, 720);
            ordersFrame.setPreferredSize(defaultSize);
            ordersFrame.setDefaultCloseOperation(DepartmentOrdersFrame.DISPOSE_ON_CLOSE);
            ordersFrame.setResizable(true);     
		}
		else if(e.getSource() == ordersButton1)
		{
			PartnerOrdersFrame ordersFrame = new PartnerOrdersFrame(session);
            ordersFrame.setTitle("Orders Manager - Arizona Incorporated");
            ordersFrame.setVisible(true);
            ordersFrame.setBounds(200, 100, 1152, 720);
            ordersFrame.setPreferredSize(defaultSize);
            ordersFrame.setDefaultCloseOperation(DepartmentOrdersFrame.DISPOSE_ON_CLOSE);
            ordersFrame.setResizable(true);     
		}
		else if(e.getSource() == reportsButton)
		{
            ReportsFrame reportsFrame = new ReportsFrame(session);
            reportsFrame.setTitle("Reports Manager - Arizona Incorporated");
            reportsFrame.setVisible(true);
            reportsFrame.setBounds(200, 100, 400, 600);
            reportsFrame.setDefaultCloseOperation(ReportsFrame.DISPOSE_ON_CLOSE);
            reportsFrame.setResizable(true);   
		}
		else if(e.getSource() == accountsButton)
		{
            AccountsFrame accountsFrame = new AccountsFrame(session);
            accountsFrame.setTitle("Accounts Manager - Arizona Incorporated");
            accountsFrame.setVisible(true);
            accountsFrame.setBounds(200, 100, 900, 720);
            accountsFrame.setDefaultCloseOperation(AccountsFrame.DISPOSE_ON_CLOSE);
            accountsFrame.setResizable(true);  
		}
		else if(e.getSource() == departmentsButton)
		{
            DepartmentsFrame departmentsFrame = new DepartmentsFrame(session);
            departmentsFrame.setTitle("Departments Manager - Arizona Incorporated");
            departmentsFrame.setVisible(true);
            departmentsFrame.setBounds(200, 100, 1152, 600);
            departmentsFrame.setDefaultCloseOperation(DepartmentsFrame.DISPOSE_ON_CLOSE);
            departmentsFrame.setResizable(true);   
		}
		else if(e.getSource() == partnersButton)
		{
            PartnersFrame partnersFrame = new PartnersFrame(session);
            partnersFrame.setTitle("Partners Manager - Arizona Incorporated");
            partnersFrame.setVisible(true);
            partnersFrame.setBounds(200, 100, 1152, 720);
            partnersFrame.setDefaultCloseOperation(PartnersFrame.DISPOSE_ON_CLOSE);
            partnersFrame.setResizable(true);  
		}
		else if(e.getSource() == departmentInventoryButton)
		{
            DepartmentInventoryFrame inventoryFrame = new DepartmentInventoryFrame(session);
            inventoryFrame.setTitle("Inventory Manager - Arizona Incorporated");
            inventoryFrame.setVisible(true);
            inventoryFrame.setBounds(200, 100, 1152, 720);
            inventoryFrame.setDefaultCloseOperation(DepartmentInventoryFrame.DISPOSE_ON_CLOSE);
            inventoryFrame.setResizable(true);  

		}
		else if(e.getSource() == supplyInventoryButton)
		{
			SupplyInventoryFrame inventoryFrame = new SupplyInventoryFrame(session);
            inventoryFrame.setTitle("Inventory Manager - Arizona Incorporated");
            inventoryFrame.setVisible(true);
            inventoryFrame.setBounds(200, 100, 1152, 720);
            inventoryFrame.setDefaultCloseOperation(DepartmentInventoryFrame.DISPOSE_ON_CLOSE);
            inventoryFrame.setResizable(true);  

		}
		else if(e.getSource() == fileLogout)
		{
            int result = JOptionPane.showConfirmDialog(this,"Are you sure you want to logout?", "Logout",
                    								   JOptionPane.YES_NO_OPTION,
                    								   JOptionPane.QUESTION_MESSAGE);
	        if(result == JOptionPane.YES_OPTION)
	        {
                JOptionPane.showMessageDialog(this, "Logout Successful");
                this.dispose();
                
		        LoginFrame loginFrame = new LoginFrame(session);
		        loginFrame.setTitle("Login - Arizona Incorporated");
		        loginFrame.setVisible(true);
		        loginFrame.setBounds(600, 200, 370, 500);
		        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        loginFrame.setResizable(false);
	        }
	        else if (result == JOptionPane.NO_OPTION)
	        {
	        }
		}
	}
   	
  //************************* File Menu Handlers ************************\\
	
	// Save the present sate of the company employee object
	private void handleFileSave()
	{
		if(session.company !=null)
		{
			Company.saveData(session.company);
		}
		else
		{
			JOptionPane.showMessageDialog(null, 
					"No Company", 
					"Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Exit the session close the GUI
	private void handleFileExit()
	{	
		confirmAndExit();
	}
	
	  void confirmAndExit() 
	  {
		    if (JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Please confirm",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
		    {
		    	handleFileSave();
		    	System.exit(0);
		    }
	  }
}
