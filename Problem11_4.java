/* 
 Author: Keith Dyer
 Date: March 15, 2013
*/


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------



class Problem11_4  extends PaintPanel
{
	JPanel thisPanel = new JPanel();
	ArrayList<Essay> Essays = new ArrayList();
	
	public Problem11_4()
	{
		thisPanel = this;
		
		add(buildPanel());
	}
	
	@Override
	public String getDescription()
	{
		return "4. Essay Class"
+"\n\nDesign an Essay class that extends the GradedActivity class presented in this chapter. The Essay class should determine the grade "
+"\na student receives for an essay. The students essay score can be up to 100 and is determined in the following manner:"
+"\nGrammar: 30 points"
+"\nSpelling: 20 points"
+"\nCorrect length: 20 points"
+"\nContent: 30 points"
+"\n\nDemonstrate the class in a simple program.";
	}
	
	
	public JPanel buildPanel()
	{	
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(KDUtil.wrap(buildButtons()), BorderLayout.NORTH);
		topPanel.add(KDUtil.wrap(buildOutputPanel()), BorderLayout.CENTER);
		return topPanel;
	}
	
	public JPanel buildButtons()
	{
		JPanel topPanel = new JPanel(new BorderLayout());	
		
		JButton button = new JButton("Grade Essay");
		topPanel.add(KDUtil.wrap(button), BorderLayout.WEST);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Essay x = new Essay();	
				Grade(x);	
				Essays.add(x);	
				
				
								
			}
		});	
				
		return topPanel;

	}
	
	public JPanel buildOutputPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel temp = new JPanel(new GridLayout(0,3,5,5));
		for(int i = 0; i<Essays.size(); i++)
		{
			temp.add(KDUtil.wrap(Essays.get(i).getPanel()));
		}
		
		topPanel.add(KDUtil.wrap(temp), BorderLayout.CENTER);
		
		
		return topPanel;
	}
	
	public void Grade(final Essay x)
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
		
		center.add(KDUtil.wrap(new JLabel("Grammer(30): ")));
		center.add(KDUtil.wrap(gram));
		
		center.add(KDUtil.wrap(new JLabel("Spelling(20): ")));
		center.add(KDUtil.wrap(spell));
		
		center.add(KDUtil.wrap(new JLabel("Length(20): ")));
		center.add(KDUtil.wrap(len));
		
		center.add(KDUtil.wrap(new JLabel("Content(30): ")));
		center.add(KDUtil.wrap(cont));
		
		JPanel complete = new JPanel();
		
		JButton enter = new JButton("Enter");
		complete.add(KDUtil.wrap(enter));
		
		enter.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(gram.getText().isEmpty()==false)
					x.grammer = Double.parseDouble(KDUtil.doubleInputValidation(gram.getText()));
				
				if(spell.getText().isEmpty()==false)
					x.spelling = Double.parseDouble(KDUtil.doubleInputValidation(spell.getText()));
					
				if(len.getText().isEmpty()==false)
					x.length = Double.parseDouble(KDUtil.doubleInputValidation(len.getText()));
					
				if(cont.getText().isEmpty()==false)
					x.content = Double.parseDouble(KDUtil.doubleInputValidation(cont.getText()));
				
				x.setScore(x.grammer, x.spelling, x.length, x.content);	
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

	
		
	
}

class GradedActivity
{
	private double score;
	
	public void setScore(double s)
	{
		score = s;
	}
	
	public double getScore()
	{
		return score;
	}
	
	public char getGrade()
	{
			char letterGrade;
			
			if(score >= 90)
				letterGrade = 'A';
			else if (score >= 80)
				letterGrade = 'B';
			else if (score >= 70)
				letterGrade = 'C';
			else if (score >= 60)
				letterGrade = 'D';
			else 
				letterGrade = 'F';
			
			return letterGrade;
	}
	
	public JPanel getPanel()
	{
		
		JPanel topPanel = new JPanel(new BorderLayout());
		
		topPanel.setBorder(BorderFactory.createTitledBorder("Essay Score"));
		
		JPanel fields  = new JPanel(new GridLayout(2,2));
		
		fields.add(KDUtil.wrap(new JLabel("Grade: ")));
		fields.add(KDUtil.wrap(new JLabel(getGrade()+"")));
		fields.add(KDUtil.wrap(new JLabel("Score: ")));
		fields.add(KDUtil.wrap(new JLabel(getScore()+"")));
		
		topPanel.add(KDUtil.wrap(fields));
		
		
		return topPanel;
	}

}

class Essay extends GradedActivity
{
	double grammer = 0;
	double spelling = 0;
	double length = 0;
	double content = 0;

	
	public Essay(double g, double s, double l, double c)
	{
		grammer = g;
		spelling = s;
		length = l;
		content = c;
		
	}
	
	public Essay()
	{
	
	}

	
	
		
	public void setScore(double g, double s, double l, double c)
	{
		if(g+s+l+c > 100) 
			super.setScore(100);
		else
			super.setScore(g+s+l+c);
		
		
	}
	
	
}