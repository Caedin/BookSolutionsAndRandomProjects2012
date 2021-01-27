/* 
 Author: Keith Dyer
 Date: February 2, 2013
 Assignment 3
 Problem Number: 7.6
 
 	Joe's Automotive charges the following
		oil change - $26
		lube job - $18
		radiator flush -$30
		Transmission Flush - $80
		inspection -$15
		muffler replacement - $100
		Tire rotation - $20
		
		+ Other Parts ___; and Labor Hours (20/hour)
		
		
	Plan: 7 check boxes, and two text fields.
			
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


class Problem7_6 extends PaintPanel
{	
	private JPanel panel = new JPanel();
	private JLabel output = new JLabel();
	private JCheckBox[] Boxes;
	private int numBoxes = 7;
	private JTextField field1;
	private JTextField field2;
	private JPanel wrapper = new JPanel();

	
	
	public Problem7_6()
	{
		panel.setLayout(new BorderLayout());
		buildPanel();
	}

	
	private void buildPanel()					// This method builds and adds all the parts to the panel, and then adds it to the frame
	{
		// -----   Create Header  ------ //
		JLabel Header = new JLabel("Welcome to Joe's Auto Parts!\n");
		wrapper = new JPanel();
		wrapper.add(Header);
		panel.add(wrapper, BorderLayout.NORTH);
		
		// -----   Create Check Boxes  ------ //
		Boxes = new JCheckBox[numBoxes];					// create boxes
		JPanel BoxPanel = new JPanel();					// create host panel
		BoxPanel.setLayout(new GridLayout(4,0,0,5));			// set layout to grid for boxes
		
		for(int i = 0; i < numBoxes; i++)				// Creates boxes and adds them to a panel.
		{
			if(i==0) Boxes[i] = new JCheckBox("Oil Change --- $26.00");
			if(i==1) Boxes[i] = new JCheckBox("Lube Job --- $18.00");
			if(i==2) Boxes[i] = new JCheckBox("Radiator Flush --- $30.00");
			if(i==3) Boxes[i] = new JCheckBox("Transmission Flush --- $80.00");
			if(i==4) Boxes[i] = new JCheckBox("Inspection --- $15.00");
			if(i==5) Boxes[i] = new JCheckBox("Muffler Replacement --- $100.00");
			if(i==6) Boxes[i] = new JCheckBox("Tire Rotation --- $20.00");
			BoxPanel.add(Boxes[i]);
		}				
		
		
		// -----   Create Additional Fields  ------ //
		
		JPanel fieldPanel = new JPanel();			// host panel
		fieldPanel.setLayout(new GridLayout(2,2));		// host panel layout
		
		JLabel label1 = new JLabel("Cost of Additional Parts:");				// fields and labels
		field1 = new JTextField(10);
		JLabel label2 = new JLabel("Hours of Labor:");
		field2 = new JTextField(10);
		
		fieldPanel.add(label1);				// add fields and labels to host panel
		fieldPanel.add(field1);
		fieldPanel.add(label2);
		fieldPanel.add(field2);
		
				
		// -----   Create Calculate Button  ------ //
		JButton calculate = new JButton("Calculate Total");
		calculate.addActionListener(new MyButtonListener());			// Links button and event
		
		wrapper = new JPanel();
		wrapper.add(calculate);
		
		panel.add(wrapper, BorderLayout.SOUTH);			


		
		createOutputPanel();					// Adds output to panel.
		// -----   Add Panel to Frame  ------ //
		
		
		wrapper = new JPanel();
		wrapper.setLayout(new GridLayout(3,0));
		wrapper.add(BoxPanel);
		
		JPanel wrapper2 = new JPanel();
		wrapper2.add(fieldPanel);
		wrapper.add(wrapper2);
		
		JPanel wrapper3 = new JPanel();
		wrapper3.add(output);
		
		wrapper.add(wrapper3);
		panel.add(wrapper, BorderLayout.CENTER);
		
		add(panel);
		
	}
	
	@Override
	public String getDescription()
	{
		return "6. Joe s Automotive"
+"\nJoe s Automotive performs the following routine maintenance services:"
+"\n\n  Oil change $26.00"
+"\n  Lube job $18.00"
+"\n  Radiator flush $30.00"
+"\n  Transmission flush $80.00"
+"\n  Inspection $15.00"
+"\n  Muffler replacement $100.00"
+"\n  Tire rotation $20.00"
+"\n\nJoe also performs other nonroutine services and charges for parts and for labor ($20 per hour). Create a GUI application that displays the total for a customer s visit to Joe s.";
	}
	
	
	private class MyButtonListener implements ActionListener
	{	
		public void actionPerformed(ActionEvent e)   //Responds to button
		{
			createOutputPanel();			// Change text of output when you click button
		}
	}
	
	private void createOutputPanel()
	{
		// -----   Create Output Field  ------ //
		output.setText("Total Cost		=		"+calculateCost());
	}

	
	private String calculateCost()			// Method returns a string with the total cost. 
	{
		DecimalFormat format = new DecimalFormat("$0.00");
		double cost = 0;
		String partsCost = null;
		String hours = null;
		
		if(field1.getText().isEmpty()); else 				// These are to prevent empty strings from being passed to Double.parseDouble(String n)
		{
			partsCost = field1.getText();
			cost = cost + Double.parseDouble(partsCost);
		}
			
		if(field2.getText().isEmpty()); else			// These are to prevent empty strings from being passed to Double.parseDouble(String n)
		{
			hours = field2.getText();
			cost = cost + (Double.parseDouble(hours)*20);
		}
		
		
		// Adds up cost if boxes are checked.
		
			if(Boxes[0].isSelected()) cost = cost + 26;
			if(Boxes[1].isSelected()) cost = cost + 18;
			if(Boxes[2].isSelected()) cost = cost + 30;
			if(Boxes[3].isSelected()) cost = cost + 80;
			if(Boxes[4].isSelected()) cost = cost + 15;
			if(Boxes[5].isSelected()) cost = cost + 100;
			if(Boxes[6].isSelected()) cost = cost + 20;
	

				
		return format.format(cost);
	}
	
	
}