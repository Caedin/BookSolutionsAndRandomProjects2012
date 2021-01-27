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



class Problem11_10  extends PaintPanel
{
	ArrayList<Ship> fleet = new ArrayList();
	PaintPanel thisPanel;
	
	public Problem11_10()
	{
		thisPanel = this;
				
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "10. Ship, CruiseShip, and CargoShip Classes"
+"\n\nDesign a Ship class that the following members:"
+"\n\n A field for the name of the ship (a string)."
+"\n A field for the year that the ship was built (a string)."
+"\n A constructor and appropriate accessors and mutators."
+"\n A toString method that displays the ships name and the year it was built."
+"\n\nDesign a CruiseShip class that extends the Ship class. The CruiseShip class should have the following members:"
+"\n "
+"\n A field for the maximum number of passengers (an int)."
+"\n A constructor and appropriate accessors and mutators."
+"\n A toString method that overrides the toString method in the base class. The CruiseShip classs toString method should display only the ships name and the maximum number of passengers."
+"\n\nDesign a CargoShip class that extends the Ship class. The CargoShip class should have the following members:"
+"\n "
+"\n A field for the cargo capacity in tonnage (an int)."
+"\n A constructor and appropriate accessors and mutators."
+"\n A toString method that overrides the toString method in the base class. The CargoShip classs toString method should display only the ships name and the ships cargo capacity."
+"\n\nDemonstrate the classes in a program that has a Ship array. Assign various Ship, CruiseShip, and CargoShip objects to the array elements. The program should then step through the array, "
+"\ncalling each objects toString method. (See Code Listing 11-25 as an example.)";
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
		
		JButton button = new JButton("Create New Ship");
		topPanel.add(KDUtil.wrap(button), BorderLayout.WEST);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Ship x = new Ship();
				x.createShip();	
				fleet.add(x);
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
								
			}
		});	
		
		button = new JButton("Create New Cruise Ship");
		topPanel.add(KDUtil.wrap(button), BorderLayout.CENTER);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				CruiseShip x = new CruiseShip();
				x.createShip();	
				fleet.add(x);
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();					
			}
		});
		
		button = new JButton("Create New Cargo Ship");
		topPanel.add(KDUtil.wrap(button), BorderLayout.EAST);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				CargoShip x = new CargoShip();
				x.createShip();	
				fleet.add(x);		
				
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
		
		temp = new JPanel(new GridLayout(0,3,15,5));

		
		for(int i = 0; i<fleet.size(); i++)
		{
			temp.add(KDUtil.wrap(fleet.get(i).getPanel()));
		}
		
		topPanel.add(temp, BorderLayout.CENTER);
		if(fleet.size()>0)
			topPanel.setBorder(BorderFactory.createTitledBorder("Fleet"));
		
		
		return topPanel;
	}

	


	private class Ship
	{
		private String name;
		private String year;
		
		protected String Class = "Ship";
		
		private JPanel topPanel = new JPanel(new BorderLayout());
		private JPanel north = new JPanel(new GridLayout(1,0));
		private JPanel south = new JPanel(new GridLayout(1,0));
		private JPanel east = new JPanel(new GridLayout(1,0));
		private JPanel west = new JPanel(new GridLayout(1,0));
		private JPanel center = new JPanel(new GridLayout(1,0));
		
		
		public Ship(String name, String year)
		{
			this.name = name;
			this.year = year;
			
		}
		
		public Ship()
		{
			name = "Default Ship";
			year = "0";

		}
		
		public JPanel getPanel()
		{
			
			topPanel = new JPanel(new BorderLayout());

			
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
		
		public void createShip()
		{
			final JFrame input = new JFrame();
			input.setSize(400,400);
		
			JPanel topPanel = new JPanel(new BorderLayout());
			
				JPanel header = new JPanel();
			header.add(KDUtil.wrap(new JLabel("Creating a new Ship")));
			
			JPanel center = new JPanel(new GridLayout(2,2));
			
			final JTextField nam = new JTextField(10);
			final JTextField yea = new JTextField(10);
	
			
			center.add(KDUtil.wrap(new JLabel("Ship's Name: ")));
			center.add(KDUtil.wrap(nam));
			
			center.add(KDUtil.wrap(new JLabel("Year Completed: ")));
			center.add(KDUtil.wrap(yea));
	
			
			JPanel complete = new JPanel();
			
			JButton enter = new JButton("Done");
			complete.add(KDUtil.wrap(enter));
			
			enter.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					if(nam.getText().isEmpty()==false)
						name = nam.getText();
					
					if(yea.getText().isEmpty()==false)
						year = yea.getText();
					
					JPanel temp = new JPanel();
					temp.add(new JLabel("Type : "+Class));
					addNorth(temp);
					
					temp = new JPanel();
					temp.add(new JLabel("Name : "+name));
					addCenter(temp);
					
					temp = new JPanel();
					temp.add(new JLabel("Year : "+year));
					addCenter(temp);
					
					input.setVisible(false);
					
					thisPanel.removeAll();
					thisPanel.add(buildPanel());
					thisPanel.revalidate();
					thisPanel.repaint();
				}
			});
			
		topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
		topPanel.add(KDUtil.wrap(center), BorderLayout.CENTER);
		topPanel.add(KDUtil.wrap(complete), BorderLayout.SOUTH);
		
		input.add(topPanel);
		input.setVisible(true);
	 }
		
		
		//Accessor and mutator methods
		public String getName() { return name; }
		public String getYear() { return year; }
		
		public void setName(String s) { name = s; }
		public void setYear(String s) { year = s; }
		
	}
	
	private class CruiseShip extends Ship
	{
		private int numPassengers;
		public CruiseShip(String name, int num)
		{
			super.setName(name);
			numPassengers = num;
			Class = "Cruise Ship";
		}
		
		public CruiseShip()
		{
			super();
			Class = "Cruise Ship";
		}
		
		// Accessor and mutator methods.
		public int getNumPassengers() { return numPassengers; }
		public void setNumPassengers(int x) { numPassengers = x; }
		
		public void createShip()
		{
			final JFrame input = new JFrame();
			input.setSize(400,400);
		
			JPanel topPanel = new JPanel(new BorderLayout());
			
				JPanel header = new JPanel();
			header.add(KDUtil.wrap(new JLabel("Creating a new Cruise Ship")));
			
			JPanel center = new JPanel(new GridLayout(2,2));
			
			final JTextField nam = new JTextField(10);
			final JTextField yea = new JTextField(10);
	
			
			center.add(KDUtil.wrap(new JLabel("Ship's Name: ")));
			center.add(KDUtil.wrap(nam));
			
			center.add(KDUtil.wrap(new JLabel("Number of Passengers: ")));
			center.add(KDUtil.wrap(yea));
	
			
			JPanel complete = new JPanel();
			
			JButton enter = new JButton("Done");
			complete.add(KDUtil.wrap(enter));
			
			enter.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					String tempString =  new String("0");
					
					if(nam.getText().isEmpty()==false)
						setName(nam.getText());
					
					if(yea.getText().isEmpty()==false)
						tempString = KDUtil.intInputValidation(yea.getText());
						
					numPassengers = Integer.parseInt(tempString);
					
					JPanel temp = new JPanel();
					temp.add(new JLabel("Type : "+Class));
					addNorth(temp);
					
					temp = new JPanel();
					temp.add(new JLabel("Name : "+getName()));
					addCenter(temp);
					
					temp = new JPanel();
					temp.add(new JLabel("Number of Passengers : "+numPassengers));
					addCenter(temp);
					
					input.setVisible(false);
					
					thisPanel.removeAll();
					thisPanel.add(buildPanel());
					thisPanel.revalidate();
					thisPanel.repaint();
				}
			});
			
		topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
		topPanel.add(KDUtil.wrap(center), BorderLayout.CENTER);
		topPanel.add(KDUtil.wrap(complete), BorderLayout.SOUTH);
		
		input.add(topPanel);
		input.setVisible(true);
	 }

		
		
	}
		
	private class CargoShip extends Ship
	{
		private int numTonnage;
		public CargoShip(String name, int num)
		{
			super.setName(name);
			numTonnage = num;
			Class = "Cargo Ship";
		}
		
		public CargoShip()
		{
			super();
			Class = "Cargo Ship";
		}
		
		// Accessor and mutator methods.
		public int getNumTonnage() { return numTonnage; }
		public void setNumTonnage(int x) { numTonnage = x; }
		
		public void createShip()
		{
			final JFrame input = new JFrame();
			input.setSize(400,400);
		
			JPanel topPanel = new JPanel(new BorderLayout());
			
				JPanel header = new JPanel();
			header.add(KDUtil.wrap(new JLabel("Creating a new Cargo Ship")));
			
			JPanel center = new JPanel(new GridLayout(2,2));
			
			final JTextField nam = new JTextField(10);
			final JTextField yea = new JTextField(10);
	
			
			center.add(KDUtil.wrap(new JLabel("Ship's Name : ")));
			center.add(KDUtil.wrap(nam));
			
			center.add(KDUtil.wrap(new JLabel("Amount of Cargo (tons): ")));
			center.add(KDUtil.wrap(yea));
	
			
			JPanel complete = new JPanel();
			
			JButton enter = new JButton("Done");
			complete.add(KDUtil.wrap(enter));
			
			enter.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					String tempString =  new String("0");
					
					if(nam.getText().isEmpty()==false)
						setName(nam.getText());
					
					if(yea.getText().isEmpty()==false)
						tempString = KDUtil.intInputValidation(yea.getText());
						
					numTonnage = Integer.parseInt(tempString);
					
					JPanel temp = new JPanel();
					temp.add(new JLabel("Type : "+Class));
					addNorth(temp);
					
					temp = new JPanel();
					temp.add(new JLabel("Name : "+getName()));
					addCenter(temp);
					
					temp = new JPanel();
					temp.add(new JLabel("Amount of Cargo : "+numTonnage+ " tons"));
					addCenter(temp);
					
					input.setVisible(false);
					
					thisPanel.removeAll();
					thisPanel.add(buildPanel());
					thisPanel.revalidate();
					thisPanel.repaint();
				}
			});
			
		topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
		topPanel.add(KDUtil.wrap(center), BorderLayout.CENTER);
		topPanel.add(KDUtil.wrap(complete), BorderLayout.SOUTH);
		
		input.add(topPanel);
		input.setVisible(true);
	 }

		
		
	}

}