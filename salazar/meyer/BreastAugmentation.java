package salazar.meyer;
public class BreastAugmentation extends Surgery
{
	final double CHARGE = 999.00;			// holds the procedure's price

	/** constructor accepts the PatientAccount object
	    sends it to the super class together with the Procedure's price */		
	public BreastAugmentation(PatientAccount pat)
	{
		super(pat, "Breast Augmentation", 999.00);			// call the super constructor with the title and fix rate for this type of surgery
	}	

	/** calls the super class's addToTotal, which calls the addToTotal in the PatientAccount object */	
	public void addToTotal()
	{
		super.addToTotal();
	}
	
	public double getSurgeryCharge()
	{
		return CHARGE;
	}
	
	public String toString()
	{
		return super.toString();
	}	 
}	