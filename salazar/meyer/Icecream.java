
package salazar.meyer;

public class Icecream extends Pharmacy
{
	/** constructor accepts the PatientAccount object
	    sends it to the super class and establishes the Medications price */		
	public Icecream(PatientAccount pat)
	{
		super(pat, "Icecream", 14.50);
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