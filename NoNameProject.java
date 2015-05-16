
// ---------- Imported Packages ------------------
   import java.text.*;
   import java.util.*;
   import javax.swing.*;
   import javax.swing.event.ChangeListener;
   import javax.swing.event.ChangeEvent;
   import java.awt.*;
   import java.io.*;
   import java.awt.event.*;


public class NoNameProject
{


	public Container getGUI()
	{
		HospitalWindow x = new HospitalWindow();
		x.setVisible(false);
		return x.getContentPane();
		
	}
	
	public Container getApplet()
	{
		HospitalApplet2 x = new HospitalApplet2();
		x.init();
		x.start();
		x.setVisible(false);
		return x.getContentPane();
	}
	
	
	 private class Surgery extends Patient
	{
	   protected int surgery;    // Maximum number of passengers
	
	   /**
	      Constructor
	
	   */
	
	   Surgery(int d, int s)
	   {
	      // Call the superclass constructor 
	      // passing the days as arguments.
	      super(d);
	      
	      // Set surgery.
	      surgery = s;
	   }
	   
	
	   public void setSurgery(int s)
	   {
	      surgery = s;
	   }
	   
	
	   public int getSurgery()
	   {
	     	if(surgery == 1)
			{	
				surgery = 1500;
			}	
				
			if(surgery == 2)
			{	
				surgery = 1600;
			}
				
			if(surgery == 3)
			{	
				surgery = 1700;
			}
				
			if(surgery == 4)
			{	
				surgery = 1800;
			}
			if(surgery == 5)
			{	
				surgery = 1900;
			} 
			
			
			return surgery;
	   }
	
	
	   public String toString()
	   {
	      return "Rooms charges: " + getchargeforDays() + "\nSurgery charges: " +
	             surgery;
	   }
	}
	
	/**
   Patient class
*/

private class Patient
{
   protected int dailyRate=199;         // Patient dailyRate
   protected int noDays;            // number of days
   
   /**
      Constructor
   */
   public Patient(int d)
   {
      noDays = d;
   }
   
   /**
      setDays method 
   */
   public void setDays(int d)
   {
      noDays = d;
   }


   /**
      getchargeforDays method
   */
   public int getchargeforDays()
   { 
      return noDays*dailyRate;
   }
   
   /**
      toString method
   */
   public String toString()
   {
      return "The daily rate is: " + dailyRate + "\nand the number of days are: " +
             noDays+"\ntotal is: " + getchargeforDays() ;
   }
	
	
	
}

private class Pharmacy extends Surgery
{
   protected int pharmacy;    // Maximum number of passengers

   /**
      Constructor
   */

   Pharmacy(int d, int s, int p)
   {
      // Call the superclass constructor 
      // passing the days and surgery as arguments.
      super(d, s);
      
      // Set medication.
      pharmacy = p;
   }
   

   public void setPharmacy(int p)
   {
      pharmacy = p;
   }
   

   public int getPharmacy()
   {
     	if(pharmacy == 1)
		{	
			pharmacy = 45;
		}	
			
		if(pharmacy == 2)
		{	
			pharmacy = 50;
		}
			
		if(pharmacy == 3)
		{	
			pharmacy = 55;
		}
			
		if(pharmacy == 4)
		{	
			pharmacy = 60;
		}
		if(pharmacy == 5)
		{	
			pharmacy = 65;
		} 
		
		
		return pharmacy;
   }


   public String toString()
   {
      return "Rooms charges: $" + getchargeforDays() + "\nSurgery charges: $" +getSurgery()+
             "\nPharmacy charges: $" + getPharmacy()+
				 "\nTotal charges: $" + (getchargeforDays()+getSurgery()+getPharmacy());
   }
	
	
}

/**
   The PatientGUI class displays a JFrame that
   lets the user enter number of days spent in a hospital,
	type of medication, and type of surgery.
*/

private class PatientGUI extends JFrame
{
   private JPanel panel;             // To reference a panel
   private JLabel messageLabel1;      // To reference a label
	private JLabel messageLabel2;
	private JLabel messageLabel3;

