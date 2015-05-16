package salazar.meyer;


// super class for the medication types

public abstract class Pharmacy
{
	protected String medication;				// the name of the medication
	protected double medCharge;			   // cost of the medication
	protected PatientAccount patient;		// the current patient object
	
	public Pharmacy()
	{
		medCharge = 0.0;
	}

	/** constructor receives the PatientAccount object, medication's name and price from subclass */	
	public Pharmacy(PatientAccount pat, String title, double charge)
	{
		medication = title;
		medCharge = charge;
		patient = pat;
	}
	
	public void setTitle(String name)
	{
		medication = name;
	}
	
	public void addToTotal()
	{
		patient.addToTotal(medCharge);
	}
	
	public String toString()
	{
		String str;
		str = medication + "\ncost: $ " + medCharge;
		return str;
	}	
}