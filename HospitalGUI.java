import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class HospitalGUI extends JFrame
{
	private SurgeryPanel surgery;
	private PharmacyPanel pharmacy;
	private PatientPanel patient;
	private HospitalBanner banner;
	private JPanel buttonPanel;
	private JButton calcButton;
	private JButton exitButton;
	
	public HospitalGUI()
	{
		setTitle("Fee Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		banner = new HospitalBanner();
		surgery = new SurgeryPanel();
		patient = new PatientPanel();
		pharmacy = new PharmacyPanel();
		buildButtonPanel();
		add(banner, BorderLayout.NORTH);
		add(surgery, BorderLayout.WEST);
		add(pharmacy, BorderLayout.CENTER);
		add(patient, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}	
	private void buildButtonPanel()
	{
		buttonPanel = new JPanel();
		calcButton = new JButton("Check out");
		exitButton = new JButton("Exit");
		calcButton.addActionListener(new CalcButtonListener());
		exitButton.addActionListener(new ExitButtonListener());
		buttonPanel.add(calcButton);
		buttonPanel.add(exitButton);
	}
	private class CalcButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			double total;
			total = surgery.getSurgeryCost()+
					  pharmacy.getPharmacyCost()+
					  patient.getPatientDays();
		//	DecimalFormat dollar = new DecimalFormat("0.00");
				JOptionPane.showMessageDialog(null,"Patient: "+patient.getPatientInfo()+"\n"+"Total: $"+total);				  
		}
	}
	private class ExitButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	public static void main(String[] args)
	{
		new HospitalGUI();
	}

}