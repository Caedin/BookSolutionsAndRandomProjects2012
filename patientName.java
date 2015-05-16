// Chris Stupka and Chrstian Reyes Project 
// imports classes needed for patientName
import java.awt.*;	
import javax.swing.*;

// Makes the patient input panel 

public class patientName extends JPanel
{
	private JTextField patientF; 

public patientName()
{  
	// Sets max characters in the text field to 20
	patientF = new JTextField(20);
	
	// Sets the layout to have 1 row and 1 column
	setLayout(new GridLayout (1,1));
	
	// sets the border and title 
	setBorder(BorderFactory.createTitledBorder("Patient Name"));
	
	add(patientF);
	
}
public String getName()
{		
	// returns the text inputed
	return patientF.getText();
}
}