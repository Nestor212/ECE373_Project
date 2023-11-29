package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import software.Company;
import users.Account;

public class AccountsFrame extends JFrame implements ActionListener
{
//    protected Company company;
//	protected Account sessionAccount;
	SupplyManagerGUI session;

	
    private ArrayList<JLabel> accountNumbers;
    private ArrayList<JLabel> accountNames;
    private ArrayList<JLabel> accountLocations;
    private ArrayList<JButton> editButton;
        
    JLabel accountLabel = new JLabel("Account Number");
    JLabel nameLabel = new JLabel("Name");
    JLabel locationLabel = new JLabel("Location");
    
    Container container = getContentPane();
  
	public AccountsFrame(SupplyManagerGUI aSession)
	{
//		company = aCompany;
//		sessionAccount = aAccount;
		session = aSession;
		
        accountNumbers = new ArrayList<JLabel>(20);
        accountNames = new ArrayList<JLabel>(20);
        accountLocations = new ArrayList<JLabel>(20);
        editButton = new ArrayList<JButton>();
        
    	populateAccounts();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
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
				accountNumbers.add(new JLabel(String.valueOf(session.company.getStoreList().get(i).getAccountList().get(i).getAccountNumber())));
				accountNames.add(new JLabel(session.company.getStoreList().get(i).getAccountList().get(i).getName()));
				accountLocations.add(new JLabel("Store ID: " + session.company.getStoreList().get(i).getID()));						 
			}
		}
		for(int i = 0; i < session.company.getWarehouseList().size(); i++)
		{
			for(int j = 0; j < session.company.getWarehouseList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(session.company.getWarehouseList().get(i).getAccountList().get(i).getAccountNumber())));
				accountNames.add(new JLabel(session.company.getWarehouseList().get(i).getAccountList().get(i).getName()));
				accountLocations.add(new JLabel("Warehouse ID: " + session.company.getWarehouseList().get(i).getID()));
			}
		}
		for(int i = 0; i < session.company.getSupplierList().size(); i++)
		{
			for(int j = 0; j < session.company.getSupplierList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(session.company.getSupplierList().get(i).getAccountList().get(i).getAccountNumber())));
				accountNames.add(new JLabel(session.company.getSupplierList().get(i).getAccountList().get(i).getName()));
				accountLocations.add(new JLabel(session.company.getSupplierList().get(i).getName()));
			}
		}
		for(int i = 0; i < session.company.getTransportList().size(); i++)
		{
			for(int j = 0; j < session.company.getTransportList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(session.company.getTransportList().get(i).getAccountList().get(i).getAccountNumber())));
				accountNames.add(new JLabel(session.company.getTransportList().get(i).getAccountList().get(i).getName()));
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
			if(e.getSource() == editButton.get(i))
			{
				editAccount(accountNames.get(i).getText());
			}
		}
		
	}
	
	public void editAccount(String name)
	{
		System.out.println("TO DO: Find account for user: " + name + ", and allow for edits.");
	}

}
