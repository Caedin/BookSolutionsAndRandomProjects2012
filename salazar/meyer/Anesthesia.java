package salazar.meyer;

public class Anesthesia extends Pharmacy
{
	/** constructor accepts the PatientAccount object
	    sends it to the super class and establishes the Medications price */		
	public Anesthesia(PatientAccount pat)
	{
		super(pat, "Anesthesia", 456.00);
	}

	/** calls the super class's addToTotal, which calls the addToTotal in the PatientAccount object */	
	public void addToTotal()
	{
		super.addToTotal();
	}
	
	public String toString()
	{
		return super.toString();
	}	

}