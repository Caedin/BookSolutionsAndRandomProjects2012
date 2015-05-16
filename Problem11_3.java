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
// -----------------------------------------------



class Problem11_3  extends PaintPanel
{
	ArrayList<Employee> employees = new ArrayList();
	PaintPanel thisPanel;
	
	public Problem11_3()
	{
		thisPanel = this;
				
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "3. TeamLeader Class\n"
		+"\nIn a particular factory, a team leader is an hourly paid production worker that leads a small team. In addition to hourly pay, team leaders earn a fixed monthly bonus. "
		+"\nTeam leaders are required to attend a minimum number of hours of training per year. Design a TeamLeader class that extends the ProductionWorker class you designed in "
		+"\nProgramming Challenge 1. The TeamLeader class should have fields for the monthly bonus amount, the required number of training hours, and the number of training hours "
		+"\nthat the team leader has attended. Write one or more constructors and the appropriate accessor and mutator methods for the class. Demonstrate the class by writing a program "
		+"\nthat uses a TeamLeader object.";
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
		
		JButton button = new JButton("Create Employee");
		topPanel.add(KDUtil.wrap(button), BorderLayout.WEST);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Employee x = new Employee();
				x.createEmployee();
				employees.add(x);			
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
								
			}
		});	
		
		button = new JButton("Create TeamLeader");
		topPanel.add(KDUtil.wrap(button), BorderLayout.EAST);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				TeamLeader x = new TeamLeader();
				x.createEmployee();
				employees.add(x);		
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();					
			}
		});
		
		return topPanel;
	}
	
	public JPanel buildOutputPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel temp = new JPanel(new GridLayout(0,3,5,5));
		for(int i = 0; i<employees.size(); i++)
		{
			temp.add(KDUtil.wrap(employees.get(i).getPanel()));
		}
		
		topPanel.add(KDUtil.wrap(temp), BorderLayout.CENTER);
		
		
		return topPanel;
	}

	
}

class TeamLeader extends ProductionWorker
{
	double monthlyBonus;
	int hoursComplete;
	int hoursRequired;
	
	public TeamLeader(double mBonus, int hoursReq, int hoursComp)
	{
		monthlyBonus=mBonus;
		hoursComplete=hoursComp;
		hoursRequired=hoursReq;
		super.Class = "TeamLeader";
	}
	
	public TeamLeader()
	{
		monthlyBonus=200;
		hoursComplete=0;
		hoursRequired=15;
		super.Class = "TeamLeader";
	}
	
	public void createEmployee()
	{
		super.createEmployee();

		String temp = JOptionPane.showInputDialog("What is your monthly bonus?");
		if(temp.isEmpty()==false) monthlyBonus = Double.parseDouble(KDUtil.moneyInputValidation(temp));
		else	monthlyBonus = 200;
		
		temp = JOptionPane.showInputDialog("How many training hours are required?");
		if(temp.isEmpty()==false) hoursRequired = Integer.parseInt(temp);
		else hoursRequired = 15;
		
		temp = JOptionPane.showInputDialog("How many training hours are complete?");
		if(temp.isEmpty()==false) hoursComplete = Integer.parseInt(temp);
		else hoursComplete = 0;
		
		super.addCenter(KDUtil.wrap(new JLabel("Monthly Bonus : "+money.format(monthlyBonus))));
		super.addEast(KDUtil.wrap(new JLabel("Training : "+"("+hoursComplete+"/"+hoursRequired+")"+" hrs")));
		
		
	}
	
}