package salazar.meyer;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// the GUI version of COSC1437 final project

public class GUI extends JFrame
{
	private PatientPanel patient;			// panel for patient's name and spent days
	private SurgeryPanel surgery;			// panel for the routine maintenances
	private PharmaPanel pharmacy;			// panel for selectable medications
	private OutputPanel output;			// to display the total cost and information
	private GreetingPanel greeting;
	private JPanel buttonPanel;			// holds the calculate button
	private JButton calcButton;			// execute button
	private double total;
	
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 350;
	
	private PatientAccount patient10001;
	
	
	// constructor
	public GUI(PatientAccount pat)
	{
		patient10001 = pat;
	
		// display a title
		setTitle("SalaMey Clinic");
		
		// set Window Location
		setLocation(300, 150);
		
		// specify action for close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create a BorderLayout manager
		setLayout(new BorderLayout());
		

		
		// create the custom panels
		patient = new PatientPanel(patient10001);
		surgery = new SurgeryPanel(patient10001);
		pharmacy = new PharmaPanel(patient10001);
		greeting = new GreetingPanel();
		
		// create the button panel
		buildButtonPanel();
		
		// set background coor for patient info
		patient.setBackground(Color.WHITE);
		greeting.setBackground(Color.ORANGE);

		
		// add components to the panel
		add(greeting, BorderLayout.NORTH);
		add(patient, BorderLayout.WEST);
		add(surgery, BorderLayout.CENTER);
		add(pharmacy, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
				
		// pack contents and display
		pack();
		setVisible(true);		
	}
	
	/** Build the Button Panel */
	private void buildButtonPanel()
	{
		// create the panel
		buttonPanel = new JPanel();
		
		// create the calc button
		calcButton = new JButton("Check out and display the total cost!");
		calcButton.setBackground(Color.BLUE);
		calcButton.setForeground(Color.WHITE);
		
		// register action listener
		calcButton.addActionListener(new CalcButtonListener());
		
		// add button to the button panel
		buttonPanel.add(calcButton);
	}
	
	// private inner class - event listener implements ActionListener 
	private class CalcButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		
			surgery.selectSurgery();
			pharmacy.selectPharmacy();
			
			new outputGUI(patient10001);
			/**
			JOptionPane.showMessageDialog(null, patient10001.getProcedure() + "\nDays in Hospital: " + patient10001.getDays() +"\nCost for Medications: $ " +
													(patient10001.getTotal() - (patient10001.getDays() * patient10001.getRate()) - patient10001.getSurgeryCharge())	+
													"\n\ntotal $: " + patient10001.getTotal());  */

		}
	}
}

//
