package test_client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener 
{	
    private static final long serialVersionUID = 1L;
	Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JTextField passwordField = new JTextField();
    //JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");

    LoginFrame() 
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
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }

    public void addComponentsToContainer() 
    {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() 
    {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) 
        {
            String userText;
            String pwdText;
            //char[] pwdText = passwordField.getPassword();
            userText = userTextField.getText();
            pwdText = passwordField.getText(); 
            SupplyManagerGUI.session.setSessionAccessLevel(SupplyManagerGUI.session.login(userText, pwdText));
            if (SupplyManagerGUI.session.getSessionAccessLevel() != -1) 
            {
                JOptionPane.showMessageDialog(this, "Login Successful");
                this.dispose();
                
                HomeFrame homeFrame = new HomeFrame();
                switch(SupplyManagerGUI.session.getSessionAccessLevel())
                {
                	case 10:
                        homeFrame.setTitle("Administrator Home - Arizona Incorporated");
                        break;
                	case 20:
                        homeFrame.setTitle("Inventory Staff Home - Arizona Incorporated");
                        break;
                	case 30:
                        homeFrame.setTitle("Transportation Home - Arizona Incorporated");
                        break;
                	case 40:
                        homeFrame.setTitle("Supply User Home - Arizona Incorporated");
                        break;
                	default:
                        homeFrame.setTitle("Home - Arizona Incorporated");	
                        break;
                }
                homeFrame.setVisible(true);
                homeFrame.setBounds(150, 150, 1344, 400); //840
                homeFrame.setDefaultCloseOperation(HomeFrame.EXIT_ON_CLOSE);
                homeFrame.setResizable(true);            
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) 
        {
            userTextField.setText("");
            passwordField.setText("");
        }
       //Coding Part of showPassword JCheckBox
//        if (e.getSource() == showPassword) 
//        {
//            if (showPassword.isSelected()) 
//            {
//                passwordField.setEchoChar((char) 0);
//            } else {
//                passwordField.setEchoChar('*');
//            }
//        }
    }
}