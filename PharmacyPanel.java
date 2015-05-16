import javax.swing.*;
import java.awt.*;

public class PharmacyPanel extends JPanel
{
	public final double MEDS1 = 50.50;
	public final double MEDS2 = 55.25;
	public final double MEDS3 = 60.75;
	public final double MEDS4 = 65.75;
	public final double MEDS5 = 70.75;
	
	private JCheckBox meds1;
	private JCheckBox meds2;
	private JCheckBox meds3;
	private JCheckBox meds4;
	private JCheckBox meds5;
	
	public PharmacyPanel()
	{
		setLayout(new GridLayout(5,1));
		meds1 = new JCheckBox("Potion!");
		meds2 = new JCheckBox("Super Potion!");
		meds3 = new JCheckBox("Hyper Potion!");
		meds4 = new JCheckBox("Max Potion!");
		meds5 = new JCheckBox("Revive!");
		
		setBorder(BorderFactory.createTitledBorder("Pharmacy"));
		
		add(meds1);
		add(meds2);
		add(meds3);
		add(meds4);
		add(meds5);
	}
	public double getPharmacyCost()
	{
		double pharmacyCost = 0.0;
		
		if (meds1.isSelected())
			pharmacyCost +=MEDS1;
		if (meds2.isSelected())
			pharmacyCost +=MEDS2;
		if (meds3.isSelected())
			pharmacyCost +=MEDS3;
		if (meds4.isSelected())
			pharmacyCost +=MEDS4;
		if (meds5.isSelected())
			pharmacyCost +=MEDS5;
			
		return pharmacyCost;
	}

}