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
import hardware.Store;
import hardware.Warehouse;

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
    
	//File Menu Declarations
	private JMenuBar menuBar;		//the horizontal container
	private JMenu fileMenu;
	private JMenu addOptionsMenu;
	private JMenuItem addStore;
	private JMenuItem addWarehouse;

	public DepartmentsFrame(SupplyManagerGUI aSession)
	{
    	session = aSession;

		departmentLabels = new ArrayList<JLabel>(20);
		departmentLocations = new ArrayList<JLabel>(20);
        editButton = new ArrayList<JButton>();
        
		menuBar = new JMenuBar();
		//***** File Menu + Drop-down Options ****\\
		fileMenu = new JMenu("File");
		addOptionsMenu = new JMenu("Add Department");
		addStore = new JMenuItem("Store");
		addWarehouse = new JMenuItem("Warehouse");
		
		addStore.addActionListener(this);
		addWarehouse.addActionListener(this);
		
		addOptionsMenu.add(addStore);
		addOptionsMenu.add(addWarehouse);
		
		fileMenu.add(addOptionsMenu);

	    menuBar.add(fileMenu);
		setJMenuBar(menuBar);
        
    	populateArrays();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
	
	public void clearArrays()
	{
		departmentLabels.clear();
		departmentLocations.clear();
		editButton.clear();
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
		container.removeAll();
    	container.add(titleLabel);
    	container.add(corporateLabel);
	    container.add(editButton.get(0));
    	
    	for(int i = 0; i < departmentLabels.size(); i++)
    	{
   	     	container.add(departmentLabels.get(i));
   	     	container.add(departmentLocations.get(i));
   	     	container.add(editButton.get(i + 1));
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
		for(int i = 1; i < editButton.size(); i++)
		{
			if(e.getSource() == editButton.get(i))
			{
				editAccount(departmentLocations.get(i-1).getText());
			}
		}
		if(e.getSource() == editButton.get(0))
		{
			editAccount("Corporate");
		}
		else if(e.getSource().equals(addStore))
		{
			handleAddDepartment("Store");
		}
		else if(e.getSource().equals(addWarehouse))
		{
			handleAddDepartment("Warehouse");
		}
		
	}
	
	public void editAccount(String alocation)
	{
		boolean noChanges = true;
		if(session.company.findDepartment(alocation) != null)
		{
			String identifier = session.company.findDepartment(alocation).getIdentifier();
			JLabel department = new JLabel();
			JTextField location = new JTextField();
			
			department.setText("Department # " + session.company.findDepartment(alocation).getID());
           
			String[] options = 
            {
        		"Apply Changes","Delete Department"
            }; 
			Object[] fields = 
			{
				department,
				"Edit Location: " + session.company.findDepartment(alocation).getLocation(), location,
			};

			int x = JOptionPane.showOptionDialog(null, fields, "Edit Department" , JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(x == JOptionPane.NO_OPTION)
			{
				int y = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this Department?\nAction cannot be undone.","Delete Department" , JOptionPane.OK_CANCEL_OPTION);
				if(y == JOptionPane.OK_OPTION) 
				{
					if(identifier.equals("C"))
					{
			            JOptionPane.showMessageDialog(null, "Corporate Office cannot be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if((session.company.getWarehouseList().size() == 1 && identifier.equals("WH")) || 
							(session.company.getStoreList().size() == 1 && identifier.equals("S")))
					{
			            JOptionPane.showMessageDialog(null, "Only 1 of this deparments type exists, cannot be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						switch(identifier)
						{
						case("S"):
							session.company.removeStore((Store) session.company.findDepartment(alocation));
				            JOptionPane.showMessageDialog(null, "Store has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
							break;
						case("WH"):
							session.company.removeWarehouse((Warehouse) session.company.findDepartment(alocation));
				            JOptionPane.showMessageDialog(null, "Warehouse has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
							break;
						default:
				            JOptionPane.showMessageDialog(null, "Error Deleting Department.", "Error", JOptionPane.ERROR_MESSAGE);
				            break;
						}
					}
				}
			}
			else if(x == JOptionPane.YES_OPTION) 
			{
				if(!location.getText().isBlank())
				{
					session.company.findDepartment(alocation).setLocation(location.getText());
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
            populateArrays();
            setLocationAndSize();
            addActionEvent();
            addComponentsToContainer();
		}
		else
		{
            JOptionPane.showMessageDialog(null, "Error finding department.", "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	
	public void handleAddDepartment(String depType)
	{
		JTextField location = new JTextField();
		
		Object[] fields = 
		{
			"Location:", location,
		};
	
		int x = JOptionPane.showConfirmDialog(null, fields, "Add New " + depType, JOptionPane.OK_CANCEL_OPTION);
		if(x == JOptionPane.OK_OPTION) 
		{
			if(location.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Location field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{ 
				switch(depType)
				{
					case "Store":
						Store s1 = new Store();
						s1.setLocation(location.getText());
						session.company.addStore(s1);
		                JOptionPane.showMessageDialog(null, s1.toString() + " Successfully Created.", "Success", JOptionPane.INFORMATION_MESSAGE);
		                break;
					case "Warehouse":
						Warehouse wh1 = new Warehouse();
						wh1.setLocation(location.getText());
						session.company.addWarehouse(wh1);
		                JOptionPane.showMessageDialog(null, wh1.toString() + " Successfully Created.", "Success", JOptionPane.INFORMATION_MESSAGE);
		                break;
					default:
		                JOptionPane.showMessageDialog(null, "Error occurred while adding department.", "Error", JOptionPane.ERROR_MESSAGE);
				}

                clearArrays();
                populateArrays();
                setLocationAndSize();
                addActionEvent();
                addComponentsToContainer();
			}
		}
	}
}
