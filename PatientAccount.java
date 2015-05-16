import java.util.Calendar;
public class PatientAccount {
	
	//All Private Members
	private String name;
	private String dateofBirth;
	private double charges;
	private int daysin;
	private final double rate = 50.00;
	
	//Constructors
	PatientAccount(){
		name = "";
		dateofBirth = "";
		charges = 0.0;
		daysin = 0;
	}
	
	PatientAccount(String n, String d){
		name = n;
		dateofBirth = d;
		charges = 0.0;
		daysin = 0;
	}
	
	//Methods used to manipulate and return charges.
	public void addCharge(double amount) {
		charges += amount;
	}
	
	public void updateCharges() {
		charges = (charges + (daysin * rate));
	}
	
	public String checkOut() {
		updateCharges();
		return ("The patient was in the hospital for: " + daysin + " days.\nThe patient will be billed: $" + charges);
	}
	
	//All accessor and mutator methods.
	public double getCharges(){
		return charges;
	}
	
	public void setCharges(double c){
		charges = c;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDateofBirth(String d){
		dateofBirth = d;
	}
	
	public String getDateofBirth(){
		return dateofBirth;
	}
	
	public int getDays(){
		return daysin;
	}
	
	public void setDays(int d){
		daysin = d;
	}
}