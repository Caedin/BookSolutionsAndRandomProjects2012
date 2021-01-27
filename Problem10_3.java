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


class Problem10_3  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input:");
	JLabel outputLabel = new JLabel("");
	JTextField input = new JTextField(40);
	
	
	public Problem10_3()
	{
		buildPanel();		
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "3. Sentence Capitalizer"
+"\nWrite a method that accepts a String object as an argument and returns a copy of the string with the first character of each sentence capitalized. "
+"\nFor instance, if the argument is  hello. my name is Joe. what is your name?  the method should return the string  Hello. My name is Joe. What is your name?  "
+"\nDemonstrate the method in a program that asks the user to input a string and then passes it to the method. The modified string should be displayed on the screen.";
	}
	
	
	public void buildPanel()
	{
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						outputLabel.setText(capitalizeWords(input.getText()));								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Sentence Capitalizer");
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
	
	private String capitalizeWords(String input)
	{
		char[] chars = new char[input.length()];
		boolean capitalizeNext = false;
		
		String output = "";
		
		for(int i = 0; i<chars.length; i++)
			chars[i] = input.charAt(i);
			
		for(int i = 0; i<chars.length; i++)
		{
			if(i==0) if((int)chars[i]>=97) chars[i] = Character.toString((char)((int)(chars[i])-32)).charAt(0);
			
			if(chars[i] != ' ' && capitalizeNext==true )
			{
				System.out.println((int)chars[i]);
				if((int)chars[i]>=97 && (int)chars[i]<=122) 
				{
					chars[i] = Character.toString((char)((int)(chars[i])-32)).charAt(0);
					capitalizeNext = false;
				}
				
				if((int)chars[i]>=65 && (int)chars[i]<=90) capitalizeNext = false;
			}
			
			if(chars[i] == '.') capitalizeNext = true;
		}
		
		for(int i = 0; i<chars.length; i++)
			output = output + chars[i];
			
		return output;
	}
	
	
}
				