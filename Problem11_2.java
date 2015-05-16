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



class Problem11_2  extends PaintPanel
{
	ArrayList<Employee> employees = new ArrayList();
	PaintPanel thisPanel;
	
	public Problem11_2()
	{
		thisPanel = this;
				
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "2. ShiftSupervisor Class\n"
		+"\nIn a particular factory, a shift supervisor is a salaried employee who supervises a shift. In addition to a salary, the shift supervisor earns a yearly bonus when his or her shift meets "
		+"\nproduction goals. Design a ShiftSupervisor class that extends the Employee class you created in Programming Challenge 1. The ShiftSupervisor class should have a field that holds the annual "
		+"\nsalary and a field that holds the annual production bonus that a shift supervisor has earned. Write one or more constructors and the appropriate accessor and mutator methods for the class. "
		+"\nDemonstrate the class by writing a program that uses a ShiftSupervisor object.";
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
		
		button = new JButton("Create ShiftSupervisor");
		topPanel.add(KDUtil.wrap(button), BorderLayout.EAST);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ShiftSupervisor x = new ShiftSupervisor();
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
		
		JPanel temp = new JPanel(new GridLayout(0,3,5,5));
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
		
		addNorth(KDUtil.wrap(new JLabel("Class: "+Class)));
		addSouth(KDUtil.wrap(new JLabel("Date Hired: "+dateFormatter.format(hire))));
		addSouth(KDUtil.wrap(new JLabel("Worker Number: "+number)));
	}
	
	public String getName() { return name; }
	public String getNumber() { return number; }
	public Date getHire() { return hire; }
	
	public void setName(String s) { name = s; }
	public void setNumber(String s) { number = s; }
	public void setHire(Date d) { hire = d; }
	
}


private class ShiftSupervisor extends Employee
{
	double annualPay;
	double annualBonus;
	
	public ShiftSupervisor(double aPay, double aBonus)
	{
		annualPay = aPay;
		annualBonus = aBonus;
		super.Class = "ShiftSupervisor";
	}
	
	public ShiftSupervisor()
	{
		annualPay = 40000;
		annualBonus = 2000;
		super.Class = "ShiftSupervisor";
	}
	
	public void createEmployee()
	{
		super.createEmployee();
		
		String temp = JOptionPane.showInputDialog("What is your annual pay?");
		if(temp.isEmpty()==false) annualPay = Double.parseDouble(KDUtil.moneyInputValidation(temp));
		else	annualPay = 40000;
		
		temp = JOptionPane.showInputDialog("What is your annual bonus?");
		if(temp.isEmpty()==false) annualBonus = Double.parseDouble(KDUtil.moneyInputValidation(temp));
		else annualBonus = 2000;
		
		super.addCenter(KDUtil.wrap(new JLabel("Annual Pay : "+money.format(annualPay))));
		super.addCenter(KDUtil.wrap(new JLabel("Annual Bonus : "+money.format(annualBonus))));
		
	}
	
	
	
	public double getAnnualPay() {return annualPay;}
	public double getBonus() {return annualBonus; }
	
	
}
}