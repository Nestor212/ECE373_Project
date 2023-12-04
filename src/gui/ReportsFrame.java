package gui;

import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class ReportsFrame  extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -4102500531403896036L;
	SupplyManagerGUI session;

    Container container = getContentPane();
    JButton reportOrders = new JButton("Orders");
    
	
	public ReportsFrame(SupplyManagerGUI aSession)
	{
		
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
    	reportOrders.setBounds(50, 100, 300, 60);
    }

    public void addComponentsToContainer() 
    {
        container.add(reportOrders);
    }

    public void addActionEvent() 
    {
    	reportOrders.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getSource() == reportOrders) 
		{
			try {
				handleReportOrders();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void handleReportOrders() throws IOException
	{
		
	    LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMMyy_HHmm");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String fileName = "Orders_" + formattedDate;
	    
		FileWriter write = new FileWriter(fileName, true);
		PrintWriter printLine = new PrintWriter(write);
		
		for(int i = 0; i < session.company.getWarehouseList().size(); i++)
		{
			for(int j = 0; j < session.company.getWarehouseList().get(i).getOrders().size(); j++)
			{
				printLine.printf("%s" + "%n", "Order #: " + session.company.getWarehouseList().get(i).getOrders().get(j).getOrderID() + " " +
											  "Ordered By: " + session.company.getWarehouseList().get(i).getOrders().get(j).getOrderedByString() + " " +
											  "Order Status: " + session.company.getWarehouseList().get(i).getOrders().get(j).getOrderStatus());
				for(int k = 0; k< session.company.getWarehouseList().get(i).getOrders().get(j).getItemList().size(); k++) 
				{
					printLine.printf("%s" + "%n", session.company.getWarehouseList().get(i).getOrders().get(j).getItemList().get(k).toString());
				}
				printLine.printf("\n");

			}
		}
		printLine.close();
    	JOptionPane.showMessageDialog(this, "Report has been generated. File name: " + fileName);

	}
}