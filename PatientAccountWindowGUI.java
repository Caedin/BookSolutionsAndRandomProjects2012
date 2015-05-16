// Chris Stupka and Chrstian Reyes Project  
// imports classes needed for PaitientAccountWindowGUIimport java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;                    
import java.awt.event.*;
import java.text.DecimalFormat;

// This is the GUI class

public class PatientAccountWindowGUI extends JFrame 
{
	// Constant to indicate the daily rate
	private final double Daily_Rate = 100.0;              	
	
	// Declares panel objects
	private patientName patient;
	private surgeryPanel2 surgery; 
	private medicinePanel medicine;       
	private DaysSpent daysSpent; 		
	
	// Declares button objects
	private JPanel buttonPanel;          
	private JButton checkoutButton;
	
	public PatientAccountWindowGUI()
   {
		// Sets name of the Title
      setTitle("Hospital");
 		
		// Operation used when you cilck X
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
		 
		// makes border layout
		setLayout(new BorderLayout());                        
		
	  // Create panel Objects
		patient = new patientName();
      surgery = new surgeryPanel2();
      medicine = new medicinePanel();
		daysSpent = new DaysSpent();
		
		// builds Button panel
      buildButtonPanel();

		// sets which direction the buttons will be placed
      add(patient, BorderLayout.NORTH);
      add(surgery, BorderLayout.CENTER);		
      add(medicine, BorderLayout.EAST);
  		add(daysSpent, BorderLayout.WEST);    
		add(buttonPanel, BorderLayout.SOUTH);
		
		// makes window visible 
		pack();
		setVisible(true);
	}
	  // builds the button panel
		private void buildButtonPanel()
		{
			buttonPanel = new JPanel();
		
		// Button users clicks to checkout
      checkoutButton = new JButton("Checkout");
		// adds action listener
      checkoutButton.addActionListener(new CheckoutButtonListener());
		// intializes button to checkout
      buttonPanel.add(checkoutButton);

		}
		
	private class CheckoutButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
			// Declares variabales used in calculations 
			String name;
         double medicationCost;
			double surgeryCost;
			double dailyCost;
			double total;
			 
			// Get the sub total costs
			medicationCost = medicine.getMedicineCost();
			dailyCost = (Daily_Rate * daysSpent.getDays());
			surgeryCost = surgery.getSurgeryCost();
			 
			 // Gets total 
			total = medicationCost + surgeryCost + dailyCost;
			 
			//creates DecimalFormat object for money
			DecimalFormat cool = new DecimalFormat("#,##0.00");
			
			// Displays Message
			JOptionPane.showMessageDialog(null, "" + patient.getName() + "\n\n" + "Hostpital Care Charge: $" + cool.format(dailyCost) + "\nSurgery Charge: $" + surgeryCost +
									"\nMedication Charges Totaled: $" + cool.format(medicationCost) + "\nTotal: $" + cool.format(total));
      }
   }
}