/* 
 Author: Keith Dyer
 Date: March 27, 2013
 Problem Number: 11.9
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------



class Problem12_2  extends PaintPanel
{
	int numScores = 0;
	
	TestScores t = new TestScores();
	
	PaintPanel thisPanel;
	
	public Problem12_2()
	{
		thisPanel = this;
		
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "2. TestScores Class Custom Exception"
+"\n\nWrite an exception class named InvalidTestScore. Modify the TestScores class you wrote in Programming Challenge 1 so that it throws an InvalidTestScore exception if any of the "
+"\ntest scores in the array are invalid.";
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
		
		topPanel.add(t.getPanel(), BorderLayout.CENTER);
		
		
		return topPanel;
	}
	
	public void addTest()
	{
		numScores = numScores+1;
	}
	
	public JPanel buildButtonsPanel()
	{
		JPanel topPanel = new JPanel();
		
		JButton input = new JButton("Input New Test Scores");

		
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
		
		topPanel.add(KDUtil.wrap(input));
		
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
		
		final JPanel center = new JPanel(new GridLayout(numScores,2));
		
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
				
				for(int i = 0; i<numScores; i++)
				{
					temp = doubleInputValidation(test[i].getText());
					if(temp.isEmpty() == false)
						scores.add(Double.parseDouble(temp));
				}
				
				t = new TestScores(scores);
				
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
				center.setLayout(new GridLayout(numScores,2));
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


	
	private class TestScores
	{
		ArrayList<Double> tests = new ArrayList();
		
		public TestScores(ArrayList<Double> tests)
		{
			this.tests = tests;
		}
		
		public TestScores()
		{
			
		}
		
		public JPanel getPanel()
		{
			DecimalFormat output = new DecimalFormat("0.00");
			
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBorder(BorderFactory.createTitledBorder("Grades"));
	
			
			try
			{
				if(tests.size()>0)
					try{
					topPanel.add(KDUtil.wrap(new JLabel("Average : "+ output.format(getAverage()))), BorderLayout.CENTER);
					}
					catch(InvalidTestScore e)
					{
						topPanel.add(KDUtil.wrap(new JLabel(e.getMessage())), BorderLayout.CENTER);
					}
				else
					topPanel.add(KDUtil.wrap(new JLabel("Average : no data")), BorderLayout.CENTER);
			}
			catch(InvalidTestScore e)
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
					throw new InvalidTestScore("Element at "+(i+1)+" is an invalid test score");	
			}
			testTotal = testTotal / tests.size();
			
			return testTotal;
		}
	}

	private class InvalidTestScore extends IllegalArgumentException
	{
		public InvalidTestScore(String s)
		{
			super(s);
		}
	}
	
}
