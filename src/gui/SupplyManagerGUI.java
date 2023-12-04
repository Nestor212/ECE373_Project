package gui;

import javax.swing.*;
import software.Company;
import users.Account;

/* 
 * Class Description
 * The starting object of the GUI.
 * 
 * The GUI is instantiated by loading the existing serial company data, saving it to a local Company variable, 
 * along with the session account that is logging in to the system. 
 * 
 * Upon loading the data, this object will instantiate the login frame/window. 
 */

public class SupplyManagerGUI 
{
	protected Company company;
	protected Account sessionAccount;

    public SupplyManagerGUI() 
    {
    	company = Company.loadData("ArizonaInc");
    	
        LoginFrame loginFrame = new LoginFrame(this);
        loginFrame.setTitle("Login - Arizona Incorporated");
        loginFrame.setVisible(true);
        loginFrame.setBounds(600, 200, 370, 500);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
    }
}
