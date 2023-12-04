package gui;

/* 
 * Class Description
 * Part of the GUI package (detailed below), this instantiates the GUI object.
 *
 * System can be stared by running this file IF 'driver.java' has already been run once, 
 * since the driver instantiates then saves a company object with all its constituents. 
 * 
 * GUI Package:
 * The GUI package is made up of various files that are extensions of the Java swing frame class. 
 * Each file is named after it's corresponding function + Frame and instantiates a new window. 
 * 
 * The system begins by presenting the LoginFrame. 
 * Upon a successful login, the loginFrame is discarded and a HomeFrame Window appears, tailored to the account type that has logged in. 
 * From there, the various options that appear will instantiate a window/Frame corresponding to a file in the gui package. 
 */


public class SupplyManagerClient {
	public static void main(String[] args)
	{	
		SupplyManagerGUI newSession = new SupplyManagerGUI();
	}
}
