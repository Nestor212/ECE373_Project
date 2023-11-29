package gui;

import javax.swing.*;
import software.Company;
import users.Account;

public class SupplyManagerGUI 
{
	protected Company company;
	protected Account sessionAccount;

    public SupplyManagerGUI() 
    {
    	company = Company.loadData();
    	
        LoginFrame loginFrame = new LoginFrame(this);
        loginFrame.setTitle("Login - Arizona Incorporated");
        loginFrame.setVisible(true);
        loginFrame.setBounds(600, 200, 370, 600);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
    }
}
