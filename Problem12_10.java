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



class Problem12_10  extends PaintPanel
{
	ArrayList<Employee> employees = new ArrayList();
	PaintPanel thisPanel;
	
	public Problem12_10()
	{
		thisPanel = this;
				
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "The Exception Project Problem"
+"\n\nThis assignment assumes you have completed Programming Challenge 1 of Chapter 11 (Employee and ProductionWorker Classes). Modify the Employee and ProductionWorker classes so they "
+"\nthrow exceptions when the following errors occur:"
+"\n\n The Employee class should throw an exception named InvalidEmployeeNumber when it receives an employee number that is less than 0 or greater than 9999."
+"\n The ProductionWorker class should throw an exception named InvalidShift when it receives an invalid shift."
+"\n The ProductionWorker class should throw an exception named InvalidPayRate when it receives a negative number for the hourly pay rate."
+"\n\nWrite a test program that demonstrates how each of these exception conditions works.";
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
		
		JButton button = new JButton("Create Employee");
		topPanel.add(KDUtil.wrap(button), BorderLayout.WEST);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Employee x = new Employee();
				x.createEmployee();
				employees.add(x);			
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
								
			}
		});	
		
		button = new JButton("Create ProductionWorker");
		topPanel.add(KDUtil.wrap(button), BorderLayout.EAST);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ProductionWorker x = new ProductionWorker();
				x.createEmployee();
				employees.add(x);		
				
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

		
		for(int i = 0; i<employees.size(); i++)
		{
			temp.add(KDUtil.wrap(employees.get(i).getPanel()));
		}
		
		topPanel.add(temp, BorderLayout.CENTER);
		
		
		return topPanel;
	}

	

	
	private class Employee
	{
		private String name;
		private String number;
		protected String Class = "Employee";
		
		private Date hire;
		private JPanel topPanel = new JPanel(new BorderLayout());
		private JPanel north = new JPanel(new GridLayout(1,0));
		private JPanel south = new JPanel(new GridLayout(1,0));
		private JPanel east = new JPanel(new GridLayout(1,0));
		private JPanel west = new JPanel(new GridLayout(1,0));
		private JPanel center = new JPanel(new GridLayout(1,0));
		
		protected SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		protected DecimalFormat money = new DecimalFormat("$#,##0.00");
		
		
		public Employee(String name, String number, Date hire)
		{
			this.name = name;
			this.number=number;
			this.hire=hire;
			
		}
		
		public Employee()
		{
			name = "Default Worker";
			int randNumber = (int)(Math.random()*999);
			int randChar = (int)(Math.random()*12)+65;
			
			number = randNumber +"-"+ Character.toString((char) randChar);
			
			hire = new Date();
		}
		
		public JPanel getPanel()
		{
			
			topPanel = new JPanel(new BorderLayout());
			
			if(name.isEmpty()==false)
				topPanel.setBorder(BorderFactory.createTitledBorder(name));
			else
				topPanel.setBorder(BorderFactory.createTitledBorder("Default Worker"));
			
			topPanel.add(north, BorderLayout.NORTH);
			topPanel.add(south, BorderLayout.SOUTH);
			topPanel.add(east, BorderLayout.EAST);
			topPanel.add(west, BorderLayout.WEST);
			topPanel.add(center, BorderLayout.CENTER);
			
			
			return topPanel;
		}
		
		protected void addNorth(JPanel panel)
		{
			JPanel temp = new JPanel(new GridLayout(1,0));
			temp.add(panel);
			north = temp;
		}
		protected void addSouth(JPanel panel)
		{
			JPanel temp = new JPanel(new GridLayout(2,0));
			temp.add(south);
			temp.add(panel);
			south = temp;
		}
		protected void addEast(JPanel panel)
		{
			JPanel temp = new JPanel(new GridLayout(2,0));
			temp.add(east);
			temp.add(panel);
			east = temp;
		}
		protected void addWest(JPanel panel)
		{
			JPanel temp = new JPanel(new GridLayout(2,0));
			temp.add(west);
			temp.add(panel);
			west = temp;
		}
		protected void addCenter(JPanel panel)
		{
			JPanel temp = new JPanel(new GridLayout(2,0));
			temp.add(center);
			temp.add(panel);
			center = temp;
		}
		
		public void createEmployee()
		{
			SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
			
			name = JOptionPane.showInputDialog("Employee's Name: ");
			hire = KDUtil.createDate(JOptionPane.showInputDialog("Date Hired: "));
			
			String empNum = "";
			empNum = JOptionPane.showInputDialog("Employee's Number (0-9999): ");

			number = empNum;
			
			addNorth(KDUtil.wrap(new JLabel("Class: "+Class)));
			addSouth(KDUtil.wrap(new JLabel("Date Hired: "+dateFormatter.format(hire))));
			
			int numTest = 0;
			try{
			numTest = Integer.parseInt(number);
			}
			catch(RuntimeException e){ number = "Invalid Employee Number";}
			
			
			try
			{
			if(numTest<0||numTest>9999)
				throw new InvalidEmployeeNumber("Invalid Employee Number");
			addSouth(KDUtil.wrap(new JLabel("Worker Number: "+number)));
			}
			catch(InvalidEmployeeNumber k)
			{
				addSouth(KDUtil.wrap(new JLabel("Worker Number: "+k.getMessage())));
			}
				
		}
		
		public String getName() { return name; }
		public String getNumber() { return number; }
		public Date getHire() { return hire; }
		
		public void setName(String s) { name = s; }
		public void setNumber(String s) { number = s; }
		public void setHire(Date d) { hire = d; }
		
	}
	
	private class ProductionWorker extends Employee
	{
		int shift;
		double hourlyPay;
		
		public ProductionWorker(int shift, double rate)
		{
			this.shift = shift;
			hourlyPay = rate;
			super.Class = "ProductionWorker";
		}
		
		public ProductionWorker()
		{
			shift = 1;
			hourlyPay = 7;
			super.Class = "ProductionWorker";
		}
		
		public void createEmployee()
		{
			super.createEmployee();
			

			String temp = JOptionPane.showInputDialog("What is your shift? (1=day, 2=night)");
			
			try
			{
			
			if(temp.isEmpty()==false) shift = Integer.parseInt(temp);
			else	throw new InvalidShift("Invalid Shift");
			
				if(shift==1)
					super.addEast(KDUtil.wrap(new JLabel("Shift : Day")));
				else if(shift==2)
					super.addEast(KDUtil.wrap(new JLabel("Shift : Night")));
				else throw new InvalidShift("Invalid Shift");
			}
			catch(InvalidShift e)
			{
				super.addEast(KDUtil.wrap(new JLabel(e.getMessage())));
			}
				
			
			temp = JOptionPane.showInputDialog("What is your hourly pay rate?");
			
			try
			{
				if(temp.isEmpty()==false) hourlyPay =  Double.parseDouble(temp);
				else throw new InvalidPayRate("Invalid Pay Rate");
				
				if(hourlyPay<=0) throw new InvalidPayRate("Invalid Pay Rate");
				super.addCenter(KDUtil.wrap(new JLabel("Hourly Pay : "+money.format(hourlyPay))));
			}
			catch(InvalidPayRate e)
			{
				super.addCenter(KDUtil.wrap(new JLabel(e.getMessage())));
			}
			
		}
		
		
		
		public int getShift() {return shift;}
		public double getRate() {return hourlyPay; }
		
		public void setShift(int i) { shift = i; }
		public void setPay(double k) { hourlyPay = k; }
		
		
	}
	
	private class InvalidEmployeeNumber extends RuntimeException
	{
		public InvalidEmployeeNumber(String e)
		{
			super(e);
		}
	}
	private class InvalidShift extends RuntimeException
	{
		public InvalidShift(String e)
		{
			super(e);
		}
	}
	private class InvalidPayRate extends RuntimeException
	{
		public InvalidPayRate(String e)
		{
			super(e);
		}
	}

}