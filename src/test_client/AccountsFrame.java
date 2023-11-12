package test_client;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AccountsFrame extends JFrame implements ActionListener
{
    private ArrayList<JLabel> accountNumbers;
    private ArrayList<JLabel> accountNames;
    private ArrayList<JLabel> accountLocations;
    
    private ArrayList<JButton> editButton;

        
    JLabel accountLabel = new JLabel("Account Number");
    JLabel nameLabel = new JLabel("Name");
    JLabel locationLabel = new JLabel("Location");

    

    
    Container container = getContentPane();
  
	public AccountsFrame()
	{
        accountNumbers = new ArrayList<JLabel>(20);
        accountNames = new ArrayList<JLabel>(20);
        accountLocations = new ArrayList<JLabel>(20);
        editButton = new ArrayList<JButton>();
        
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
	
	public void populateAccounts()
	{
		
		for(int i = 0; i < GUI.session.getCompany().getCorporateOffice().getAccountList().size(); i++)
		{
			accountNumbers.add(new JLabel(String.valueOf(GUI.session.getCompany().getCorporateOffice().getAccountList().get(i).getAccountNumber())));
			accountNames.add(new JLabel(GUI.session.getCompany().getCorporateOffice().getAccountList().get(i).getName()));
			accountLocations.add(new JLabel("Corporate Office"));						 

		}
		for(int i = 0; i < GUI.session.getCompany().getStoreList().size(); i++)
		{
			for(int j = 0; j < GUI.session.getCompany().getStoreList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(GUI.session.getCompany().getStoreList().get(i).getAccountList().get(i).getAccountNumber())));
				accountNames.add(new JLabel(GUI.session.getCompany().getStoreList().get(i).getAccountList().get(i).getName()));
				accountLocations.add(new JLabel("Store ID: " + GUI.session.getCompany().getStoreList().get(i).getID()));						 
			}
		}
		for(int i = 0; i < GUI.session.getCompany().getWarehouseList().size(); i++)
		{
			for(int j = 0; j < GUI.session.getCompany().getWarehouseList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(GUI.session.getCompany().getWarehouseList().get(i).getAccountList().get(i).getAccountNumber())));
				accountNames.add(new JLabel(GUI.session.getCompany().getWarehouseList().get(i).getAccountList().get(i).getName()));
				accountLocations.add(new JLabel("Warehouse ID: " + GUI.session.getCompany().getWarehouseList().get(i).getID()));
			}
		}
		for(int i = 0; i < GUI.session.getCompany().getSupplierList().size(); i++)
		{
			for(int j = 0; j < GUI.session.getCompany().getSupplierList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(GUI.session.getCompany().getSupplierList().get(i).getAccountList().get(i).getAccountNumber())));
				accountNames.add(new JLabel(GUI.session.getCompany().getSupplierList().get(i).getAccountList().get(i).getName()));
				accountLocations.add(new JLabel(GUI.session.getCompany().getSupplierList().get(i).getName()));
			}
		}
		for(int i = 0; i < GUI.session.getCompany().getTransportList().size(); i++)
		{
			for(int j = 0; j < GUI.session.getCompany().getTransportList().get(i).getAccountList().size(); j++)
			{
				accountNumbers.add(new JLabel(String.valueOf(GUI.session.getCompany().getTransportList().get(i).getAccountList().get(i).getAccountNumber())));
				accountNames.add(new JLabel(GUI.session.getCompany().getTransportList().get(i).getAccountList().get(i).getName()));
				accountLocations.add(new JLabel(GUI.session.getCompany().getTransportList().get(i).getName()));
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
    	
    	populateAccounts();
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
		// TODO Auto-generated method stub
		
	}

}
