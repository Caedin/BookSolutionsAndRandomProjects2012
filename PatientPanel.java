import javax.swing.*;
import java.awt.event.*;
import java.awt.*; //probably wont need this. check for later.

public class PatientPanel extends JPanel
{
	private JLabel messageLabel;
	private JLabel messageLabel2;
	private JTextField patientName;
	private JTextField patientDays;
	
	public PatientPanel()
	{
		setLayout(new GridLayout(4,1));
		messageLabel = new JLabel ("Patient name");
		patientName = new JTextField(10);
		messageLabel2 = new JLabel("Days in");
		patientDays = new JTextField(10);
		
		add(messageLabel);
		add(patientName);
		add(messageLabel2);
		add(patientDays);
	}
	public String getPatientInfo()
	{
		String input;
		input = patientName.getText();
		//if(input.euals("")==false)
		return input;	
	}
	public int getPatientDays()
	{
		int days;
		int stayFee;
		days = Integer.parseInt(patientDays.getText()); 
		stayFee = days*50;
		return stayFee;
	}
}