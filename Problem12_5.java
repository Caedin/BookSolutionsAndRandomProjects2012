/* 
 Author: Keith Dyer
 Date: February 14, 2013
 Assignment 4
 Problem Number: 9.9
	Create a class called geometry that has static methods for calculating the area of a triangle, circle, and a rectangle.
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------



class Problem12_5  extends PaintPanel
{
	PaintPanel thisPanel;
	Payroll payRoll;
	
	public Problem12_5()
	{
		thisPanel = this;
				
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "5. Payroll Class Exceptions"
+"\n\nProgramming Challenge 5 of Chapter 6 required you to write a Payroll class that calculates an employee’s payroll. Write exception classes for the following error conditions:"
 
+"\n\n• An empty string is given for the employee’s name."
+"\n• An invalid value is given for the employee’s ID number. If you implemented this field as a string, then an empty string would be invalid. If you implemented this field as a numeric variable, "
+"\nthen a negative number or zero would be invalid."
+"\n• An invalid number is given for the number of hours worked. This would be a negative number or a number greater than 84."
+"\n• An invalid number is given for the hourly pay rate. This would be a negative number or a number greater than 25."
+"\n\nModify the Payroll class so that it throws the appropriate exception when any of these errors occurs. Demonstrate the exception classes in a program.";
	}
	
	
	public JPanel buildPanel()
	{	
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(KDUtil.wrap(buildButtons()), BorderLayout.NORTH);
		topPanel.add(KDUtil.wrap(buildOutputPanel()), BorderLayout.CENTER);
		return topPanel;
	}
	
	public JPanel buildButtons()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel inputPanel = new JPanel(new GridLayout(4,2,5,5));

	
		final JTextField nameIn = new JTextField(10);
		final JTextField idIn = new JTextField(10);
		final JTextField hoursIn = new JTextField(10);
		final JTextField rateIn = new JTextField(10);
		
		inputPanel.add(KDUtil.wrap(new JLabel("Employee Name:")));
		inputPanel.add(KDUtil.wrap(nameIn));
		
		inputPanel.add(KDUtil.wrap(new JLabel("Employee ID:")));
		inputPanel.add(KDUtil.wrap(idIn));
		
		inputPanel.add(KDUtil.wrap(new JLabel("Employee Pay:")));
		inputPanel.add(KDUtil.wrap(rateIn));
		
		inputPanel.add(KDUtil.wrap(new JLabel("Hours Worked:")));
		inputPanel.add(KDUtil.wrap(hoursIn));
		
		
		
		JButton button = new JButton("Create Payroll");
		
		topPanel.add(KDUtil.wrap(inputPanel), BorderLayout.NORTH);
		topPanel.add(KDUtil.wrap(button), BorderLayout.SOUTH);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String name;
				String id;
				int hours;
				double rate;
				
				try
				{
					name = nameIn.getText();
				}catch(NullPointerException e) {name = "Empty Name";}
				
				try
				{
					id = idIn.getText();
				}catch(NullPointerException e) {id = "Empty ID";}
				
				try{
				try
				{
					hours = Integer.parseInt(hoursIn.getText());
				}catch(NullPointerException e) {hours = 0;}}catch(NumberFormatException k){hours = 0;}
				
				try{
				try
				{
					rate = Double.parseDouble(rateIn.getText());
				}catch(NullPointerException e) {rate = 0;}}catch(NumberFormatException k){rate = 0;}
				
				

				payRoll = new Payroll(name,id);
				payRoll.setRate(rate);
				payRoll.setHrs(hours);

				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
								
			}
		});	
		
		
		
		return topPanel;
	}
	
	public JPanel buildOutputPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel temp = new JPanel();
		
		temp = new JPanel(new GridLayout(0,3,5,5));

		try{
		temp.add(KDUtil.wrap(payRoll.toPanel()));
		} catch (NullPointerException e) {}
		
		topPanel.add(temp, BorderLayout.CENTER);
		
		
		return topPanel;
	}
	
	

	

	private class Payroll
	{
		String name;
		String idNum;
		double payRate;
		int hrsNum;
		
		public String getName() {return name;}
		public String getId() {return idNum; }
		public int getHrs() {return hrsNum; }
		public double getRate() {return payRate; }
		public void setName(String s) { name = s; }
		
		public void setRate(double s) 
		{ 
			payRate = s; 
		}
		
		public void setId(String s) { idNum = s; }
		
		public void setHrs(int i)
		 { 
			hrsNum = i;
		 }
		 
		
		public Payroll(String name, String id)
		{
			
			this.name = name;
			idNum = id;
			
		}
		
		public double getPay()
		{
			return payRate * (double) hrsNum;
		}
		
		public JPanel toPanel()
		{
			DecimalFormat money = new DecimalFormat("$#.##");
			JPanel topPanel = new JPanel(new GridLayout(5,1,5,5));
			
			topPanel.setBorder(BorderFactory.createTitledBorder("ID:  "+idNum+" : "+name));
			
			try
			{
				if(hrsNum<0||hrsNum>84)	throw new IllegalArgumentException("Invalid Hours");
				else	topPanel.add(KDUtil.wrap(new JLabel("Hours Worked: "+hrsNum)));

				if(payRate<0||payRate>25)	throw new IllegalArgumentException("Invalid Pay");
				else topPanel.add(KDUtil.wrap(new JLabel("Pay Rate: "+money.format(payRate))));
			
				topPanel.add(KDUtil.wrap(new JLabel("Gross Pay: "+money.format(getPay()))));
			}
			catch(IllegalArgumentException e)
			{
				topPanel.removeAll();
				
				topPanel.add(KDUtil.wrap(new JLabel("Hours Worked: "+e.getMessage())));

				topPanel.add(KDUtil.wrap(new JLabel("Pay Rate: "+e.getMessage())));
			
				topPanel.add(KDUtil.wrap(new JLabel("Gross Pay: "+e.getMessage())));
			}
			
			
			return topPanel;
		}
	}
}