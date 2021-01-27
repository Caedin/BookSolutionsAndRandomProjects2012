


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------
public class Problem9_5 extends PaintPanel
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
	
	public Problem9_5()
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
		return "5. Month Class"
+"\nWrite a class named Month. The class should have an int field named monthNumber that holds the number of the month. For example, January would be 1, February would be 2, and so forth. In addition, provide the following methods:"
+"\n\n A no-arg constructor that sets the monthNumber field to 1."
+"\n A constructor that accepts the number of the month as an argument. It should set the monthNumber field to the value passed as the argument. If a value less than 1 or greater than 12 is passed, the constructor should set monthNumber to 1."
+"\n A constructor that accepts the name of the month, such as January or February as an argument. It should set the monthNumber field to the correct corresponding value."
+"\n A setMonthNumber method that accepts an int argument, which is assigned to the monthNumber field. If a value less than 1 or greater than 12 is passed, the method should set monthNumber to 1."
+"\n A getMonthNumber method that returns the value in the monthNumber field."
+"\n A getMonthName method that returns the name of the month. For example, if the monthNumber field contains 1, then this method should return January."
+"\n A toString method that returns the same value as the getMonthName method."
+"\n An equals method that accepts a Month object as an argument. If the argument object holds the same data as the calling object, this method should return true. Otherwise, it should return false."
+"\n A greaterThan method that accepts a Month object as an argument. If the calling objects monthNumber field is greater than the arguments monthNumber field, this method should return true. Otherwise, it should return false."
+"\n A lessThan method that accepts a Month object as an argument. If the calling objects monthNumber field is less than the arguments monthNumber field, this method should return true. Otherwise, it should return false.";
	}
		
}
	
class Month
{
	int monthNum = 0;
	
	public Month()
	{
		monthNum = 1;
	}
	
	public Month(int i)
	{
		monthNum = i;
		if(monthNum<=0 || monthNum>12) monthNum=1;
	}
	
	public Month(String s)
	{
		if(s.equals("January")) monthNum =1;
		if(s.equals("February")) monthNum =2;
		if(s.equals("March")) monthNum =3;
		if(s.equals("April")) monthNum =4;
		if(s.equals("May")) monthNum =5;
		if(s.equals("June")) monthNum =6;
		if(s.equals("July")) monthNum =7;
		if(s.equals("August")) monthNum =8;
		if(s.equals("September")) monthNum =9;
		if(s.equals("October")) monthNum =10;
		if(s.equals("November")) monthNum =11;
		if(s.equals("December")) monthNum =12;
		
		if(monthNum<=0 || monthNum>12) monthNum=1;
	}
	
	public void setMonthNumber(int i)
	{
		monthNum = i;
		if(monthNum<=0 || monthNum>12) monthNum=1;

	}
	
	public JPanel panel()
	{
			JPanel monthOutput = new JPanel();
			
			monthOutput.setBorder(BorderFactory.createTitledBorder(getMonthName()));
			monthOutput.setLayout(new GridLayout(0,2));
			
			JLabel label = new JLabel("Number: "+monthNum);
			
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