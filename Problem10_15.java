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



class Problem10_15 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input :");
	JTextArea outputLabel = new JTextArea(30,20);
	JTextField input = new JTextField(40);
	
	
	public Problem10_15()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "15. Pig Latin"
+"\n\nWrite a program that reads a sentence as input and converts each word to “Pig Latin”. In one version of Pig Latin, you convert a word by removing the first letter, placing that letter at the end of the word, and then appending “ay” to the word. Here is an example:"
+"\n\nEnglish: I SLEPT MOST OF THE NIGHT"
+"\nPig Latin: IAY LEPTSAY OSTMAY FOAY HETAY IGHTNAY";
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
							outputLabel.append(pigLatin(input.getText()));
							outputLabel.setEditable(false);
						}								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Pig Latin");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		mainPanel.add(inputPanel, BorderLayout.CENTER);

		
		JScrollPane scroll = new JScrollPane(outputLabel);
		outputPanel.add(scroll);
		mainPanel.add(scroll, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
	
	}
	
	private String pigLatin(String input)
	{
		String[] temp = getWords(input);
		for(int i = 0; i<temp.length; i++)
		{
			temp[i] = transform(temp[i]);
		}
		
		String finalOut = "";
		
		for(int i = 0; i<temp.length; i++)
		{
			finalOut = finalOut + temp[i] + " ";
		}
		
		return "Pig Latin : "+finalOut;
		
	}
	
	private String[] getWords(String input)				// Gets an array list of strings corresponding to each word in the sentance;
	{
		ArrayList<String> tempList = new ArrayList();
		String holder = "";
		
		for(int i = 0; i<input.length(); i++)
		{
			if(input.charAt(i)!= ' ')			// adds the character to the word
			{
				holder = holder + input.charAt(i);
			}
			
			if(input.charAt(i) == ' ' || i == input.length()-1)					// Adds the word to the list
			{
				tempList.add(holder);
				holder="";
			}

		}
		
		String[] temp = new String[tempList.size()];
		for(int k = 0; k<tempList.size(); k++)
		{
			temp[k] = tempList.get(k);
		}
		return temp;
	}
	
	private String transform(String input)				// Converts a single word to pig latin form
	{
		char[] temp = new char[input.length()];
		int counter = 0;
		
		for(int i = 0; i<input.length(); i++)
		{
			if(i<input.length()-1)
				temp[i] = input.charAt(i+1);
			else
				temp[i] = input.charAt(0);
				
			counter = counter + (int)input.charAt(i);
		}
		
		counter = counter / input.length();
		
		String output = new String(temp);
		
		if(counter>=97)
			output = output +"ay";
		if(counter<97)
			output = output +"AY";
		return output;
	}
	
	
}
				