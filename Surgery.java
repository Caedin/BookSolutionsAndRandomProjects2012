public class Surgery {
	
	/* For Pharmacy and Surgery, we decided to use a static method 
		that could be called without needing to create a Pharmacy 
		or Surgery object.  This is to save space on code, maintain 
		clarity, and reduce resources used.  Both classes contain only
		one method that accepts a PatientAccount and a choice integer
		as arguments.  The methods then use the addCharge method in 
		the PatientAccount to add a charge based on the choice passed
		through the choice integer.  
	*/
		
	public static void addSurgery(PatientAccount p, int c) {
		if (c == 1) p.addCharge(50);
		else if (c == 2) p.addCharge(100);
		else if (c == 3) p.addCharge(150);
		else if (c == 4) p.addCharge(200);
		else if (c == 5) p.addCharge(250);
		else System.out.println("That is not a valid choice.");
	}
}