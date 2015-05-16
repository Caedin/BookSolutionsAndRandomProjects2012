package salazar.meyer;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** The Patient Panel allows input about the patient and the days in hospital */


public class WelcomePanel extends JPanel
{
	private String name;									// Patients Name
	private int days;										// days spent in hospital
	final private double DAILY_RATE = 949.00;		// charge for one day hospital
	
	private PatientAccount patient10001; 			// to create the patient object and send into GUI
		
	private JLabel nameLabel;							
	private JLabel daysLabel;
	private JLabel rateLabel;
	private JTextField nameField;
	private JTextField daysField;
	
			
	/** constructor */
	public WelcomePanel()
	{		
		// create a grid Layout with 3 rows and 1 column
		setLayout(new GridLayout(3,2));
		
		// create the labels and textfields
		nameLabel = new JLabel("Patient's name: ");
		nameField = new JTextField(12);
		daysLabel = new JLabel("Days in hospital: ");
		daysField = new JTextField(3);
	
		// add a border to the panel
		setBorder(BorderFactory.createTitledBorder("Patient"));
		
		// create the PatientButton
		JButton PatientButton = new JButton("Please Confirm Patient");
		PatientButton.setBackground(Color.ORANGE);
		PatientButton.addActionListener(new PatientButtonListener());   
		
		// add components to the panel
		add(nameLabel);
		add(nameField);
		add(daysLabel);
		add(daysField);
		add(PatientButton);
		
	}
	
	private class PatientButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// get Patient input values
			name = nameField.getText();
			try
			{
				days = Integer.parseInt(daysField.getText());	
				
				// create the PatientAccount object
			   patient10001 = new PatientAccount(name, days, DAILY_RATE);
			   new GUI(patient10001);
			}
			catch (NumberFormatException f)
			{
				JOptionPane.showMessageDialog(null, "Wrong format " + f.getMessage());
			}	
			
			

		}	
	} 
}