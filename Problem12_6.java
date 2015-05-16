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



class Problem12_6 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel(new GridLayout(2,1));
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Name of File :");
	JTextArea outputLabel = new JTextArea(25,25);
	JTextArea fileList = new JTextArea(25,25);
	JTextField input = new JTextField(40);
	
	
	public Problem12_6()
	{
		buildPanel();
				
				
		add(KDUtil.fileHandleTest(topPanel));
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "6. FileArray Class"
+"\n\nDesign a class that has a static method named writeArray. The method should take two arguments: the name of a file and a reference to an int array. The file should be opened as a"
+"\nbinary file, the contents of the array should be written to the file, and then the file should be closed."
+"\nWrite a second method in the class named readArray. The method should take two arguments: the name of a file and a reference to an int array. The file should be opened, data should be "
+"\nread from the file and stored in the array, and then the file should be closed. Demonstrate both methods in a program.";
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
		
		JLabel header = new JLabel("File Name (*.dat)");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		
		JPanel wrapper = new JPanel();
		wrapper.add(inputLabel);
		wrapper.add(input);
		
		inputPanel.add(wrapper);
		
		JButton create = new JButton("Write New File");
		create.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int[] numbers = new int[(int)(Math.random()*10)];	
				for(int i = 0; i<numbers.length; i++)
				{
					numbers[i] = (int)(Math.random()*100);
					
				}
				
				if(input.getText().isEmpty()==false)
						{
							FileArray.writeArray(input.getText(), numbers);
						}
						
						
				
				populateList();				
			}
		});
		
		JButton read = new JButton("Read Existing File");
		read.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
					if(input.getText().isEmpty()==false)
						{
							outputLabel.setText(" ");
							ArrayList<String> strings = new ArrayList();
							String temp = FileArray.readArray(input.getText());
							String temp2 = "";
							
							for(int i = 0; i<temp.length(); i++)
							{
								temp2 = temp2 + temp.charAt(i);
								if(i%50==0)
								{
									temp2=temp2+"\n";
									strings.add(temp2);
									temp2 = "";
								}
							}
							strings.add(temp2);
							
							for(int i = 0; i<strings.size(); i++)
							{
								outputLabel.append(strings.get(i));
								
							}
							
						}								
			}
		});
		
		
		wrapper = new JPanel();
		wrapper.add(create);
		wrapper.add(read);
		
		inputPanel.add(wrapper);
		
		
		
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		
	
		outputPanel.setLayout(new GridLayout(0,2));
		
		JPanel left = new JPanel(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		
		left.add(new JLabel("List of available files"), BorderLayout.NORTH);
		right.add(new JLabel("Contents"), BorderLayout.NORTH);
		
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
							//if(files.endsWith(".dat"))
						   	fileList.append(files + "\n");
					   }
				   }
			}
			catch(java.security.AccessControlException k){}
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

	class FileArray
	{
		public static void writeArray(String s, int[] x)
		{
			try
			{
				RandomAccessFile output = new RandomAccessFile(s,"rw");
				for(int i = 0; i <x.length; i++)
					output.writeInt(x[i]);
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		public static String readArray(String s)
		{
			String output = "[ ";
			boolean endOfFile = false;
			
			try
			{
				RandomAccessFile input = new RandomAccessFile(s,"rw");
				input.seek(0);
				
				while(!endOfFile)
				{
					try
					{
						output = output + input.readInt() + " ";
					}
					catch(EOFException e)
					{
						endOfFile = true;
						output = output + "]";
					}
				}
			}
			catch(IOException e)
			{
				return e.getMessage();
			}
			
			return output;
		}
	}
				