package salazar.meyer;


import javax.swing.*;

/** The GreetingPanel displays a message in a panel */

public class GreetingPanel extends JPanel
{
	private JLabel greeting;
	
	/** constructor */
	public GreetingPanel()
	{
		// create the message
		greeting = new JLabel("Your Family Clinic: Something for everyone");
		
		// add the label to this panel
		add(greeting);
	}
}