   private JButton calcButton;       // To reference a button
   private final int WINDOW_WIDTH = 260;  // Window width
   private final int WINDOW_HEIGHT = 300; // Window height
	
	private JTextField kiloTextField1;
	private JTextField day; // To reference a text field
	
	private JTextField kiloTextField2;
	private JTextField sur; // To reference a text field
	
	private JTextField kiloTextField3;
	private JTextField phar; // To reference a text field

   /**
      Constructor
   */

   public PatientGUI()
   {
      // Set the window title.
      setTitle("Welcome to MD COSC1437");

      // Set the size of the window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Specify what happens when the close button is clicked.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Build the panel and add it to the frame.
      buildPanel();

      // Add the panel to the frame's content pane.
      add(panel);

      // Display the window.
      setVisible(true);
   }

   /**
      The buildPanel method adds a label, text field, and
      and a button to a panel.
   */

   private void buildPanel()
   {
      // Create a label to display instructions.
      messageLabel1 = new JLabel("         Enter number of days:           ");
		kiloTextField1 = new JTextField(13);
		day = new JTextField(10);
		
		messageLabel2 = new JLabel("Choose type of surgery   (1,2,3,4, or 5):");
      kiloTextField2 = new JTextField(13);
		sur = new JTextField(10);
		 										  
      messageLabel3 = new JLabel("Choose type of medication(1,2,3,4, or 5):");
      kiloTextField3 = new JTextField(13);
		phar = new JTextField(10);
		       
		// Create a text field 10 characters wide.

      // Create a button with the caption "Calculate".
      calcButton = new JButton("Check out");

      // Add an action listener to the button.
      calcButton.addActionListener(new CalcButtonListener());

      // Create a JPanel object and let the panel
      // field reference it.
      panel = new JPanel();

      // Add the label, text field, and button
      // components to the panel.
      panel.add(messageLabel1);
		panel.add(kiloTextField1);
		//panel.add(day);
		
		panel.add(messageLabel2);
		panel.add(kiloTextField2);
		//panel.add(sur);
		
		panel.add(messageLabel3);
      panel.add(kiloTextField3);
		//panel.add(phar);
      
		panel.add(calcButton);
   }

   /**
      CalcButtonListener is an action listener class for
      the Calculate button.
   */

   private class CalcButtonListener implements ActionListener
   {
      /**
         The actionPerformed method executes when the user
         clicks on the Calculate button.
         @param e The event object.
      */

      public void actionPerformed(ActionEvent e)
      {
         String input1;  // To hold the user's input
         int days;  // The number of days
         String input2;  // To hold the user's input
         int surg;  // The type of surgery
         String input3;  // To hold the user's input
         int pharm;  // The type of medication			
						

         // Get the text entered by the user into the
         // text field.
         input1 = kiloTextField1.getText();
			input2 = kiloTextField2.getText();
			input3 = kiloTextField3.getText();
         
         // For debugging, display the text entered, and
         // its value converted to a double.
         System.out.println("Reading " + input1 + input2 + input3);


         // Convert the input to miles.
                  // Convert the input to miles.
         days = Integer.parseInt(input1);
		   day.setText(String.valueOf(days));
			
         surg = Integer.parseInt(input1);
			sur.setText(String.valueOf(surg));

         pharm = Integer.parseInt(input1);
			phar.setText(String.valueOf(days));						
			
			
         Pharmacy patientX = new Pharmacy( days, surg, pharm);

         // Display the result.
         JOptionPane.showMessageDialog(null," Your total is:\n" + patientX + ".","Conversion",JOptionPane.INFORMATION_MESSAGE );


      }
   } // End of CalcButtonListener class

   /**
      The main method creates an instance of the
      PatientGUI class, which displays
      its window on the screen.
   */


}

private class SurgeryPanel extends JPanel
{
	public final int CARDIO = 1500;
	public final int TRAUMA = 2000;
	public final int PLASTIC = 2500;
	public final int ORAL = 3000;
	public final int SKIN = 3500;
	
	private JRadioButton cardio;
	private JRadioButton trauma;
	private JRadioButton plastic;
	private JRadioButton oral;
	private JRadioButton skin;
	private ButtonGroup bg;
	
