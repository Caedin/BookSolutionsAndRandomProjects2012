


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------
public class Problem13_3 extends PaintPanel
{
	String[] halls = {"Allen - ($1500)", "Pike - ($1600)", "Farthing - ($1200)","University Suites - ($1800)"};
	String[] plans = {"Seven/Week - ($560)", "Fourteen/Week - ($1095)", "Unlimited - ($1500)"};
	final JComboBox hallBox = new JComboBox(halls);
	final JComboBox planBox = new JComboBox(plans);
	
	double cost, hallCosts, planCosts;
	JTextField output = new JTextField(10);
	
	public Problem13_3()
	{
		setLayout(new BorderLayout());
		add(KDUtil.wrap(topPanel()), BorderLayout.NORTH);
		add(KDUtil.wrap(centerPanel()), BorderLayout.CENTER);
		add(KDUtil.wrap(bottomPanel()), BorderLayout.SOUTH);
	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		
		hallBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculateCost();
			}
		});
		
		planBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculateCost();
			}
		});
		
		JPanel wrapper = new JPanel();
		wrapper.add(new JLabel("Choose your Residence Hall:"));
		wrapper.add(KDUtil.wrap(hallBox));
		topPanel.add(KDUtil.wrap(wrapper), BorderLayout.NORTH);
		
		wrapper = new JPanel();
		wrapper.add(new JLabel("Choose your Meal Plan:"));
		wrapper.add(KDUtil.wrap(planBox));
		topPanel.add(KDUtil.wrap(wrapper), BorderLayout.SOUTH);
		
		return topPanel;
	}
	
	public JPanel bottomPanel()
	{
		JPanel bottomPanel = new JPanel();
	
		
		return bottomPanel;	
	}
	
	public JPanel centerPanel()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.add(new JLabel("Costs:"));
		centerPanel.add(output);
		
		calculateCost();
		
		return centerPanel;
		
	}
	
	private void calculateCost()
	{
			double planCost = 0;
				
			int index;
			index = planBox.getSelectedIndex();
				
			switch(index)
			{
				case -1: planCost = 0; break;
				case 0: planCost = 560; break;
				case 1: planCost = 1095; break;
				case 2: planCost = 1500; break;
				default: break;
			}
				
			planCosts = planCost;
			
			double hallCost = 0;
				
			index = hallBox.getSelectedIndex();
				
			switch(index)
			{
				case -1: hallCost = 0; break;
				case 0: hallCost = 1500; break;
				case 1: hallCost = 1600; break;
				case 2: hallCost = 1200; break;
				case 3: hallCost = 1800; break;
				default: break;
			}
				
			hallCosts = hallCost;
			
	
			DecimalFormat money = new DecimalFormat("$#,###.00");
			cost = hallCosts + planCosts;
			output.setText(money.format(cost));
	}
	

	@Override
	public String getDescription()
	{
		return "3. Dorm and Meal Plan Calculator"
		
+"\n\nA university has the following dormitories:"
+"\nAllen Hall: $1,500 per semester"
+"\nPike Hall: $1,600 per semester"
+"\nFarthing Hall: $1,200 per semester"
+"\nUniversity Suites: $1,800 per semester"
+"\n\nThe university also offers the following meal plans:"
+"\n7 meals per week: $560 per semester"
+"\n14 meals per week: $1,095 per semester"
+"\nUnlimited meals: $1,500 per semester"
 
+"\n\nCreate an application with two combo boxes. One should hold the names of the dormitories, and the other should hold the meal plans." 
+"\nThe user should select a dormitory and a meal plan, and the application should show the total charges for the semester.";
	}
		

}