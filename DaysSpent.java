// Chris Stupka and Chrstian Reyes Project 
// Imports classes needed for DaysSpent
import java.awt.*;  
import javax.swing.*; 

// This class makes the panel for the input of days spent at the hospital
public class DaysSpent extends JPanel // 
{
 // creates the textfield for input
	private JTextField days = new JTextField("0", 6); 

public DaysSpent() 
{

// Sets the grid to have 5 rows and 1 column
	setLayout(new GridLayout(5, 1)); 
	
	// Makes title of Border
	setBorder(BorderFactory.createTitledBorder("Days Spent"));  
	
	// adds days to the panel
	add(days); 
}
public int getDays()	
{
	// returns days
	return Integer.parseInt(days.getText()); 
}
}	
	
	