	public SurgeryPanel()
	{
		setLayout(new GridLayout(5, 1));
		
		cardio = new JRadioButton("CardioVascular");
		trauma = new JRadioButton("Trauma");
		plastic = new JRadioButton("Plastic");
		oral = new JRadioButton("Oral");
		skin = new JRadioButton("Skin");
		
		bg = new ButtonGroup();
		bg.add(cardio);
		bg.add(trauma);
		bg.add(plastic);
		bg.add(oral);
		bg.add(skin);
		
		setBorder(BorderFactory.createTitledBorder("Surgery"));
		
		add(cardio);
		add(trauma);
		add(plastic);
		add(oral);
		add(skin);
		 
	}
	public int getSurgeryCost()
	{
		int surgeryCost = 0;
		
		if (cardio.isSelected())
			surgeryCost += CARDIO;
		if (trauma.isSelected())
			surgeryCost += TRAUMA;
		if (plastic.isSelected())
			surgeryCost += PLASTIC;
		if (oral.isSelected())
			surgeryCost += ORAL;
		if (skin.isSelected())
			surgeryCost += SKIN;
				
		return surgeryCost;
	}
}

private class GreetingPanel extends JPanel
{
	private JLabel greeting; 
	
	public GreetingPanel()
	{
		greeting = new JLabel("Patient Report");
		
		add(greeting);
	}
}

private class DaysPanel extends JPanel
{
	private JTextField daysTextField;
	private JLabel daysLabel;
	final int dailyRate=199;     

	public DaysPanel()
	{	
		setLayout(new FlowLayout());
		
		daysLabel = new JLabel("How many days did you " +
											"stay in the hospital?");
											
		daysTextField = new JTextField(4);
		
		setBorder(BorderFactory.createTitledBorder("Days"));
		
		add(daysLabel);
		add(daysTextField);
	}
	public int getDays()
	{
		String input;
		int result = 0;
		
		input = daysTextField.getText();
		
		result = Integer.parseInt(input) * dailyRate;
		
		return result;
	}	
}

private class HospitalWindow extends JFrame
{
	private DaysPanel days;
	private MedicationPanel med;
	private SurgeryPanel sur;
	private GreetingPanel banner;
	private JButton checkOut, exit;
	private JPanel buttonPanel;
	
	public HospitalWindow()
	{
		setTitle("Patient Form");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		banner = new GreetingPanel();
		med = new MedicationPanel();
		sur = new SurgeryPanel();
		days = new DaysPanel();
		
		buildButtonPanel();
		
		add(banner, BorderLayout.NORTH);
		add(days, BorderLayout.WEST);
		add(med, BorderLayout.CENTER);
		add(sur, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
		
	private void buildButtonPanel()
	{
		buttonPanel = new JPanel();
	
		JButton checkOut = new JButton("Check out");
		JButton exit = new JButton("Exit");
		
		checkOut.addActionListener(new CheckOutButtonListener());
		exit.addActionListener(new ExitButtonListener());
		
		buttonPanel.add(checkOut);
		buttonPanel.add(exit);	
	
	}
	private class CheckOutButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null,"Room charges: $" + days.getDays() + "\nSurgery charges: $" + sur.getSurgeryCost()+
             "\nPharmacy charges: $" + med.getMedicationCost()+
				 "\nTotal charges: $" + (days.getDays()+sur.getSurgeryCost()+med.getMedicationCost()));
		}
	}	
	
	private class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
			
}


private class HospitalApplet2 extends JApplet
{
	private DaysPanel days;					//Days Panel
	private MedicationPanel med;			//Medication Panel
	private SurgeryPanel sur;				//Surgery Panel
	private GreetingPanel banner;			//Greeting Panel
	private JButton checkOut, exit;		//To calculate the total and exit
	private JPanel buttonPanel;			//To hold the buttons
	
	/**
		Constructor
	*/
	
	public void init()
	{	
		//Display Title
		//setTitle("Patient Form");Not necesary for applet
		
		//Specify an action for the close button.
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); not necesary for applet
		
		//Create a BorderLayout manager.
		setLayout(new BorderLayout());
		
		//Create the custom panels.
		banner = new GreetingPanel();
		med = new MedicationPanel();
		sur = new SurgeryPanel();
		days = new DaysPanel();
		
