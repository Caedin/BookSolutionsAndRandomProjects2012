package salazar.meyer;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** The SurgeryPanel displays the available procedures */

public class SurgeryPanel extends JPanel
{
	private JRadioButton appen;
	private JRadioButton tons;
	private JRadioButton breast;
	private JRadioButton cardio;	
	private JRadioButton lasik;
	
	private Surgery thisSurgery = null;								// to create the surgery object
	private static PatientAccount patient10001 = null;			// to hold the patient object 

		
	// constructor accepts the patient object
	public SurgeryPanel(PatientAccount pat)
	{
		patient10001 = pat;
	
		// create a grid Layout with 5 rows and 1 column
		setLayout(new GridLayout(5,1));
		
		// create the radio buttons
		appen = new JRadioButton("Appendectomy");
		tons = new JRadioButton("Tonsillectomy");
		breast = new JRadioButton("Breast Augmentation");
		cardio = new JRadioButton("Cardio Bypass");
		lasik = new JRadioButton("Lasik");
		
		// create a button group object
		ButtonGroup group = new ButtonGroup();

						
		// add checkboxes to the panel
		group.add(appen);
		group.add(tons);
		group.add(breast);
		group.add(cardio);
		group.add(lasik);
				
		// add checkboxes to the panel
		add(appen);
		add(tons);
		add(breast);
		add(cardio);
		add(lasik);
		
		// add a border to the panel
		setBorder(BorderFactory.createTitledBorder("Procedures"));
	}
	
	public static void setPatient(PatientAccount pat)
	{
		patient10001 = pat;
	}	

	/** select surgery creates the pharmacy objects according to the selcted medications */
	public void selectSurgery()
	{				
		if (appen.isSelected())
		{
			thisSurgery = new Appendectomy(patient10001);
			patient10001.setProcedure(thisSurgery);
			patient10001.setSurgeryPrice(thisSurgery.getSurgeryCharge());
			thisSurgery.addToTotal();
		}	
					
		if (tons.isSelected())
		{
			thisSurgery = new Tonsillectomy(patient10001);
			patient10001.setProcedure(thisSurgery);
			patient10001.setSurgeryPrice(thisSurgery.getSurgeryCharge());
			thisSurgery.addToTotal();
		}
		
		if (breast.isSelected())
		{
			thisSurgery = new BreastAugmentation(patient10001);
			patient10001.setProcedure(thisSurgery);
			patient10001.setSurgeryPrice(thisSurgery.getSurgeryCharge());
			thisSurgery.addToTotal();
		}			
		
		if (cardio.isSelected())
		{
			thisSurgery = new CardioBypass(patient10001);
			patient10001.setProcedure(thisSurgery);
			patient10001.setSurgeryPrice(thisSurgery.getSurgeryCharge());
			thisSurgery.addToTotal();
		}			
		
		if (lasik.isSelected())
		{
			thisSurgery = new Lasik(patient10001);
			patient10001.setProcedure(thisSurgery);
			patient10001.setSurgeryPrice(thisSurgery.getSurgeryCharge());
			thisSurgery.addToTotal();
		}			
	}	
}