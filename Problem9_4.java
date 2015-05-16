


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------

class Problem9_4 extends PaintPanel
{
	JLabel label = new JLabel();
	JTextField widthOne = new JTextField(10);
	JTextField heightOne = new JTextField(10);
	JTextField widthTwo = new JTextField(10);
	JTextField heightTwo = new JTextField(10);
	
	JTextArea output = new JTextArea(10,35);
	
	public Problem9_4()
	{
		add(buildPanel());
	}
	
	private JPanel buildPanel()
	{
		JPanel finalPanel = new JPanel(new BorderLayout());
		finalPanel.add(buildTop(), BorderLayout.NORTH);
		finalPanel.add(buildCenter(), BorderLayout.CENTER);
		finalPanel.add(buildBottom(), BorderLayout.SOUTH);
		return finalPanel;
	}
	
	private JPanel buildTop()
	{
		JPanel topPanel = new JPanel(new GridLayout(0,2,20,0));
		JPanel topLeft = new JPanel(new BorderLayout());
		JPanel topRight = new JPanel(new BorderLayout());
		
		label = new JLabel("Tract of Land 1");
		topLeft.add(wrapper(label), BorderLayout.NORTH);
		
		JPanel topLeftGrid = new JPanel(new GridLayout(2,2));
		label = new JLabel("Height");
		topLeftGrid.add(wrapper(label));
		topLeftGrid.add(wrapper(heightOne));
		label = new JLabel("Width");
		topLeftGrid.add(wrapper(label));
		topLeftGrid.add(wrapper(widthOne));
		
		topLeft.add(wrapper(topLeftGrid), BorderLayout.CENTER);
		
		label = new JLabel("Tract of Land 2");
		topRight.add(wrapper(label), BorderLayout.NORTH);
		
		JPanel topRightGrid = new JPanel(new GridLayout(2,2));
		label = new JLabel("Height");
		topRightGrid.add(wrapper(label));
		topRightGrid.add(wrapper(heightTwo));
		label = new JLabel("Width");
		topRightGrid.add(wrapper(label));
		topRightGrid.add(wrapper(widthTwo));
		
		topRight.add(wrapper(topRightGrid), BorderLayout.CENTER);
		
		topPanel.add(wrapper(topLeft));
		topPanel.add(wrapper(topRight));
		return topPanel;
	}
	
	private JPanel buildCenter()
	{
		JPanel centerPanel = new JPanel(new BorderLayout());
		label = new JLabel("Output");
		centerPanel.add(wrapper(label), BorderLayout.NORTH);
		output.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(output);
		centerPanel.add(wrapper(scrollPane), BorderLayout.CENTER);
		
		return centerPanel;
	}
	
	private JPanel buildBottom()
	{
		JPanel bottomPanel = new JPanel();
		JButton generate = new JButton("Generate!");
		generate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				double width1 = parseDouble(widthOne.getText());
				double width2 = parseDouble(widthTwo.getText());
				double height1 = parseDouble(heightOne.getText());
				double height2 = parseDouble(heightTwo.getText());
				
				LandTract tract1, tract2;
				
				if(width1!=-1 && height1!=-1)
				{tract1 = new LandTract(width1,height1);}
				else	{tract1 = new LandTract();}
				
				if(width2!=-1 && height2!=-1)
				{tract2 = new LandTract(width2,height2);}
				else	{tract2 = new LandTract();}
				
				output.setText("");
				output.append("Tract 1 = "+tract1.toString()+"\n");
				output.append("Tract 2 = "+tract2.toString()+"\n");
				if(tract1.equals(tract2)) output.append("The Land Tracts are equal in size."); else output.append("The Land Tracts are not equal in size.");
			}
			
			private double parseDouble(String input)
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
							case '.': newInput = newInput+ inputArray[i]; break;
							default: inputValidation = false; break;
						}
			
					}
				}
				if(inputValidation && newInput.isEmpty()==false) return Double.parseDouble(newInput); else return -1;
			}
		});
		bottomPanel.add(wrapper(generate));
		return bottomPanel;
	}
	
	public JPanel wrapper(JComponent comp)
	{
		JPanel wrapper = new JPanel();
		wrapper.add(comp);
		return wrapper;
	}

		
	@Override
	public String getDescription()
	{
		return "4. LandTract Class"
+"\nMake a LandTract class that has two fields: one for the tract’s length and one for the width. The class should have a method that returns the tract’s area, as well as an equals method and a "
+"\ntoString method. Demonstrate the class in a program that asks the user to enter the dimensions for two tracts of land. The program should display the area of each tract of land and indicate "
+"\nwhether the tracts are of equal size.";
	}
	
	private class LandTract
	{
		double length;
		double width;
		
		public LandTract()
		{
			length = 0;
			width = 0;
		}
		
		public LandTract(double l, double w)
		{
			length = l;
			width = w;
		}
		
		public String toString()
		{
			DecimalFormat format = new DecimalFormat("0.00");
			
			return "Area: "+format.format(Area());
		}
		
		public double Area()
		{
			return length*width;
		}
		
		public boolean equals(LandTract t)
		{
			if(Area()==t.Area()) return true; else return false;
		}
	}

		
}
