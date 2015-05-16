//This class extends the JPanel by adding a banner.
import javax.swing.*;// Imports swing class.

public class HospitalBanner extends JPanel
{
	private JLabel banner;
	
	public HospitalBanner()// Puts in banner into the JPanel.
	{
		banner = new JLabel("Welcome to the Pokemon Hospital!");
		
		add(banner);
	}
}