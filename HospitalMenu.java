import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class HospitalMenu extends JFrame
{
	private JPanel SurgeryPanel;
	private JPanel selectedSurgeryPanel;
	private JComboBox SurgeryBox;
	private JLabel label;
	private JButton B1;
	private JTextField selectedSurgery;
	
	//types of surgery to choose from in a cocmbo box.
	private String[] surgeryType = {"Heart Transplant"/*16 chr**/, "Limb Reatachment"/*16 chr**/,
											  "Kidney Transplant"/*17 chr**/, "Cataract Repair"/*14 chr**/,
											  "Multiple Sclerosis"/*18 chr**/};
	
	public HospitalMenu()
	{
	  	setTitle("Welcome to St. Draven Hospital");
	  	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	setLayout(new BorderLayout());
	  
	  	buildSurgeryPanel();
	  	buildSelectedSurgeryPanel();
	  
	  	add(SurgeryPanel, BorderLayout.SOUTH);
	  	add(selectedSurgeryPanel, BorderLayout.CENTER);
	  
	  	pack();
	  	setVisible(true);
	}
	
	private void buildSurgeryPanel()
	{
		SurgeryPanel = new JPanel();
		B1 = new JButton ("Next");
		SurgeryBox = new JComboBox(surgeryType);
		
		B1.setEnabled(false);
	  
		SurgeryBox.addActionListener(new ComboBoxListener());
	 	B1.addActionListener(new ButtonListener());
	  
	 	SurgeryPanel.add(SurgeryBox);
		SurgeryPanel.add(B1);
	}

	private void buildSelectedSurgeryPanel()
	{
	  	selectedSurgeryPanel = new JPanel();
	  	label = new JLabel("You Selected: ");
	  	selectedSurgery = new JTextField(20);
	  
	  	selectedSurgery.setEditable(false);
	  
	  	selectedSurgeryPanel.add(label);
	  	selectedSurgeryPanel.add(selectedSurgery);
	}
	
	private class ComboBoxListener implements ActionListener
	{
	  	public void actionPerformed(ActionEvent e)
	  	{
	   	String selection = (String) SurgeryBox.getSelectedItem();
		 
		 	selectedSurgery.setText(selection);
			
			B1.setEnabled(true);
	  	}
	}
	
	private class ButtonListener implements ActionListener
	{
		Pharmacy Meds = new Pharmacy();
		
		public void actionPerformed (ActionEvent e)
		{
			String selected;
			
			selected = (String)SurgeryBox.getSelectedItem();
			
			Meds.medication();
			setVisible(false);
		}
	}
	
	private class Surgery extends PatientAccount
	{
	  //stores charges of @ least 5 surgeries
		private String[] surgeryType = {"Heart Transplant"/*16 chr**/, "Limb Reatachment"/*16 chr**/,
												  "Kidney Transplant"/*17 chr**/, "Cataract Repair"/*14 chr**/,
												  "Multiple Sclerosis"/*18 chr**/};
		
	  //updates charges in PatientAccount
	  //only be able to select one surgery usign GUI
	  /*Prices:
	      heart transplant = 5400.00
			limb reatachment = 1875.00
			kidney transplant = (1 kidney) 2280.00, (2 kidneys) 4560.00
			cataract repair = 10000.00(1 eye), 20000.00(2 eyes)
			multiple Sclerosis = 1580.00**/
		
		public void CheckOut()
		{
			String input;
			int days;
			
			input = JOptionPane.showInputDialog("How many days were you in the Hospital?");
				days = Integer.parseInt(input);
				
			setDaysIn(days);
			
			
		}
	}
	
		
	private class Pharmacy extends JFrame
	{
	  	//store prices of @ least 5 medications
	  	/*private String[] meds = {"Anesthesia", "Codeine", "Ridilyn",
	  									"Orphenadrine", "Dofetilide", "Hydroxyzine", "Baclofen"};*/
		public final double Anesthesia = 440;
		public final double Codeine = 590;
		public final double Ridilyn = 500;
		public final double Orphenadrine = 360;
		public final double Dofetilide = 570;
		public final double Hydroxyzine = 700;
		public final double Baclofen = 670;
		
		private JPanel MedsPanel;
		private JCheckBox box1;
		private JCheckBox box2;
		private JCheckBox box3;
		private JCheckBox box4;
		private JCheckBox box5;
		private JCheckBox box6;
		private JCheckBox box7;
		private JButton B1;
		
		//update charges in PatientAccount
	  	//GUI to be able to select multiple medications.
	  	//Peter select prices for the medications i'll select prices for surgery.
		public Pharmacy()
		{
			setTitle("Welcome to St. Draven Hospital");
		  	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  	setLayout(new GridLayout());
		  	setLayout(new GridLayout(1,7));
			
		  	buildMedsPanel();
		  
		  	add(MedsPanel);
		  
		  	pack();
		}
		
		private void buildMedsPanel()
		{
			String title = "";
			MedsPanel= new JPanel();
			
			box1 = new JCheckBox("Anesthesia");
			box2 = new JCheckBox("Codeine");
			box3 = new JCheckBox("Ridilyn");
			box4 = new JCheckBox("Orphenadrine");
			box5 = new JCheckBox("Dofetilide");
			box6 = new JCheckBox("Hydroxyzine");
			box7 = new JCheckBox("Baclofen");
			B1 = new JButton ("Next");
			
			B1.addActionListener(new ButtonListener());
			
			BorderFactory.createTitledBorder(title);
			
			MedsPanel.setBorder(BorderFactory.createTitledBorder("Check all medications needed"));
			
			MedsPanel.add(box1);
			MedsPanel.add(box2);
			MedsPanel.add(box3);
			MedsPanel.add(box4);
			MedsPanel.add(box5);
			MedsPanel.add(box6);
			MedsPanel.add(box7);
			MedsPanel.add(B1);
		}
			
		public void medication()
		{
			new Pharmacy();
			
			setVisible(true);
		}
		
		public void price()
		{
			double total = 0;
			
			if(box1.isSelected())
				total += Anesthesia;
			
			if(box2.isSelected())
				total += Codeine;
	
			if(box3.isSelected())
				total += Ridilyn;
				
			if(box4.isSelected())
				total += Orphenadrine;
			
			if(box5.isSelected())
				total += Dofetilide;
			
			if(box6.isSelected())
				total += Hydroxyzine;
			
			if(box7.isSelected())
				total += Baclofen;
		}
		
		private class ButtonListener implements ActionListener
		{
			Surgery days = new Surgery();
			
			public void actionPerformed (ActionEvent e)
			{
				days.CheckOut();
				setVisible(false);
			}
		}
	
	}
	
		private class PatientAccount //extends Hospital
	{
	 	private double TotalCharges;
		public double medsTotal;
		public double surgeries;		//holds total hospital charges and is updated by Surgery and Pharmacy
	  	private int DaysIn;					//holds # of days in hospital
	  	double DayRate = 3000;				//agreed dayly hospital rate
	  
	  	//PatientAccount object
	  	public PatientAccount()
	  	{
	    	TotalCharges = 0;
		 	DaysIn = 0;
	  	}
	  
	  public PatientAccount(double total, int days)
	  {
	    TotalCharges = total;
		 DaysIn = days;
	  }
	  
	  public void setTotalCharges(double total)
	  {
	    TotalCharges = total;
	  }
	  
	  public void setDaysIn(int days)
	  {
	    DaysIn = days;
	  }
	  
	  public double getTotalCharges(int days)
	  {
	    //have surgery and pharmacy update to this method 
		 //then add all charges and return the total
		 TotalCharges = (DayRate*DaysIn) /*+ surgery charge + pharmacy charge(s)*/;
		 return TotalCharges;
	  }
	  
	  public int getDaysIn()
	  {
	    //JOptionPane to ask user how long they have been in hospital.
		 return DaysIn;
	  }
	  
	  
	}
	
		
	private class Hospital extends PatientAccount
	{
		public double medsTotal;
		public double surgeries;
		
		public Hospital()
		{
			medsTotal = 0;
			surgeries = 0;
		}
		
		public Hospital(double m, double s)
		{
			medsTotal = m;
			surgeries = s;
		}
		
		public void meds(double m)
		{
			medsTotal = m;
		}
		
		public void surgical(double s)
		{
			surgeries = s;
		}
		
		public void totalPaid()
		{
			double total;
			
			//medsTotal + surgeries = total;
			total = medsTotal + surgeries;
			
			setTotalCharges(total);
		}
			
			
	 /*	public static void main(String[] args)
	  	{
	    	//PatientAccount pa = new PatientAccount();
		 	//will have a menu.
		 	//HospitalMenu hm = new HospitalMenu();
		 	//allows user to input a type of surgery.
		 	//allows user to input one or more types of meds.
		 	//check patient out of hospital.
		 	//display total charges when patient is checked out.
	  	}*/
	}
}