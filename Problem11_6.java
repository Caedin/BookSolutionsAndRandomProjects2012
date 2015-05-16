/* 
 Author: Keith Dyer
 Date: March 20, 2013
*/


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------




class Problem11_6  extends PaintPanel
{
	ArrayList<CourseGrades2> courses = new ArrayList();
	JPanel thisPanel = new JPanel();
	
	public Problem11_6()
	{
		thisPanel = this;
		add(buildPanel());
	}
	
	@Override
	public String getDescription()
	{
		return "6. Analyzable Interface"
+"\n\nModify the CourseGrades class you created in Programming Challenge 5 so it implements the following interface:"
 

+"\n\npublic interface Analyzable"
+"\n {"
+"\n double getAverage();"
+"\n GradedActivity getHighest();"
+"\n GradedActivity getLowest();"
+"\n }"

+"\n\nThe getAverage method should return the average of the numeric scores"
+"\nstored in the grades array. The getHighest method should return a "
+"\nreference to the element of the grades array that has the highest numeric "
+"\nscore. The getLowest method should return a reference to the element of "
+"\nthe grades array that has the lowest numeric score. Demonstrate the new "
+"\nmethods in a complete program.";
	}
	
	
	public JPanel buildPanel()
	{	
		JPanel topPanel = new JPanel(new BorderLayout());
		// adds button
		topPanel.add(KDUtil.wrap(buildButtons()), BorderLayout.NORTH);
		topPanel.add(KDUtil.wrap(buildOutputPanel()), BorderLayout.CENTER);
		return topPanel;
	}
	
	public JPanel buildButtons()
	{
		JPanel topPanel = new JPanel();
		JButton inputGrades = new JButton("Input Course Grades");
		inputGrades.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				CourseGrades2 x = new CourseGrades2();
				generateGrades(x);
				courses.add(x);				
			}
		});	
		
		topPanel.add(inputGrades);
		return topPanel;
	}
	
	public void generateGrades(final CourseGrades2 x)
	{
		final JFrame input = new JFrame();
		input.setSize(400,400);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.add(KDUtil.wrap(new JLabel("Type in Scores")));
		
		JPanel center = new JPanel(new GridLayout(4,2));
		
		final JTextField gram = new JTextField(10);
		final JTextField spell = new JTextField(10);
		final JTextField len = new JTextField(10);
		final JTextField cont = new JTextField(10);
		
		center.add(KDUtil.wrap(new JLabel("Lab Score: ")));
		center.add(KDUtil.wrap(gram));
		
		center.add(KDUtil.wrap(new JLabel("Pass/Fail Score: ")));
		center.add(KDUtil.wrap(spell));
		
		center.add(KDUtil.wrap(new JLabel("Essay Score: ")));
		center.add(KDUtil.wrap(len));
		
		center.add(KDUtil.wrap(new JLabel("Final Exam Score: ")));
		center.add(KDUtil.wrap(cont));
		
		JPanel complete = new JPanel();
		
		JButton enter = new JButton("Enter");
		complete.add(KDUtil.wrap(enter));
		
		enter.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				double lab = 0;
				double passfail = 0;
				double essay = 0;
				double exam = 0;
				
				if(gram.getText().isEmpty()==false)
					lab = Double.parseDouble(KDUtil.doubleInputValidation(gram.getText()));
				
				if(spell.getText().isEmpty()==false)
					 passfail = Double.parseDouble(KDUtil.doubleInputValidation(spell.getText()));
					
				if(len.getText().isEmpty()==false)
					 essay = Double.parseDouble(KDUtil.doubleInputValidation(len.getText()));
					
				if(cont.getText().isEmpty()==false)
					 exam  = Double.parseDouble(KDUtil.doubleInputValidation(cont.getText()));

				GradedActivity ALab = new GradedActivity();
				ALab.setScore(lab);
				
				PassFailExam Apf = new PassFailExam();
				Apf.setScore(passfail);
				
				Essay ess = new Essay();
				ess.setScore(essay);
				
				FinalExam Afinal = new FinalExam();
				Afinal.setScore(exam);

				x.setLab(ALab);
				x.setPassFailExam(Apf);
				x.setEssay(ess);
				x.setFinalExam(Afinal);
				
				input.setVisible(false);
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
	topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
	topPanel.add(KDUtil.wrap(center), BorderLayout.CENTER);
	topPanel.add(KDUtil.wrap(complete), BorderLayout.SOUTH);
	
	input.add(topPanel);
	input.setVisible(true);
	
		
		
		
	}
	
	public JPanel buildOutputPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel temp = new JPanel(new GridLayout(0,3,5,5));
		for(int i = 0; i<courses.size(); i++)
		{
			temp.add(KDUtil.wrap(courses.get(i).getPanel()));
		}
		
		topPanel.add(KDUtil.wrap(temp), BorderLayout.CENTER);
		
		
		return topPanel;
	}



private class CourseGrades2 implements Analyzable
{
	GradedActivity[] grades = new GradedActivity[4];
	
	public CourseGrades2()
	{
		for(int i = 0; i<4; i++)
			grades[i] = new GradedActivity();
	}
	
	public void setLab(GradedActivity lab)
	{
		grades[0] = lab;
	}
	
	public void setPassFailExam(PassFailExam pfexam)
	{
		grades[1] = pfexam;
	}
	
	public void setFinalExam(FinalExam exam)
	{
		grades[3] = exam;
	}
	
	public void setEssay(Essay essay)
	{
		grades[2] = essay;
	}
	
	public String toString()
	{
		String s = new String();
		s = s + "Lab Grade: "+grades[0].getScore()+ "\n";
		
		if(grades[1].getScore()==1)
			s = s + "Pass Fail Grade: Pass\n";
		else if(grades[1].getScore()==0)
			s = s + "Pass Fail Grade: Fail\n";
			
		s = s + "Essay Grade: "+grades[2].getScore()+ "\n";
		s = s + "Final Exam Grade: "+grades[3].getScore()+ "\n\n";
		s = s + "Average Score: "+getAverage()+ "\n";
		s = s + "Highest Score: "+getHighest().getScore()+ "\n";
		s = s + "Lowest Score: "+getLowest().getScore()+ "\n";
		
		
		return s;
	}
	
	public double getAverage()
	{
		double avg = 0;
		for(int i = 0; i<4; i++)
		{
			if(i!=1)
				avg = avg + grades[i].getScore();
		}
		avg = avg / 3;
		
		return avg;
	}
	
	public GradedActivity getHighest()
	{
		GradedActivity max = new GradedActivity();
		
		for(int i = 0; i<4; i++)
		{
			if(max.getScore()<grades[i].getScore())
			{
				max = grades[i];
			}

		}

		
		return max;
	}

	public GradedActivity getLowest()
	{
		GradedActivity min = new GradedActivity();
		min.setScore(110);
		
	
		if(min.getScore()>grades[0].getScore())
		{
			min = grades[0];
		}

		if(min.getScore()>grades[2].getScore())
		{
			min = grades[2];
		}
		if(min.getScore()>grades[3].getScore())
		{
			min = grades[3];
		}
		
		
		return min;
	}

	
	
	
	public JPanel getPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel temp = new JPanel();
		temp.setBorder(BorderFactory.createTitledBorder("Course Grades"));
		JTextArea textArea = new JTextArea(toString());
		
		temp.add(textArea);
		
		topPanel.add(temp, BorderLayout.CENTER);
		
		
		return topPanel;
	}
	
	
}
}


interface Analyzable
{
	double getAverage();
	GradedActivity getHighest();
	GradedActivity getLowest();
}