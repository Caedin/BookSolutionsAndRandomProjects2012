/* 
 Author: Keith Dyer
 Date: January 31, 2013
 Assignment 3
 Problem Number: 7.4
 
 Problem: Create a GUI application that calculates and displays the total travel expenses of a business person on a trip. Here is the information that the user must provide:
 		Number of days
		amount of airfare
		amount of car rental fees
		amount of miles driven in personal car
		parking fees
		taxi charges
		conference or seminar fees
		lodging charges per night
		
		Reinbursement:
		$37 per day for meals
		$10 parking per day
		$20 taxi charges per day
		$95.0 lodging per day
		$0.27 per mile in personal car
		
		Should display the following:
			Total expenses
			total allowable expenses
			excess to be paid
			amount saved if expenses are under allowed
			
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


class Problem7_4 extends PaintPanel
{
	private JPanel panel;
	private JPanel finalPanel;
	private JPanel[] panelFields;
	private JLabel[] Label;
	private JTextField[] textField;
	private JButton[] button;
	private JLabel[] output = new JLabel[5];
	private int numButtons = 8;
	
	
	public Problem7_4()
	{
		
		BusinessCalcWindow();
	}
	
	public void BusinessCalcWindow()
	{
		buildBusinessPanel();
		add(finalPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "4. Travel Expenses"
+"\nCreate a GUI application that calculates and displays the total travel expenses of a business person on a trip. Here is the information that the user must provide:"
+"\n\n• Number of days on the trip"
+"\n• Amount of airfare, if any"
+"\n• Amount of car rental fees, if any"
+"\n• Number of miles driven, if a private vehicle was used"
+"\n• Amount of parking fees, if any"
+"\n• Amount of taxi charges, if any"
+"\n• Conference or seminar registration fees, if any"
+"\n• Lodging charges, per night\n"
+"\nThe company reimburses travel expenses according to the following policy:"
+"\n\n• $37 per day for meals"
+"\n• Parking fees, up to $10.00 per day"
+"\n• Taxi charges up to $20.00 per day"
+"\n• Lodging charges up to $95.00 per day"
+"\n• If a private vehicle is used, $0.27 per mile driven\n"
+"\nThe application should calculate and display the following:"
+"\n\n• Total expenses incurred by the business person"
+"\n• The total allowable expenses for the trip"
+"\n• The excess that must be paid by the business person, if any"
+"\n• The amount saved by the business person if the expenses are under the total allowed;";
	}
	
	
	private void buildBusinessPanel()
	{
		//Setup stuff (buttons, labels, text, ect.)
		Label = new JLabel[numButtons];
		textField = new JTextField[numButtons];
		panelFields = new JPanel[numButtons+1];
		button = new JButton[1];
		
		
		Label[0] = new JLabel(" Trip Length in Days:");
		Label[1] = new JLabel(" Cost of Airfare:");
		Label[2] = new JLabel(" Car Rental Fees:");
		Label[3] = new JLabel(" Miles Driven in Personal Car:");
		Label[4] = new JLabel(" Parking Fees:");
		Label[5] = new JLabel(" Taxi Charges:");
		Label[6] = new JLabel(" Conference Fees:");
		Label[7] = new JLabel(" Lodging Charges:");
		
		for(int i = 0; i<numButtons; i++)	
		{
			textField[i] = new JTextField(10);
			panelFields[i] = new JPanel();
			panelFields[i].add(textField[i]);
		}
		
		button[0] = new JButton("Calculate");
		button[0].addActionListener(new MyButtonListener());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(10,3));
		finalPanel = new JPanel();
		finalPanel.setLayout(new BorderLayout());
		
		//Add stuff to panel
		
		for(int i = 0; i<numButtons; i++)	
		{
			panel.add(Label[i]);
			panel.add(panelFields[i]);
		}
		
		panelFields[numButtons] = new JPanel();
		panelFields[numButtons].add(button[0]);
		finalPanel.add(panelFields[numButtons], BorderLayout.CENTER);
		
		finalPanel.add(panel, BorderLayout.NORTH);
		
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new GridLayout(5,0));
		for( int i = 0; i<5; i++)
		output[i] = new JLabel();
		
		wrapper.add(output[0]);
		wrapper.add(output[1]);
		wrapper.add(output[2]);
		wrapper.add(output[3]);
		wrapper.add(output[4]);
		
		finalPanel.add(wrapper, BorderLayout.SOUTH);
		
	}
	
	private class MyButtonListener implements ActionListener
	{
		//Variables
		String[] input = new String[numButtons];
		Double[] values = new Double[numButtons];
		
		public void actionPerformed(ActionEvent e)   //Responds to button, displays output
		{
			for(int i = 0; i<numButtons; i++)			// Collect data from text fields
			{
				input[i] = textField[i].getText();
				if(input[i].isEmpty()) values[i] = 0.0; else values[i] = Double.parseDouble(input[i]);			// Makes sure the text fields aren't empty.
			}
			
			DecimalFormat format = new DecimalFormat("$0.00");
			String s = "Total Costs: \t"+format.format(CalculateExpenses())+"\n"
			+"Total Allowable Costs: "+format.format(CalculateAllowableExpenses())+"\n"
			+"Remaining Costs: "+format.format(remainingCosts())+"\n"
			+"Amount Saved: "+format.format(amountSaved());
			
			output[0].setText("Total Costs: \t"+format.format(CalculateExpenses())+"\n");
			output[1].setText("Total Allowable Costs: "+format.format(CalculateAllowableExpenses())+"\n");
			output[2].setText("Remaining Costs: "+format.format(remainingCosts())+"\n");
			output[3].setText("Total Costs: \t"+format.format(CalculateExpenses())+"\n");
			output[4].setText("Amount Saved: "+format.format(amountSaved()));
		}
		
		private double CalculateExpenses()     // Calculates total cost for business person before reinbursement
		{
				double totalExpenses = 0;
				totalExpenses = values[1]+values[2]+values[4]+values[5]+values[6]+values[7]*values[0];
				return totalExpenses;
		}
		
		private double CalculateAllowableExpenses()			// Calculates total reinbursement
		{
				double totalExpenses = 0;
				totalExpenses = 37*values[0] + 10*values[0] + 20*values[0] + 95*values[0] + 0.27*values[3];
				return totalExpenses;
		}
		
		private double remainingCosts()  // Calculates remaining amount owed by the business person
		{
			double expense;
			expense = CalculateExpenses() - CalculateAllowableExpenses();
			
			if (expense>0)
			return expense;
			else return 0;
		}
		
		private double amountSaved()			//Calculates amount saved
		{
			double expense;
			
			expense = CalculateAllowableExpenses() - CalculateExpenses();
			
			if (expense>0)
			return expense;
			else return 0;
		}

		
	}
	
}