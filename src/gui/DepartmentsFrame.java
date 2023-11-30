package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DepartmentsFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 7585464666756410571L;
	SupplyManagerGUI session;
	
	JLabel titleLabel = new JLabel("Departments: ");
	JLabel corporateLabel;
    ArrayList<JLabel> departmentLabels;
    ArrayList<JLabel> departmentLocations;
    ArrayList<JButton> editButton;
            
    Container container = getContentPane();
  
	public DepartmentsFrame(SupplyManagerGUI aSession)
	{
    	session = aSession;

	
		departmentLabels = new ArrayList<JLabel>(20);
		departmentLocations = new ArrayList<JLabel>(20);
        editButton = new ArrayList<JButton>();
        
    	populateArrays();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
	
	public void populateArrays()
	{
		corporateLabel = new JLabel(session.company.getCorporateOffice().toString());

		for(int i = 0; i < session.company.getStoreList().size(); i++)
		{
			departmentLabels.add(new JLabel("Store # " + session.company.getStoreList().get(i).getID()));
			departmentLocations.add(new JLabel(session.company.getStoreList().get(i).getLocation()));						 
		}
		for(int i = 0; i < session.company.getWarehouseList().size(); i++)
		{
			departmentLabels.add(new JLabel("Warehouse # " + session.company.getWarehouseList().get(i).getID()));
			departmentLocations.add(new JLabel(session.company.getWarehouseList().get(i).getLocation()));	
		}
	}
	
	
    public void setLayoutManager() 
    {
        container.setLayout(null);
    }
    
    public void setLocationAndSize() 
    {	
    	titleLabel.setBounds(100, 80,  200, 25);
    	titleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
    	
    	corporateLabel.setBounds(200, 150,  400, 20);
    	titleLabel.setFont(new Font("Lucida", Font.BOLD, 16));

    	editButton.add(new JButton("Edit"));
		editButton.get(0).setBounds(100, 150, 50, 20);


    	for(int i = 1; i <= departmentLabels.size(); i++)
    	{
    		departmentLabels.get(i - 1).setBounds(200, (i * 20) + 200,  600, 20);
    		departmentLocations.get(i - 1).setBounds(400, (i * 20) + 200,  600, 20);
    		    		
    		editButton.add(new JButton("Edit"));
    		editButton.get(i).setBounds(100, (i * 20) + 200, 50, 20);
    	}
    }

    public void addComponentsToContainer() 
    {
    	container.add(titleLabel);
    	container.add(corporateLabel);
	    container.add(editButton.get(0));
    	
    	for(int i = 0; i < departmentLabels.size(); i++)
    	{
   	     	container.add(departmentLabels.get(i));
   	     	container.add(departmentLocations.get(i));
   	     	container.add(editButton.get(i + 1));
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
				System.out.println(departmentLabels.get(i).getText());
				editAccount(departmentLocations.get(i).getText());
			}
		}
		
	}
	
	public void editAccount(String location)
	{
		System.out.println("TO DO: Find department with location: " + location + ", and allow for edits.");
	}

}
