package salazar.meyer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** The GreetingPanel displays a message in a panel */

public class OutputPanel extends JPanel
{
	PatientAccount patient10001;

	private JLabel line1, line2, line3, line4, line5;

	
	/** constructor */
	public OutputPanel(PatientAccount pat)
	{
		
		// create a grid Layout with 5 rows and 1 column
		setLayout(new GridLayout(5,1));
		
		patient10001 = pat;
		
		// create the message
		line1 = new JLabel("Patient :    " + patient10001.getName());
		line2 = new JLabel("Procedure :    " + patient10001.getProcedure());
		line3 = new JLabel("Charges for Pharmacy:  $ " + (patient10001.getTotal() - (patient10001.getDays() * patient10001.getRate()) - patient10001.getSurgeryCharge()));
		line4 = new JLabel(patient10001.getDays() + " days in hospital for $ " + patient10001.getRate() + " each. - Total: $ " + patient10001.getRate() * patient10001.getDays());	
		line5 = new JLabel("Your total bill :  $ " +  patient10001.getTotal());	
		
		// add a border to the panel
		setBorder(BorderFactory.createTitledBorder("Thank you for your trust!"));
		
		// add the label to this panel
		add(line1);
		add(line2);
		add(line3);
		add(line4);	
		add(line5);

	}
}