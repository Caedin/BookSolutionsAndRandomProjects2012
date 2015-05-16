package salazar.meyer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** The PharmaPanel displays the available medications */

public class PharmaPanel extends JPanel
{
	private JCheckBox anesthesia;
	private JCheckBox icecream;
	private JCheckBox plastic;
	private JCheckBox cardio;	
	private JCheckBox eyedrops;
	
	private Pharmacy Medication1 = null;					// to create the medication object
	private Pharmacy Medication2 = null;					// to create the medication object
	private Pharmacy Medication3 = null;					// to create the medication object
	private Pharmacy Medication4 = null;					// to create the medication object
	private Pharmacy Medication5 = null;					// to create the medication object
	private static PatientAccount patient10001 = null;	// to hold the patient object 
	
		
	/** constructor */
	public PharmaPanel(PatientAccount pat)
	{
		patient10001 = pat;
		
		// create a grid Layout with 5 rows and 1 column
		setLayout(new GridLayout(5,1));
		
		// create the checkboxes
		anesthesia = new JCheckBox("Anesthesia");
		icecream = new JCheckBox("Icecream");
		plastic = new JCheckBox("Plastic Surgery package");
		cardio = new JCheckBox("Cardio package");
		eyedrops = new JCheckBox("Eyedrops");
				
		// add a border to the panel
		setBorder(BorderFactory.createTitledBorder("Medications"));
		
		// add checkboxes to the panel
		add(anesthesia);
		add(icecream);
		add(plastic);
		add(cardio);
		add(eyedrops);
	}
	
	public static void setPatient(PatientAccount pat)
	{
		patient10001 = pat;
	}	
	
	/** select pharmacy creates the pharmacy objects according to the selcted medications */
	public void selectPharmacy()
	{
		if (anesthesia.isSelected())
		{
			Medication1 = new Anesthesia(patient10001);
			Medication1.addToTotal();
		}	
			
		if (icecream.isSelected())
		{
			Medication2 = new Icecream(patient10001);
			Medication2.addToTotal();
		}	
		
		if (plastic.isSelected())
		{
			Medication3 = new PlasticSurgeryPackage(patient10001);
			Medication3.addToTotal();
		}			
		
		if (cardio.isSelected())
		{
			Medication4 = new CardioPackage(patient10001);
			Medication4.addToTotal();
		}			
		
		if (eyedrops.isSelected())
		{
			Medication5 = new Eyedrops(patient10001);
			Medication5.addToTotal();
		}			
	}
}