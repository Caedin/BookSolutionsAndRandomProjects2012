package salazar.meyer;


public class PatientAccount
{
	private String name;							// to hold patient's name
	private double charges;						// to hold the total charge for the patient: days * rate
	private int days;								// the amount of days the patient stays at the hospital
	private double rate;							// the daily charge for the hospital 
	private Surgery procedure = null;		// to hold the applied surgery
	private Pharmacy medication = null;		// to hold the applied medication
	private double SurgeryPrice;				// to hold the price for the selcted procedure
	
	// no args constructor
	public PatientAccount()
	{
		name = "";
		days = 0;
		rate = 0;
	}
	
	// constructor with name, days, and daily rate
	public PatientAccount(String n, int d, double r)
	{
		name = n;
		days = d;
		rate = r;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setRate(double r)
	{
		rate = r;
	}
	
	public void setDays(int d)
	{
		days = d;
	}		
	
	public void setProcedure(Surgery proc)
	{
		procedure = proc;			
	}
	
	public void setMedication(Pharmacy med)
	{
		medication = med;			
	}
	
	public void setSurgeryPrice(double su)
	{
		SurgeryPrice = su;
	}
	
	public void addToTotal(double x)
	{
		charges += x;
	}	
	
	public String getName()
	{
		return name;
	}
	
	public Integer getDays()
	{
		return days;
	}
	
	public Surgery getProcedure()
	{
		return procedure;
	}
	
	public double getSurgeryCharge()
	{
		return SurgeryPrice;
	}
	
	public Double getRate()
	{
		return rate;
	}
	
	public double getTotal()
	{
		return days * rate + charges;
	}
	
	public String toString()
	{
		String str;
		str = "Patient: " + name + "\n------------\n" + days + " days charged for $ " + rate + " each\n\nSurgery: " + procedure + "\n\nPharmacy: " + medication;
		return str;
	}
}