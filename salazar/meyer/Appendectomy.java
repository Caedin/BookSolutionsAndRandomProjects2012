package salazar.meyer;
public class Appendectomy extends Surgery
{
	final double CHARGE = 6450.00;		// holds th eprocedure's price

	/** constructor accepts the PatientAccount object
	    sends it to the super class together with the Procedure's price */			
	public Appendectomy(PatientAccount pat)
	{
		super(pat, "Appendectomy", 6450.00);			// call the super constructor with the title and fix rate for this type of surgery
		final double CHARGE = 6450.00;
	}	

	/** calls the super class's addToTotal, which calls the addToTotal in the PatientAccount object */	
	public void addToTotal()
	{
		super.addToTotal();
	}
	
	public double getSurgeryCharge()
	{
		return super.getSurgeryCharge();
	}
	
	public String toString()
	{
		return super.toString();
	}	 
}	