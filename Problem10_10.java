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



class Problem10_10 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Name of File :");
	JTextArea outputLabel = new JTextArea(25,25);
	JTextArea fileList = new JTextArea(25,25);
	JTextField input = new JTextField(40);
	
	
	public Problem10_10()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "10. Word Counter"+
"\n\nWrite a program that asks the user for the name of a file. The program should display the number of words that the file contains."
+"\n\nNOTE: Calculus.txt was added to the zip folder to be used as an example for this problem.";
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
							outputLabel.append(getFileWords(input.getText()));
							outputLabel.setEditable(false);
						}								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Word Counter : Example ( Calculus.txt ) ");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		
	
		outputPanel.setLayout(new GridLayout(0,2));
		
		JPanel left = new JPanel(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		
		left.add(new JLabel("List of available files"), BorderLayout.NORTH);
		right.add(new JLabel("Word Count"), BorderLayout.NORTH);
		
		JScrollPane scrollFileList = new JScrollPane(fileList);
		left.add(scrollFileList, BorderLayout.CENTER);
		JScrollPane scroll = new JScrollPane(outputLabel);
		right.add(scroll, BorderLayout.CENTER);
		
		outputPanel.add(left);
		outputPanel.add(right);
		
		mainPanel.add(outputPanel, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
		populateList();
	
	}
	
	private void populateList()
	{
					String path = "."; 
 
 					String files;
  					File folder = new File(path);
  					File[] listOfFiles = folder.listFiles(); 
 
  					for (int i = 0; i < listOfFiles.length; i++) 
  					{
 
					   if (listOfFiles[i].isFile()) 
					   {
						   files = listOfFiles[i].getName();
							if(files.endsWith(".txt") || files.endsWith(".TXT") || files.endsWith(".java") || files.endsWith(".JAVA"))
						   	fileList.append(files + "\n");
					   }
				   }
	}

	
	private String getFileWords(String s)
	{
		String output = "";
		try
				{
					// THIS CODE SEGMENT WILL NOT RUN IN AN APPLET THAT IS NOT "SIGNED"
					// By default applets cannot alter files on the hard drive of the user.
					// If the panel is opened in a regular application the save button should work.
					// This can be tested in HomeworkApplication.java through HomeworkRun.java only
					
					File inputFile = new File(s);
					Scanner inputScanner = new Scanner(inputFile);
					
					while(inputScanner.hasNext())
					{
						output = output + inputScanner.nextLine() + "\n";
						
					}
					
					inputScanner.close();
					String numWords = getWords(output);
					return numWords;
				}
				catch(IOException e){outputLabel.append(e+"");}
				
		
		
		return output;
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
				