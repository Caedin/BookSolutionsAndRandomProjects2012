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



class Problem11_8  extends PaintPanel
{
	ArrayList<Person> persons = new ArrayList();
	PaintPanel thisPanel;
	
	public Problem11_8()
	{
		thisPanel = this;
				
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "8. PreferredCustomer Class"
+"\n\nA retail store has a preferred customer plan where customers can earn discounts on all their purchases. The amount of a customers discount is determined by the amount of the customers cumulative "
+"\npurchases in the store as follows:"
 
+"\n When a preferred customer spends $500, he or she gets a 5 percent discount on all future purchases."
+"\n When a preferred customer spends $1,000, he or she gets a 6 percent discount on all future purchases."
+"\n When a preferred customer spends $1,500, he or she gets a 7 percent discount on all future purchases."
+"\n When a preferred customer spends $2,000 or more, he or she gets a 10 percent discount on all future purchases."
+"\n\nDesign a class named PreferredCustomer, which extends the Customer class you created in Programming Challenge 7. The PreferredCustomer class should have fields for the amount of the customers "
+"\npurchases and the customers discount level. Write one or more constructors and the appropriate mutator and accessor methods for the classs fields. Demonstrate the class in a simple program.";
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
		
		JButton button = new JButton("Create Person");
		topPanel.add(KDUtil.wrap(button), BorderLayout.WEST);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Person x = new Person();
				x.createPerson();
				persons.add(x);			
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
								
			}
		});	
		
		button = new JButton("Create PreferredCustomer");
		topPanel.add(KDUtil.wrap(button), BorderLayout.EAST);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				PreferredCustomer x = new PreferredCustomer();
				x.createPerson();
				persons.add(x);		
				
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
		JPanel temp = new JPanel();
		
		temp = new JPanel(new GridLayout(0,2,5,5));

		
		for(int i = 0; i<persons.size(); i++)
		{
			temp.add(KDUtil.wrap(persons.get(i).getPanel()));
		}
		
		topPanel.add(temp, BorderLayout.CENTER);
		
		
		return topPanel;
	}

	
}

class PreferredCustomer extends Customer
{
	double totalPurchase = 0;
	int discountLevel = 0;
	
	public PreferredCustomer(double tp)
	{
		totalPurchase  = tp;
		super.Class = "PreferredCustomer";
	}
	
	public PreferredCustomer()
	{
		
		super.Class = "PreferredCustomer";
	}
	
	public void createPerson()
	{
		super.createPerson();
		double holder = 0;
		
		String temp = JOptionPane.showInputDialog("What is your current purchase total?");
		if(temp.isEmpty()==false) holder = Double.parseDouble(KDUtil.doubleInputValidation(temp));
		
		totalPurchase = holder;
		
		if(totalPurchase>500 && totalPurchase <= 1000) discountLevel = 5;
		if(totalPurchase>1000 && totalPurchase <= 1500) discountLevel = 6;
		if(totalPurchase>1500 && totalPurchase <= 2000) discountLevel = 7;
		if(totalPurchase>2000) discountLevel = 10;
		

		super.addEast(KDUtil.wrap(new JLabel("Discount Percent = "+discountLevel+"%")));
	
		
		
		super.addCenter(KDUtil.wrap(new JLabel("Purchase Total : "+money.format(totalPurchase))));
		
	}
}