		//Creates the button panel.
		buildButtonPanel();
		
		//Adds the components to the content pane.
		add(banner, BorderLayout.NORTH);
		add(days, BorderLayout.WEST);
		add(med, BorderLayout.CENTER);
		add(sur, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		
		//Packs the contents of the window and display it.
		//pack(); not needed for applet
		setVisible(true);
	}
	
	/**
		The buildButtonPanel method builds the button panel.
	*/
	
	private void buildButtonPanel()
	{
		//Creates a panel for the buttons.
		buttonPanel = new JPanel();
		
		//Creates the butrons.
		JButton checkOut = new JButton("Check out");
		JButton exit = new JButton("Exit");
		
		//Registers the action listeners.
		checkOut.addActionListener(new CheckOutButtonListener());
		exit.addActionListener(new ExitButtonListener());
		
		//Adds the buttons to the button panel.
		buttonPanel.add(checkOut);
		//buttonPanel.add(exit);	not nee
	
	}
	
	/**
		Private inner class that handles the event when 
		the user clicks the Check Out button.
	*/
	
	private class CheckOutButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//Displays the charges.
			JOptionPane.showMessageDialog(null,"Room charges: $" + days.getDays() + "\nSurgery charges: $" + sur.getSurgeryCost()+
             "\nPharmacy charges: $" + med.getMedicationCost()+
				 "\nTotal charges: $" + (days.getDays()+sur.getSurgeryCost()+med.getMedicationCost()));
		}
	}	
	
	/**
		Private inner class that handles the event when
		the user clicks the Exit button.
	*/
	
	private class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	/**
		This program creates an instance of the HospitalWindow class
		which displays the GUI for the Patient Form application.
	*/
	

			
}
private class HospitalWindow2 extends JApplet
{
	private DaysPanel days;
	private MedicationPanel med;
	private SurgeryPanel sur;
	private GreetingPanel banner;
	private JButton checkOut, exit;
	private JPanel buttonPanel;
	
	public void init()
	{
		//setTitle("Patient Form");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		banner = new GreetingPanel();
		med = new MedicationPanel();
		sur = new SurgeryPanel();
		days = new DaysPanel();
		
		buildButtonPanel();
		
		add(banner, BorderLayout.NORTH);
		add(days, BorderLayout.WEST);
		add(med, BorderLayout.CENTER);
		add(sur, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		
		//pack();
		setVisible(true);
	}
	private class HospitalApplet extends JApplet
{
	private DaysPanel days;
	private MedicationPanel med;
	private SurgeryPanel sur;
	private GreetingPanel banner;
	private JButton checkOut, exit;
	private JPanel buttonPanel;
	
	public void init()
	{
		//setTitle("Patient Form");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		banner = new GreetingPanel();
		med = new MedicationPanel();
		sur = new SurgeryPanel();
		days = new DaysPanel();
		
		buildButtonPanel();
		
		add(banner, BorderLayout.NORTH);
		add(days, BorderLayout.WEST);
		add(med, BorderLayout.CENTER);
		add(sur, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		
		//pack();
		setVisible(true);
	}
		
	private void buildButtonPanel()
	{
		buttonPanel = new JPanel();
	
		JButton checkOut = new JButton("Check out");
		JButton exit = new JButton("Exit");
		
		checkOut.addActionListener(new CheckOutButtonListener());
		//exit.addActionListener(new ExitButtonListener());
		
		buttonPanel.add(checkOut);
		//buttonPanel.add(exit);	
	
	}
	private class CheckOutButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null,"Room charges: $" + days.getDays() + "\nSurgery charges: $" + sur.getSurgeryCost()+
             "\nPharmacy charges: $" + med.getMedicationCost()+
				 "\nTotal charges: $" + (days.getDays()+sur.getSurgeryCost()+med.getMedicationCost()));
		}
	}	
	
