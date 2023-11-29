package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import software.Company;
import users.Account;

public class InventoryFrame extends JFrame implements ActionListener 
{
    protected Company company;
	protected Account sessionAccount;
	SupplyManagerGUI session;
	
	Container container = getContentPane();
	JScrollPane scrollPane;
	
   	JLabel titleLabel = new JLabel();
	JLabel itemNumLabel = new JLabel("Item Number");
	JLabel itemNameLabel = new JLabel("Name");
	JLabel QtyLabel = new JLabel("Qty");
	JLabel supplierPriceLabel = new JLabel("Supplier Price");
	JLabel retailPriceLabel = new JLabel("Retail Price");

	ArrayList<JLabel> itemNumbers;
	ArrayList<JLabel> itemNames;
	ArrayList<JLabel> itemQtys;
	ArrayList<JLabel> retailPrices;
	ArrayList<JLabel> supplierPrices;

	public InventoryFrame(SupplyManagerGUI aSession)
	{      
//		company = aCompany;
//		sessionAccount = aAccount;
		session = aSession;
		
		titleLabel = new JLabel("Inventory - " + sessionAccount.getDepartment().toString());
		itemNumbers = new ArrayList<JLabel>(100);
		itemNames = new ArrayList<JLabel>(100);
		itemQtys = new ArrayList<JLabel>(100);
		retailPrices = new ArrayList<JLabel>(100);
		supplierPrices = new ArrayList<JLabel>(100);

		populateInventoryArray();
		
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        
//		scrollPane = new JScrollPane(container);   
//		scrollPane.setVisible(true);
    }
	
	public void populateInventoryArray()
	{
		for(int i = 0; i < sessionAccount.getDepartment().getInventory().size(); i++)
		{
			itemNumbers.add(new JLabel(String.valueOf(sessionAccount.getDepartment().getInventory().get(i).getItemNum())));
			itemNames.add(new JLabel(sessionAccount.getDepartment().getInventory().get(i).getName()));
			itemQtys.add(new JLabel(String.valueOf(sessionAccount.getDepartment().getInventory().get(i).getQty())));
			retailPrices.add(new JLabel("$" + sessionAccount.getDepartment().getInventory().get(i).getRetailPrice()));
			supplierPrices.add(new JLabel("$" + sessionAccount.getDepartment().getInventory().get(i).getSupplierPrice()));
		}
	}
    public void setLayoutManager() 
    {
        container.setLayout(null);
    }
    
    public void setLocationAndSize() 
    {
    	titleLabel.setBounds(50, 20,  1000, 30);
    	titleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
    	
    	itemNumLabel.setBounds(50, 80,  200, 20);
    	itemNumLabel.setFont(new Font("Lucida", Font.BOLD, 18));
    	itemNameLabel.setBounds(250, 80,  200, 20);
    	itemNameLabel.setFont(new Font("Lucida", Font.BOLD, 18));
    	QtyLabel.setBounds(500, 80,  200, 20);
    	QtyLabel.setFont(new Font("Lucida", Font.BOLD, 18));
    	supplierPriceLabel.setBounds(600, 80,  200, 20);
    	supplierPriceLabel.setFont(new Font("Lucida", Font.BOLD, 18));
    	retailPriceLabel.setBounds(800, 80,  200, 20);
    	retailPriceLabel.setFont(new Font("Lucida", Font.BOLD, 18));
    	
		for(int i = 1; i <= sessionAccount.getDepartment().getInventory().size(); i++)
		{
			itemNumbers.get(i - 1).setBounds(50, (i * 20) + 100,  600, 20);
			itemNames.get(i - 1).setBounds(250, (i * 20) + 100,  600, 20);
			itemQtys.get(i - 1).setBounds(500, (i * 20) + 100,  600, 20);
			supplierPrices.get(i - 1).setBounds(600, (i * 20) + 100,  600, 20);		
			retailPrices.get(i - 1).setBounds(800, (i * 20) + 100,  600, 20);
		}
    }
    
    public void addComponentsToSP() 
    {
    	//container.add(sb);
    	scrollPane.add(titleLabel);
    	scrollPane.add(itemNumLabel);
    	scrollPane.add(itemNameLabel);
    	scrollPane.add(QtyLabel);
    	scrollPane.add(supplierPriceLabel);
    	scrollPane.add(retailPriceLabel);
        
		for(int i = 0; i < sessionAccount.getDepartment().getInventory().size(); i++)
		{
			scrollPane.add(itemNumbers.get(i));
			scrollPane.add(itemNames.get(i));
			scrollPane.add(itemQtys.get(i));
			scrollPane.add(retailPrices.get(i));
			scrollPane.add(supplierPrices.get(i));	
		}
    }
    
    public void addComponentsToContainer() 
    {
    	//container.add(sb);
    	container.add(titleLabel);
        container.add(itemNumLabel);
        container.add(itemNameLabel);
        container.add(QtyLabel);
        container.add(supplierPriceLabel);
        container.add(retailPriceLabel);
        
		for(int i = 0; i < sessionAccount.getDepartment().getInventory().size(); i++)
		{
	        container.add(itemNumbers.get(i));
	        container.add(itemNames.get(i));
	        container.add(itemQtys.get(i));
	        container.add(retailPrices.get(i));
	        container.add(supplierPrices.get(i));	
		}
    }

    public void addActionEvent() 
    {
//        ordersButton.addActionListener(this);
//        reportsButton.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
