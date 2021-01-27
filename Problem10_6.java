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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// -----------------------------------------------


class Problem10_6  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel(new GridLayout(0,2));
	JLabel inputLabel = new JLabel("Input:");
	JLabel outputLabel = new JLabel("");
	JTextField input = new JTextField(40);
	JTextArea output = new JTextArea(25,25);
	JTextArea output2 = new JTextArea(25,25);
	
	ArrayList<String> names = new ArrayList();
	ArrayList<String> numbers = new ArrayList();
	
	int phoneBookSize = 100;
	
	
	public Problem10_6()
	{
		buildPanel();		
		add(topPanel);
		setVisible(true);
	}
	
	private void createPhoneBook()
	{
		int randFirst;
		int randLast;
		
		String name;
		String number;
		
		
		for(int i = 0; i<phoneBookSize; i++)
		{
			name = "";
			number = "";
			
			randFirst = (int)Math.round(Math.random()*20);
			randLast = (int)Math.round(Math.random()*20);
			
			number = String.format("%03d",Math.round(Math.random()*999))+"-"+String.format("%04d",Math.round(Math.random()*9999));
			boolean unique = true;
			
			
			switch (randLast)
			{
				case 0: name = name + "Smith, "; break;
				case 1: name = name + "Johnson, "; break;
				case 2: name = name + "Williams, "; break;
				case 3: name = name + "Jones, "; break;
				case 4: name = name + "Brown, "; break;
				case 5: name = name + "Davis, "; break;
				case 6: name = name + "Miller, "; break;
				case 7: name = name + "Wilson, "; break;
				case 8: name = name + "Moore, "; break;
				case 9: name = name + "Taylor, "; break;
				case 10: name = name + "Anderson, "; break;
				case 11: name = name + "Thomas, "; break;
				case 12: name = name + "Jackson, "; break;
				case 13: name = name + "White, "; break;
				case 14: name = name + "Harris, "; break;
				case 15: name = name + "Martin, "; break;
				case 16: name = name + "Thompson, "; break;
				case 17: name = name + "Garcia, "; break;
				case 18: name = name + "Martinez, "; break;
				case 19: name = name + "Robinson, "; break;
				case 20: name = name + "Clark, "; break;
				default: break;
			}
			
			switch (randFirst)
			{
				case 0: name = name + "James"; break;
				case 1: name = name + "John"; break;
				case 2: name = name + "Robert"; break;
				case 3: name = name + "Michael"; break;
				case 4: name = name + "William"; break;
				case 5: name = name + "David"; break;
				case 6: name = name + "Richard"; break;
				case 7: name = name + "Charles"; break;
				case 8: name = name + "Joseph"; break;
				case 9: name = name + "Thomas"; break;
				case 10: name = name + "Mary"; break;
				case 11: name = name + "Patricia"; break;
				case 12: name = name + "Linda"; break;
				case 13: name = name + "Barbara"; break;
				case 14: name = name + "Elizabeth"; break;
				case 15: name = name + "Jennifer"; break;
				case 16: name = name + "Maria"; break;
				case 17: name = name + "Susan"; break;
				case 18: name = name + "Margaret"; break;
				case 19: name = name + "Sandra"; break;
				case 20: name = name + "Lisa"; break;
				default: break;
			}
			
			names.add(name);
			numbers.add(number);
			

		}
	}
		
	
	@Override
	public String getDescription()
	{
		return "6. Telemarketing Phone Number List"
+"\n\nWrite a program that has two parallel arrays of String objects. One of the arrays should hold peoples names and the other should hold their phone numbers. "
+"\nHere are example contents of both arrays:"



 
+"\n\nThe program should ask the user to enter a name or the first few characters of a name to search for in the array. The program should display all of the names that match the "
+"\nuser's input and their corresponding phone numbers. For example, if the user enters Smith, the program should display the following names and phone numbers from the list:"
 
+"\n\n Smith, William: 555-1785"
+"\n Smith, Brad: 555-9224";
	}
	
	
	public void buildPanel()
	{
		input.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
				search(input.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
				search(input.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
				search(input.getText());
            }
        });
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Phone Book Search");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		mainPanel.add(inputPanel, BorderLayout.CENTER);

		output.setEditable(false);
		output2.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		JScrollPane scroll2 = new JScrollPane(output2);
		
		outputPanel.add(scroll);
		outputPanel.add(scroll2);
		mainPanel.add(outputPanel, BorderLayout.SOUTH);
		
		createPhoneBook();
		output.setText("");
		output2.setText("");
		
		for(int i = 0; i<phoneBookSize; i++)
		{
			output.append(names.get(i)+"\n");
			output2.append(numbers.get(i)+"\n");
		}

		topPanel.add(mainPanel);
	
	}
	
	private void search(String s)
	{
		ArrayList<Integer> index = new ArrayList();
		
		for(int i = 0; i<phoneBookSize; i++)
		{
			if(names.get(i).contains(s) || numbers.get(i).contains(s)) index.add(i);
		}
		
		
		
		
		
		output.setText("");
		output2.setText("");
		for(int i = 0; i<index.size(); i++)
		{
			output.append(names.get(index.get(i))+"\n");
			output2.append(numbers.get(index.get(i))+"\n");
		}
	}
	
	
	
}

				