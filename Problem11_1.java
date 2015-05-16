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



class Problem11_1  extends PaintPanel
{
	ArrayList<Employee> employees = new ArrayList();
	PaintPanel thisPanel;
	
	public Problem11_1()
	{
		thisPanel = this;
				
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "1. Employee and ProductionWorker Classes"
		+"\n\n Design a class named Employee. The class should keep the following informaitonin fields:"
		+"\n\n*Employee name\nEmployee number in the format XXX-L, where each X is a digit within the range 0-9 and the L is a letter within the range A-M.\nHire date.\n\nWrite one or more constructors and the appropriate accessor and mutator methods for the class..\n\nNext, write a class named"
		+"\n ProductionWorker that extends the Employee class. The ProductionWorker that extends the Employee class. The ProductionWorker class should have fields to hold the following informaiton:"
		+"\n\n Shift(an integer)"
		+"\n Hourly pay rate( a double)"
		+"\n\n The workday is divided into two shifts: day and night. The shift field will be an integer value representing the shift that the employee works.\n The day shift is shit 1 and the night shift is shift 2. Wriute one or more constructors\n and the appropriate accessor and mutator methods for the class. Demonstrate the classes by writing a program that uses a ProductionWorker object.";
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

	
}

class Employee
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

class ProductionWorker extends Employee
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
		
		String temp = JOptionPane.showInputDialog("What is your shift? (1= day, 2= night)");
		if(temp.isEmpty()==false) shift = Integer.parseInt(temp);
		else	shift = 1;
		
		if(shift==1)
			super.addEast(KDUtil.wrap(new JLabel("Shift : Day")));
		else
			super.addEast(KDUtil.wrap(new JLabel("Shift : Night")));
		
		temp = JOptionPane.showInputDialog("What is your hourly pay rate?");
		if(temp.isEmpty()==false) hourlyPay =  Double.parseDouble(temp);
		else hourlyPay = 7.50;
		
		super.addCenter(KDUtil.wrap(new JLabel("Hourly Pay : "+money.format(hourlyPay))));
		
	}
	
	
	
	public int getShift() {return shift;}
	public double getRate() {return hourlyPay; }
	
	public void setShift(int i) { shift = i; }
	public void setPay(double k) { hourlyPay = k; }
	
	
}