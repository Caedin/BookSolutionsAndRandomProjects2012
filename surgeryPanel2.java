//Chris Stupka and Christian Reyes project
// imports classes needed for surgeryPanel
import javax.swing.*;
import java.awt.*;

public class surgeryPanel2 extends JPanel
{
 	// Sets constant price for each surgery :) 
	public final double SURGERY_1 = 700.0;
   public final double SURGERY_2 = 1000.0;
	public final double SURGERY_3 = 700.0;
	public final double SURGERY_4 = 800.0;
	public final double SURGERY_5	= 900.0;
											
	//declare radio buttons and group 
   private JRadioButton surgery1;  
   private JRadioButton surgery2;
	private JRadioButton surgery3;
	private JRadioButton surgery4;
	private JRadioButton surgery5; 
   private ButtonGroup bg;          
	
	public surgeryPanel2()
   {
		// sets the layout to have 5 rows and 1 column
      setLayout(new GridLayout(5, 1));
		
		// Create radio buttons 
      surgery1 = new JRadioButton("Laparoscopic");
      surgery2 = new JRadioButton("Organ Transplant");
		surgery3 = new JRadioButton("Keyhole");
		surgery4 = new JRadioButton("Micro Surgery");
		surgery5 = new JRadioButton("Emergency Surgery");
		
		// Group Radio Buttons
      bg = new ButtonGroup();
      bg.add(surgery1);
      bg.add(surgery2);
		bg.add(surgery3);
		bg.add(surgery4);
		bg.add(surgery5);
		
		// add a border around the panel
      setBorder(BorderFactory.createTitledBorder("Surgery"));
		
		// add the radio buttons to the panel.
      add(surgery1);
      add(surgery2);
		add(surgery3);
		add(surgery4);
		add(surgery5);
   }

   public double getSurgeryCost()
   {
      double surgeryCost = 0.0;
		
		// Sets surgery cost according to the user input.
      if (surgery1.isSelected())
         surgeryCost = SURGERY_1;
      if (surgery2.isSelected())
         surgeryCost = SURGERY_2;
		if (surgery3.isSelected())
			surgeryCost = SURGERY_3;
		if (surgery4.isSelected())
			surgeryCost = SURGERY_4;
		if (surgery5.isSelected()) 
			surgeryCost = SURGERY_5;

      return surgeryCost;
   }
}