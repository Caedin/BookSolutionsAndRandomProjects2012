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



class Problem10_8  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input {##,##,##} :");
	JLabel outputLabel = new JLabel("");
	JTextField input = new JTextField(40);
	
	
	public Problem10_8()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "8. Sum of Numbers in a String"
+"\nWrite a program that asks the user to enter a series of numbers separated by commas. Here is an example of valid input:"
 
+"\n\n7,9,10,2,18,6"

+"\n\nThe program should calculate and display the sum of all the numbers.";
	}
	
	
	public void buildPanel()
	{
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						if(input.getText().isEmpty()==false)
							outputLabel.setText(sumNumbers(input.getText()));								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Sum Counter");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		mainPanel.add(inputPanel, BorderLayout.CENTER);

		outputPanel.add(outputLabel);
		mainPanel.add(outputPanel, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
	
	}
	
	private String sumNumbers(String input)
	{
		long[] numbers = new long[input.length()];
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
				case(','): 
					if(temp.length()>18) return "OVERFLOW";
					numbers[counter] = Long.parseLong(temp);
					temp = "";
					counter++;
					break;
				case(' '): 
					if(temp.length()>18) return "OVERFLOW";
					numbers[counter] = Long.parseLong(temp);
					temp = "";
					counter++;
					break;
				
				default: break;
				
			}
			
		}
		if(temp.length()>18) return "OVERFLOW";
		
		if(temp.isEmpty()==false)
			numbers[counter] = Long.parseLong(temp);
		temp = "";
		counter++;
		
		long sum = 0;
		for(int i = 0; i<counter; i++)
		{
			sum = sum + numbers[i];
		}
		
		return "The sum of the numbers is: "+sum;
		
	}
	
	
}
				