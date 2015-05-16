/* 
 Author: Keith Dyer
 Date: February 14, 2013
 Assignment 4
 Problem Number: 12.6
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



class Problem12_7 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel(new GridLayout(2,1));
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("File Name (*.dat):");
	JTextArea outputLabel = new JTextArea(25,25);
	JTextArea fileList = new JTextArea(25,25);
	final JTextField input = new JTextField(25);
	
	
	public Problem12_7()
	{
		try
		{
		try{RandomAccessFile test = new RandomAccessFile("test.txt","rw");test.close();}catch(IOException e){}

		buildPanel();
		}
		catch(java.security.AccessControlException k)
		{
			topPanel.removeAll();
			topPanel.add(new JLabel("ERROR: Applets cannot read or write files"));
		}	
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "7. File Encryption Filter"
+"\n\nFile encryption is the science of writing the contents of a file in a secret code. Your encryption program should work like a filter, reading the contents of one file, "
+"\nmodifying the data into a code, and then writing the coded contents out to a second file. The second file will be a version of the first file, but written in a secret code."
+"\nAlthough there are complex encryption techniques, you should come up with a simple one of your own. For example, you could read the first file one character at a time, and add 10 to "
+"\nthe character code of each character before it is written to the second file.";
	}
	
	
	public void buildPanel()
	{
		/*input.addActionListener(new ActionListener() 
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
		});	*/
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Encryption Program");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		

		
		JPanel wrapper = new JPanel();
		wrapper.add(inputLabel);
		wrapper.add(input);
		wrapper.add(new JLabel("Encryption Key"));
		final JTextField inputKey = new JTextField(10);
		wrapper.add(inputKey);
	
		inputPanel.add(wrapper);
		
		JButton create = new JButton("Write New File");
		create.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				outputLabel.setText(" ");
				String temp = fileList.getText();
				String key = inputKey.getText();
				
				temp = Encryption.code(temp, key);
				outputLabel.append(temp);	

				try
				{
					RandomAccessFile output = new RandomAccessFile(input.getText(),"rw");
					output.writeUTF(temp);
				}
				catch(IOException e)
				{
					outputLabel.append(e.getMessage());
				}
	
			}
		});
		
				
		
		wrapper = new JPanel();
		wrapper.add(create);
		
		inputPanel.add(wrapper);
		
		
		
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		
	
		outputPanel.setLayout(new GridLayout(0,2));
		
		JPanel left = new JPanel(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		
		left.add(new JLabel("Input"), BorderLayout.NORTH);
		right.add(new JLabel("Encryption"), BorderLayout.NORTH);
		
		JScrollPane scrollFileList = new JScrollPane(fileList);
		left.add(scrollFileList, BorderLayout.CENTER);
		JScrollPane scroll = new JScrollPane(outputLabel);
		right.add(scroll, BorderLayout.CENTER);
		
		outputPanel.add(left);
		outputPanel.add(right);
		
		mainPanel.add(outputPanel, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
	
	}
	
	
}			

class Encryption
{
	public static String code(String s, String key)
	{
		String temp = "";
		int coder = 0;
		
		for(int i = 0; i<key.length(); i++)
		{
			coder = coder + (int)key.charAt(i);
		}
		
		for(int i = 0; i<s.length(); i++)
		{
			int value = (int)s.charAt(i);
			char k = (char)(value + coder);
			
			temp = temp + k;
		}
		
		return temp;
	}
}