/* 
 Author: Keith Dyer
 Date: February 4, 2013
 Assignment 4
 Problem Number: 9.8
	Create classes for parking meter, parked car, police officer, and ticket with all the required attributes from the problem in the text book.
	Create a simple program to show how the different classes interact with each other.
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------
public class Problem9_8 extends PaintPanel
{
	JTextArea output = new JTextArea(35,50);
	JPanel topPanel = new JPanel();
	static ParkedCar[] cars = new ParkedCar[20];
	static ParkingMeter[] meters = new ParkingMeter[20];
	
	public Problem9_8()
	{
		
		JScrollPane scrollPane = new JScrollPane(output); 
		output.setEditable(false);
		

		output.append("Creating Rob, the police officer!\n");
		
		PoliceOfficer rob = new PoliceOfficer("Robert", "12DFGE");
		// Creating some cars
			
		output.append("\nCreating some cars, and parking them near meters\n");
		for(int i = 0; i < 20; i++)
		{
			String s = "";
			if(Math.random()>0.3) s = "Toyota";
			else if(Math.random()>0.6) s = "Honda";
			else if(Math.random()>0.9) s = "Ford";
			else s = "Station Wagon";
			
			String g = "";
			if(Math.random()>0.3) g = "T100";
			else if(Math.random()>0.6) g = "S64";
			else if(Math.random()>0.9) g = "GD3";
			else g = "G150";
			
			
			cars[i] = new ParkedCar(s,g,"Green",Math.random()+"",Math.random()*360);
			meters[i] = new ParkingMeter(Math.random()*360);
		}
			
			
		// Checked for tickets
			
		for(int i = 0; i < 20; i++)
		{
			output.append(rob.examine(cars[i],meters[i])+"\n");
							
		}
			
		output.append(rob.displayAllTickets());
		
		topPanel.add(scrollPane);
		add(topPanel);
	}

	
	@Override
	public String getDescription()
	{
		return "8. Parking Ticket Simulator"
+"\n\nFor this assignment you will design a set of classes that work together to simulate a police officer issuing a parking ticket. You should design the following classes:"
 
+"\n The ParkedCar Class: This class should simulate a parked car. The classs responsibilities are as follows:"
+"\n\n		 To know the cars make, model, color, license number, and the number of minutes that the car has been parked."
+"\n\n The ParkingMeter Class: This class should simulate a parking meter. The classs only responsibility is as follows:"
+"\n\n		 To know the number of minutes of parking time that has been purchased."
+"\n\n The ParkingTicket Class: This class should simulate a parking ticket. The classs responsibilities are as follows:"
+"\n\n		 To report the make, model, color, and license number of the illegally parked car"
+"\n		 To report the amount of the fine, which is $25 for the first hour or part of an hour that the car is illegally parked, plus $10 for every additional hour or part of an hour that the car is illegally parked"
+"\n		 To report the name and badge number of the police officer issuing the ticket"
+"\n\n The PoliceOfficer Class: This class should simulate a police officer inspecting parked cars. The classs responsibilities are as follows:"
+"\n\n		 To know the police officers name and badge number"
+"\n		 To examine a ParkedCar object and a ParkingMeter object, and determine whether the cars time has expired"
+"\n		 To issue a parking ticket (generate a ParkingTicket object) if the cars time has expired"
+"\n\nWrite a program that demonstrates how these classes collaborate.";
}
	
}

class ParkedCar
{
	private String make;
	private String model;
	private String color;
	private String plates;
	double time;
	
	public ParkedCar(String make, String model, String color, String plates, double time)
	{
		this.make=make;
		this.model=model;
		this.color=color;
		this.plates=plates;
		this.time=time;
	}
	
	public String getPlates() { return plates;}
	public String getModel() { return model;}
	public String getMake() { return make;}
	public double getTime() { return time;}
	public String getColor() { return color;}
}

class ParkingMeter
{
	private double timePurchased;
	public ParkingMeter(double d)
	{
		timePurchased=d;
	}
	
	public double getTimePurchased() {return timePurchased;}
}

class ParkingTicket
{
	private String make;
	private String model;
	private String carNumber;
	private String color;
	private double fine;
	private String officerName;
	private String officerNumber;
	
	public ParkingTicket(String make, String model, String carNumber, String color, String officerName, String officerNumber)
	{
		this.make=make;
		this.model=model;
		this.carNumber=carNumber;
		this.color=color;
		this.officerName=officerName;
		this.officerNumber=officerNumber;
	}
	
	public void calculateFee(int car, int meter)
	{
	
		if(car-meter>0) fine=fine+25;
		for(int i = 0; i<(car-meter)/60; i++) fine=fine+10;
	}
	
	public String toString()
	{
		String s = "";
		s=s+"Vehicle Make: "+make
		+"\nVehicle Model: "
		+model+"\nPlate Number: "
		+carNumber+"\nVehicle Color: "
		+color+"\nCost: "
		+fine+"\nOfficer Name: "
		+officerName+"\nOfficer Badge#: "
		+officerNumber+"\n";
		
		return s;
	}
	
	
	
}

class PoliceOfficer
{
	private String name;
	private String number;
	ArrayList<ParkingTicket> tickets = new ArrayList();
	
	public PoliceOfficer(String n, String n2)
	{
		name=n;
		number=n2;
	}
	
	public String examine(ParkedCar car, ParkingMeter m)
	{
		String s = "";
		if(car.getTime()>m.getTimePurchased()) s = writeTicket(car,m);
		return s;
	}
	
	private String writeTicket(ParkedCar car, ParkingMeter m)
	{
		ParkingTicket t = new ParkingTicket(car.getMake(),car.getModel(),car.getPlates(),car.getColor(),name,number);
		t.calculateFee((int)car.getTime(),(int)m.getTimePurchased());
		tickets.add(t);
		String s = "";
		s=s+"\nWriting Ticket!\n";
		s=s+t.toString()+"\n";
		return s;
	}
	
	public String displayAllTickets()
	{
		String out = "";
		
		out = out + "\nPrinting records of all current tickets: \n";
		
		for(int i = 0; i<tickets.size(); i++)
		{
			out = out + tickets.get(i).toString()+ "\n";
		}
		out = out +"End of Tickets "+"\n";
		return out;
	}
}