/* 
 Author: Keith Dyer
 Date: February 4, 2013
 Assignment 3
 Problem Number: 8.7
	Create a program that holds a students name, four test scores, the test score average, and provides methods for returning the name, average test score, and a letter grade.
			
			
	Idea: Create a GradeBook class, this class will allow adding of new students, and allow entering of test scores, clicking students will give options to add new test scores,
	and give output for test score average, and class average.
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------




// --------- GradeBook Class -------------------------

class Problem8_7 extends PaintPanel
{	
	JPanel topPanel = new JPanel();
	JPanel panel = new JPanel();
	Student[] students1 = new Student[255];
	JPanel[] StudentPanel = new JPanel[255];	
	int activeStudents = 0;
	
	int numStudents = 0;
	
	public Problem8_7()
	{	
		setLayout(new GridLayout(1,0));
		buildPanel();
		add(topPanel);
	}

	private void buildPanel()
	{
		topPanel.setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(3,0));
		
		// ----- Create Header ---- ////
		JPanel head = new JPanel();
		JLabel header = new JLabel("Welcome to the Grade Book!");
		head.add(header);
		
		
		// ------ Add Students to Frame ---- //
		
		updateDisplay();
		
		// ---- Create new student button ----- //
		JPanel Buttons = new JPanel();
		Buttons.setLayout(new GridLayout(2,0));
		Buttons.add(head);
		
		JButton newStudent = new JButton("Create a New Student!");
		newStudent.addActionListener(new MyButtonListener());
		
		JPanel wrapper = new JPanel();
		wrapper.add(newStudent);
		
		Buttons.add(wrapper);
		
		// ----- Add to the viewing frame ----- //
		wrapper = new JPanel();
		wrapper.add(panel);
		
		topPanel.add(wrapper, BorderLayout.CENTER);
		topPanel.add(Buttons, BorderLayout.NORTH);
	}
	
	private void updateDisplay()
	{
		panel.setLayout(new GridLayout(2+numStudents,0));
		
		for(int i = 0; i < numStudents; i++)
		{
			panel.remove(StudentPanel[i]);
		}
		
		
		for(int i = 0; i < numStudents; i++)
		{
			StudentPanel[i] = new JPanel();
			StudentPanel[i] = students1[i].getFrame();
			
			panel.add(StudentPanel[i]);
		}
		
		setVisible(false);

		panel.revalidate();
		panel.repaint();
		setVisible(true);
	}
	
	
	
	private void removeStudent(Student s)
	{
		int start = 0;
		
		for(int k = 0; k < numStudents; k++)
		{
			if(students1[k].getName().equals(s.getName())) start = k;
			
		}
		
		for(int i = start; i<numStudents; i++)
		{
			students1[i] = students1[i+1];
		}
		
		for(int i = 0; i < numStudents; i++)
		{
			panel.remove(StudentPanel[i]);
		}
		
		numStudents--;
		
		panel.setLayout(new GridLayout(2+numStudents,0));

		for(int i = 0; i < numStudents; i++)
		{
			
			StudentPanel[i] = new JPanel();
			StudentPanel[i] = students1[i].getFrame();
			panel.add(StudentPanel[i]);
		}
		
		
		panel.revalidate();
		panel.repaint();
	}
	
	private void exportFile()
	{
		
	}
	
	private void importFile()
	{
	
	}
	
	
	
	private class MyButtonListener implements ActionListener
	{	
		public void actionPerformed(ActionEvent e)   //Responds to button
		{
				Student x = new Student();
				x.displayWindow();		
		}
	}
	
	@Override 
	public String getDescription()
	{
		return "7. Grade Book"
+"\nA teacher has five students who have taken four tests. The teacher uses the following grading scale to assign a letter grade to a student, based on the average of his or her four test scores:"
+"\nWrite a class that uses a String array or an ArrayList object to hold the five students names, an array of five characters to hold the five students letter grades, and five arrays of four "
+"\ndoubles each to hold each students set of test scores. The class should have methods that return a specific students name, average test score, and a letter grade based on the average."
+"\nDemonstrate the class in a program that allows the user to enter each students name and his or her four test scores. It should then display each students average test score and letter grade."
+"\nInput validation: Do not accept test scores less than zero or greater than 100.";
	}
	
	
	
	
			
	


		
		
		
private class Student
{
	private String name;
	private char grade;
	private boolean windowActive = false;
	private double[] scores = new double[255];
	private int numTestsStudent = 0;
	NewStudentWindow window;	
	
	
	public Student()
	{
		name = null;
		grade = 'F';
		
		for(int i = 0; i < 255; i++) scores[i] = -1;
	}
	
	public Student(Student s)
	{
		window = new NewStudentWindow(s);
		this.name = s.getName();
		this.grade = s.getGrade();
		this.scores = s.getScores();
	}
	
	public Student(String name, double[] scores)
	{
		this.name=name;
		this.scores = scores;
		grade = getGrade();
	}

	public double[] getScores() {return scores;}
	
	public String getName() {return name;}
	public int getTests() { average(); return numTestsStudent;}
	public void setName(String s) {name=s;}
	
	public char getGrade() 
	{
		if(average()<60) grade = 'F';
		if(average()>=60 && average()<70) grade = 'D';
		if(average()>=70 && average()<80) grade = 'C';
		if(average()>=80 && average()<90) grade = 'B';
		if(average()>=90 && average()<100) grade = 'A';
		
		return grade;
	}
	public double average()
	{
		int count = 0;
		numTestsStudent = 0;
		double sum = 0;
		
		while(count < scores.length)
		{
			if(scores[count]!=-1) 
			{
				sum=sum+scores[count];
				numTestsStudent++;
			}
			count++;
		}
		sum=sum/numTestsStudent;
		return sum;
	}
	

	
	public JPanel getFrame()
	{
			JPanel StudentPanel = new JPanel();
			StudentPanel.setPreferredSize(new Dimension(380,75));
			StudentPanel.setBorder(BorderFactory.createTitledBorder(name));
			StudentPanel.setLayout(new BorderLayout());
				
			JLabel average = new JLabel("Average : "+(int)average());
			StudentPanel.add(average, BorderLayout.WEST);
				
			JLabel grade = new JLabel("Grade: "+getGrade());
			JPanel gradePanel = new JPanel();
			gradePanel.add(grade);
			
			StudentPanel.add(gradePanel, BorderLayout.CENTER);
			
			JPanel buttons = new JPanel();
			
			JButton removeButton = new JButton("Delete");
			removeButton.addActionListener(new ActionListener() 
			{

	         public void actionPerformed(ActionEvent arg0) 
				{
					removeStudent(new Student(name, scores));
				}
         });
			
			buttons.add(removeButton);
			
			JButton editButton = new JButton("Edit");
			
			editButton.addActionListener(new ActionListener() 
			{
			
	         public void actionPerformed(ActionEvent arg0) 
				{
            	displayWindow();
					removeStudent(new Student(name, scores));
				}
         });
			
			
			buttons.add(editButton);
			
			StudentPanel.add(buttons, BorderLayout.EAST);
			
			JPanel wrapper = new JPanel();
			wrapper.add(StudentPanel);
			
			return wrapper;			
	}
	
	public void displayWindow()
	{
		window = new NewStudentWindow(this);
		window.display();
	}

	private class NewStudentWindow extends JFrame
	{	
			JPanel topPanel = new JPanel();
			JPanel nameField = new JPanel();
			JTextField[] tests = new JTextField[255];
			JLabel[] testNumbers = new JLabel[255];
			JTextField name = new JTextField();
			
			double[] scores = new double[255];
			
			
			int numTests = 1;
			
			public NewStudentWindow(Student k)
			{
				int WINDOW_WIDTH = 100;
				int WINDOW_HEIGHT = 100;
				
				setTitle("New Student Window");
				setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		
						
						
				buildPanel(k);
				setVisible(false);
				setResizable(false);
			}
			

			
			public void display()
			{
				pack();
				panel.revalidate();
				panel.repaint();
				setVisible(true);
			}
			
		
			private void buildPanel(Student k)
			{
				numTests = 0;
				for(int i = 0; i < k.scores.length; i++)
				{
					if(k.scores[i]!=-1) numTests++;
				}
				if(numTests == 0) numTests = 1;
				
				topPanel.setLayout(new BorderLayout());
				
				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());
						
				// ----- Create Header ---- ////
				JPanel headFrame = new JPanel();
				JLabel header = new JLabel("Create a New Student!");
				headFrame.add(header);
				topPanel.add(headFrame, BorderLayout.NORTH);
				
				// ------ Setup panel ------ ///
				nameField.setLayout(new GridLayout((numTests)+2,1,5,5));
				
				
				// ------- Add a name field --------- //
				name = new JTextField();
				name.setText(k.name);
				
				JLabel inputName = new JLabel("What is your students name?");
				
				nameField.add(inputName);
				nameField.add(name);
				
				
				for(int i = 0; i < numTests; i++)
				{
					tests[i] = new JTextField();
					if(k.scores[i]<0) tests[i].setText("0"); else
					tests[i].setText(""+k.scores[i]);
					
					testNumbers[i]= new JLabel("Test # "+(i+1));
					
					nameField.add(testNumbers[i]);
					nameField.add(tests[i]);
				}
				
				
				panel.add(nameField, BorderLayout.CENTER);
				
				
				// ---- Create a test score button ----- //
				JButton newTestScore = new JButton("Add a Test Score!");
				newTestScore.addActionListener(new TestScoreButton());
				panel.add(newTestScore, BorderLayout.SOUTH);
				
				// ----- Create Finish Button ------ //
				
				JButton finish = new JButton("Finish!");
				finish.addActionListener(new FinishButton());
				
				// ----- Add to the viewing frame ----- //
				
				topPanel.add(panel, BorderLayout.CENTER);
				topPanel.add(finish,BorderLayout.SOUTH);
				add(topPanel);
				pack();
			}
			
				
			private class TestScoreButton implements ActionListener
			{	
				public void actionPerformed(ActionEvent e)   //Responds to button
				{
						addTestField();
						numTests++;
				}
			}
			
			private void addTestField()
			{
				nameField.setLayout(new GridLayout((numTests)+3,0,5,5));
				
				tests[numTests] = new JTextField();
				testNumbers[numTests]= new JLabel("Test # "+(numTests+1));
					
				nameField.add(testNumbers[numTests]);
				nameField.add(tests[numTests]);
				
				pack();
				panel.revalidate();
				panel.repaint();
			}
			
			private class FinishButton implements ActionListener
			{	
				public void actionPerformed(ActionEvent e)   //Responds to button
				{
						ErrorFrame error = new ErrorFrame();				// Error frame if needed.
						
						String studentName = null;
						if(!(name.getText().isEmpty())) studentName = name.getText(); else studentName = "Default Student";
						
						double[] scores2 = new double[255];
						
						for(int i = 0; i<255; i++)
						{
							scores2[i] = -1;
						}
						
						for(int i = 0; i<numTests; i++)
						{
							String s = tests[i].getText();
							boolean notNumber = false;
							
							for(int z = 0; z<s.length(); z++) 
							{
								switch(s.charAt(z))
								{
									case '0' : break;
									case '1' : break;
									case '2' : break;
									case '3' : break;
									case '4' : break;
									case '5' : break;
									case '6' : break;
									case '7' : break;
									case '8' : break;
									case '9' : break;
									case '.' : break;
									
									default: notNumber = true; break;
								}
							}
							
							if(notNumber) 
							{
								s = "";
								error.addError(i);
							}
							
							if(s.isEmpty()!=true) 
							{
								scores2[i] = Double.parseDouble(s);
								if(scores2[i]<0) scores2[i]=0;
								if(scores2[i]>100) scores2[i]=100;
							}
							
						}
						
						// need to add only if student is NEW, not if student is EDIT
						
						
						students1[numStudents] = new Student(studentName,scores2);
						
						StudentPanel[numStudents] = students1[numStudents].getFrame();
						panel.add(StudentPanel[numStudents]);
						updateDisplay();
						numStudents++;

										
						window.setVisible(false);
				}
			}
			
					
		private class ErrorFrame extends JFrame
		{	
			JPanel topPanel = new JPanel();
			JPanel headFrame = new JPanel();
			int numErrors = 1;
			
			public ErrorFrame()
			{
				int WINDOW_WIDTH = 300;
				int WINDOW_HEIGHT = 300;
				
				setTitle("Error Frame");
				setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		
						
						
				buildPanel();
				setResizable(false);
			}
		
			private void buildPanel()
			{
				topPanel = new JPanel();
				topPanel.setLayout(new BorderLayout());
						
				// ----- Create Header ---- ////
				headFrame.setLayout(new GridLayout(255,0));
				
				topPanel.add(headFrame, BorderLayout.NORTH);
				
				add(topPanel);
				pack();
			}
			
			public void addError(int x)
			{
				numErrors++;
				
				headFrame.setLayout(new GridLayout(numErrors+1,0));
				JLabel header = new JLabel("Error Reading in Test # "+(x+1)+" = Non-Number Character");
				headFrame.add(header);
				
				pack();
				panel.revalidate();
				panel.repaint();
				setVisible(true);
			}
			
			
		}			

}
}
	
}

