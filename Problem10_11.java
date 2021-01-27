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
import java.io.*;
import java.awt.event.*;
// -----------------------------------------------



class Problem10_11 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Name of File :");
	JTextArea outputLabel = new JTextArea(30,30);
	JTextArea file = new JTextArea(30,30);
	JTextField input = new JTextField(40);
	
	
	public Problem10_11()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "11. Sales Analysis"
+"\n\nThe file SalesData.txt, available with this books source code at www.pearsonhighered.com/gaddis, contains the dollar amount of sales that a retail store made each day for a number of weeks. Each line in the file contains seven numbers, "
+"\nwhich are the sales numbers for one week. The numbers are separated by a comma. The following line is an example from the file:"
 
+ "\n\n2541.36,2965.88,1965.32,1845.23,7021.11,9652.74,1469.36"

+"\n\nWrite a program that opens the file and processes its contents. The program should display the following:"
+"\n\n The total sales for each week"
+"\n The average daily sales for each week"
+"\n The total sales for all of the weeks"
+"\n The average weekly sales"
+"\n The week number that had the highest amount of sales"
+"\n The week number that had the lowest amount of sales"
+"\n\n NOTE: At this point I can't get access to the original SalesData.txt, so the program will generate and save a random SalesData.txt file with a similar format, and then compute the same answers.";
	}
	
	
	public void buildPanel()
	{
		/*input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						if(input.getText().isEmpty()==false)
						{
							outputLabel.setText("");
							outputLabel.append(getFileWords(input.getText()));
							outputLabel.setEditable(false);
						}								
			}
		});*/	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Sales Analysis");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		/*inputPanel.add(inputLabel);
		inputPanel.add(input);
		mainPanel.add(inputPanel, BorderLayout.CENTER);*/
		
	
		outputPanel.setLayout(new GridLayout(0,2));
		
		JPanel left = new JPanel(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		
		left.add(new JLabel("SalesData.txt"), BorderLayout.NORTH);
		right.add(new JLabel("Processed Data"), BorderLayout.NORTH);
		
		JScrollPane scrollFileList = new JScrollPane(file);
		left.add(scrollFileList, BorderLayout.CENTER);
		JScrollPane scroll = new JScrollPane(outputLabel);
		right.add(scroll, BorderLayout.CENTER);
		
		outputPanel.add(left);
		outputPanel.add(right);
		
		mainPanel.add(outputPanel, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
		createSalesData();
		processData();
	
	}
	
	private void createSalesData()			// Creates SalesData.txt and saves it, then proceeds to read it and load it into the left textArea.
	{
		// CREATE DATA	//
		int numberWeeks = (int)Math.round(Math.random()*20)+10;
		DecimalFormat saleFormat = new DecimalFormat("0.00");			// This format is used to keep sales at 2 decimal places
		double sales = 0;
		String output = "";
		
		for(int i = 0; i<numberWeeks; i++)
		{
			for(int k = 0; k<7; k++)
			{
				sales = Math.random()*9999;					// Sales is a random number between 0 and 9,999;
				if(k<6)
					output = output + saleFormat.format(sales) + ",";			// Entries are seperated by commas
				if(k==6)
					output = output + saleFormat.format(sales);				// No comma after last entry in the week
			}
			output = output + "\n";					// Each week is on a new line.
		}
		
		
		// WRITE TO FILE
		// THIS CODE SEGMENT WILL NOT RUN IN AN APPLET THAT IS NOT "SIGNED"
		// By default applets cannot alter files on the hard drive of the user.
		// If the panel is opened in a regular application the save button should work.
		// This can be tested in HomeworkApplication.java through HomeworkRun.java only
		try
		{
			File salesData = new File("SalesData.txt");
			PrintStream fwriter = new PrintStream(salesData);
			fwriter.write((new String()).getBytes());

			
			
			fwriter.append(output);
			
			fwriter.close();
			file.append(output);
		}
		catch(IOException e){file.append(e+"");}
		
	}

	
	private void processData()		// Reads in the data from SalesData.txt and then computes the required totals, and loads them into the right text area.
	{
		ArrayList<String> weeks = new ArrayList();
		DecimalFormat money = new DecimalFormat("$#,##0.00");
		
				try
				{
					// THIS CODE SEGMENT WILL NOT RUN IN AN APPLET THAT IS NOT "SIGNED"
					// By default applets cannot alter files on the hard drive of the user.
					// If the panel is opened in a regular application the save button should work.
					// This can be tested in HomeworkApplication.java through HomeworkRun.java only
					
					File inputFile = new File("SalesData.txt");
					Scanner inputScanner = new Scanner(inputFile);
					
					while(inputScanner.hasNext())
					{
						weeks.add(inputScanner.nextLine());
					}
					
					inputScanner.close();
					
					
				}
				catch(IOException e){outputLabel.append(e+"");}
		
		outputLabel.append("\n\n--------------------- TOTAL ------------------------\n\n");
		
		outputLabel.append("\tTotal Sales : "+money.format(totalSales(weeks))+"\n");
		outputLabel.append("\tAverage Sales per Week : "+money.format(averageSalesWeek(weeks))+"\n");
		outputLabel.append("\tHighest Week : "+(int)weekHighest(weeks)+" ------ "+money.format(weekHighestValue(weeks))+"\n");
		outputLabel.append("\tLowest Week : "+(int)weekLowest(weeks)+" ------ "+money.format(weekLowestValue(weeks))+"\n");

		
		outputLabel.append("\n\n--------------------- BY WEEK ------------------------\n\n");
		
		for(int i = 0; i<weeks.size(); i++)			// Display data per week
		{
			outputLabel.append("Week "+(i+1)+" :\n");
			outputLabel.append("\tTotal Sales : "+money.format(totalSalesWeek(weeks.get(i)))+"\n");
			outputLabel.append("\tAverage Sales : "+money.format(averageSalesDay(weeks.get(i)))+"\n");
		}
				
		
		
	}
	
	private double totalSalesWeek(String input)
	{
		DecimalFormat money = new DecimalFormat("$#,##0.00");
		double[] numbers = new double[input.length()];
		String temp = "";
		int counter = 0;
		
		for(int i = 0; i<input.length(); i++)
		{
			char x = input.charAt(i);
			switch(x)
			{
				case('0'): temp = temp + x; break;
				case('1'): temp = temp + x; break;
				case('2'): temp = temp + x; break;
				case('3'): temp = temp + x; break;
				case('4'): temp = temp + x; break;
				case('5'): temp = temp + x; break;
				case('6'): temp = temp + x; break;
				case('7'): temp = temp + x; break;
				case('8'): temp = temp + x; break;
				case('9'): temp = temp + x; break;
				case('.'): temp = temp + x; break;
				case(','): 
					if(temp.length()>18) return -1;
					numbers[counter] = Double.parseDouble(temp);
					temp = "";
					counter++;
					break;
				case(' '): 
					if(temp.length()>18) return -1;
					numbers[counter] = Double.parseDouble(temp);
					temp = "";
					counter++;
					break;
				
				default: break;
				
			}
			
		}
		if(temp.length()>18) return -1;
		
		if(temp.isEmpty()==false)
			numbers[counter] = Double.parseDouble(temp);
		temp = "";
		counter++;
		
		double sum = 0;
		for(int i = 0; i<counter; i++)
		{
			sum = sum + numbers[i];
		}
		
		return sum;
		
	}
	
	private double averageSalesDay(String input)
	{
		double sum = totalSalesWeek(input)/7;
		
		return sum;
		
	}
	
	private double totalSales(ArrayList<String> weeks)
	{
		double sum = 0;
		
		for(int i = 0; i<weeks.size(); i++)
		{
			sum = sum + totalSalesWeek(weeks.get(i));
		}
		
		return sum;
	}
	
	private double averageSalesWeek(ArrayList<String> weeks)
	{
		double sum = totalSales(weeks);
		return sum/weeks.size();
	}
	
	private double weekHighest(ArrayList<String> weeks)
	{
		double highest = totalSalesWeek(weeks.get(0));
		double highWeek = 1;
		
		for(int i = 0; i<weeks.size(); i++)
		{
			if(totalSalesWeek(weeks.get(i))>highest)	{highest = totalSalesWeek(weeks.get(i)); highWeek = i+1;}
		}
		
		return highWeek;

	}
	
	private double weekHighestValue(ArrayList<String> weeks)
	{
		double highest = totalSalesWeek(weeks.get(0));
		double highWeek = 1;
		
		for(int i = 0; i<weeks.size(); i++)
		{
			if(totalSalesWeek(weeks.get(i))>highest)	{highest = totalSalesWeek(weeks.get(i)); highWeek = i+1;}
		}
		
		return highest;

	}
	
	private double weekLowest(ArrayList<String> weeks)
	{
		double lowest = totalSalesWeek(weeks.get(0));
		double lowWeek = 1;
		
		for(int i = 0; i<weeks.size(); i++)
		{
			if(totalSalesWeek(weeks.get(i))<lowest)	{lowest = totalSalesWeek(weeks.get(i)); lowWeek = i+1;}
		}
		
		return lowWeek;

	}
	
	private double weekLowestValue(ArrayList<String> weeks)
	{
		double lowest = totalSalesWeek(weeks.get(0));
		double lowWeek = 1;
		
		for(int i = 0; i<weeks.size(); i++)
		{
			if(totalSalesWeek(weeks.get(i))<lowest)	{lowest = totalSalesWeek(weeks.get(i)); lowWeek = i+1;}
		}
		
		return lowest;

	}
	
	

	
}
				