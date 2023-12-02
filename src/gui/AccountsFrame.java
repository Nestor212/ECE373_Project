package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import users.Admin;
import users.InventoryStaff;
import users.SupplierStaff;
import users.TransportStaff;


public class AccountsFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 2474290467540176620L;
	SupplyManagerGUI session;
	
    private ArrayList<JLabel> accountNumbers;
    private ArrayList<JLabel> accountNames;
    private ArrayList<JLabel> accountLocations;
    private ArrayList<JButton> editButton;
        
    JLabel accountLabel = new JLabel("Account Number");
    JLabel nameLabel = new JLabel("Name");
    JLabel locationLabel = new JLabel("Location");
    
    Container container = getContentPane();
    
	private JMenuBar menuBar;		//the horizontal container
	
	//File Menu Declarations
	private JMenu fileMenu;
	private JMenu addOptionsMenu;
	private JMenuItem addSupplierAccount;
	private JMenuItem addTransportAccount;
	private JMenuItem addStoreAccount;
	private JMenuItem addWarehouseAccount;
	private JMenuItem addAdminAccount;
 
	public AccountsFrame(SupplyManagerGUI aSession)
	{
		session = aSession;
        accountNumbers = new ArrayList<JLabel>(20);
        accountNames = new ArrayList<JLabel>(20);
        accountLocations = new ArrayList<JLabel>(20);
        editButton = new ArrayList<JButton>();
        
		menuBar = new JMenuBar();
		//***** File Menu + Drop-down Options ****\\
		fileMenu = new JMenu("File");
		addOptionsMenu = new JMenu("Create Account");
		addSupplierAccount = new JMenuItem("Supplier Account");
		addTransportAccount = new JMenuItem("Transport Account");
		addStoreAccount = new JMenuItem("Store Account");
		addWarehouseAccount = new JMenuItem("Warehouse Account");
		addAdminAccount = new JMenuItem("Admin Account");
		
		addSupplierAccount.addActionListener(this);
		addTransportAccount.addActionListener(this);
		addStoreAccount.addActionListener(this);
		addWarehouseAccount.addActionListener(this);
		addAdminAccount.addActionListener(this);

		addOptionsMenu.add(addSupplierAccount);
		addOptionsMenu.add(addTransportAccount);
		addOptionsMenu.add(addStoreAccount);
		addOptionsMenu.add(addWarehouseAccount);
		addOptionsMenu.add(addAdminAccount);
		
		fileMenu.add(addOptionsMenu);

	    menuBar.add(fileMenu);
		setJMenuBar(menuBar);
        
    	populateAccounts();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
	
	public void clearArrays()
	{
		accountNumbers.clear();
		accountNames.clear();
		accountLocations.clear();
		editButton.clear();
	}
	
	public void populateAccounts()
	{
		
		for(int i = 0; i < session.company.getCorporateOffice().getAccountList().size(); i++)
		{
			accountNumbers.add(new JLabel(String.valueOf(session.company.getCorporateOffice().getAccountList().get(i).getAccountNumber())));
			accountNames.add(new JLabel(session.company.getCorporateOffice().getAccountList().get(i).getName()));
			accountLocations.add(new JLabel("Corporate Office"));						 

		}
		for(int i = 0; i < session.company.getStoreList().size(); i++)
		{
			for(int j = 0; j < session.company.getStoreList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(session.company.getStoreList().get(i).getAccountList().get(j).getAccountNumber())));
				accountNames.add(new JLabel(session.company.getStoreList().get(i).getAccountList().get(j).getName()));
				accountLocations.add(new JLabel("Store ID: " + session.company.getStoreList().get(i).getID()));						 
			}
		}
		for(int i = 0; i < session.company.getWarehouseList().size(); i++)
		{
			for(int j = 0; j < session.company.getWarehouseList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(session.company.getWarehouseList().get(i).getAccountList().get(j).getAccountNumber())));
				accountNames.add(new JLabel(session.company.getWarehouseList().get(i).getAccountList().get(j).getName()));
				accountLocations.add(new JLabel("Warehouse ID: " + session.company.getWarehouseList().get(i).getID()));
			}
		}
		for(int i = 0; i < session.company.getSupplierList().size(); i++)
		{
			for(int j = 0; j < session.company.getSupplierList().get(i).getAccountList().size(); j++)
			{

				accountNumbers.add(new JLabel(String.valueOf(session.company.getSupplierList().get(i).getAccountList().get(j).getAccountNumber())));
				accountNames.add(new JLabel(session.company.getSupplierList().get(i).getAccountList().get(j).getName()));
				accountLocations.add(new JLabel(session.company.getSupplierList().get(i).getName()));
			}
		}
		for(int i = 0; i < session.company.getTransportList().size(); i++)
		{
			for(int j = 0; j < session.company.getTransportList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(session.company.getTransportList().get(i).getAccountList().get(j).getAccountNumber())));
				accountNames.add(new JLabel(session.company.getTransportList().get(i).getAccountList().get(j).getName()));
				accountLocations.add(new JLabel(session.company.getTransportList().get(i).getName()));
			}
		}
	}
	
    public void setLayoutManager() 
    {
        container.setLayout(null);
    }
    
    public void setLocationAndSize() 
    {	
    	accountLabel.setBounds(200, 80,  200, 20);
    	accountLabel.setFont(new Font("Lucida", Font.BOLD, 18));
    	nameLabel.setBounds(400, 80,  200, 20);
    	nameLabel.setFont(new Font("Lucida", Font.BOLD, 18));
    	locationLabel.setBounds(600, 80,  200, 20);
    	locationLabel.setFont(new Font("Lucida", Font.BOLD, 18));
    	
    	for(int i = 1; i <= accountNumbers.size(); i++)
    	{
    		accountNumbers.get(i - 1).setBounds(200, (i * 20) + 100,  600, 20);
    		accountNames.get(i - 1).setBounds(400, (i * 20) + 100,  600, 20);
    		accountLocations.get(i - 1).setBounds(600, (i * 20) + 100,  600, 20);
    		
    		editButton.add(new JButton("Edit"));
    		editButton.get(i - 1).setBounds(100, (i * 20) + 100, 50, 20);
    	}
    }

    public void addComponentsToContainer() 
    {
		container.removeAll();
    	container.add(accountLabel);
    	container.add(nameLabel);
    	container.add(locationLabel);
    	
    	for(int i = 0; i < accountNumbers.size(); i++)
    	{
   	     	container.add(accountNumbers.get(i));
   	     	container.add(accountNames.get(i));
   	     	container.add(accountLocations.get(i));
   	     	container.add(editButton.get(i));
    	}
		super.update(getGraphics());
    }

    public void addActionEvent() 
    {
    	for(int i = 0; i < editButton.size(); i++)
    	{
    		editButton.get(i).addActionListener(this);
    	}
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0; i < editButton.size(); i++)
		{
			if(e.getSource().equals(editButton.get(i)))
			{
				editAccount(accountNames.get(i).getText());
			}
		}
		if(e.getSource().equals(addSupplierAccount))
		{
			handleAddSupplierAccount();
		}
		else if(e.getSource().equals(addTransportAccount))
		{
			handleAddTransportAccount();
		}
		else if(e.getSource().equals(addStoreAccount))
		{
			handleAddStoreAccount();
		}
		else if(e.getSource().equals(addWarehouseAccount))
		{
			handleAddWHAccount();
		}
		else if(e.getSource().equals(addAdminAccount))
		{
			handleAddAdminAccount();
		}
	}

	public void editAccount(String name)
	{
		boolean noChanges = true;
		if(session.company.findAccountName(name) != null)
		{
			JLabel location = new JLabel();
			JTextField fullName = new JTextField();
			JTextField username = new JTextField();
			JTextField email = new JTextField();
			JTextField resetPwd = new JTextField();
           
			String[] options = 
            {
        		"Apply Changes","Delete Account"
            }; 
			Object[] fields = 
			{
				location,
				"Edit Name: " + session.company.findAccountName(name).getName(), fullName,
				"Edit Username: " + session.company.findAccountName(name).getUsername(), username,
				"Edit Email: " + session.company.findAccountName(name).getEmail(), email,
				"Reset Password:", resetPwd
			};

			
			if(session.company.findAccountName(name).getDepartment() != null)
			{
				location.setText("Department # " + session.company.findAccountName(name).getDepartment().getID());
			}
			else
			{
				location.setText(session.company.findAccountName(name).getPartner().getName());
			}
			int x = JOptionPane.showOptionDialog(null, fields, "Edit Account" , JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			
			if(x == JOptionPane.NO_OPTION)
			{
				int y = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this account?\nAction cannot be undone.","Delete Account" , JOptionPane.OK_CANCEL_OPTION);
				if(y == JOptionPane.OK_OPTION) 
				{
					if(session.company.findAccountName(name).getDepartment() != null)
					{
						session.company.findAccountName(name).getDepartment().getAccountList().remove(session.company.findAccountName(name));
					}
					else
					{
						session.company.findAccountName(name).getPartner().getAccountList().remove(session.company.findAccountName(name));
					}
		            JOptionPane.showMessageDialog(null, "Account has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if(x == JOptionPane.YES_OPTION) 
			{
				if(!username.getText().isBlank())
				{
					session.company.findAccountName(name).setUsername(username.getText());
					noChanges = false;
				}
				if(!email.getText().isBlank())
				{
					session.company.findAccountName(name).setEmail(email.getText());
					noChanges = false;
				}
				if(!fullName.getText().isBlank())
				{
					session.company.findAccountName(name).setName(fullName.getText());
					noChanges = false;
				}
				if(!resetPwd.getText().isBlank())
				{
					session.company.findAccountName(name).setPassword(resetPwd.getText());
					noChanges = false;

				}
				if(noChanges)
				{
		            JOptionPane.showMessageDialog(null, "No Changes were made.", "Done", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
		            JOptionPane.showMessageDialog(null, "Changes have been saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
            clearArrays();
            populateAccounts();
            setLocationAndSize();
            addActionEvent();
            addComponentsToContainer();
		}
		else
		{
            JOptionPane.showMessageDialog(null, "Error finding account.", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}
	
	public void handleAddSupplierAccount()
	{	
		boolean success = false;
		JTextField partnerName = new JTextField();
		JTextField fullName = new JTextField();
		JTextField username = new JTextField();
		JTextField email = new JTextField();
		
		Object[] fields = 
		{
			"Supplier Name:", partnerName,
			"Full Name:", fullName,
			"Username:", username,
			"Email:", email,
		};
	
		int x = JOptionPane.showConfirmDialog(null, fields, "Create New Supplier Account" , JOptionPane.OK_CANCEL_OPTION);
		if(x == JOptionPane.OK_OPTION) 
		{
			if(partnerName.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Supplier Name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(fullName.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Full Name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(username.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Username field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(email.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Email field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 0; i < session.company.getSupplierList().size(); i++)
				{
					if(session.company.getSupplierList().get(i).getName().equals(partnerName.getText()))
					{
						SupplierStaff s1 = new SupplierStaff(session.company.getSupplierList().get(i));
						s1.setName(fullName.getText());
						s1.setEmail(email.getText());
						s1.setUsername(username.getText());
						s1.setPassword(username.getText() + "temp");
						
		                clearArrays();
		                populateAccounts();
		                setLocationAndSize();
		                addActionEvent();
		                addComponentsToContainer();
		                
		                success = true;
					}
				}
			}
			if(success)
			{
                JOptionPane.showMessageDialog(null, "Account has been created. Temporary password is: " + username.getText() + "temp", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
                JOptionPane.showMessageDialog(null, "Supplier Partner not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void handleAddTransportAccount()
	{
		boolean success = false;
		JTextField partnerName = new JTextField();
		JTextField fullName = new JTextField();
		JTextField username = new JTextField();
		JTextField email = new JTextField();
		
		Object[] fields = 
		{
			"Transport Name:", partnerName,
			"Full Name:", fullName,
			"Username:", username,
			"Email:", email,
		};

		int x = JOptionPane.showConfirmDialog(null, fields, "Create New Transport Account" , JOptionPane.OK_CANCEL_OPTION);
		if(x == JOptionPane.OK_OPTION) 
		{
			if(partnerName.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Transport Name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(fullName.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Full Name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(username.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Username field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(email.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Email field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 0; i < session.company.getTransportList().size(); i++)
				{
					if(session.company.getTransportList().get(i).getName().equals(partnerName.getText()))
					{
						TransportStaff s1 = new TransportStaff(session.company.getTransportList().get(i));
						s1.setName(fullName.getText());
						s1.setEmail(email.getText());
						s1.setUsername(username.getText());
						s1.setPassword(username.getText() + "temp");
								                
		                clearArrays();
		                populateAccounts();
		                setLocationAndSize();
		                addActionEvent();
		                addComponentsToContainer();
		                success = true;
					}
				}
			}
			if(success)
			{
                JOptionPane.showMessageDialog(null, "Account has been created. Temporary password is: " + username.getText() + "temp", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
                JOptionPane.showMessageDialog(null, "Transport Partner not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void handleAddStoreAccount()
	{
		boolean success = false;
		JTextField storeNum = new JTextField("S");
		JTextField fullName = new JTextField();
		JTextField username = new JTextField();
		JTextField email = new JTextField();
		
		Object[] fields = 
		{
			"Store ID", storeNum,
			"Full Name:", fullName,
			"Username:", username,
			"Email:", email,
		};
	
		int x = JOptionPane.showConfirmDialog(null, fields, "Create New Store Account" , JOptionPane.OK_CANCEL_OPTION);
		if(x == JOptionPane.OK_OPTION) 
		{
			if(storeNum.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Store ID is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(fullName.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Full Name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(username.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Username field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(email.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Email field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 0; i < session.company.getStoreList().size(); i++)
				{
					if(session.company.getStoreList().get(i).getID().equals(storeNum.getText()))
					{
						InventoryStaff s1 = new InventoryStaff(session.company.getStoreList().get(i));
						s1.setName(fullName.getText());
						s1.setEmail(email.getText());
						s1.setUsername(username.getText());
						s1.setPassword(username.getText() + "temp");
								                
		                clearArrays();
		                populateAccounts();
		                setLocationAndSize();
		                addActionEvent();
		                addComponentsToContainer();
		                success = true;
					}
				}
			}
			if(success)
			{
                JOptionPane.showMessageDialog(null, "Account has been created. Temporary password is: " + username.getText() + "temp", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
                JOptionPane.showMessageDialog(null, "Store not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void handleAddWHAccount()
	{
		boolean success = false;
		JTextField whNum = new JTextField("WH");
		JTextField fullName = new JTextField();
		JTextField username = new JTextField();
		JTextField email = new JTextField();
		
		Object[] fields = 
		{
			"Warehouse ID", whNum,
			"Full Name:", fullName,
			"Username:", username,
			"Email:", email,
		};

		int x = JOptionPane.showConfirmDialog(null, fields, "Create New Warehouse Account" , JOptionPane.OK_CANCEL_OPTION);
		if(x == JOptionPane.OK_OPTION) 
		{
			if(whNum.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Warehouse ID is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(fullName.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Full Name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(username.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Username field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(email.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Email field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				for(int i = 0; i < session.company.getWarehouseList().size(); i++)
				{
					if(session.company.getWarehouseList().get(i).getID().equals(whNum.getText()))
					{
						InventoryStaff s1 = new InventoryStaff(session.company.getWarehouseList().get(i));
						s1.setName(fullName.getText());
						s1.setEmail(email.getText());
						s1.setUsername(username.getText());
						s1.setPassword(username.getText() + "temp");
								                
		                clearArrays();
		                populateAccounts();
		                setLocationAndSize();
		                addActionEvent();
		                addComponentsToContainer();
		                success = true;
					}
				}
			}
			if(success)
			{
                JOptionPane.showMessageDialog(null, "Account has been created. Temporary password is: " + username.getText() + "temp", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
                JOptionPane.showMessageDialog(null, "Warehouse not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void handleAddAdminAccount()
	{
		JTextField fullName = new JTextField();
		JTextField username = new JTextField();
		JTextField email = new JTextField();
		
		Object[] fields = 
		{
			"Full Name:", fullName,
			"Username:", username,
			"Email:", email,
		};
	
		int x = JOptionPane.showConfirmDialog(null, fields, "Create New Admin Account" , JOptionPane.OK_CANCEL_OPTION);
		if(x == JOptionPane.OK_OPTION) 
		{
			if(fullName.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Full Name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(username.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Username field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(email.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Email field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				Admin s1 = new Admin(session.company.getCorporateOffice());
				s1.setName(fullName.getText());
				s1.setEmail(email.getText());
				s1.setUsername(username.getText());
				s1.setPassword(username.getText() + "temp");
				
                JOptionPane.showMessageDialog(null, "Account has been created. Temporary password is: " + username.getText() + "temp", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                clearArrays();
                populateAccounts();
                setLocationAndSize();
                addActionEvent();
                addComponentsToContainer();
			}
		}
	}
}
