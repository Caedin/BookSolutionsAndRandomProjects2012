package salazar.meyer;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** The Patient Panel allows input about the patient and the days in hospital */

public class PatientPanel extends JPanel
{
	private String name;									// Patients Name
	private int days;										// days spent in hospital
	final private double DAILY_RATE = 949.00;		// charge for one day hospital
	
	private String daysInput;							// for user input
	private PatientAccount patient10001;			// to hold the patient object
		
	// create the Lables and fields	
	private JLabel nameLabel;
	private JLabel daysLabel;
	private JLabel rateLabel;
	private JTextField nameField;
	private JTextField daysField;
	
			
	// constructor accepts the patient object
	public PatientPanel(PatientAccount pat)
	{
		patient10001 = pat;
	
		// create a grid Layout with 3 rows and 1 column
		setLayout(new GridLayout(3,1));
		
		// create the labels to display info from welcomeGUI
		nameLabel = new JLabel(patient10001.getName());
		daysLabel = new JLabel("Days in hospital: " + patient10001.getDays());			
		
		// add a border to the panel
		setBorder(BorderFactory.createTitledBorder("Patient"));
		
		// add components to the panel
		add(nameLabel);
		add(daysLabel);
	}
}