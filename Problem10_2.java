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


class Problem10_2  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input:");
	JLabel outputLabel = new JLabel("");
	JTextField input = new JTextField(40);
	
	
	public Problem10_2()
	{
		buildPanel();		
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "2. Word Counter\n"+
"Write a method that accepts a String object as an argument and returns the number of words it contains. For instance, if the argument is “Four score and seven" 
+"\nyears ago” the method should return the number 6. Demonstrate the method in a program that asks the user to input a string and then passes it to the method. "
+"\nThe number of words in the string should be displayed on the screen.";
	}
	
	
	public void buildPanel()
	{
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						outputLabel.setText(getWords(input.getText()));								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Number of Words Calculator");
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
	
	private String getWords(String input)
	{
		String output = null;
		int wordNumbers = 0;
		int i = 0;
			
		while(i<input.length())					// Count through entire string
		{
			if(input.charAt(i)==' ')			// Increment string, but don't increase word count if it is spaces
			{
				i++;
			}
			else
			if(input.charAt(i)!=' ')			// If charAt(i) is not a space, it is the start of a word
			{
				while(input.charAt(i)!=' ')			// Loop through word until you reach a space, increase word count by one.
				{
					i++;
					if(i>=input.length()) break;
				}
				wordNumbers++;
			}
		}

		
	
		
		output = "Number of Words = "+(wordNumbers);
		return output;
	}
	
	
}
				