package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import hardware.Supplier;
import hardware.Transport;

public class PartnersFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -4939456472059373172L;
	SupplyManagerGUI session;
	
	JLabel titleLabel = new JLabel("Partners: ");
	JLabel supplierLabel = new JLabel("Suppliers: ");
	JLabel transportLabel = new JLabel("Transport: ");
	JLabel balanceLabel = new JLabel("Account Balance");
	
    ArrayList<JLabel> partnerLabels;
    ArrayList<JButton> editButton;
    
    ArrayList<JLabel> balanceLabels;
    ArrayList<JButton> payButtons;
    
    // PartnerID stored to be able to find partner for editing
    private ArrayList<Integer> partnerID;
            
    Container container = getContentPane();
    
	//File Menu Declarations
	private JMenuBar menuBar;		//the horizontal container
	private JMenu fileMenu;
	private JMenu addOptionsMenu;
	private JMenuItem addSupplier;
	private JMenuItem addTransport;
  
	public PartnersFrame(SupplyManagerGUI aSession)
	{
		session = aSession;
		partnerLabels = new ArrayList<JLabel>(20);
        editButton = new ArrayList<JButton>(20);
        
        balanceLabels = new ArrayList<JLabel>(20);
        payButtons = new ArrayList<JButton>(20);
        
		partnerID= new ArrayList<Integer>(20);
        
		menuBar = new JMenuBar();
		//***** File Menu + Drop-down Options ****\\
		fileMenu = new JMenu("File");
		addOptionsMenu = new JMenu("Add Department");
		addSupplier = new JMenuItem("Supplier");
		addTransport = new JMenuItem("Transportation");
		
		addSupplier.addActionListener(this);
		addTransport.addActionListener(this);
		
		addOptionsMenu.add(addSupplier);
		addOptionsMenu.add(addTransport);
		
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
		partnerLabels.clear();
		balanceLabels.clear();
		partnerID.clear();
		editButton.clear();
		payButtons.clear();
	}
	
	public void populateArrays()
	{
		NumberFormat dollarsUS = NumberFormat.getCurrencyInstance();
		for(int i = 0; i < session.company.getSupplierList().size(); i++)
		{
			partnerLabels.add(new JLabel(session.company.getSupplierList().get(i).getName()));
			partnerID.add(session.company.getSupplierList().get(i).getPartnerID());
			balanceLabels.add(new JLabel(dollarsUS.format(session.company.getSupplierList().get(i).getAccountBalance())));
		}
		for(int i = 0; i < session.company.getTransportList().size(); i++)
		{
			partnerLabels.add(new JLabel(session.company.getTransportList().get(i).getName()));
			partnerID.add(session.company.getTransportList().get(i).getPartnerID());
			balanceLabels.add(new JLabel(dollarsUS.format(session.company.getTransportList().get(i).getAccountBalance())));
		}
	}
	
    public void setLayoutManager() 
    {
        container.setLayout(null);
    }
    
    public void setLocationAndSize() 
    {	
    	titleLabel.setBounds(100, 80,  200, 20);
    	titleLabel.setFont(new Font("Lucida", Font.BOLD, 22));
    	
    	balanceLabel.setBounds(500, 80,  200, 20);
    	balanceLabel.setFont(new Font("Lucida", Font.BOLD, 22));

    	for(int i = 0; i < partnerLabels.size(); i++)
    	{
    		partnerLabels.get(i).setBounds(200, (i * 20) + 120,  600, 20);
    		balanceLabels.get(i).setBounds(500, (i * 20) + 120,  600, 20);
    		    		
    		editButton.add(new JButton("Edit"));
    		editButton.get(i).setBounds(100, (i * 20) + 120, 50, 20);
    		
    		payButtons.add(new JButton("Pay"));
    		payButtons.get(i).setBounds(600, (i * 20) + 120, 50, 20);
    	}
    }

    public void addComponentsToContainer() 
    {
		container.removeAll();
    	container.add(titleLabel);
    	container.add(balanceLabel);
    	
    	for(int i = 0; i < partnerLabels.size(); i++)
    	{
   	     	container.add(partnerLabels.get(i));
   	     	container.add(balanceLabels.get(i));
   	     	container.add(editButton.get(i));
   	     	container.add(payButtons.get(i));
    	}
		super.update(getGraphics());
    }

    public void addActionEvent() 
    {
    	for(int i = 0; i < editButton.size(); i++)
    	{
    		editButton.get(i).addActionListener(this);
    		payButtons.get(i).addActionListener(this);
    	}
    }
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0; i < editButton.size(); i++)
		{
			if(e.getSource() == editButton.get(i))
			{
				editAccount(partnerID.get(i));
			}
		}
		for(int i = 0; i < payButtons.size(); i++)
		{
			if(e.getSource() == payButtons.get(i))
			{
				payAccount(partnerID.get(i));
			}
		}
		if(e.getSource().equals(addSupplier))
		{
			handleAddPartner("Supplier");
		}
		else if(e.getSource().equals(addTransport))
		{
			handleAddPartner("Transport");
		}
	}
	
	public void payAccount(Integer aPartnerID)
	{
		if(session.company.findPartner(aPartnerID) != null)
		{
			NumberFormat dollarsUS = NumberFormat.getCurrencyInstance();
			JLabel partner = new JLabel(session.company.findPartner(aPartnerID).getName());
			JLabel amountDue = new JLabel("Amount Due: " + dollarsUS.format(session.company.findPartner(aPartnerID).getAccountBalance()));
			JTextField amount = new JTextField();
			

			Object[] fields = 
			{
				partner,
				amountDue,
				"Payment Amount: $", amount,
			};

			int x = JOptionPane.showConfirmDialog(null, fields, "Pay Partner" , JOptionPane.OK_CANCEL_OPTION);
			if(x == JOptionPane.OK_OPTION)
			{
				if(!amount.getText().isBlank())
				{
					if(Float.parseFloat(amount.getText()) <= session.company.findPartner(aPartnerID).getAccountBalance())
					{
						session.company.findPartner(aPartnerID).makePayment(Float.parseFloat(amount.getText()));
			            JOptionPane.showMessageDialog(null, "Partner has been paid. Remaining balance is: " + 
			            							  dollarsUS.format(session.company.findPartner(aPartnerID).getAccountBalance()), "Success", JOptionPane.INFORMATION_MESSAGE);
			            
			            clearArrays();
			            populateArrays();
			            setLocationAndSize();
			            addActionEvent();
			            addComponentsToContainer();
					}
					else
					{
			            JOptionPane.showMessageDialog(null, "Error paying partner, payment amount exceeds amount due.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}	
		}
		else
		{
            JOptionPane.showMessageDialog(null, "Error paying partner.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editAccount(Integer aPartnerID)
	{
		boolean noChanges = true;
		if(session.company.findPartner(aPartnerID) != null)
		{
			JLabel department = new JLabel();
			JTextField name = new JTextField();
			
			department.setText(session.company.findPartner(aPartnerID).getName());
           
			String[] options = 
            {
        		"Apply Changes","Delete Partner"
            }; 
			Object[] fields = 
			{
				department,
				"Edit Name:", name,
			};

			int x = JOptionPane.showOptionDialog(null, fields, "Edit Partner" , JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(x == JOptionPane.NO_OPTION)
			{
				int y = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this Partner?\nAction cannot be undone.","Delete Department" , JOptionPane.OK_CANCEL_OPTION);
				if(y == JOptionPane.OK_OPTION) 
				{
					if(session.company.getSupplierList().contains(session.company.findPartner(aPartnerID)))
					{
						session.company.getSupplierList().remove(session.company.findPartner(aPartnerID));
			            JOptionPane.showMessageDialog(null, "Supplier has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(session.company.getTransportList().contains(session.company.findPartner(aPartnerID)))
					{
						session.company.getTransportList().remove(session.company.findPartner(aPartnerID));
			            JOptionPane.showMessageDialog(null, "Transport has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
			            JOptionPane.showMessageDialog(null, "Error Deleting Partner.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else if(x == JOptionPane.YES_OPTION) 
			{
				if(!name.getText().isBlank())
				{
					session.company.findPartner(aPartnerID).setName(name.getText());
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
            JOptionPane.showMessageDialog(null, "Error finding partner.", "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	
	public void handleAddPartner(String partnerType)
	{
		JTextField aName = new JTextField();
		
		Object[] fields = 
		{
			"Name:", aName,
		};
	
		int x = JOptionPane.showConfirmDialog(null, fields, "Add New " + partnerType, JOptionPane.OK_CANCEL_OPTION);
		if(x == JOptionPane.OK_OPTION) 
		{
			if(aName.getText().isBlank())
			{
                JOptionPane.showMessageDialog(null, "Location field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{ 
				switch(partnerType)
				{
					case "Supplier":
						Supplier s1 = new Supplier();
						s1.setName(aName.getText());
						session.company.addSupplier(s1);
		                JOptionPane.showMessageDialog(null, s1.getName() + " Successfully Created.", "Success", JOptionPane.INFORMATION_MESSAGE);
		                break;
					case "Transport":
						Transport t1 = new Transport();
						t1.setName(aName.getText());
						session.company.addTransport(t1);
		                JOptionPane.showMessageDialog(null, t1.getName() + " Successfully Created.", "Success", JOptionPane.INFORMATION_MESSAGE);
		                break;
					default:
		                JOptionPane.showMessageDialog(null, "Error occurred while adding partner.", "Error", JOptionPane.ERROR_MESSAGE);
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
