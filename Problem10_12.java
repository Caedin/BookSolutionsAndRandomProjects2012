


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// -----------------------------------------------



class Problem10_12 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input :");
	JTextArea outputLabel = new JTextArea(10,40);
	JTextField input = new JTextField(40);
	JTextField replaceLeft = new JTextField(10);
	JTextField replaceRight = new JTextField(10);
	
	public Problem10_12()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "12. Miscellaneous String Operations"
+"\n\nWrite a class with the following static methods:"
+"\n\n• WordCount. This method should accept a reference to a String object as an argument and return the number of words contained in the object."
+"\n• arrayToString. This method accepts a char array as an argument and converts it to a String object. The method should return a reference to the String object."
+"\n• mostFrequent. This method accepts a reference to a String object as an argument and returns the character that occurs the most frequently in the object."
+"\n• replaceSubstring. This method accepts three references to String objects as arguments. Let’s call them string1, string2, and string3. It searches string1 for all occurrences of string2."
+"\n\n When it finds an occurrence of string2, it replaces it with string3. For example, suppose the three arguments have the following values:"
+"\nWith these three arguments, the method would return a reference to a String object with the value “that dog jumped over that fence”."
+"\nDemonstrate each of these methods in a complete program.";
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
							outputLabel.append(input.getText()+"\n\n");
							outputLabel.append("Number of Words: "+StringOperations.wordCount(input.getText())+"\n");
							outputLabel.append("Most frequent character is : "+StringOperations.mostFrequent(input.getText())+"\n");
						}								
			}
		});	
		
					
		outputLabel.setEditable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel headerFrame = new JPanel(new BorderLayout());
		
		JLabel header = new JLabel("Miscellaneous String Operations");
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		
		headerFrame.add(KDUtil.wrap(header), BorderLayout.NORTH);
		headerFrame.add(inputPanel, BorderLayout.CENTER);
		
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new GridLayout(2,2));
		centerPanel.add(KDUtil.wrap(new JLabel("Replace This")));
		centerPanel.add(KDUtil.wrap(new JLabel("With This")));
		
		centerPanel.add(KDUtil.wrap(replaceLeft));
		centerPanel.add(KDUtil.wrap(replaceRight));
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(outputLabel);
		outputPanel.setLayout(new BorderLayout());
		JButton button = new JButton("Replace");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String in = input.getText();
				if(replaceLeft.getText().isEmpty()==false)
					in = StringOperations.replace(in, replaceLeft.getText(), replaceRight.getText());
				
						if(input.getText().isEmpty()==false)
						{
							outputLabel.setText("");
							outputLabel.append(in+"\n\n");
							outputLabel.append("Number of Words: "+StringOperations.wordCount(in)+"\n");
							outputLabel.append("Most frequent character is : "+StringOperations.mostFrequent(in)+"\n");
						}								
			}
		});
		
		outputPanel.add(KDUtil.wrap(new JLabel("Output")), BorderLayout.NORTH);
		outputPanel.add(KDUtil.wrap(scroll), BorderLayout.CENTER);
		outputPanel.add(KDUtil.wrap(button), BorderLayout.SOUTH);
		
		mainPanel.add(outputPanel, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
	
	}
	
	
		
		
	
}

class StringOperations
{
	public static int wordCount(String input)
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

		return wordNumbers;
	}
	
	public static String arrayToString(char[] array)
	{
		String output = new String();
		
		for(int i = 0; i<array.length; i++)
		{
			output = output + array[i];
		}
		
		return output;
	}
	
	public static char mostFrequent(String input)
	{
		int[] numChars = new int[255];				// numChars stores how many times that character occurs in the string. The index is a reference to the chars ascii code.
		char mostFrequent = ' ';
		
		for(int i = 0; i<input.length(); i++)
		{
			if(input.charAt(i)!=' ')	numChars[(int)input.charAt(i)]++;
		}
		
		int highest = 0;
		int indexHolder = 0;
		
		for(int i = 0; i<255; i++)
		{
			if(numChars[i]> highest) 
			{
				highest = numChars[i];
				indexHolder = i;
			}
		}
		
		mostFrequent = (char)indexHolder;
		
		return mostFrequent;
	}
	
	public static String replace(String input, String rThis, String wThat)
	{
			String output = new String(input);
			String temp = new String();
			
			int replaceLength = rThis.length();
			int index = 0;
			int offset = 0;
			
			for(int i = 0; i<input.length(); i++)						// parse through entire string
			{
				temp =  new String();
				for(int k = i; k<(i+replaceLength); k++)					// At each index compare if the string you are looking to replace starts at that index
				{
					if((i+replaceLength)<input.length())	temp = temp + input.charAt(k);
				}
				
				if(temp.equals(rThis))					// If the string to be replaced exsists at that index, create a new string
				{
					index = i+offset;
					String holder = new String();
					for(int z = 0; z<output.length(); z++)			// Parse through original string
					{
						if(z==index)				// When you get to the location where you need to replace
						{
								for(int p = 0; p<wThat.length(); p++)		// Add the new string in 
								{
									holder = holder + wThat.charAt(p);
								}
						
						offset=offset+(wThat.length()-replaceLength);		// After the new string is in, skip over the remaining set of chars that are being replaced
						z=z+(replaceLength);
					
						}
					holder = holder + output.charAt(z);
					}
					
					// At this point holder should have replaced the substring with new string.
					output = holder;					// Update output and continue parsing.

				}
				else
				{
					// Go to next index
				}
				
			}
			return output;
	}
}
				