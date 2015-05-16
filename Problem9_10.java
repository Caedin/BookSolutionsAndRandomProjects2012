// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

class Problem9_10  extends PaintPanel
{
	JLabel label;
	JTextField odo;
	JTextField speed;
	JTextField fuel;
	JButton button;

	
	FuelGauge fuels = new FuelGauge(0.0,15.0);
	Odometer meter = new Odometer(0.0,fuels);

	public Problem9_10()
	{
		
		setLayout(new BorderLayout());
		add(topPanel(), BorderLayout.NORTH);
		add(centerPanel(), BorderLayout.CENTER);
		add(bottomPanel(), BorderLayout.SOUTH);
		
		odo.setText("0.0");
		fuel.setText("0.0");
	}
	
	public JPanel topPanel()
	{
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1,2));
		
		label = new JLabel("Odometer");
		top.add(wrap(label));
		
		odo = new JTextField(10);
		odo.setEditable(false);
		
		top.add(wrap(odo));
		
		return top;
	}
	
	public JPanel centerPanel()
	{
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		JPanel centerLeft = new JPanel(new GridLayout(0,2));
		centerLeft.add(wrap(new JLabel("Fuel")));
		fuel = new JTextField(10);
		fuel.setEditable(false);
		centerLeft.add(wrap(fuel));
		
		center.add(wrap(centerLeft), BorderLayout.WEST);
		
		return center;
		
	}
	
	public JPanel bottomPanel()
	{
		JPanel bottom = new JPanel();
				
		button = new JButton("Fill Up");
		button.addActionListener(new ActionListener() 
					{
							public void actionPerformed(ActionEvent arg0) 
						{
							fuels.addFuel();
							fuel.setText(fuels.getFuel()+" gallons");
						}
					});	
					
		
		bottom.add(wrap(button), BorderLayout.EAST);
	
		button = new JButton("Run");
		button.addActionListener(new ActionListener() 
					{
							public void actionPerformed(ActionEvent arg0) 
						{
							meter.drive(odo, fuel);

						}
					});
		bottom.add(wrap(button));
	

		
		return bottom;
		
	}
	

	
	
	
	
	@Override
	public String getDescription()
	{
		return "10. Car Instrument Simulator"
+"\nFor this assignment, you will design a set of classes that work together to simulate a car’s fuel gauge and odometer. The classes you will design are the following:"
+"\n\n• The FuelGauge Class: This class will simulate a fuel gauge. Its responsibilities are as follows:"
+"\n• To know the car’s current amount of fuel, in gallons."
+"\n• To report the car’s current amount of fuel, in gallons."
+"\n• To be able to increment the amount of fuel by 1 gallon. This simulates putting fuel in the car. (The car can hold a maximum of 15 gallons.)"
+"\n• To be able to decrement the amount of fuel by 1 gallon, if the amount of fuel is greater than 0 gallons. This simulates burning fuel as the car runs."
+"\n• The Odometer Class: This class will simulate the car’s odometer. Its responsibilities are as follows:"
+"\n• To know the car’s current mileage."
+"\n• To report the car’s current mileage"
+"\n• To be able to increment the current mileage by 1 mile. The maximum mileage the odometer can store is 999,999 miles. When this amount is exceeded, the odometer resets the current mileage to 0."
+"\n• To be able to work with a FuelGauge object. It should decrease the FuelGauge object’s current amount of fuel by 1 gallon for every 24 miles traveled. (The car’s fuel economy is 24 miles per gallon.)"
+"\n\nDemonstrate the classes by creating instances of each. Simulate filling the car up with fuel, and then run a loop that increments the odometer until the car runs out of fuel. During each loop iteration, print the car’s current mileage and amount of fuel.";
	}
	
	public JPanel wrap(JComponent c)
	{
		JPanel wrapper = new JPanel();
		wrapper.add(c);
		return wrapper;
	}
	
}

class FuelGauge
{
	double currentFuel;
	double maxFuel;
	
	public FuelGauge(double fuel, double max)
	{
		currentFuel = fuel;
		maxFuel = max;
	}
	
	public double getFuel() 	{ return currentFuel; }
	public void addFuel() 	{ if(currentFuel<maxFuel)	currentFuel = currentFuel+1;}
	public void decFuel()	{ if(currentFuel>0)	currentFuel = currentFuel-1;}
	
}

class Odometer
{
	double currentMiles;
	FuelGauge fuel;
	
	public Odometer(double c, FuelGauge f)
	{
		currentMiles = c;
		fuel = f;
		
	}
	
	public void drive(final JTextField odo, final JTextField fuels)
	{
		new Thread()
		{  
 			 public void run() 
			 { 
			 	while(fuel.getFuel()>0)
				{
					addMile();
					odo.setText(""+getMiles()+" miles");
					fuels.setText(""+fuel.getFuel()+" gallons");
					delay(40);
				}
				
				
    		 }
			 
			 public void delay(int n)
			{
				long startDelay = System.currentTimeMillis();
				long endDelay = 0;
				while (endDelay - startDelay < n)
					endDelay = System.currentTimeMillis();	
			}

		}.start();
	}
	
	public void addMile() 	
	{
	if(currentMiles<999999)
			
		currentMiles++; 
	else 
		currentMiles=0;
		
	if(currentMiles%24==0) fuel.decFuel();
	}
	public double getMiles()	{return currentMiles;}
}