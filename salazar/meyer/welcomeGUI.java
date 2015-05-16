package salazar.meyer;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// the GUI version of COSC1437 final project

public class welcomeGUI extends JFrame
{
	WelcomePanel welcome;
	private final int WINDOW_WIDTH = 450;
	private final int WINDOW_HEIGHT = 150;
	
	// constructor
	public welcomeGUI() 
	{
		// display a title
		setTitle("SalaMey Clinic - Something for everyone.");
			
		// set Window Size and Location
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocation(300, 150);
		
		// specify action for close button
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		welcome = new WelcomePanel();
			
		add(welcome);
		setVisible(true);	
	
	}
}


