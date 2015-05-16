import java.util.Scanner;

public class RunSystemText {
	public static void main(String[] args) {
	
		PatientAccount patient = new PatientAccount();
		Scanner kb = new Scanner(System.in);
		
		//Asks user for initial patient info.
		System.out.println("What is the patient's name?");
		String name = kb.next();
		System.out.println("What is the patient's date of birth?");
		String dob = kb.next();
		System.out.println("For how many days was the patient in the hospital?");
		int numdays = kb.nextInt();
		patient.setName(name);
		patient.setDateofBirth(dob);
		patient.setDays(numdays);
		
		//This loop continues to ask for user input until a 4 is entered and closed is changed from 0 to 1.
		int closed = 0;
		while (closed == 0) closed = DisplayMenu(patient);
	}
	
	//The menu is called in three methods.  The first one asks the user to make a choice in what they want to enter or see.
	public static int DisplayMenu(PatientAccount p) {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Enter 1 to add a surgery.");
		System.out.println("Enter 2 to add a pharmaceudical.");
		System.out.println("Enter 3 to display the final charges.");
		System.out.println("Enter 4 to exit.\n");
		
		int choice = kb.nextInt();
		
		if (choice == 1) SurgMenu(p);
		else if (choice == 2) PharmMenu(p);
		else if (choice == 3) System.out.println(p.checkOut() + "\n");
		else if (choice == 4) return 1;
		else System.out.println("That is not a valid choice.\n");
		
		return 0;
	}
	
	//If the user asks to enter a surgery, this menu is called and adds whatever surgery the user wants.
	public static void SurgMenu(PatientAccount p) {
		Scanner kb = new Scanner(System.in);
		System.out.println("There are five surgeries available:");
		System.out.println("1.  Fix All Limbs, $50");
		System.out.println("2.  Cure Radiation, $100");
		System.out.println("3.  Speed Implant, $150");
		System.out.println("4.  Dermal Regeneration, $200");
		System.out.println("5.  Lobotomize, $250\n");
		System.out.println("Please enter the number of the surgery you wish to add.\nEnter 0 to exit without picking a surgery.\n");
		int surgchoice = kb.nextInt();
		if (surgchoice == 0);
		else if (surgchoice > 0 && surgchoice < 6) Surgery.addSurgery(p, surgchoice);
		else System.out.println("That is not a valid choice.\n");
	}
	
	//If the user wishes to enter a drug, this menu is called.
	public static void PharmMenu(PatientAccount p) {
		Scanner kb = new Scanner(System.in);
		System.out.println("There are five pharmaceudicals available:");
		System.out.println("1.  Buffout, $50");
		System.out.println("2.  Med X, $100");
		System.out.println("3.  Psycho, $150");
		System.out.println("4.  Turbo, $200");
		System.out.println("5.  Mentats, $250\n");
		System.out.println("Please enter the number of the pharmaceudical you wish to add.\nEnter 0 to exit without picking a pharmaceudical.\n");
		int pharmchoice = kb.nextInt();
		if (pharmchoice == 0);
		else if (pharmchoice > 0 && pharmchoice < 6) Pharmacy.addDrug(p, pharmchoice);
		else System.out.println("That is not a valid choice.\n");
	}
}