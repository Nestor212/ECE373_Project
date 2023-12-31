package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Class Description
 * The loginFrame is responsible for displaying the login window that takes in the user input username and password, 
 * then interacts with the session Company to check if these are valid credentials. 
 * 
 * If successful, this object will be discarded and the Home Frame will be generated. 
 * 
 * After 3 failed login attempts, this objects will disable the login functionality for some amount of time (5 seconds for demo purposes)
 */

public class LoginFrame extends JFrame implements ActionListener 
{		
	private static final long serialVersionUID = -5971805714815431922L;
	SupplyManagerGUI session;
	
	JLabel imageUofA = new JLabel(new ImageIcon("supplyChain300.jpg"));
    
	Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    
    private int failedAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCKOUT_TIME = 5000; // Lockout time in milliseconds (5 seconds)
    private Timer lockoutTimer;
    
    LoginFrame(SupplyManagerGUI aSession) 
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
    	imageUofA.setBounds(35, 20, 300, 156);;
        userLabel.setBounds(50, 200, 100, 30);
        passwordLabel.setBounds(50, 270, 100, 30);
        userTextField.setBounds(150, 200, 150, 30);
        passwordField.setBounds(150, 270, 150, 30);
        showPassword.setBounds(150, 300, 150, 30);
        loginButton.setBounds(50, 350, 100, 30);
        resetButton.setBounds(200, 350, 100, 30);
    }

    public void addComponentsToContainer() 
    {
    	container.add(imageUofA);
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

    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) 
    {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) 
        {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText(); 
          
            
            session.sessionAccount = session.company.login(userText, pwdText);
            
            if (session.sessionAccount != null) 
            {
                JOptionPane.showMessageDialog(this, "Login Successful");
                this.dispose();
                
                HomeFrame homeFrame = new HomeFrame(session);
                switch(session.sessionAccount.getAccessLevel())
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
                homeFrame.setResizable(true); 
                homeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                ++failedAttempts;
                if (failedAttempts >= MAX_ATTEMPTS) {
                    lockoutAccount();
                }
            }
        }

        if (e.getSource() == showPassword) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            if (checkBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('*'); // Hide password
            }
        }

        // Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
    
        //Coding Part of RESET button
        if (e.getSource() == resetButton) 
        {
            userTextField.setText("");
            passwordField.setText("");
        }
    }
    
    private void lockoutAccount() {
    	JOptionPane.showMessageDialog(this, "Your account is temporarily locked for 5 seconds");
        loginButton.setEnabled(false);
        lockoutTimer = new Timer(LOCKOUT_TIME, new ActionListener() {
        	
            
            public void actionPerformed(ActionEvent e) {
                failedAttempts = 0;
                loginButton.setEnabled(true);
                lockoutTimer.stop();
            }
        });
        lockoutTimer.start();
    }
}
