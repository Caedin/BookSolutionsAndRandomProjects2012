/* 
 Author: Keith Dyer
 Date: March 27, 2013
 Problem Number: 11.9
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.Serializable;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------



class Problem12_9  extends PaintPanel
{
	int numScores = 0;
	
	TestScoresSerial t = new TestScoresSerial();
	
	final ArrayList<TestScoresSerial> tests = new ArrayList();
	
	PaintPanel thisPanel;
	
	public Problem12_9()
	{
		thisPanel = this;
		
		add(KDUtil.fileHandleTest(buildPanel()));
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "9. TestScores Modification for Serialization"
+"\n\nModify the TestScores class that you created for Programming Challenge 1 to be serializable. Write a program that creates an array of at least five TestScore objects and serializes them. "
+"\nWrite another program that deserializes the objects from the file.";
	}	
	
	public JPanel buildPanel()
	{	
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(KDUtil.wrap(buildOutputPanel()), BorderLayout.CENTER);
		topPanel.add(KDUtil.wrap(buildButtonsPanel()), BorderLayout.NORTH);
		return topPanel;
	}
	

	public JPanel buildOutputPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel temp = new JPanel(new GridLayout(tests.size()/3+1,3,5,5));
		
		for(int i = 0; i<tests.size(); i++)
		{
			temp.add(tests.get(i).getPanel());
		}
		
		topPanel.add(temp, BorderLayout.CENTER);
		
		
		return topPanel;
	}
	
	public void addTest()
	{
		numScores = numScores+1;
	}
	
	public JPanel buildButtonsPanel()
	{
		JPanel topPanel = new JPanel();
		
		JButton input = new JButton("Add New Test Score");
		JButton save = new JButton("Save Grades");
		JButton load = new JButton("Load Grades");

		
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				createAccount(1);
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
		save.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				save(tests);
				tests.clear();
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
		load.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				load(tests);
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
		topPanel.add(KDUtil.wrap(input));
		topPanel.add(KDUtil.wrap(save));
		topPanel.add(KDUtil.wrap(load));
		
		return topPanel;

	}
	
	public void createAccount(int x)
	{
		numScores = x;
		
		final JFrame input = new JFrame();
		input.setSize(400,400);
		
		
		final JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.add(KDUtil.wrap(new JLabel("Add New Test Scores")));
		
		final JPanel center = new JPanel(new GridLayout(numScores+2,2));
		
		final JTextField name = new JTextField(10);
		center.add(KDUtil.wrap(new JLabel("Student Name:")));
		center.add(KDUtil.wrap(name));
		
		final JTextField[] test = new JTextField[200];
		test[0] = new JTextField(10);


		for(int i = 0; i<numScores; i++)
		{
			test[i] = new JTextField(10);
			center.add(KDUtil.wrap(new JLabel("Test Score #"+(i+1)+": ")));
			center.add(KDUtil.wrap(test[i]));
		}


		
		JPanel complete = new JPanel();
		
		JButton enter = new JButton("Done");
		complete.add(KDUtil.wrap(enter));
		
		JButton newScore = new JButton("New Test Score");
		complete.add(KDUtil.wrap(newScore));
		
		enter.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ArrayList<Double> scores = new ArrayList();
				
				String temp = new String();
				String nameField = "";
				
				if(name.getText().isEmpty()==false)
					nameField = new String(name.getText());
				
				for(int i = 0; i<numScores; i++)
				{
					temp = doubleInputValidation(test[i].getText());
					if(temp.isEmpty() == false && temp.equals("Input Error") == false)
						scores.add(Double.parseDouble(temp));
				}
				
				t = new TestScoresSerial(scores, nameField);
				tests.add(t);
				
				input.setVisible(false);
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
	newScore.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//createAccount(numScores+1);
				numScores++;
				center.removeAll();
				center.setLayout(new GridLayout(numScores+2,2));
				center.add(KDUtil.wrap(new JLabel("Student Name:")));
				center.add(KDUtil.wrap(name));
				for(int i = 0; i<numScores; i++)
				{
					test[i] = new JTextField(10);
					center.add(KDUtil.wrap(new JLabel("Test Score #"+(i+1)+": ")));
					center.add(KDUtil.wrap(test[i]));
				}
				
				center.revalidate();
				topPanel.revalidate();
				
				
				//input.setVisible(false);
			}
		});
		
	topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
	topPanel.add(KDUtil.wrap(center), BorderLayout.CENTER);
	topPanel.add(KDUtil.wrap(complete), BorderLayout.SOUTH);
	
	JScrollPane scroll = new JScrollPane(topPanel);
	
	input.add(scroll);
	input.setVisible(true);
	}
	
	public static String doubleInputValidation(String input)
	{
		boolean inputValidation = true;
		char[] inputArray = input.toCharArray();
								
		String newInput = "";
								
		for(int i = 0; i<input.length(); i++)
		{
			switch (inputArray[i])
				{
					case '0': newInput = newInput+ inputArray[i]; break;
					case '1': newInput = newInput+ inputArray[i]; break;
					case '2': newInput = newInput+ inputArray[i]; break;
					case '3': newInput = newInput+ inputArray[i]; break;
					case '4': newInput = newInput+ inputArray[i]; break;
					case '5': newInput = newInput+ inputArray[i]; break;
					case '6': newInput = newInput+ inputArray[i]; break;
					case '7': newInput = newInput+ inputArray[i]; break;
					case '8': newInput = newInput+ inputArray[i]; break;
					case '9': newInput = newInput+ inputArray[i]; break;
					case '.': newInput = newInput+ inputArray[i]; break;
					case '-': if(i==0) {newInput = newInput+ inputArray[i]; break;} else {inputValidation = false; break;}
					default: inputValidation = false; break;
				}
			
		}
		if(inputValidation) return newInput; else return "Input Error";
	}

	private void save(ArrayList<TestScoresSerial> t)
	{
		try
		{
		try
		{
			String temp = JOptionPane.showInputDialog("File Name:");
			FileOutputStream outStream = new FileOutputStream(temp+".dat");
			ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
	
			for(int i = 0; i<t.size(); i++)
			{
				objectOutputFile.writeObject(t.get(i));
			}
			
			objectOutputFile.close();

		}catch(FileNotFoundException e){}}catch(IOException k){}
		
	}

	private void load(ArrayList<TestScoresSerial> t)
	{
		t.clear();
			
		
		try
		{
		try
		{
			String temp = JOptionPane.showInputDialog("File Name:");
			FileInputStream inStream = new FileInputStream(temp+".dat");
			ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
			
			boolean endOfFile = false;
			while(!endOfFile)
			{
				try
				{
					try{t.add((TestScoresSerial)objectInputFile.readObject());}catch(ClassNotFoundException notFound){}
				}
				catch(EOFException e)
				{
				endOfFile = true;
				}
			}

			
			objectInputFile.close();
			
		}catch(FileNotFoundException e){}}catch(IOException k){}
		
	}



	
}

class TestScoresSerial implements Serializable
{
	ArrayList<Double> tests = new ArrayList();
	String name = "";
	
	public TestScoresSerial(ArrayList<Double> tests, String name)
	{
		this.tests = tests;
		this.name = name;
	}
	
	public TestScoresSerial()
	{
		
	}
	
	public JPanel getPanel()
	{
		DecimalFormat output = new DecimalFormat("0.00");
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createTitledBorder(name));

		
		try
		{
			if(tests.size()>0)
				try{
				topPanel.add(KDUtil.wrap(new JLabel("Average : "+ output.format(getAverage()))), BorderLayout.CENTER);
				}
				catch(IllegalArgumentException e)
				{
					topPanel.add(KDUtil.wrap(new JLabel(e.getMessage())), BorderLayout.CENTER);
				}
			else
				topPanel.add(KDUtil.wrap(new JLabel("Average : no data")), BorderLayout.CENTER);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
				
		return topPanel;
	}

	
	public double getAverage()
	{
		double testTotal = 0;
		
		for(int i = 0; i<tests.size(); i++)
		{
			if(tests.get(i)>=0 && tests.get(i)<=100)
				testTotal = testTotal + tests.get(i);
			else
				throw new IllegalArgumentException("Element at "+(i+1)+" is an invalid test score");	
		}
		testTotal = testTotal / tests.size();
		
		return testTotal;
	}
}