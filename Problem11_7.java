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



class Problem11_7  extends PaintPanel
{
	ArrayList<Person> persons = new ArrayList();
	PaintPanel thisPanel;
	
	public Problem11_7()
	{
		thisPanel = this;
				
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "7. Person and Customer Classes"
+"\n\nDesign a class named Person with fields for holding a persons name, address, and telephone number. Write one or more constructors and the appropriate mutator and accessor methods for the classs fields."
+"\nNext, design a class named Customer, which extends the Person class. The Customer class should have a field for a customer number and a boolean field indicating whether the customer wishes to be on a "
+"\nmailing list. Write one or more constructors and the appropriate mutator and accessor methods for the classs fields. Demonstrate an object of the Customer class in a simple program.";
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
		
		JButton button = new JButton("Create Person");
		topPanel.add(KDUtil.wrap(button), BorderLayout.WEST);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Person x = new Person();
				x.createPerson();
				persons.add(x);			
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
								
			}
		});	
		
		button = new JButton("Create Customer");
		topPanel.add(KDUtil.wrap(button), BorderLayout.EAST);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Customer x = new Customer();
				x.createPerson();
				persons.add(x);		
				
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

		
		for(int i = 0; i<persons.size(); i++)
		{
			temp.add(KDUtil.wrap(persons.get(i).getPanel()));
		}
		
		topPanel.add(temp, BorderLayout.CENTER);
		
		
		return topPanel;
	}

	
}

class Person
{
	private String name;
	private String number;
	
	protected String Class = "Person";
	
	private String address;
	private JPanel topPanel = new JPanel(new BorderLayout());
	private JPanel north = new JPanel(new GridLayout(1,0));
	private JPanel south = new JPanel(new GridLayout(1,0));
	private JPanel east = new JPanel(new GridLayout(1,0));
	private JPanel west = new JPanel(new GridLayout(1,0));
	private JPanel center = new JPanel(new GridLayout(1,0));
	
	protected SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
	protected DecimalFormat money = new DecimalFormat("$#,##0.00");
	
	
	public Person(String name, String number, String address)
	{
		this.name = name;
		this.number=number;
		this.address=address;
		
	}
	
	public Person()
	{
		name = "Default Person";
		
		number = "N/A";
		
		address = "N/A";
	}
	
	public JPanel getPanel()
	{
		
		topPanel = new JPanel(new BorderLayout());
		
		if(name.isEmpty()==false)
			topPanel.setBorder(BorderFactory.createTitledBorder(name));
		else
			topPanel.setBorder(BorderFactory.createTitledBorder("Default Person"));
		
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
	
	public void createPerson()
	{
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		
		name = JOptionPane.showInputDialog("Person's Name: ");
		address = JOptionPane.showInputDialog("Address: ");
		number = JOptionPane.showInputDialog("Telephone Number: ");
		
		addNorth(KDUtil.wrap(new JLabel("Class: "+Class)));
		addSouth(KDUtil.wrap(new JLabel("Address: "+address)));
		addSouth(KDUtil.wrap(new JLabel("Telephone Number: "+number)));
	}
	
	public String getName() { return name; }
	public String getNumber() { return number; }
	public String getAddress() { return address; }
	
	public void setName(String s) { name = s; }
	public void setNumber(String s) { number = s; }
	public void setAddress(String s) { address = s; }
	
}

class Customer extends Person
{
	boolean mailing = false;
	private int custNum;
	
	public Customer(boolean mail)
	{
		mailing = mail;
		super.Class = "Customer";
	}
	
	public Customer()
	{
		mailing = false;
		super.Class = "Customer";
	}
	
	public void createPerson()
	{
		super.createPerson();
		int holder = 0;
		int num = (int)(Math.random()*10000);
		custNum = num;
		
		String temp = JOptionPane.showInputDialog("Would you like to be on the mailing list? (1 = yes, 0 = no)");
		if(temp.isEmpty()==false) holder = Integer.parseInt(temp);
		
		if(holder==1) mailing = true; else mailing = false;
		

		if(mailing)
			super.addEast(KDUtil.wrap(new JLabel("Mailing list: 'Yes'")));
		else
			super.addEast(KDUtil.wrap(new JLabel("Mailing list: 'No'")));
		
		
		super.addCenter(KDUtil.wrap(new JLabel("Customer Number : "+custNum)));
		
	}
}