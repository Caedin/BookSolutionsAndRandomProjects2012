   import java.text.*;
   import java.util.*;
   import javax.swing.*;
   import javax.swing.event.ChangeListener;
   import javax.swing.event.ChangeEvent;
   import java.awt.*;
   import java.io.*;
   import java.awt.event.*;

public class hospitalDemo extends JFrame
{
	private JPanel patientPanel = new JPanel();
	private JList surgeryList;
	private JList medsList;
	private JScrollPane surgeryScroll;
	private JScrollPane medsScroll;
	private JButton totalButton;
	private JButton exitButton;
	
	private JLabel patientName;
	private JLabel patientNumber;
	private JLabel nightOfStay;
	
	private JTextField textName;
	private JTextField textNumber;
	private JTextField textDays;
	
	private final int WINDOW_HEIGHT =375;
	private final int WINDOW_LENGTH =450;
	
	public hospitalDemo()
	{
		setTitle("St. Josephs Methodist Pediatrics");
		setSize(WINDOW_HEIGHT, WINDOW_LENGTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BuildPatientPanel();
		add(patientPanel);
		//setVisible(true);
	}
	
	public JPanel getTopPanel()
	{
		return patientPanel;
	}
	
	private void BuildPatientPanel()
	{
		patientPanel = new JPanel();
		patientPanel.setLayout(new BorderLayout());
		
		patientName = new JLabel("Patient Name");
		textName = new JTextField(12);
		textName.setEditable(true);
		
		patientNumber = new JLabel("Patient Number");
		textNumber = new JTextField(12);
		textNumber.setEditable(true);
		
		nightOfStay = new JLabel("Nights: $100.00/Night");
		textDays = new JTextField(12);
		textDays.setEditable(true);
		
		
		JPanel tempPanel = new JPanel(new GridLayout(3,3,5,5));
		
		tempPanel.add(patientName);
		tempPanel.add(textName);
		tempPanel.add(patientNumber);
		tempPanel.add(textNumber);
		tempPanel.add(nightOfStay);
		tempPanel.add(textDays);
		
		patientPanel.add(tempPanel, BorderLayout.NORTH);
		
		pharmacy pharm = new pharmacy();
		
		medsList = new JList(pharm.PopulateList());
		
		medsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		medsScroll = new JScrollPane(medsList);
		
		patientPanel.add(medsScroll, BorderLayout.CENTER);
		
		sugery surgery = new sugery();
		
		surgeryList = new JList(surgery.populateList());
		
		surgeryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		surgeryScroll = new JScrollPane(surgeryList);
		
		patientPanel.add(surgeryScroll, BorderLayout.WEST);
		
		totalButton = new JButton("Total Bill");
		totalButton.setMnemonic('B');
		exitButton = new JButton("Exit");
		exitButton.setMnemonic('X');
		
		totalButton.addActionListener(new TotalBillListener());
		exitButton.addActionListener(new ExitButtonListener());
		
		tempPanel = new JPanel();
		tempPanel.add(totalButton);
		tempPanel.add(exitButton);
		
		
		patientPanel.add(tempPanel, BorderLayout.SOUTH);
		
		/*patientName.setBounds(10,10,150,30);
		textName.setBounds(200,10,150,30);
		patientNumber.setBounds(10,45,150,30);
		textNumber.setBounds(200,45,150,30);
		nightOfStay.setBounds(10,80,150,30);
		textDays.setBounds(200,80,150,30);
		medsScroll.setBounds(50,125,120,110);
		surgeryScroll.setBounds(190,125,120,110);
		totalButton.setBounds(125,250,100,50);
		exitButton.setBounds(125,310,100,50);*/
			
	}
	
	private class TotalBillListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		
			Object[] selections = surgeryList.getSelectedValues();
			Object[] selections2 = medsList.getSelectedValues();
			
			sugery surg = new sugery();
			Patient pat = new Patient();
			pharmacy pharm = new pharmacy();
			
			for(int numb = 0; numb < selections.length; numb++)
			{
				surg.setSurgery(selections[numb]);
			}
			for(int numb = 0; numb < selections2.length; numb++)
			{
				pharm.setMedicine(selections2[numb]);
			}
			
			pat.setMedsCharge(pharm.getMedicine());
			pat.setSurgeryCharge(surg.getSurgery());
			pat.setPatientName(textName.getText());
			pat.setPatientNumb(textNumber.getText());
			pat.setTotalStay(Integer.parseInt(textDays.getText()));
			
			DecimalFormat form = new DecimalFormat("$##,#00.00");
			
			JOptionPane.showMessageDialog(null, "Your name is  " + pat.getName()
													+ "\nYour number is " + pat.getPatNumb()
													+	"\nYour total bill is " + form.format(pat.getBill()));
			
		}
	}
	
	private class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Have a great day!!");
			System.exit(0);
		}
	}
	
	private class Patient
{
	private final double NIGHT_STAY = 100.00;
	private String patName;
	private String patNumb;
	private int stayNumb;
	private double totalStay;
	private double totalMeds;
	private double totalSurgery;
	private double patientBill;
	//mutator method that accepts a string for patients name
	public void setPatientName(String name)
	{
		patName = name;
	}
	//mutator method that accepts a string for the patients number
	public void setPatientNumb(String number)
	{
		patNumb = number;
	}
	// mutator method that accepts a double  for total medication charge
	public void setMedsCharge(double meds)
	{
		totalMeds += meds;
	}
	// mutator method that accepts a double for total surgery charge
	public void setSurgeryCharge(double surgery)
	{
		totalSurgery += surgery;
	} 
	//mutator method that accepets an int for the number of nights stayed at the hopital
	
