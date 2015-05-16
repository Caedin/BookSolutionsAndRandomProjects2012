


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------



class Problem10_13 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input {###-AAA-AAAA} :");
	JTextArea outputLabel = new JTextArea(20,20);
	JTextField input = new JTextField(40);
	
	
	public Problem10_13()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "13. Alphabetic Telephone Number Translator"
+"\n\nMany companies use telephone numbers like 555-GET-FOOD so the number is easier for their customers to remember. On a standard telephone, the alphabetic letters are mapped to numbers in the following fashion:"
+"\n\nA, B, and C = 2"
+"\nD, E, and F = 3"
+"\nG, H, and I = 4"
+"\nJ, K, and L = 5"
+"\nM, N, and O = 6"
+"\nP, Q, R, and S = 7"
+"\nT, U, and V = 8"
+"\nW, X, Y, and Z = 9"
+"\n\nWrite an application that asks the user to enter a 10-character telephone number in the format XXX-XXX-XXXX. The application should display the telephone number with any alphabetic characters"
+"\n that appeared in the original translated to their numeric equivalent. For example, if the user enters 555-GET-FOOD the application should display 555-438-3663.";
	}
	
	
	public void buildPanel()
	{
		input.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
						if(input.getText().isEmpty()==false)
						{
							outputLabel.setText("");
							outputLabel.append(convertPhone(input.getText()));
							outputLabel.setEditable(false);
						}								
			}
		});	
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Alphabetic Telephone Number Translator");
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
	
	private String convertPhone(String input)
	{
		String output = "";
		
		for(int k = 0; k<input.length(); k++)
		{
			int x = (int)input.charAt(k);
			
			if(x>=48 && x<=57)
			{
				output = output+input.charAt(k);
			}else
			if(x==45) output = output+input.charAt(k);
			else
			{
				for(int i = 0; i<3; i++)
					if(x==65+i || x==97+i) output=output+"2";
				for(int i = 3; i<6; i++)
					if(x==65+i || x==97+i) output=output+"3";
				for(int i = 6; i<9; i++)
					if(x==65+i || x==97+i) output=output+"4";
				for(int i = 9; i<12; i++)
					if(x==65+i || x==97+i) output=output+"5";
				for(int i = 12; i<15; i++)
					if(x==65+i || x==97+i) output=output+"6";
				for(int i = 15; i<19; i++)
					if(x==65+i || x==97+i) output=output+"7";
				for(int i = 19; i<22; i++)
					if(x==65+i || x==97+i) output=output+"8";
				for(int i = 22; i<26; i++)
					if(x==65+i || x==97+i) output=output+"9";
			}

			
				
		}
			
		
		
		
		return "The phone number = "+output;
		
	}
	
	
}
				