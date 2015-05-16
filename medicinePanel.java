// imports classsees needed for medicenePanel
import javax.swing.*; 
import java.awt.*;

// This class allows the user to select medicine

public class medicinePanel extends JPanel
{
	// Sets the price of the medicines
   public final double MEDICINE_1 = 150.0;
   public final double MEDICINE_2 = 200.0;
   public final double MEDICINE_3 = 375.0;    
   public final double MEDICINE_4 = 400.0;
	public final double MEDICINE_5 = 250.0;
	
	// Sets a checkbox for the medicines 
	private JCheckBox medicine1;  
   private JCheckBox medicine2;      
   private JCheckBox medicine3;              
   private JCheckBox medicine4;  
	private JCheckBox medicine5;
	// sets button group
	private ButtonGroup med;                   
	
public medicinePanel()
{
	// sets layout to have 5 rows and 1 column
	setLayout(new GridLayout(5,1)); 
	
		// puts names of medicene in the checkbox 	
	   medicine1 = new JCheckBox("Thulaca      ");
      medicine2 = new JCheckBox("Ransien      ");
      medicine3 = new JCheckBox("Calsine      ");		   		
      medicine4 = new JCheckBox("Cthulu       ");
		medicine5 = new JCheckBox("Razltaf      ");

		// Sets border name 
      setBorder(BorderFactory.createTitledBorder("Select Medicine")); 
		
		// adds checkbox to the panel
      add(medicine1);
      add(medicine2);
      add(medicine3);       
      add(medicine4);
		add(medicine5);
		
	}


   public double getMedicineCost()
   {
      double medicineCost = 0.0;             //  Intialize medicenecost set to 0 

      if (medicine1.isSelected())
         medicineCost += MEDICINE_1;
      if (medicine2.isSelected())
         medicineCost += MEDICINE_2;
      if (medicine3.isSelected())                 // Sets if statement if a medicine is chosen
         medicineCost += MEDICINE_3;
      if (medicine4.isSelected())
         medicineCost += MEDICINE_4;

      return medicineCost;                   // returns cost of the medicene
   }
}