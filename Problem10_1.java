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


class Problem10_1  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input:");
	JLabel outputLabel = new JLabel("");
	JTextField input = new JTextField(40);
	
	
	public Problem10_1()
	{
		buildPanel();		
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "1. Backward String"
+"\nWrite a method that accepts a String object as an argument and displays its contents backward. For instance, if the string argument is “gravity” the method should display “ytivarg”. "
+"\nDemonstrate the method in a program that asks the user to input a string and then passes it to the method.";
	}
	
	
	public void buildPanel()
	{
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						outputLabel.setText(reverseWords(input.getText()));								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Backword String Program");
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
	
	private String reverseWords(String input)
	{
		char[] chars = new char[input.length()];
		
		String output = "";
		
		for(int i = 0; i<chars.length; i++)
			chars[i] = input.charAt(input.length()-(i+1));
		
		for(int i = 0; i<chars.length; i++)
			output = output + chars[i];
			
		return output;
	}
	
	
}
				