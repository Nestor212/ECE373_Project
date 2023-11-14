package test_client;

import javax.swing.*;

public class GUI 
{
	static Client session;
	
    public static void main(String[] a) 
    {
    	session = new Client();
    	
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setTitle("Login - Arizona Incorporated");
        loginFrame.setVisible(true);
        loginFrame.setBounds(600, 200, 370, 600);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
    }
    
    public static Client getSession()
    {
    	return session;
    }
}


