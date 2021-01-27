/* 
 Author: Keith Dyer
 Date: February 14, 2013
 Assignment 4
 Problem Number: 9.1
	Problem creates rectangle, circle, and cylinder objects. Passes those objects to a static Area class to get the area.
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------

class Problem9_1  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	JPanel wrapper = new JPanel();
	
	JLabel[] labels = new JLabel[10];
	JTextField[] fields = new JTextField[10];			// input
	JTextField[] fieldsOut = new JTextField[10];		// output
	JButton[] calculate = new JButton[10];
	JPanel[] fieldPanel = new JPanel[10];

	JLabel image = new JLabel();
	
	public Problem9_1()
	{
		panel.setLayout(new BorderLayout());
		buildPanel();
		add(topPanel);
	}
	
	private void buildPanel()
	{
				JPanel header = new JPanel();
				final JPanel shapePanel = new JPanel();
				shapePanel.setLayout(new GridLayout(0,3));
				
				JLabel head = new JLabel("Welcome to the Static Area Class Demonstration");
				header.add(head);
				JPanel grid = new JPanel();
				grid.setLayout(new GridLayout(8,0));
				grid.add(header);
				
				final DecimalFormat format = new DecimalFormat("0.0");
				
					
				
					labels[0] = new JLabel("Circle");
					
					wrapper = new JPanel();
					wrapper.setLayout(new GridLayout(0,2));
					label = new JLabel("Radius:");
					fields[0] = new JTextField(10);
					wrapper.add(label);
					wrapper.add(fields[0]);
					
					fieldsOut[0] = new JTextField(10);
					calculate[0] = new JButton("Calculate");
					fieldPanel[0] = new JPanel();
					
					
					fieldPanel[0].add(wrapper);
					
					wrapper = new JPanel();
					wrapper.add(labels[0]);
					grid.add(wrapper);
					
					fieldPanel[0].add(calculate[0]);
					fieldPanel[0].add(fieldsOut[0]);
					grid.add(fieldPanel[0]);
					
					calculate[0].addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent arg0) 
						{
							boolean inputValidation = true;
							if(fields[0].getText().isEmpty()==false)
							{
								String input = fields[0].getText();
								char[] inputArray = input.toCharArray();
								
								String newInput = "";
								
								for(int i = 0; i<input.length(); i++)
								{
									switch (inputArray[i])
									{
										case '0': newInput = newInput+ inputArray[i]; break;
										case '1': newInput = newInput+ inputArray[i]; break;
										case '2': newInput = newInput+ inputArray[i]; break;
										case '3': newInput = newInput+ inputArray[i]; break;
										case '4': newInput = newInput+ inputArray[i]; break;
										case '5': newInput = newInput+ inputArray[i]; break;
										case '6': newInput = newInput+ inputArray[i]; break;
										case '7': newInput = newInput+ inputArray[i]; break;
										case '8': newInput = newInput+ inputArray[i]; break;
										case '9': newInput = newInput+ inputArray[i]; break;
										case '.': newInput = newInput+ inputArray[i]; break;
										case '-': if(i==0) {break;} else {inputValidation = false; break;}
										default: inputValidation = false; break;
									}
		
								}
								if(inputValidation)
								{
									double radius = Double.parseDouble(newInput);
									Circle9_1 c = new Circle9_1(radius);
									fieldsOut[0].setText(Area.getArea(c));
								}
								else
								fieldsOut[0].setText("Input Error");
								
							}
						}
							
					});					
					
					
					
					labels[1] = new JLabel("Rectangle");
					fields[1] = new JTextField(10);
					fields[2] = new JTextField(10);
					
					JPanel rectInput = new JPanel();
					rectInput.setLayout(new GridLayout(2,2));
					JLabel rectHeight = new JLabel("Height:");
					JLabel rectWidth = new JLabel("Width:");
					rectInput.add(rectHeight);
					rectInput.add(fields[1]);
					rectInput.add(rectWidth);
					rectInput.add(fields[2]);
					
					fieldsOut[1] = new JTextField(10);
					calculate[1] = new JButton("Calculate");
					fieldPanel[1] = new JPanel();
					
					wrapper = new JPanel();
					wrapper.add(labels[1]);
					grid.add(wrapper);
					
					fieldPanel[1].add(rectInput);
					fieldPanel[1].add(calculate[1]);
					fieldPanel[1].add(fieldsOut[1]);
					grid.add(fieldPanel[1]);
					
					calculate[1].addActionListener(new ActionListener() 
					{
							public void actionPerformed(ActionEvent arg0) 
							{
								boolean inputValidation = true;
								if(fields[1].getText().isEmpty()==false && fields[2].getText().isEmpty()==false)
								{
									String input = fields[1].getText();
									String input2 = fields[2].getText();
									
									input = inputValidation(input);
									input2 = inputValidation(input2);
									
									if((input.equals("Input Error"))==false && (input2.equals("Input Error"))==false)
									{
										double height = Double.parseDouble(input);
										double width = Double.parseDouble(input2);
										KeithRectangle r = new KeithRectangle(height, width);
										
										fieldsOut[1].setText(Area.getArea(r));
									}
									else
									fieldsOut[1].setText("Input Error");
									
								}
						
							}
							
							private String inputValidation(String input)
							{
									boolean inputValidation = true;
									char[] inputArray = input.toCharArray();
									
									String newInput = "";
									
									for(int i = 0; i<input.length(); i++)
									{
										switch (inputArray[i])
										{
											case '0': newInput = newInput+ inputArray[i]; break;
											case '1': newInput = newInput+ inputArray[i]; break;
											case '2': newInput = newInput+ inputArray[i]; break;
											case '3': newInput = newInput+ inputArray[i]; break;
											case '4': newInput = newInput+ inputArray[i]; break;
											case '5': newInput = newInput+ inputArray[i]; break;
											case '6': newInput = newInput+ inputArray[i]; break;
											case '7': newInput = newInput+ inputArray[i]; break;
											case '8': newInput = newInput+ inputArray[i]; break;
											case '9': newInput = newInput+ inputArray[i]; break;
											case '.': newInput = newInput+ inputArray[i]; break;
											case '-': if(i==0) {break;} else {inputValidation = false; break;}
											default: inputValidation = false; break;
										}
			
									}
									
									if(inputValidation) return newInput; else return "Input Error";

							}
							

					});	
					
					labels[2] = new JLabel("Cylinder");
					wrapper = new JPanel();
					wrapper.setLayout(new GridLayout(2,2));
					label = new JLabel("Radius:");
					wrapper.add(label);
					label = new JLabel("Height:");
					fields[3] = new JTextField(10);
					wrapper.add(fields[3]);
					wrapper.add(label);
					fields[4] = new JTextField(10);
					wrapper.add(fields[4]);
					
					fieldsOut[2] = new JTextField(10);
					calculate[2] = new JButton("Calculate");
					fieldPanel[2] = new JPanel();
					
					fieldPanel[2].add(wrapper);
					
					wrapper = new JPanel();
					wrapper.add(labels[2]);
					grid.add(wrapper);
					
					fieldPanel[2].add(calculate[2]);
					fieldPanel[2].add(fieldsOut[2]);
					grid.add(fieldPanel[2]);
					
					calculate[2].addActionListener(new ActionListener() 
					{
					
							public void actionPerformed(ActionEvent arg0) 
							{
								boolean inputValidation = true;
								if(fields[3].getText().isEmpty()==false && fields[4].getText().isEmpty()==false)
								{
									String input = fields[3].getText();
									String input2 = fields[4].getText();
									
									input = inputValidation(input);
									input2 = inputValidation(input2);
									
									if((input.equals("Input Error"))==false && (input2.equals("Input Error"))==false)
									{
										double height = Double.parseDouble(input);
										double width = Double.parseDouble(input2);
										Cylinder r = new Cylinder(height, width);
										
										fieldsOut[2].setText(Area.getArea(r));
									}
									else
									fieldsOut[2].setText("Input Error");
									
								}
						
							}
							
							private String inputValidation(String input)
							{
									boolean inputValidation = true;
									char[] inputArray = input.toCharArray();
									
									String newInput = "";
									
									for(int i = 0; i<input.length(); i++)
									{
										switch (inputArray[i])
										{
											case '0': newInput = newInput+ inputArray[i]; break;
											case '1': newInput = newInput+ inputArray[i]; break;
											case '2': newInput = newInput+ inputArray[i]; break;
											case '3': newInput = newInput+ inputArray[i]; break;
											case '4': newInput = newInput+ inputArray[i]; break;
											case '5': newInput = newInput+ inputArray[i]; break;
											case '6': newInput = newInput+ inputArray[i]; break;
											case '7': newInput = newInput+ inputArray[i]; break;
											case '8': newInput = newInput+ inputArray[i]; break;
											case '9': newInput = newInput+ inputArray[i]; break;
											case '.': newInput = newInput+ inputArray[i]; break;
											case '-': if(i==0) {break;} else {inputValidation = false; break;}
											default: inputValidation = false; break;
										}
			
									}
									
									if(inputValidation) return newInput; else return "Input Error";

							}

							
					});			
							
					
				
				
				
				panel.add(grid, BorderLayout.CENTER);
				topPanel.add(panel);
	}
	
	@Override
	public String getDescription()
	{
		return "1. Area Class"
+"\nWrite a class that has three overloaded static methods for calculating the areas of the following geometric shapes:"
+"\n  circles"
+"\n  rectangles"
+"\n  cylinders"
+"\nHere are the formulas for calculating the area of the shapes."
+"\nBecause the three methods are to be overloaded, they should each have the same name, but different parameter lists. Demonstrate the class in a complete program.";
	}
		
}

// --------

// --------- Area Class -------------------------

class Area
{
	static DecimalFormat x = new DecimalFormat("0.00");
	
	public static String getArea(KeithRectangle r)
	{
		double area = 0.0;
		
		area = r.getLength()*r.getWidth();
	
		return x.format(area);
	}
	
	public static String getArea(Circle9_1 c)
	{
		double area = 0.0;
		
		area = Math.pow(c.getRadius(),2)*Math.PI;
	
		return x.format(area);
	}
	
	public static String getArea(Cylinder c)
	{
		double area = 0.0;
		
		area = Math.pow(c.getRadius(),2)*Math.PI*c.getHeight();
	
		return x.format(area);
	}
	
	
}

// Circle Class

class Circle9_1
{
	double radius;
	public Circle9_1(double radius)
	{
		this.radius=radius;
	}
	
	public double getRadius()
	{
		return radius;
	}
}

class KeithRectangle
{
	double width;
	double length;
	
	public KeithRectangle(double length, double width)
	{
		this.length=length;
		this.width=width;
	}
	
	public double getLength()
	{
		return length;
	}
	
	public double getWidth()
	{
		return width;
	}
}

class Cylinder
{
	double radius;
	double height;
	
	public Cylinder(double radius, double height)
	{
		this.radius=radius;
		this.height=height;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public double getHeight()
	{
		return height;
	}
}