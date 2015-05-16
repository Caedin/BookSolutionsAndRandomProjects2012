
package salazar.meyer;


public class PlasticSurgeryPackage extends Pharmacy
{
	/** constructor accepts the PatientAccount object
	    sends it to the super class and establishes the Medications price */		
	public PlasticSurgeryPackage(PatientAccount pat)
	{
		super(pat, "Plastic Surgery Package", 725.00);
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