
package salazar.meyer;

// super class for the procedures

public abstract class Surgery
{
	protected String procedure;				// the name of the surgery
	protected double charge;					// cost of the procedure
	protected PatientAccount patient;		// the current patient object
	
	public Surgery()
	{
		charge = 0.0;
	}

	/** constructor receives the PatientAccount object, procedure's name and price from subclass */
	public Surgery(PatientAccount pat, String title, double ch)
	{
		procedure = title;
		charge = ch;
		patient = pat;
	}
	
	public void setTitle(String name)
	{
		procedure = name;
	}
	
	public void addToTotal()
	{
		patient.addToTotal(charge);
	}
	
	public double getSurgeryCharge()
	{
		return charge;
	}
	
	public String toString()
	{
		String str;
		str = procedure + " - cost: $ " + charge;
		return str;
	}
}