	public void setTotalStay(int stay)
	{
		stayNumb = stay;
		totalStay += stay * NIGHT_STAY;
	}
	//accesor method to get patients name
	public String getName()
	{
		return patName;
	}
	//accessor method that gets the patients number
	
	public String getPatNumb()
	{
		return patNumb;
	}
	//accesor method that gets the total patients bill
	
	public double getBill()
	{
		patientBill = totalStay + totalSurgery + totalMeds;
		return patientBill;
	}
}

private class pharmacy
{
	// the final cost of each medicine
	private final double MEDS_1 =150.00;
	private final double MEDS_2 =75.00;
	private final double MEDS_3 =25.00;
	private final double MEDS_4 =30.00;
	private final double MEDS_5 =200.00;
	private double finalBill;
	//populating the string array
	public String[] PopulateList()
	{
		String[] medicine ={"MEDS_1 = $150.00","MEDS_2 = $75.00","MEDS_3 = $25.00","MEDS_4 = $30.00","MEDS_5 = $200.00",};
		
		return medicine;
	}
	//mutator method that passes an object
	public void setMedicine(Object check)
	{
		if(check.equals("MEDS_1 = $150.00"))
			finalBill += MEDS_1;
		else if(check.equals("MEDS_2 = $75.00"))
			finalBill += MEDS_2;
		else if(check.equals("MEDS_3 = $25.00"))
			finalBill += MEDS_3;
		else if(check.equals("MEDS_4 = $30.00"))
			finalBill += MEDS_4;
		else if(check.equals("MEDS_5 = $200.00"))
			finalBill += MEDS_5;
		else
			System.out.println("Nothing has been selected!!");
	}
	//accessor method that gets the final bill
	public double getMedicine()
	{
		return finalBill;
	}
}

private class sugery
{
	//cost of each king of surgery
	private final double FOOT = 500.00;
	private final double KNEE = 1000.00;
	private final double RIB = 1500.00;
	private final double LIVER = 2000;
	private final double HEART = 15000.00;
	private double total;
	// populating the string array
	public String[] populateList()
	{
		String[] surgery = {"Foot = $500.00","Knee = $1000.00","Rib = $1500.00","Liver = $2000.00","Heart = $15,000.00",};
		return surgery;
	}
	// mutator that accepts on object
	public void setSurgery(Object check)
	{
		if(check.equals("Foot = $500.00"))
			total += FOOT;
		else if(check.equals("Knee = $1000.00"))
			total += KNEE;
		else if(check.equals("Rib = $1500.00"))
			total += RIB;
		else if(check.equals("Liver = $2000.00"))
			total += LIVER;
		else if(check.equals("Heart = $15,000.00"))
			total += HEART;
		else
			System.out.println("No choices were made!!");
	}
	// accessor method to get surgery charge
	public double getSurgery()
	{
		return total;
	}
}
	
	
}