


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------
public class Problem12_4 extends PaintPanel
{
	JPanel wrapper = new JPanel();
	JPanel northPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JTextField nameInput = new JTextField(10);
	JTextField numberInput = new JTextField(10);
	
	ArrayList<Month> months = new ArrayList();
	Month m = new Month(3);
	
	Month compare1 = new Month(3);
	Month compare2 = new Month(4);
	
	public Problem12_4()
	{
		setLayout(new BorderLayout());
		
		buildPanel();
		
		wrapper = new JPanel();
		wrapper.add(northPanel);
		add(wrapper, BorderLayout.NORTH);
		
		wrapper = new JPanel();
		wrapper.add(centerPanel);
		add(wrapper, BorderLayout.CENTER);
	}
		
	public void buildPanel()
	{
		buildNorthPanel();
	}
	
	public void buildNorthPanel()
	{
		northPanel.setLayout(new GridLayout(2,0));
		JLabel header = new JLabel("Month Method Demonstrations");
		wrapper = new JPanel();
		wrapper.add(header);
		northPanel.add(wrapper);
		
		wrapper = new JPanel();
		wrapper.setLayout(new GridLayout(0,3,5,5));
		JButton constructors = new JButton("Constructors");
		JButton modifiers = new JButton("Modifiers");
		JButton comparisons = new JButton("Comparisons");
		wrapper.add(constructors);
		wrapper.add(modifiers);
		wrapper.add(comparisons);
		northPanel.add(wrapper);
		
		
		constructors.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						centerPanel.removeAll();
						centerPanel.add(buildConstructorPanel());
						repaint();
						revalidate();
					}
	
	      	});
				
		modifiers.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						centerPanel.removeAll();
						centerPanel.add(buildModifierPanel());
						repaint();
						revalidate();
					}
	
	      	});
				
		comparisons.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						centerPanel.removeAll();
						centerPanel.add(buildComparisonPanel());
						repaint();
						revalidate();
					}
	
	      	});
		
	}
	
	public JPanel buildConstructorPanel()
	{
		JPanel conPanel = new JPanel();
		conPanel.setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(2,0));
		
		wrapper = new JPanel();
		wrapper.setLayout(new GridLayout(2,2));
		
		JLabel name = new JLabel("  Name:");
		JLabel number = new JLabel("Number:");
		wrapper.add(name);
		wrapper.add(nameInput);
		wrapper.add(number);
		wrapper.add(numberInput);
		
		leftPanel.add(wrapper);
		
		wrapper = new JPanel();
		JButton create = new JButton("Create");
		wrapper.add(create);
		leftPanel.add(wrapper);
		
		create.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						String nameIn = nameInput.getText();
						String numberIn = numberInput.getText();
						

						Month m = new Month();
						if(nameIn.isEmpty()==false)
						{
							m = new Month(nameIn);
						}
							
						if(numberIn.isEmpty()==false)
						{
							m = new Month(Integer.parseInt(numberIn));
						}


						
						months.add(m);
						centerPanel.removeAll();
						centerPanel.add(buildConstructorPanel());
						repaint();
						revalidate();
						
					}
	
	      	});
		
		JPanel rightPanel = new JPanel();
		if(months.size()>0) rightPanel.setLayout(new GridLayout(months.size(),0,5,5));
		
		for(int i = 0; i<months.size(); i++)
		{
			rightPanel.add(months.get(i).panel());
		}
		
		wrapper = new JPanel();
		wrapper.add(leftPanel);
		conPanel.add(wrapper, BorderLayout.WEST);
		
		wrapper = new JPanel();
		wrapper.add(rightPanel);
		conPanel.add(wrapper, BorderLayout.EAST);
		
		return conPanel;
	}
	
	public JPanel buildModifierPanel()
	{
		JPanel modPanel = new JPanel();
		JPanel internal = new JPanel();
		
		internal.setLayout(new GridLayout(3,0));
		
		JLabel output = new JLabel("Output");
		final JTextField out = new JTextField(10);
		
		wrapper = new JPanel();
		wrapper.add(m.panel());
		internal.add(wrapper);
		
		wrapper = new JPanel();
		
		wrapper.setLayout(new GridLayout(0,4,5,5));
		JButton button = new JButton("Set Number");
		JPanel wrapper2 = new JPanel();
		wrapper2.add(button);
		wrapper.add(wrapper2);
		button.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						String input = inputValidation(JOptionPane.showInputDialog(null, "New Month Number:"));
						
						if(input.isEmpty()==false && input.equals("Input Error")==false)
							m.setMonthNumber(Integer.parseInt(input));
							
						centerPanel.removeAll();
						centerPanel.add(buildModifierPanel());
						repaint();
						revalidate();
					}
					
					private String inputValidation(String input)
					{
					boolean inputValidation = true;
					String newInput = "";
					
					if(input.isEmpty()==false)
						{
							char[] inputArray = input.toCharArray();
										
										
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
										default: inputValidation = false; break;
									}
					
							}
						}
					if(inputValidation) return newInput; else return "Input Error";
					}
	
	      	});
		button = new JButton("Get Number");
		wrapper2 = new JPanel();
		wrapper2.add(button);
		wrapper.add(wrapper2);
		button.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						out.setText(m.getMonthNumber()+"");
					}
	
	      	});
		button = new JButton("Get Month");
		wrapper2 = new JPanel();
		wrapper2.add(button);
		wrapper.add(wrapper2);
		button.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						out.setText(m.getMonthName());
					}
	
	      	});
		button = new JButton("toString");
		wrapper2 = new JPanel();
		wrapper2.add(button);
		wrapper.add(wrapper2);
		internal.add(wrapper);
		button.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						out.setText(m.toString());
					}
	
	      	});
		
		
		
		wrapper = new JPanel();
		wrapper.setLayout(new GridLayout(2,0));
		wrapper2 = new JPanel();
		wrapper2.add(output);
		wrapper.add(wrapper2);
		wrapper2 = new JPanel();
		wrapper2.add(out);
		wrapper.add(wrapper2);
		internal.add(wrapper);
		
		wrapper = new JPanel();
		wrapper.add(internal);
		modPanel.add(wrapper);
		
		return modPanel;
	}
	
	public JPanel buildComparisonPanel()
	{
		JPanel comparisonPanel = new JPanel();
		comparisonPanel.setLayout(new GridLayout(4,0));
		
		JPanel output = new JPanel();
		JLabel out = new JLabel("Output");
		final JTextField field = new JTextField(10);
		
		JPanel months = new JPanel();
		months.setLayout(new GridLayout(0,2,5,5));
		months.add(wrap(compare1.panel()));
		months.add(wrap(compare2.panel()));
	
		comparisonPanel.add(wrap(months));
		
		JButton button = new JButton("Randomize");
		button.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						compare1 = new Month((int)Math.round((Math.random()*12)));
						compare2 = new Month((int)Math.round((Math.random()*12)));
						centerPanel.removeAll();
						centerPanel.add(buildComparisonPanel());
						repaint();
						revalidate();
					}
	
	      	});
		

		comparisonPanel.add(wrap(button));
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(0,3));
		
		button = new JButton("Equal");
		buttons.add(wrap(button));
		button.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						field.setText(compare1.equals(compare2)+"");
					}
	
	      	});
		button = new JButton("Greater Than");
		buttons.add(wrap(button));
		button.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						field.setText(compare1.greaterThan(compare2)+"");
					}
	
	      	});
		button = new JButton("Less Than");
		buttons.add(wrap(button));
		comparisonPanel.add(wrap(buttons));
		button.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						field.setText(compare1.lessThan(compare2)+"");
					}
	
	      	});
		output.setLayout(new GridLayout(2,0));
		output.add(wrap(out));
		output.add(wrap(field));
		
		comparisonPanel.add(wrap(output));
		
		
		return comparisonPanel;
	}
	
	public JPanel wrap(Component panel)
	{
		wrapper = new JPanel();
		wrapper.add(panel);
		return wrapper;
	}

	

	
	@Override
	public String getDescription()
	{
		return "4. Month Class Exceptions"
+"\n\nProgramming Challenge 5 of Chapter 9 required you to write a Month class that holds information about the month. Write exception classes for the following error conditions:"
 
+"\n\n• A number less than 1 or greater than 12 is given for the month number."
+"\n• An invalid string is given for the name of the month."
+"\n\nModify the Month class so that it throws the appropriate exception when either of these errors occurs. Demonstrate the classes in a program.";	}
		

		
	private class Month
	{
		int monthNum = 1;
		boolean validName = true;
		
		public Month()
		{
			monthNum = 1;
		}
		
		public Month(int i)
		{
			monthNum = i;
		}
		
		public Month(String s)
		{
			validName = false;
			
			
			if(s.contains("January") || s.contains("january") || s.contains("JANUARY") || s.contains("jan") || s.contains("Jan") || s.contains("JAN")) {monthNum =1; validName = true;}
			if(s.contains("February") || s.contains("february")|| s.contains("FEBRUARY") || s.contains("feb") || s.contains("Feb") || s.contains("FEB")) {monthNum =2; validName = true;}
			if(s.contains("March") || s.contains("march")|| s.contains("MARCH") || s.contains("mar") || s.contains("Mar") || s.contains("MAR")) {monthNum =3; validName = true;}
			if(s.contains("April") || s.contains("april")|| s.contains("APRIL") || s.contains("apr") || s.contains("Apr") || s.contains("APR")) {monthNum =4; validName = true;}
			if(s.contains("May") || s.contains("may")|| s.contains("MAY") || s.contains("may") || s.contains("May") || s.contains("MAY")) {monthNum =5; validName = true;}
			if(s.contains("June") || s.contains("june")|| s.contains("JUNE") || s.contains("jun") || s.contains("Jun") || s.contains("JUN")) {monthNum =6; validName = true;}
			if(s.contains("July") || s.contains("july")|| s.contains("JULY") || s.contains("jul") || s.contains("Jul") || s.contains("JUL")) {monthNum =7; validName = true;}
			if(s.contains("August") || s.contains("august")|| s.contains("AUGUST") || s.contains("aug") || s.contains("Aug") || s.contains("AUG")) {monthNum =8; validName = true;}
			if(s.contains("September") || s.contains("september")|| s.contains("SEPTEMBER") || s.contains("sep") || s.contains("Sep") || s.contains("SEP")) {monthNum =9; validName = true;}
			if(s.contains("October") || s.contains("october")|| s.contains("OCTOBER") || s.contains("oct") || s.contains("Oct") || s.contains("OCT")) {monthNum =10; validName = true;}
			if(s.contains("November") || s.contains("november")|| s.contains("NOVEMBER") || s.contains("nov") || s.contains("Nov") || s.contains("NOV")) {monthNum =11; validName = true;}
			if(s.contains("December") || s.contains("december")|| s.contains("DECEMBER") || s.contains("dec") || s.contains("Dec") || s.contains("DEC")) {monthNum =12; validName = true;}
	
		}
		
		public void setMonthNumber(int i)
		{
			monthNum = i;
		}
		
		public JPanel panel()
		{
				JPanel monthOutput = new JPanel();
				
				monthOutput.setBorder(BorderFactory.createTitledBorder(getMonthName()));
				monthOutput.setLayout(new GridLayout(0,2));
				
				JLabel label = new JLabel();
				
				try
				{
					label = new JLabel("Number: "+monthNum);
					if(monthNum<=0 || monthNum>12) throw new IllegalArgumentException("Invalid Month Number");
					if(validName == false) throw new IllegalArgumentException("Invalid Month Name");
				}
				catch(IllegalArgumentException e)
				{
					monthOutput.setBorder(BorderFactory.createTitledBorder(""));
					label = new JLabel(e.getMessage());
				}
				
				JPanel wrapper = new JPanel();
				wrapper.add(label);
				monthOutput.add(wrapper);
				
				return monthOutput;
	
	
		}
		
		public int getMonthNumber() {return monthNum;}
		public String getMonthName() 
		{
			switch(monthNum)
			{
				case 1: return ("January");
				case 2: return ("February");
				case 3: return ("March");
				case 4: return ("April");
				case 5: return ("May");
				case 6: return ("June");
				case 7: return ("July");
				case 8: return ("August");
				case 9: return ("September");
				case 10: return ("October");
				case 11: return ("November");
				case 12: return ("December");
				default: return ("January");
				
			}
		}
		
		public String toString()
		{
			return getMonthName();
		}
		
		public boolean equals(Month m)
		{
			if(this.getMonthNumber()==m.getMonthNumber()) return true; else return false;
		}
		
		public boolean greaterThan(Month m)
		{
			if(this.getMonthNumber()>m.getMonthNumber()) return true; else return false;
		}
		
		public boolean lessThan(Month m)
		{
			if(this.getMonthNumber()<m.getMonthNumber()) return true; else return false;
		}
	}
}