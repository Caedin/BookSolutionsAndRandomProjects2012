import javax.swing.*;
import java.awt.*;

public class SurgeryPanel extends JPanel
{
	public final double SURGERY1 = 1.25;
	public final double SURGERY2 = 2.50;
	public final double SURGERY3 = 3.25;
	public final double SURGERY4 = 4.50;
	public final double SURGERY5 = 5.25;
	
	private JRadioButton surgery1;
	private JRadioButton surgery2;
	private JRadioButton surgery3;
	private JRadioButton surgery4;
	private JRadioButton surgery5;
	private ButtonGroup bg;
	
	public SurgeryPanel()
	{
		setLayout(new GridLayout(5,1));
		surgery1 = new JRadioButton("Charizard eats Eric!", true);
		surgery2 = new JRadioButton("Snorlax uses Body Slam on Eric!");
		surgery3 = new JRadioButton("Mewtwo uses Psychic on Eric!");
		surgery4 = new JRadioButton("Venusaur uses Razor Leaf on Eric!");
		surgery5 = new JRadioButton("Pikachu uses Thunder on Eric!");
		bg = new ButtonGroup();
		bg.add(surgery1);
		bg.add(surgery2);
		bg.add(surgery3);
		bg.add(surgery4);
		bg.add(surgery5);
		setBorder(BorderFactory.createTitledBorder("Surgery"));
		add(surgery1);
		add(surgery2);
		add(surgery3);
		add(surgery4);
		add(surgery5);
	}
	public double getSurgeryCost()
	{
		double surgeryCost = 0.0;
		
		if (surgery1.isSelected())
			surgeryCost = SURGERY1;
		if (surgery2.isSelected())
			surgeryCost = SURGERY2;
		if (surgery3.isSelected())
			surgeryCost = SURGERY3;
		if (surgery4.isSelected())
			surgeryCost = SURGERY4;
		if (surgery5.isSelected())
			surgeryCost = SURGERY5;		
			
		return surgeryCost;
	}
}