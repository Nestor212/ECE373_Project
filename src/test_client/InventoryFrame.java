package test_client;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InventoryFrame extends JFrame implements ActionListener 
{
   Container container = getContentPane();
   
	public InventoryFrame()
	{        
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
