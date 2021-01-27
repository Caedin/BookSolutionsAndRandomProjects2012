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



class Problem10_5  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("New Password:");
	JLabel outputLabel = new JLabel("");
	JTextField input = new JTextField(40);
	
	
	public Problem10_5()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "5. Password Verifier"
+"\nImagine you are developing a software package for Amazon.com that requires users to enter their own passwords. Your software requires that users passwords meet the following criteria:"
+"\n The password should be at least six characters long."
+"\n The password should contain at least one uppercase and at least one lowercase letter."
+"\n The password should have at least one digit.";
	}
	
	
	public void buildPanel()
	{
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						outputLabel.setText(passwordVerify(input.getText()));								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Password Verification");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		mainPanel.add(inputPanel, BorderLayout.CENTER);

		outputPanel.add(outputLabel);
		mainPanel.add(outputPanel, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
	
	}
	
	private String passwordVerify(String input)
	{
		
		boolean hasCap = false;
		boolean hasLow = false;
		boolean hasDig = false;
		
		for(int i = 0; i<input.length(); i++)
		{
			int ascii = (int)input.charAt(i);
			
			if(ascii>=65 && ascii<=90) hasCap = true;
			if(ascii>=97 && ascii<=122) hasLow = true;
			if(ascii>=48 && ascii<=57) hasDig = true;
			
		}
		
		if(hasCap&&hasLow&&hasDig&& input.length()>=6) return "Password Verified!";
		else
		{
			if(!hasCap) return "New Password must contain at least one capital letter.";
			if(!hasLow) return "New Password must contain at least one lower case letter.";
			if(!hasDig) return "New Password must contain at least one number";
			if(input.length()<6) return "New Password must be at least 6 characters long";
		}
		return "Error";
		

	}
	
	
}
				