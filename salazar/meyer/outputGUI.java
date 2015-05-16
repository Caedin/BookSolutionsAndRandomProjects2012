package salazar.meyer;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// the GUI version of COSC1437 final project

public class outputGUI extends JFrame
{
	private OutputPanel output;			// display the final information
		
	private final int WINDOW_WIDTH = 350;
	private final int WINDOW_HEIGHT = 350;
	
	private PatientAccount patient10001;
	
	
	// constructor
	public outputGUI(PatientAccount pat)
	{
		patient10001 = pat;
			
		// display a title
		setTitle("Checkout Summary");
		
		// set Window Size and Location
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocation(300, 150);
		
		// specify action for close button
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		output = new OutputPanel(patient10001);
		output.setBackground(Color.WHITE);
		output.setForeground(Color.RED);		
		
				
		add(output);
		setVisible(true);		
	}
	

}

