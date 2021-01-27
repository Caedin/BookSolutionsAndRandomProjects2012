/* 
 Author: Keith Dyer
 Date: February 4, 2013
 Assignment 3
 Problem Number: 8.5
 Create drivers test
 Correct answers: BDAACABACDBCDADCCBDA
 15/20 required to pass
 boolean passed()
 int totalCorrect()		
 int totalIncorrect()
 int[] questionsMissed()
			
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------



// ---------- Simple Window Class -------------------------

class Problem8_5 extends PaintPanel
{	
	JPanel topPanel = new JPanel();
	JButton grade = new JButton();
	JRadioButton[][] Radio = new JRadioButton[20][4];
	JLabel[] Question = new JLabel[20];
	JPanel[] Panels = new JPanel[20];
	ButtonGroup[] RadioGroups = new ButtonGroup[20];
	JPanel wrapper = new JPanel();
	
	char[] Correct = {'B','D','A','A','C','A','B','A','C','D','B','C','D','A','D','C','C','B','D','A'};
	char[] Students = new char[20];
	
	public Problem8_5()
	{
		
		topPanel.setLayout(new BorderLayout());
		
		buildPanel();
	}
	
	@Override
	public String getDescription()
	{
		return "5. Drivers License Exam"
+"\nThe local Drivers License Office has asked you to write a program that grades the written portion of the drivers license exam. The exam has 20 multiple choice questions. Here are the correct answers:"
+"\n\n1. B"
+"\n2. D"
+"\n3. A"
+"\n4. A"
+"\n5. C"
+"\n6. A"
+"\n7. B"
+"\n8. A"
+"\n9. C"
+"\n10. D"
+"\n11. B"
+"\n12. C"
+"\n13. D"
+"\n14. A"
+"\n15. D"
+"\n16. C"
+"\n17. C"
+"\n18. B"
+"\n19. D"
+"\n20. A"
+"\n\nA student must correctly answer 15 of the 20 questions to pass the exam."
+"\nWrite a class named DriverExam that holds the correct answers to the exam in an array field. The class should also have an array field that holds the students answers. The class should have the following methods:"
+"\n\n passed. Returns true if the student passed the exam, or false if the student failed"
+"\n totalCorrect. Returns the total number of correctly answered questions"
+"\n totalIncorrect. Returns the total number of incorrectly answered questions"
+"\n questionsMissed. An int array containing the question numbers of the questions that the student missed"
+"\n\nDemonstrate the class in a complete program that asks the user to enter a students answers, and then displays the results returned from the DriverExam classs methods."
+"\nInput Validation: Only accept the letters A, B, C, or D as answers.";
	}

	private void buildPanel()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(20,1));
		
		// ----- Create Header ---- ////
		JLabel header = new JLabel("Welcome to the Driving Test!");
		wrapper = new JPanel();
		wrapper.add(header);
		topPanel.add(wrapper, BorderLayout.NORTH);
		
				
		// ------ Create Questions ----- //
		for(int i = 0; i<20; i++)
		{
			RadioGroups[i] = new ButtonGroup();
			
			Question[i] = new JLabel("Question #"+(i+1));	
			centerPanel.add(Question[i]);
					
			Radio[i][0] = new JRadioButton("A");
			Radio[i][1] = new JRadioButton("B");
			Radio[i][2] = new JRadioButton("C");
			Radio[i][3] = new JRadioButton("D"); 
			
			RadioGroups[i].add(Radio[i][0]);
			RadioGroups[i].add(Radio[i][1]);
			RadioGroups[i].add(Radio[i][2]);
			RadioGroups[i].add(Radio[i][3]);
			
			Panels[i] = new JPanel();
			Panels[i].setLayout(new GridLayout(1,0));
			Panels[i].add(Radio[i][0]);
			Panels[i].add(Radio[i][1]);
			Panels[i].add(Radio[i][2]);
			Panels[i].add(Radio[i][3]);
			
			centerPanel.add(Panels[i]);
			}
		topPanel.add(centerPanel, BorderLayout.CENTER);
		
		// ------ Create Grade Button ----- //
		
		grade = new JButton("Grade!");
		grade.addActionListener(new MyButtonListener());
		wrapper = new JPanel();
		wrapper.add(grade);
		topPanel.add(wrapper, BorderLayout.SOUTH);
		
		add(topPanel);
	}
	
		
	private class MyButtonListener implements ActionListener
	{	
		public void actionPerformed(ActionEvent e)   //Responds to button
		{
			
			for(int i = 0; i<20; i++)
			{
				if(Radio[i][0].isSelected()) Students[i] = 'A';
				if(Radio[i][1].isSelected()) Students[i] = 'B';
				if(Radio[i][2].isSelected()) Students[i] = 'C';
				if(Radio[i][3].isSelected()) Students[i] = 'D';
			}
			
			passed();
			totalCorrect();
			totalIncorrect();
			questionsMissed();
			
			GradedExam gradedWindow = new GradedExam();
			gradedWindow.grade(passed(),totalCorrect(),totalIncorrect(), questionsMissed());
		
		}
	}
	
	private boolean passed()
	{
		boolean pass = false;
		
		int correct = 0;
		for(int i = 0; i < 20; i++)
		{
			if(Correct[i]==Students[i]) correct++;
		}
		
		if(correct>=15) pass = true;
		
		return pass;
		
	}
	
	private int totalCorrect()
	{
		int totalCorrect = 0;
		

		for(int i = 0; i < 20; i++)
		{
			if(Correct[i]==Students[i]) totalCorrect++;
		}
		
		return totalCorrect;
	}
	
	private int totalIncorrect()
	{
		int totalIncorrect = 0;
		
		
		for(int i = 0; i < 20; i++)
		{
			if(Correct[i]!=Students[i]) totalIncorrect++;
		}
		


		return totalIncorrect;
	}
	
	private int[] questionsMissed()
	{
		int[] questionsMissed = new int[20];
		
		for(int i = 0; i < 20; i++)
		{
			if(Correct[i]!=Students[i]) 
			{
				questionsMissed[i]=(i+1);
			}
		}


		return questionsMissed;
	}
	
}


class GradedExam extends JFrame
{	
	JPanel panel = new JPanel();
	JPanel wrapper = new JPanel();
	
	public GradedExam()
	{
		int WINDOW_WIDTH = 500;
		int WINDOW_HEIGHT = 200;
		panel.setLayout(new GridLayout(0,1));
		
		setTitle("Driving Grade");
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
				
				
		buildPanel();
		
		add(panel);
		
		setVisible(true);
		setResizable(false);
	}

	private void buildPanel()
	{
		JLabel header = new JLabel("Grades!");
		wrapper = new JPanel();
		wrapper.add(header);
		panel.add(wrapper);
		
		
	}
	
	
	public void grade(boolean passed, int correct, int incorrect, int[] questionsMissed)
	{
			JLabel correctField = new JLabel("Number of Correct Answers: "+correct);
			wrapper = new JPanel();
			wrapper.add(correctField);
			panel.add(wrapper);
			
			JLabel incorrectField = new JLabel("Number of Incorrect Answers: "+incorrect);
			wrapper = new JPanel();
			wrapper.add(incorrectField);
			panel.add(wrapper);
			
			
			JLabel passedField = new JLabel();
			if(passed) {passedField = new JLabel("Final Score: Passing!");}  else {passedField = new JLabel("Final Score: Failing.");}
			wrapper = new JPanel();
			wrapper.add(passedField);
			panel.add(wrapper);
			
			String missed = "Questions Missed: {";
			int counter = 0;
			
			for(int i = 0; i < 20; i++) 
			{
				if(questionsMissed[i]!=0 && counter==0) 
				{
					missed = missed+(i+1);
					counter++;
				}
				else
				if(questionsMissed[i]!=0) 
				{
					missed = missed+ ", " + (i+1);
					counter++;
				}
	
			}
			missed = missed + "}";

			
			JLabel QuestionsMissed = new JLabel(missed);
			
			wrapper = new JPanel();
			wrapper.add(QuestionsMissed);
			panel.add(wrapper);
	}
	

	
}