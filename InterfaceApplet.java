import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class InterfaceApplet extends JApplet {

	//All private members declared here.
	private JPanel panel;
	private JLabel messageLabelName;
	private JTextField textFieldName;
	private JLabel messageLabelBirthday;
	private JTextField textFieldBirthday;
	
	private JLabel messageLabelDays;
	private JTextField textFieldDays;
	
	private JCheckBox checkBoxSurgery1;
	private JCheckBox checkBoxSurgery2;
	private JCheckBox checkBoxSurgery3;
	private JCheckBox checkBoxSurgery4;
	private JCheckBox checkBoxSurgery5;
	
	private JCheckBox checkBoxMedicine1;
	private JCheckBox checkBoxMedicine2;
	private JCheckBox checkBoxMedicine3;
	private JCheckBox checkBoxMedicine4;
	private JCheckBox checkBoxMedicine5;	
		
	private JButton calcButton;
	PatientAccount patient;
	
	//This method puts the panel together, containing the various buttons and objects.
	private void buildPanel() {
		messageLabelName = new JLabel("Enter the Patient's Name");
		textFieldName = new JTextField(10);
		messageLabelBirthday = new JLabel("Enter Patient's Date Of Birth");
		textFieldBirthday = new JTextField(10);
		
		messageLabelDays = new JLabel("Enter Number Of Days In Hospital");
		textFieldDays = new JTextField(10);	
		
		checkBoxSurgery1 = new JCheckBox("Fix All Limbs - $50.0");
		checkBoxSurgery2 = new JCheckBox("Cure Radiation - $100.0");
		checkBoxSurgery3 = new JCheckBox("Speed Implant - $150.0");
		checkBoxSurgery4 = new JCheckBox("Dermal Regeneration - $200.0");
		checkBoxSurgery5 = new JCheckBox("Lobotomize - $250.0");
		
		checkBoxMedicine1 = new JCheckBox("Buffout - $20.0");
		checkBoxMedicine2 = new JCheckBox("Med X - $40.0");
		checkBoxMedicine3 = new JCheckBox("Psycho - $60.0");
		checkBoxMedicine4 = new JCheckBox("Turbo - $80.0");
		checkBoxMedicine5 = new JCheckBox("Mentats - $100.0");
		
		calcButton = new JButton("Calculate");
		calcButton.addActionListener(new CalcButtonListener());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(messageLabelName);
		panel.add(textFieldName);
		panel.add(messageLabelBirthday);
		panel.add(textFieldBirthday);
		
		panel.add(messageLabelDays);
		panel.add(textFieldDays);
		
		panel.add(checkBoxSurgery1);
		panel.add(checkBoxSurgery2);
		panel.add(checkBoxSurgery3);
		panel.add(checkBoxSurgery4);
		panel.add(checkBoxSurgery5);
		
		panel.add(checkBoxMedicine1);
		panel.add(checkBoxMedicine2);
		panel.add(checkBoxMedicine3);
		panel.add(checkBoxMedicine4);
		panel.add(checkBoxMedicine5);
		
		panel.add(calcButton);
	}
	
	//The init function provides the contrutor for an applet.  This one
	//calls the buildPanel function and adds the contructed panel to the window
	public void init() {
		patient = new PatientAccount();
		buildPanel();
		setLayout(new GridLayout(0,1));
		add(panel);
	}
	
	/* 
		This is the callback method that is triggered when the calculate button is pressed.
		It uses the methods contained in PatientAccount, Surgery, and Pharmacy to calculate
		the final cost, then creates a text box using the checkOut method contained in 
		PatientAccount.
	*/
	
	private class CalcButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			patient.setName(textFieldName.getText());
			patient.setDateofBirth(textFieldBirthday.getText());
			patient.setDays(Integer.parseInt(textFieldDays.getText()));
			patient.setCharges(0.0);
			
			if(checkBoxSurgery1.isSelected()) Surgery.addSurgery(patient, 1);
			if(checkBoxSurgery2.isSelected()) Surgery.addSurgery(patient, 2);
			if(checkBoxSurgery3.isSelected()) Surgery.addSurgery(patient, 3);
			if(checkBoxSurgery4.isSelected()) Surgery.addSurgery(patient, 4);
			if(checkBoxSurgery5.isSelected()) Surgery.addSurgery(patient, 5);
			
			if(checkBoxMedicine1.isSelected()) Pharmacy.addDrug(patient, 1);
			if(checkBoxMedicine2.isSelected()) Pharmacy.addDrug(patient, 2);
			if(checkBoxMedicine3.isSelected()) Pharmacy.addDrug(patient, 3);
			if(checkBoxMedicine4.isSelected()) Pharmacy.addDrug(patient, 4);
			if(checkBoxMedicine5.isSelected()) Pharmacy.addDrug(patient, 5);
			
			JOptionPane.showMessageDialog(null, patient.checkOut());
		}	
	}
}