	/*private class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		//	System.exit(0);
		}
	}*/

			
}
	private void buildButtonPanel()
	{
		buttonPanel = new JPanel();
	
		JButton checkOut = new JButton("Check out");
		JButton exit = new JButton("Exit");
		
		checkOut.addActionListener(new CheckOutButtonListener());
		//exit.addActionListener(new ExitButtonListener());
		
		buttonPanel.add(checkOut);
		//buttonPanel.add(exit);	
	
	}
	private class CheckOutButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null,"Room charges: $" + days.getDays() + "\nSurgery charges: $" + sur.getSurgeryCost()+
             "\nPharmacy charges: $" + med.getMedicationCost()+
				 "\nTotal charges: $" + (days.getDays()+sur.getSurgeryCost()+med.getMedicationCost()));
		}
	}	
	
	/*private class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		//	System.exit(0);
		}
	}*/
			
}

private class PatientApplet extends JApplet
{
	private JTextField daysTextField, surgeryTextField, medTextField;
	private JTextField day, sur, phar;
	private JButton checkOut, exit;
	
	public void init()
	{
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton checkOut = new JButton("Check out");
		JButton exit = new JButton("Exit");
		
		JLabel label1 = new JLabel("How many days did you " +
											"stay in the hospital?");
		daysTextField = new JTextField(4);
		day = new JTextField(10);
											
		JLabel label2 = new JLabel("Please choose the type of surgery" +
											" that was performed? ");
		surgeryTextField = new JTextField(4);
		sur = new JTextField(10);
											
		JLabel label3 = new JLabel("Please choose the medication" +
											" that was taken?");
		medTextField = new JTextField(4);
		phar = new JTextField(10);
											
		checkOut.addActionListener(new CheckOutButtonListener());																											
											
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();	
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();			
		
		panel.add(label1);
		panel.add(daysTextField);
		panel2.add(label2);
		panel2.add(surgeryTextField);	
		panel3.add(label3);		
		panel3.add(medTextField);
		panel4.add(checkOut);	
		//panel4.add(exit);								
		
		add(panel);
		add(panel2);
		add(panel3);
		add(panel4);
	
	}
	private class CheckOutButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int days, surg, pharm;
			String input1, input2, input3;
			
			input1 = daysTextField.getText();
			input2 = surgeryTextField.getText();
			input3 = medTextField.getText();
			
			days = Integer.parseInt(input1);
		   day.setText(String.valueOf(days));
			
         surg = Integer.parseInt(input1);
			sur.setText(String.valueOf(surg));

         pharm = Integer.parseInt(input1);
			phar.setText(String.valueOf(days));
				
			Pharmacy patientX = new Pharmacy( days, surg, pharm);
			
			JOptionPane.showMessageDialog(null," Your total bill is: \n" + patientX + ".");
			
			System.out.println("Ready for the next input.");
			
		}
	}
		
	
}

private class MedicationPanel extends JPanel
{
	public final int STEROID_M = 45;
	public final int ANTIBIOTIC = 50;
	public final int MUSCLE_RELAX = 55;
	public final int ANTIBAC = 60;
	public final int ANTIART = 65;
	
	private JCheckBox steroid;
	private JCheckBox antibiotic;
	private JCheckBox muscleRelax;
	private JCheckBox antiBac;
	private JCheckBox antiArt;
	
	public MedicationPanel()
	{
		setLayout(new GridLayout(5, 1));
		
		steroid = new JCheckBox("Steroid");
		antibiotic = new JCheckBox("Antibiotic");
		muscleRelax = new JCheckBox("Muscle Relazer");
		antiBac = new JCheckBox("Antibacterial");
		antiArt = new JCheckBox("Antiarrythmics");
		
		setBorder(BorderFactory.createTitledBorder("Medication"));
		
		add(steroid);
		add(antibiotic);
		add(muscleRelax);
		add(antiBac);
		add(antiArt);
		
	}
	public int getMedicationCost()
	{
		int medCost = 0;
		
		if (steroid.isSelected())
			medCost += STEROID_M;
		if (antibiotic.isSelected())
			medCost += ANTIBIOTIC;
		if (muscleRelax.isSelected())
			medCost += MUSCLE_RELAX;
		if (antiBac.isSelected())
			medCost += ANTIBAC;
		if (antiArt.isSelected())
			medCost += ANTIART;
				
		return medCost;
	}
}

}