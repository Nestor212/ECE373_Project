package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ReportsFrame  extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -4102500531403896036L;
	SupplyManagerGUI session;

    Container container = getContentPane();
	
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
