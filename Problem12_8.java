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



class Problem12_8 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel(new GridLayout(2,1));
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("File Name (*.dat):");
	JTextArea outputLabel = new JTextArea(25,25);
	JTextArea fileList = new JTextArea(25,25);
	final JTextField input = new JTextField(25);
	
	
	public Problem12_8()
	{
		buildPanel();
		
		add(KDUtil.fileHandleTest(topPanel));
		
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "8. File Decryption Filter"
+"\n\nWrite a program that decrypts the file produced by the program in Programming Challenge 7. The decryption program should read the contents of the coded file, "
+"\nrestore the data to its original state, and write it to another file.";
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
		
		JLabel header = new JLabel("Decryption Program");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		

		
		JPanel wrapper = new JPanel();
		wrapper.add(inputLabel);
		wrapper.add(input);
		wrapper.add(new JLabel("Decryption Key"));
		final JTextField inputKey = new JTextField(10);
		wrapper.add(inputKey);
	
		inputPanel.add(wrapper);
		
		JButton create = new JButton("Read New File");
		create.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				outputLabel.setText(" ");
				populateList();
				
				String temp = "";
				String key = inputKey.getText();

				try
				{

					RandomAccessFile output = new RandomAccessFile(input.getText(),"rw");
					boolean endOfFile = false;
					while(!endOfFile)
					{
						try
						{
							temp = temp + output.readUTF();
						}
						catch(EOFException e)
						{
							endOfFile = true;
						}
					}
					
				}
				catch(IOException e)
				{
					outputLabel.append(e.getMessage());
				}
				
				outputLabel.append(Decryption.decode(temp, key));
	
			}
		});
		
				
		
		wrapper = new JPanel();
		wrapper.add(create);
		
		inputPanel.add(wrapper);
		
		
		
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		
	
		outputPanel.setLayout(new GridLayout(0,2));
		
		JPanel left = new JPanel(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		
		left.add(new JLabel("File List"), BorderLayout.NORTH);
		right.add(new JLabel("Decryption"), BorderLayout.NORTH);
		
		JScrollPane scrollFileList = new JScrollPane(fileList);
		left.add(scrollFileList, BorderLayout.CENTER);
		JScrollPane scroll = new JScrollPane(outputLabel);
		right.add(scroll, BorderLayout.CENTER);
		
		outputPanel.add(left);
		outputPanel.add(right);
		
		mainPanel.add(outputPanel, BorderLayout.SOUTH);

		populateList();
		topPanel.add(mainPanel);
		
	}
	
	private void populateList()
	{
			try{
					String path = "."; 
					fileList.setText(" ");
 
 					String files;
  					File folder = new File(path);
  					File[] listOfFiles = folder.listFiles(); 
 
  					for (int i = 0; i < listOfFiles.length; i++) 
  					{
 
					   if (listOfFiles[i].isFile()) 
					   {
						   files = listOfFiles[i].getName();
							if(files.endsWith(".dat"))
						   	fileList.append(files + "\n");
					   }
				   }
			}
			catch(java.security.AccessControlException k){}

	}

	
	
}			

class Decryption
{
	public static String decode(String s, String key)
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
			char k = (char)(value - coder);
			
			temp = temp + k;
		}
		
		return temp;
	}
}