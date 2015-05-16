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


class Problem10_4  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input:");
	JLabel outputLabel = new JLabel("");
	JTextField input = new JTextField(40);
	JTextArea output = new JTextArea(25,25);
	
	
	public Problem10_4()
	{
		buildPanel();		
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "4. Vowels and Consonants"
+"\n\nWrite a class with a constructor that accepts a String object as its argument. The class should have a method that returns the number of vowels in the string, and another method that returns the number of consonants in the string. Demonstrate the class in a program that performs the following steps:"
+"\n1. The user is asked to enter a string."
+"\n2. The program displays the following menu:"
+"\n\na. Count the number of vowels in the string"
+"\nb. Count the number of consonants in the string"
+"\nc. Count both the vowels and consonants in the string"
+"\nd. Enter another string"
+"\ne. Exit the program"
+"\n\n3. The program performs the operation selected by the user and repeats until the user selects e, to exit the program.";
	}
	
	
	public void buildPanel()
	{
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						StringCounter counter = new StringCounter(input.getText());
						
						output.setText("");		
						output.append(counter.getVowels()+"\n"+counter.getConsonants()+"\n"+counter.getTotal());						
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Vowel and Consonent Counter");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		mainPanel.add(inputPanel, BorderLayout.CENTER);

		output.setEditable(false);
		outputPanel.add(output);
		mainPanel.add(outputPanel, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
	
	}
	
	
	private class StringCounter
	{
		private String s;
		private int numVowel;
		private int numCons;
		
		public StringCounter(String s)
		{
			this.s=s;
		}
		
		
		public String getVowels()
		{
			numVowel = 0;
			
			for(int i = 0; i<s.length(); i++)
			{
				switch(s.charAt(i))
				{
					case 'a': numVowel++; break;
					case 'e': numVowel++; break;
					case 'i': numVowel++; break;
					case 'o': numVowel++; break;
					case 'u': numVowel++; break;
					
					case 'A': numVowel++; break;
					case 'E': numVowel++; break;
					case 'I': numVowel++; break;
					case 'O': numVowel++; break;
					case 'U': numVowel++; break;		
					default : break;
				}
			}
		return "Number of vowels: "+numVowel;
		}
		
		public String getConsonants()
		{
			numCons = 0;
			
			for(int i = 0; i<s.length(); i++)
			{
				switch(s.charAt(i))
				{
					case 'b': numCons++; break;
					case 'c': numCons++; break;
					case 'd': numCons++; break;
					case 'f': numCons++; break;
					case 'g': numCons++; break;
					case 'h': numCons++; break;
					case 'j': numCons++; break;
					case 'k': numCons++; break;
					case 'l': numCons++; break;
					case 'm': numCons++; break;
					case 'n': numCons++; break;
					case 'p': numCons++; break;
					case 'q': numCons++; break;
					case 'r': numCons++; break;
					case 's': numCons++; break;
					case 't': numCons++; break;
					case 'v': numCons++; break;
					case 'w': numCons++; break;
					case 'x': numCons++; break;
					case 'y': numCons++; break;
					case 'z': numCons++; break;
					
					case 'B': numCons++; break;
					case 'C': numCons++; break;
					case 'D': numCons++; break;
					case 'F': numCons++; break;
					case 'G': numCons++; break;
					case 'H': numCons++; break;
					case 'J': numCons++; break;
					case 'K': numCons++; break;
					case 'L': numCons++; break;
					case 'M': numCons++; break;
					case 'N': numCons++; break;
					case 'P': numCons++; break;
					case 'Q': numCons++; break;
					case 'R': numCons++; break;
					case 'S': numCons++; break;
					case 'T': numCons++; break;
					case 'V': numCons++; break;
					case 'W': numCons++; break;
					case 'X': numCons++; break;
					case 'Y': numCons++; break;
					case 'Z': numCons++; break;
					
					default : break;
				}
			}
		return "Number of consonants: "+numCons;
		}
		
		
		public String getTotal()
		{
			getVowels();
			getConsonants();
			
			return "Vowels+Consonants: "+(numCons+numVowel);
		}

	}
	
	
}
				