package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import software.Company;
import users.Account;

public class ReportsFrame  extends JFrame implements ActionListener
{
    protected Company company;
	protected Account sessionAccount;
	SupplyManagerGUI session;

    Container container = getContentPane();
	
	public ReportsFrame(SupplyManagerGUI aSession)
	{
//		company = aCompany;
//		sessionAccount = aAccount;
		session = aSession;
		
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
//        ordersButton.setBounds(50, 300, 100, 30);   
//        reportsButton.setBounds(200, 300, 100, 30);
    }

    public void addComponentsToContainer() 
    {
//        container.add(ordersButton);
//        container.add(reportsButton);
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
