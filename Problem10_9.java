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



class Problem10_9 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input {###} :");
	JTextArea outputLabel = new JTextArea(20,20);
	JTextField input = new JTextField(40);
	
	
	public Problem10_9()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "9. Sum of Digits in a String"
+"\nWrite a program that asks the user to enter a series of single digit numbers with nothing separating them. The program should display the sum of all the single digit numbers in the string. "
+"\nFor example, if the user enters 2514, the method should return 12, which is the sum of 2, 5, 1, and 4. The program should also display the highest and lowest digits in the string. "
+"\n(Hint: Convert the string to an array.)";
	}
	
	
	public void buildPanel()
	{
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						if(input.getText().isEmpty()==false)
						{
							outputLabel.setText("");
							outputLabel.append(sumNumbers(input.getText()));
							outputLabel.setEditable(false);
						}								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Sum of all digits");
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
		int counter = 0;
		int high = 0;
		int low = 9;
		
		for(int i = 0; i<input.length(); i++)
		{
			char x = input.charAt(i);
			switch(x)
			{
				case('0'): if(0>high) high=0; if(0<low) low = 0; counter = counter+0; break;
				case('1'): if(1>high) high=1; if(1<low) low = 1; counter = counter+1; break;
				case('2'): if(2>high) high=2; if(2<low) low = 2; counter = counter+2; break;
				case('3'): if(3>high) high=3; if(3<low) low = 3; counter = counter+3; break;
				case('4'): if(4>high) high=4; if(4<low) low = 4; counter = counter+4; break;
				case('5'): if(5>high) high=5; if(5<low) low = 5; counter = counter+5; break;
				case('6'): if(6>high) high=6; if(6<low) low = 6; counter = counter+6; break;
				case('7'): if(7>high) high=7; if(7<low) low = 7; counter = counter+7; break;
				case('8'): if(8>high) high=8; if(8<low) low = 8; counter = counter+8; break;
				case('9'): if(9>high) high=9; if(9<low) low = 9; counter = counter+9; break;		
				default: break;
				
			}
			
		}
		
		
		
		return "The sum of the digits is: "+counter+"\nLargest Digit: "+high+"\nSmallest Digit: "+low;
		
	}
	
	
}
				